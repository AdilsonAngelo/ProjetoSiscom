var Simulator = function (estimator, numTags, step, maxTags, iterations, initialFrameSize) {
    let inicio = new Date().getTime();
    let estimates = [];

    for (; numTags <= maxTags; numTags += step) {
        let totalEmpty = 0,
            totalSuccess = 0,
            totalCollision = 0,
            totalTime = 0;

        for (let i = 0; i < iterations; i++) {
            let begining = new Date().getTime();
            let success = 0,
                empty = 0,
                collision = 0,
                tagsRemaining = numTags,
                frameSize = initialFrameSize;

            while (tagsRemaining > 0) {
                let frame = Array.apply(null, Array(frameSize)).map(Number.prototype.valueOf, 0);

                for (let j = 0; j < tagsRemaining; j++)
                    frame[randomInt(0, frameSize - 1)]++;

                for (let j = 0; j < tagsRemaining; j++)
                    if (frame[j] == 1)
                        success++;
                    else if (frame[j] > 1)
                        collision++;
                    else if (frame[j] < 1)
                        empty++;

                tagsRemaining -= success;

                frameSize = estimator(success, collision, empty);

                totalSuccess += success;
                totalCollision += collision;
                totalEmpty += empty;
            }

            totalTime += new Date().getTime() - begining;
        }

        let avgSuccess = totalSuccess / iterations,
            avgEmpty = totalEmpty / iterations,
            avgCollision = totalCollision / iterations,
            avgTime = totalTime / iterations,
            avgTotal = avgSuccess + avgCollision + avgEmpty,
            efficiency = 100 * (avgSuccess / avgTotal);

        estimates.push({ total: avgTotal, efficiency: efficiency, success: avgSuccess, collision: avgCollision, empty: avgEmpty, time: avgTime });
    }
    let result = { estimator: estimator.name, estimates: estimates };
    console.log("Duracao " + estimator.name + ": ", (new Date().getTime() - inicio) + " ms");
    console.log(result);
    return result;
};