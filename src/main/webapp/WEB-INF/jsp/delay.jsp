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
<jsp:include page="includes/header.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        $("#updateDashboard").click(function () {
                var data = $('#timeForm').serialize();
                sendForm(data, '/delay/by_time');
                return false;
            }
        );
    });
    function sendForm(data, url) {
        $.ajax({
            type: 'POST',
            headers: {
                Accept: "application/json; charset=utf-8"
            },
            dataType: "json",
            data: data,
            url: url,
            success: function (result) {
                google.charts.load('current', {
                    'packages': ['corechart',]
                });
                google.charts.load('current', {'packages': ['gauge']});
                google.charts.load('current', {'packages': ['table']});
                google.charts.setOnLoadCallback(function () {
                    drawChart(result);
                });
            }
        });
    }

/*    function compare( a, b ) {
        return a-b;
    }*/

    function drawChart(result) {

        /*ЗАТРИМКА*/
        var delay = new google.visualization.DataTable();
        delay.addColumn('string', 'Час');
        delay.addColumn('number', 'Затримка');
        delay.addColumn('number', 'Середня затримка 2019');

        var delaySortedTable = new google.visualization.DataTable();
        delaySortedTable.addColumn('string', 'Час');
        delaySortedTable.addColumn('number', 'Затримка');
        delaySortedTable.addColumn('number', 'Середня затримка 2019');



        var delayArray = [];
        $.each(result, function (i, obj) {
            delayArray.push([obj.time, obj.currentDelay, obj.history_delay]);
        });
        var sortedDelayArray  =  [...delayArray];
        sortedDelayArray.sort(function(a,b) { if (a[1] > b[1]) return -1;
            if (a[1] < b[1]) return 1; });
        sortedDelayArray.length=10;
        delay.addRows(delayArray);
        delaySortedTable.addRows(sortedDelayArray);
        var delay_options = {
            title: 'Затримка індивідуального транспорту по годинам',
            width: 1200,
            height: 600,
            legend: {position: 'none'},
            curveType: 'function',
            colors: ['#e4000b', '#FFA500'],
            hAxis: {
                title: "Дата/час",
                textStyle: {
                    fontSize: 9
                }
            },
        };
        var delayChart = new google.visualization.LineChart(document
            .getElementById('delay'));
        delayChart.draw(delay, delay_options);
       var delayTable = new google.visualization.Table(document.getElementById('delayTable'));

        delayTable.draw(delaySortedTable, {showRowNumber: true, width: '100%', height: '100%'});


    }
</script>
<div class="container">
    <form id="timeForm" method="post"><label for="start_time">Початкова дата:</label>
        <input type="datetime-local" name="start_time" id="start_time"/>
        <label for="end_time">Кінцева дата:</label>
        <input type="datetime-local" name="end_time" id="end_time"/>
        <input id="updateDashboard" type="button" value="Оновити">
    </form>
    <div class="row">
    <div class="centered-top">
        <div class="col-md-auto">
    <div id="delay" style="border: 1px solid #ccc"></div>
            <div id="delayTable" style="border: 1px solid #ccc"></div>
        </div>
    </div>
    </div>
</div>
</body>
</html>