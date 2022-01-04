<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        background: rgba(0, 0, 0, .8) !important;}

    #speedGauge {
        position: fixed;
        right: 15px;
        top: 65%;
        display: block;
        z-index:99999999;
    }
    svg:first-child > g > text[text-anchor~=middle]{
        font-size:13px;
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
<jsp:include page="includes/header.jsp" />
    <div>
        <form id="timeForm" method="post"><label for="start_time">Початкова дата:</label>
            <input type="datetime-local" name="start_time" id="start_time"/>
            <label for="end_time">Кінцева дата:</label>
            <input type="datetime-local" name="end_time" id="end_time"/>
            <label for="max_speed">Максимальна швидкість:</label>
            <input type="number" name="max_speed" id="max_speed" value="5"/>
            <input id="updateDashboard" type="button" value="Оновити">
        </form>
    </div>
    <div id="map" style="margin-top: 10px"></div>
    <div id="speedGauge"></div>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#updateDashboard").click(function () {
                    var data = $('#timeForm').serialize();
                    var max_speed = $('#max_speed').val();
                    sendForm(data, 'public_transport', max_speed);
                    return false;

                }
            );
        });
        function sendForm(data, url, max_speed) {
            $.ajax({
                type: 'GET',
                headers: {
                    Accept: "application/json; charset=utf-8"
                },
                dataType: "json",
                data: data,
                url: url,
                success: function (result) {

                    var sum = 0;
                    var speedArray = [];
                    var public_transport = [];
                    $.each(result, function (i, obj) {
                        speedArray.push(obj.currentSpeed);
                        if (obj.currentSpeed <= max_speed) {
                            public_transport.push(obj);
                        }
                    });
                    $.each(speedArray, function (i, obj) {
                        sum += obj;
                    });
                    var average = Math.round(sum / speedArray.length);
                    var mapData = {data: public_transport}
                    heatmapLayer.setData(mapData);
                    google.charts.load('current', {'packages': ['gauge']});
                    google.charts.setOnLoadCallback(function () {
                        drawChart(average);
                    });
                    function drawChart(average) {
                        var speedAverage = google.visualization.arrayToDataTable([
                            ['Label', 'Value'],
                            ['Сер. Швидкість км/год', average],
                        ]);

                        var speedGaugeOption = {
                            width: 250,
                            height: 250,
                            redFrom: 0, redTo: 20,
                            yellowFrom: 20, yellowTo: 40,
                            greenFrom: 40, greenTo: 70,
                        };

                        var speedGauge = new google.visualization.Gauge(document
                            .getElementById('speedGauge'));
                        speedGauge.draw(speedAverage, speedGaugeOption);
                    }
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
            "radius": 0.0004,
            "maxOpacity": .8,
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
                0.25: '#fcbba1',
                0.5: '#fb6a4a',
                0.75: '#cb181d',
                1.0: '#67000d'
            }
        };


        var heatmapLayer = new HeatmapOverlay(cfg);

        var map = new L.Map('map', {
            center: new L.LatLng(46.4795998,30.7226228),
            zoom: 14,
            layers: [baseLayer, heatmapLayer]
        });

        // make accessible for debugging
        layer = heatmapLayer;
    </script>
</div>
</body>
</html>