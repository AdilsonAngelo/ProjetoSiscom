var EomLee = function (success, collision, empty) {
    let gama = EomLeeGamaIteration(success, collision, empty);

    return Math.ceil(gama * collision);
};

function EomLeeGamaIteration(success, collision, empty) {
    let beta, currentGama, previousGama;

    currentGama = 2;
    previousGama = Number.MAX_VALUE;
    beta = Number.MAX_VALUE;

    while (Math.abs(previousGama - currentGama) >= 0.001) {
        previousGama = currentGama;

        beta = EomLeeBeta(success, collision, empty, previousGama);
        currentGama = EomLeeGama(beta);
    }

    return currentGama;
};

function EomLeeBeta(success, collision, empty, previousGama) {
    let frameSize = success + collision + empty;
    return frameSize / (previousGama * collision + success);
};

function EomLeeGama(beta) {
    return (1 - Math.pow(Math.E, -(1 / beta))) / (beta * (1 - (1 + 1 / beta) * Math.pow(Math.E, -(1 / beta))));
};