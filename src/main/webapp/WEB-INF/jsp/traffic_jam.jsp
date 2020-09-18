<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title></title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://www.gstatic.com/charts/loader.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
</head>
<body>
<style>
    body, html {
        margin: 0;
        padding: 0;
        height: 100%;
    }

    body {
        font-family: sans-serif;
    }

    body * {
        font-weight: 200;
    }

    h1 {
        position: absolute;
        background: white;
        padding: 10px;
    }

    #map {
        height: 100%;
    }

    .leaflet-container {
        background: rgba(0, 0, 0, .8) !important;
    }

    #speedGauge {
        position: fixed;
        right: 15px;
        top: 65%;
        display: block;
        z-index: 99999999;
    }

    svg:first-child > g > text[text-anchor~=middle] {
        font-size: 13px;
        font-weight: 500;
    }
</style>
<link rel="stylesheet" href="css/prism.css">
<link rel="stylesheet" href="css/leaflet.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.6.0/leaflet.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="/js/heatmap.min.js"></script>
<script src="/js/leaflet-heatmap.js"></script>
<jsp:include page="includes/header.jsp"/>
<div>
    <form id="timeForm" method="post"><label for="start_time">Початкова дата:</label>
        <input type="datetime-local" name="start_time" id="start_time"/>
        <label for="end_time">Кінцева дата:</label>
        <input type="datetime-local" name="end_time" id="end_time"/>
        <input id="updateDashboard" type="button" value="Оновити">
    </form>
</div>
<div id="map" style="margin-top: 10px"></div>
<div id="speedGauge"></div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#updateDashboard").click(function () {
                var data = $('#timeForm').serialize();
                var config = {
                    packages: ['corechart', 'table'],
                    col: [{type: 'string', name: 'Час початку затору'},
                        {type: 'number', name: 'Широта'},
                        {type: 'number', name: 'Довгота'},
                        {type: 'string', name: 'Початок затору'},
                        {type: 'string', name: 'Кінець затору'},
                        {type: 'number', name: 'Довжина, м'},
                        {type: 'number', name: 'Затримка, сек'}],
                    jsonCol: ["jamStartTime", "lat", "lon", "jamStart", "jamEnd", "distance", "delay"]
                }
                sendForm(data, 'traffic_jam', config);
                return false;
            }
        );
    });
    function sendForm(data, url, config) {
        $.ajax({
            type: 'GET',
            headers: {
                Accept: "application/json; charset=utf-8"
            },
            dataType: "json",
            data: data,
            url: url,
            success: function (result) {

                var traffic_jam = [];
                $.each(result, function (i, obj) {
                    traffic_jam.push(obj);
                });
                var mapData = {data: traffic_jam}
                heatmapLayer.setData(mapData);
            }
        });
        $.ajax({
            type: 'GET',
            headers: {
                Accept: "application/json; charset=utf-8"
            },
            dataType: "json",
            data: data,
            url: "traffic_jam/top",
            success: function (result) {
                google.charts.load('current', {
                    'packages': config.packages
                });
                google.charts.setOnLoadCallback(function () {
                    drawChart(result, config);
                });
            }
        });
    }


</script>
<script>
    var baseLayer = L.tileLayer(
        'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
            maxZoom: 18
        }
    );

    var cfg = {
        // radius should be small ONLY if scaleRadius is true (or small radius is intended)
        "radius": 0.004,
        "maxOpacity": .5,
        // scales the radius based on map zoom
        "scaleRadius": true,
        // if set to false the heatmap uses the global maximum for colorization
        // if activated: uses the data maximum within the current map boundaries
        //   (there will always be a red spot with useLocalExtremas true)
        "useLocalExtrema": true,
        // which field name in your data represents the latitude - default "lat"
        latField: 'lat',
        // which field name in your data represents the longitude - default "lng"
        lngField: 'lon',
        // which field name in your data represents the data value - default "value"
        valueField: 'currentSpeed',
        gradient: {
            0.0: '#fff5f0',
            0.25: '#fff686',
            0.5: '#fbd14e',
            0.75: '#ffa931',
            1.0: '#ff5f00'
        }
    };


    var heatmapLayer = new HeatmapOverlay(cfg);

    var map = new L.Map('map', {
        center: new L.LatLng(50.449087, 30.523772),
        zoom: 14,
        layers: [baseLayer, heatmapLayer]
    });

    // make accessible for debugging
    layer = heatmapLayer;

    function drawChart(result, config) {
        var sortedTable = new google.visualization.DataTable();
        for (i = 0; i < config.col.length; i++) {
            sortedTable.addColumn(config.col[i].type, config.col[i].name);
        }


        var sortedPropsArray = [];
        $.each(result, function (i, obj) {
            sortedPropsArray.push([obj[config.jsonCol[0]], obj[config.jsonCol[1]], obj[config.jsonCol[2]], obj[config.jsonCol[3]], obj[config.jsonCol[4]], obj[config.jsonCol[5]], obj[config.jsonCol[6]]])
        });
        sortedTable.addRows(sortedPropsArray);
        var table = new google.visualization.Table(document.getElementById('amountTable'));
        table.draw(sortedTable, {showRowNumber: true, width: '100%', height: '100%'});
    }
</script>
<div class="centered-top">
    <div class="col-md-auto">
        <div id="amountTable" style="border: 1px solid #ccc"></div>
    </div>
</div>
</div>
</body>
</html>