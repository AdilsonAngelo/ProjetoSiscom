var SimulatorQ = function (numTags, step, maxTags, iterations) {
    let inicio = new Date().getTime();
    let estimates = [];

    let qfp = 4,
        q = Math.round(qfp),
        c = 0.1;

    for (; numTags <= maxTags; numTags += step) {
        let totalEmpty = 0,
            totalSuccess = 0,
            totalCollision = 0,
            totalTime = 0;

        for (let i = 0; i < iterations; i++) {
            let begining = new Date().getTime();
            let tagsRemaining = numTags;

            let tagsSN = generateArray(numTags, 0);

            query(q, tagsSN);
            let zeros = checkZeros(tagsSN);

            while(tagsRemaining > 0){
                if(zeros == 0){
                    let qs = queryAdj(tagsSN, false, qfp, q, c);
                    q = qs.q;
                    qfp = qs.qfp;

                    totalEmpty++;
                } else if(zeros == 1) {
                    tagsRemaining--;
                    tagsSN = generateArray(tagsRemaining, 0);

                    totalSuccess++;
                } else {
                    let qs = queryAdj(tagsSN, true, qfp, q, c);
                    q = qs.q;
                    qfp = qs.qfp;

                    totalCollision++;
                }
                zeros = checkZeros(tagsSN);
            }
            totalTime += new Date().getTime() - begining;
        }
        
        let avgSuccess = totalSuccess / iterations,
        avgEmpty = totalEmpty / iterations,
        avgCollision = totalCollision / iterations,
        avgTime = totalTime / iterations,
        avgTotal = avgSuccess + avgCollision + avgEmpty,
        efficiency = 100 * (avgSuccess / avgTotal);
        
        estimates.push({total: avgTotal, efficiency: efficiency, success: avgSuccess, collision: avgCollision, empty: avgEmpty, time: avgTime });
    }
    let result = { estimator: "Q", estimates: estimates };
    console.log("Duracao Q: ", (new Date().getTime() - inicio) + " ms");
    console.log(result);
    return result;
}

function checkZeros(arr) {
    let zeros = 0;
    for (let x of arr) {
        if (x == 0) zeros++;
        if (zeros > 1) break;
    }
    return zeros;
}

function query(q, arr) {
    for (let i = 0; i < arr.length; i++) {
        arr[i] = randomInt(0, Math.pow(2, q) - 1);
    }
}

function queryAdj(arr, collision, qfp, q, c) {
    if (collision) {
        qfp = Math.min(15, qfp + c);
        q = Math.round(qfp);
    } else {
        qfp = Math.max(0, qfp - c);
        q = Math.round(qfp);
    }

    query(q, arr);
    return { q: q, qfp: qfp };
}

function queryRep(arr) {
    for (let i = 0; i < arr.length; i++) {
        arr[i]--;
    }
}