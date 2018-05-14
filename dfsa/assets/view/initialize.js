var margin = { top: 5, bottom: 20, left: 20, right: 25 };
var height = 500 - margin.top - margin.bottom;
var width = 1200 - margin.left - margin.right;

var svgTotal = d3.select("#total")
    .attrs({
        height: height + margin.top + margin.bottom,
        width: width + margin.left + margin.right
    })
    .append("g")
    .attrs({
        transform: "translate(" + margin.left + ", " + margin.top + ")"
    });
var svgEmpty = d3.select("#empty")
    .attrs({
        height: height + margin.top + margin.bottom,
        width: width + margin.left + margin.right
    })
    .append("g")
    .attrs({
        transform: "translate(" + margin.left + ", " + margin.top + ")"
    });
var svgCollision = d3.select("#collision")
    .attrs({
        height: height + margin.top + margin.bottom,
        width: width + margin.left + margin.right
    })
    .append("g")
    .attrs({
        transform: "translate(" + margin.left + ", " + margin.top + ")"
    });
var svgEfficiency = d3.select("#efficiency")
    .attrs({
        height: height + margin.top + margin.bottom,
        width: width + margin.left + margin.right
    })
    .append("g")
    .attrs({
        transform: "translate(" + margin.left + ", " + margin.top + ")"
    });
var svgTime = d3.select("#time")
    .attrs({
        height: height + margin.top + margin.bottom,
        width: width + margin.left + margin.right
    })
    .append("g")
    .attrs({
        transform: "translate(" + margin.left + ", " + margin.top + ")"
    });

var totalDataset = (function () {
    var json = null;
    $.ajax({
        async: false,
        global: false,
        url: "./total.json",
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
var emptyDataset = (function () {
    var json = null;
    $.ajax({
        'async': false,
        'global': false,
        'url': "./empty.json",
        'dataType': "json",
        'success': function (data) {
            json = data;
        },
        error: function (err) {
            console.error(err);
        }
    });
    return json;
})();
var collisionDataset = (function () {
    var json = null;
    $.ajax({
        'async': false,
        'global': false,
        'url': "./collision.json",
        'dataType': "json",
        'success': function (data) {
            json = data;
        },
        error: function (err) {
            console.error(err);
        }
    });
    return json;
})();
var effDataset = (function () {
    var json = null;
    $.ajax({
        'async': false,
        'global': false,
        'url': "./eff.json",
        'dataType': "json",
        'success': function (data) {
            json = data;
        },
        error: function (err) {
            console.error(err);
        }
    });
    return json;
})();
var timeDataset = (function () {
    var json = null;
    $.ajax({
        'async': false,
        'global': false,
        'url': "./time.json",
        'dataType': "json",
        'success': function (data) {
            json = data;
        },
        error: function (err) {
            console.error(err);
        }
    });
    return json;
})();
