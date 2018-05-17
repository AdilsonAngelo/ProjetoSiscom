function generateArray(size, fill) {
    if (!fill || isNaN(fill)) fill = 0;

    return Array.apply(null, Array(size)).map(Number.prototype.valueOf, fill);
}

function randomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}