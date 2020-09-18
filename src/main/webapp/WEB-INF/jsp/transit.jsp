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
<jsp:include page="includes/header.jsp"/>
<div>
    <form id="timeForm" method="post"><label for="date">Дата:</label>
        <input type="date" name="date" id="date"/>
        <input id="updateDashboard" type="button" value="Оновити">
    </form>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#updateDashboard").click(function () {
                var data = $('#timeForm').serialize();
                var config = {
                    packages: ['corechart', 'table'],
                    col: [{type: 'number', name: 'Кількість'},
                        {type: 'string', name: 'Контрольна точка, вї\'зд'},
                        {type: 'string', name: 'Дата'},],
                    jsonCol: ["amount", "roadName", "startTime"]
                }
                sendForm(data, 'transit/by_time', config);
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

    function drawChart(result, config) {
        var sortedTable = new google.visualization.DataTable();
        for (i = 0; i < config.col.length; i++) {
            sortedTable.addColumn(config.col[i].type, config.col[i].name);
        }


        var sortedPropsArray = [];
        $.each(result, function (i, obj) {
            sortedPropsArray.push([obj[config.jsonCol[0]], obj[config.jsonCol[1]], obj[config.jsonCol[2]]])
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