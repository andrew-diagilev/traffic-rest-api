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
    svg:first-child > g > text[text-anchor~=middle] {
        font-size: 13px;
        font-weight: 500;
    }
    .leaflet-div-field
    {
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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/leaflet-extra-markers@1.2.1/src/assets/css/leaflet.extra-markers.css">
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
<jsp:include page="includes/header.jsp" />
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
                    var max_speed = $('#max_speed').val();
                    sendForm(data, 'crossing/by_time', max_speed);
                    return false;

                }
            );
        });
        function sendForm(data, url) {
            $.ajax({
                type: 'post',
                headers: {
                    Accept: "application/json; charset=utf-8"
                },
                dataType: "json",
                url: url,
                data: data,
                success: function (result) {
                    map.eachLayer((layer) => {
                        layer.remove();
                });
                    baseLayer.addTo(map);
                    for (var i = 0; i < result.length; i++) {

/*                    <svg viewbox="0 0 10 10">
                            <defs>
                            <circle id="circle" cx="5" cy="5" r="4" stroke-width="0.5" fill="transparent" />
                            </defs>
                            <use xlink:href="#circle" stroke="pink" stroke-dasharray="0,2.09,8.38,30" />
                            <use xlink:href="#circle" stroke="green" stroke-dasharray="0,10.47,8.38,30" />
                            <use xlink:href="#circle" stroke="orange" stroke-dasharray="2.09,16.75,6.3" />
                            </svg>*/

/*                        var numMarker = L.ExtraMarkers.icon({
                            icon: 'fa-number',
                            number: result[i].amount,
                            markerColor: 'blue',
                            fontSize: 10
                        });
                        L.marker([result[i].lat, result[i].lon], {icon: numMarker}).addTo(map);*/

                       var icon = L.divIcon({className: 'leaflet-div-field',
                       html: result[i].amount});
                        L.marker([result[i].lat, result[i].lon], {icon: icon}).addTo(map);

                    }

                }
            });
        }
        var baseLayer = L.tileLayer(
            'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>',
                maxZoom: 18
            });
        var map = new L.Map('map', {
            center: new L.LatLng(50.449087, 30.523772),
            zoom: 14,
            layers: [baseLayer]
        });



    </script>
</div>
</body>
</html>