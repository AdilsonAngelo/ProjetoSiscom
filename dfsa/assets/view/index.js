var $ = jQuery.noConflict();

var dataset = (function () {
    var json = null;
    $.ajax({
        async: false,
        global: false,
        url: "./data.json",
        dataType: "json",
        success: function (data) {
            json = data;
        },
        error: function (err) {
            console.error(err);
        }
    });
    return json;
})();

console.log(dataset);

var totalCtx = document.getElementById("total").getContext("2d");
var emptyCtx = document.getElementById("empty").getContext("2d");
var collisionCtx = document.getElementById("collision").getContext("2d");
var effCtx = document.getElementById("efficiency").getContext("2d");
var timeCtx = document.getElementById("time").getContext("2d");

var dataTotal = [];
var dataEmpty = [];
var dataCollision = [];
var dataEff = [];
var dataTime = [];

var xLabels = dataset[0].xAxis;

for (let ds of dataset) {
    let cor = getRandomColor();

    dataTotal.push({
        label: ds.title,
        data: ds.total,
        fill: false,
        backgroundColor: cor,
        borderColor: cor
    });
    dataEmpty.push({
        label: ds.title,
        data: ds.empty,
        fill: false,
        backgroundColor: cor,
        borderColor: cor
    });
    dataCollision.push({
        label: ds.title,
        data: ds.collision,
        fill: false,
        backgroundColor: cor,
        borderColor: cor
    });
    dataEff.push({
        label: ds.title,
        data: ds.efficiency,
        fill: false,
        backgroundColor: cor,
        borderColor: cor
    });
    dataTime.push({
        label: ds.title,
        data: ds.time,
        fill: false,
        backgroundColor: cor,
        borderColor: cor
    });
}

new Chart(totalCtx, createConfig("Total Slots", dataTotal, "Tags", "Total Slots"));
new Chart(emptyCtx, createConfig("Empty Slots", dataEmpty, "Tags", "Empty Slots"));
new Chart(collisionCtx, createConfig("Collision Slots", dataCollision, "Tags", "Collision Slots"));
new Chart(effCtx, createConfig("Efficiency (%)", dataEff, "Tags", "Efficiency (%)"));
new Chart(timeCtx, createConfig("Time (ms)", dataTime, "Tags", "Time (ms)"));

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function createConfig(title, data, xTitle, yTitle) {
    return {
        type: "line",
        data: {
            labels: xLabels,
            datasets: data,
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: title
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: xTitle
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: yTitle
                    }
                }]
            }
        }
    };
}