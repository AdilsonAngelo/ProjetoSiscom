$("#btn-submit").click(function () {
    $(this).attr("disabled", "true");

    let numTags = parseInt($("#numTags").val());
    let maxTags = parseInt($("#maxTags").val());
    let step = parseInt($("#step").val());
    let iterations = parseInt($("#iterations").val());
    let initialFrameSize = parseInt($("#initialFrameSize").val());
    let lb = $("#lb").is(":checked");
    let el = $("#el").is(":checked");
    let q = $("#q").is(":checked");

    if ((!lb && !el && !q) || isNaN(numTags) || isNaN(maxTags) || isNaN(step) || isNaN(iterations) || isNaN(initialFrameSize)
        || numTags < 1 || maxTags < 1 || step < 1 || iterations < 1 || initialFrameSize < 1) {
        $("#alert").fadeIn();
        $("#loading").fadeOut(1000);
        $("#btn-submit").removeAttr("disabled");
        return;
    } else $("#alert").fadeOut();

    $.ajax({
        url: "/submit-filter",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
            numTags: numTags,
            maxTags: maxTags,
            step: step,
            iterations: iterations,
            initialFrameSize: initialFrameSize,
            lb: lb,
            el: el,
            q: q
        }),
        method: "POST",
        async: false,
        beforeSend: function () {
            $("#loading").show();
        },
        success: function (data) {
            console.log(data);
            // if (q) data.push(SimulatorQ(numTags, step, maxTags, iterations));
            generateCharts(data);
            $("#loading").fadeOut(1000);
            $("#btn-submit").removeAttr("disabled");
        },
        error: function (err) {
            console.error(err);
            $("#loading").fadeOut(1000);
            $("#btn-submit").removeAttr("disabled");
        }
    });
});

function generateCharts(datasets) {
    let totalCtx = document.getElementById("total").getContext("2d");
    let emptyCtx = document.getElementById("empty").getContext("2d");
    let collisionCtx = document.getElementById("collision").getContext("2d");
    let effCtx = document.getElementById("efficiency").getContext("2d");
    let timeCtx = document.getElementById("time").getContext("2d");

    let numTags = parseInt($("#numTags").val());
    let maxTags = parseInt($("#maxTags").val());
    let step = parseInt($("#step").val());
    let iterations = parseInt($("#iterations").val());
    let initialFrameSize = parseInt($("#initialFrameSize").val());

    let dataTotal = [];
    let dataEmpty = [];
    let dataCollision = [];
    let dataEff = [];
    let dataTime = [];

    let xLabels = [];

    for (let i = numTags; i <= maxTags; i += step) xLabels.push(i);

    for (let ds of datasets) {
        let cor = getRandomColor();

        let total = [];
        let empty = [];
        let collision = [];
        let eff = [];
        let time = [];

        for (let data of ds.estimates) {
            total.push(data.total);
            empty.push(data.empty);
            collision.push(data.collision);
            eff.push(data.efficiency);
            time.push(data.time);
        }

        dataTotal.push({
            label: ds.estimator,
            data: total,
            fill: false,
            backgroundColor: cor,
            borderColor: cor
        });
        dataEmpty.push({
            label: ds.estimator,
            data: empty,
            fill: false,
            backgroundColor: cor,
            borderColor: cor
        });
        dataCollision.push({
            label: ds.estimator,
            data: collision,
            fill: false,
            backgroundColor: cor,
            borderColor: cor
        });
        dataEff.push({
            label: ds.estimator,
            data: eff,
            fill: false,
            backgroundColor: cor,
            borderColor: cor
        });
        dataTime.push({
            label: ds.estimator,
            data: time,
            fill: false,
            backgroundColor: cor,
            borderColor: cor
        });
    }

    new Chart(totalCtx, createConfig(xLabels, "Total Slots", dataTotal, "Tags", "Total Slots"));
    new Chart(emptyCtx, createConfig(xLabels, "Empty Slots", dataEmpty, "Tags", "Empty Slots"));
    new Chart(collisionCtx, createConfig(xLabels, "Collision Slots", dataCollision, "Tags", "Collision Slots"));
    new Chart(effCtx, createConfig(xLabels, "Efficiency (%)", dataEff, "Tags", "Efficiency (%)"));
    new Chart(timeCtx, createConfig(xLabels, "Time (ms)", dataTime, "Tags", "Time (ms)"));
};

function getRandomColor() {
    let letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}
function createConfig(xLabels, title, data, xTitle, yTitle) {
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