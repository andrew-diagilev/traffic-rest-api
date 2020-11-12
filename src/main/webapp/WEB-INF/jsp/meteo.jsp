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

    .leaflet-popup-content b {
        font-weight: bold;
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

    .leaflet-div-field {
        background: orange;
        border: 2px solid darkorange;
        border-radius: 5px;
        text-align: center;
        line-height: 20px;
        width: 40px !important;
        height: 23px !important;
        opacity: 0.7;
    }
</style>
<link rel="stylesheet" href="css/prism.css">
<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/leaflet-extra-markers@1.2.1/src/assets/css/leaflet.extra-markers.css">
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
      integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
      crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
        integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
        crossorigin=""></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="/js/heatmap.min.js"></script>
<script src="/js/leaflet-heatmap.js"></script>
<script src="https://cdn.jsdelivr.net/npm/leaflet-extra-markers@1.2.1/dist/js/leaflet.extra-markers.min.js"></script>
<jsp:include page="includes/header.jsp"/>
<ul class="nav nav-tabs" id="myTab" role="tablist">
    <li class="nav-item">
        <a class="nav-link active" id="temp-tab" data-toggle="tab" href="#temperature" role="tab"
           aria-controls="temperature" aria-selected="true">Температура</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" id="atmo-tab" data-toggle="tab" href="#atmosphere" role="tab" aria-controls="atmosphere"
           aria-selected="false">Атмосферні параметри/опади</a>
    </li>
<%--    <li class="nav-item">
        <a class="nav-link" id="road-tab" data-toggle="tab" href="#road" role="tab" aria-controls="road"
           aria-selected="false">Параметри дорожнього полотна</a>
    </li>--%>
</ul>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="temperature" role="tabpanel" aria-labelledby="temp-tab"></div>
    <div class="tab-pane fade" id="atmosphere" role="tabpanel" aria-labelledby="atmo-tab"></div>
    <div class="tab-pane fade" id="road" role="tabpanel" aria-labelledby="road-tab"></div>
</div>

<div id="map" style="margin-top: 10px; height: 700px; width:100%"></div>
<script type="text/javascript">
    $(document).ready(function () {
        getMeteo('meteo_station/param/last');
        setInterval(function (result) {
            getMeteo('meteo_station/param/last')
        }, 180000);
        $(document).on('click', '.leaflet-marker-icon', function (e) {
            // Use the event to find the clicked element
            var el = $(e.srcElement || e.target),
                id = el.attr('id');
            sendForm({id: id}, urlChart, configTemp);

        });
    });
    var urlChart = 'meteo_param/last_day';
    var configTemp = {
        packages: ['corechart', 'table'],
        XField: {type: 'string', name: 'Час'},
        YField: {type: 'number', name: 'Температура повітря'},
        YField2: {type: 'number', name: 'Температура дорожнього полотна'},
        YField3: {type: 'number', name: 'Точка роси'},
        XJsonField: 'captureTime',
        YJsonField: 'temperatureAir',
        YJsonField2: 'pavementSurfaceTemp',
        YJsonField3: 'dewpointTemp',
        options: {
            title: 'Статистика за останні 24 години',
            width: 1200,
            height: 250,
            hAxis: {
                title: "Дата/час",
                textStyle: {
                    fontSize: 9
                }
            },
            vAxis: {
                title: "Температура °C",
                textStyle: {
                    fontSize: 9
                }
            },
        }
    };



    var materialOptions = {
        XField: {type: 'string', name: 'Час'},
        YField: {type: 'number', name: 'К-ть опадів'},
        YField2: {type: 'number', name: 'Відносна вологість'},
        XJsonField: 'captureTime',
        YJsonField: 'rainAccum',
        YJsonField2: 'relativeHumidity',
        chart: {
            title: 'Average Temperatures and Daylight in Iceland Throughout the Year'
        },
        width: 1200,
        height: 250,
        series: {
            // Gives each series an axis name that matches the Y-axis below.
            0: {axis: 'raid'},
            1: {axis: 'hum'}
        },
        axes: {
            // Adds labels to each axis; they don't have to match the axis names.
            y: {
                rain: {label: 'К-ть опадів'},
                hum: {label: 'Відносна вологість'}
            }
        }
    };

    function sendForm(data, url) {
        $.ajax({
            type: 'GET',
            headers: {
                Accept: "application/json; charset=utf-8"
            },
            dataType: "json",
            data: data,
            url: url,
            success: function (result) {
                google.charts.load('current', {
                    'packages': ['corechart', 'table']
                });
                google.charts.setOnLoadCallback(function () {
                    drawTempChart(result, configTemp);
                    drawAtmoChart(result, materialOptions);
                });
            }
        });
    };

    function drawTempChart(result, config) {
        var props = new google.visualization.DataTable();
        props.addColumn(config.XField.type, config.XField.name);
        props.addColumn(config.YField.type, config.YField.name);
        props.addColumn(config.YField2.type, config.YField2.name);
        props.addColumn(config.YField3.type, config.YField3.name);
        var propsArray = [];
        $.each(result, function (i, obj) {
            propsArray.push([obj[config.XJsonField], obj[config.YJsonField], obj[config.YJsonField2], obj[config.YJsonField3]]);
        });
        props.addRows(propsArray);
        var chart = new google.visualization.LineChart(document
            .getElementById('temperature'));
        chart.draw(props, configTemp.options);

    };

    function drawAtmoChart(result, config) {
        var props = new google.visualization.DataTable();
        props.addColumn(config.XField.type, config.XField.name);
        props.addColumn(config.YField.type, config.YField.name);
        props.addColumn(config.YField2.type, config.YField2.name);
        var propsArray = [];
        $.each(result, function (i, obj) {
            propsArray.push([obj[config.XJsonField], obj[config.YJsonField], obj[config.YJsonField2]]);
        });
        props.addRows(propsArray);
        var chart = new google.visualization.ColumnChart(document
            .getElementById('atmosphere'));
        chart.draw(props, materialOptions);

    };


    function getMeteo(url) {
        $.ajax({
            type: 'get',
            headers: {
                Accept: "application/json; charset=utf-8"
            },
            dataType: "json",
            url: url,
            success: function (result) {
                map.eachLayer((layer) => layer.remove()
                );
                baseLayer.addTo(map);
                for (var i = 0; i < result.length; i++) {
                    var marker = L.marker([result[i].lat, result[i].lon]).addTo(map);
                    marker._icon.id = result[i].id;
                    marker.bindPopup("<b>" + result[i].id + "</b>" + " " + result[i].parametersList[0].captureTime
                        + "<br><b>Температура</b>"
                        + "<br>Температура повітря: " + result[i].parametersList[0].temperatureAir + ", °C"
                        + "<br>Точка роси: " + result[i].parametersList[0].dewpointTemp + " °C"
                        + "<br>Температура замерзання: " + result[i].parametersList[0].freezingTemp + ", °C"
                        + "<br>Температура дорожнього полотна: " + result[i].parametersList[0].pavementSurfaceTemp + ", °C"
                        + "<br><b>Атмосферні параметри/опади</b>"
                        + "<br>К-ть опадів: " + result[i].parametersList[0].rainAccum + ", мм"
                        + "<br>Інтенсивність опадів: " + result[i].parametersList[0].rainIntens + ", мм/год"
                        + "<br>Відносна вологість: " + result[i].parametersList[0].relativeHumidity + " %"
                        + "<br>Атмосферний тиск: " + result[i].parametersList[0].airPressure + ", мбар"
                        + "<br><b>Параметри дорожнього полотна</b>"
                        + "<br>Водна плівка: " + result[i].parametersList[0].waterHeight + ", мм"
                        + "<br>Вміст реагентів: " + result[i].parametersList[0].chemicalFactor + ", %"
                        + "<br>Рівень тривоги: " + result[i].parametersList[0].surfaceSignal + ""
                        + "<br><b>Вітер</b>"
                        + "<br>Швидкість вітру (мах): " + result[i].parametersList[0].windSpeedMax + ", м/сек"
                        + "<br>Напрям вітру: " + result[i].parametersList[0].windDirectionAvg + ", °");
                }
            }
        });
    };
    var baseLayer = L.tileLayer(
        'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
            maxZoom: 18
        });
    var map = new L.Map('map', {
        center: new L.LatLng(50.449087, 30.523772),
        zoom: 12,
        layers: [baseLayer],
    });

</script>
</body>
</html>