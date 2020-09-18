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
    var chartWidth;
    var chartHeight;
    $(document).ready(function () {
        sendForm({period: 'last_half'}, 'main_dash_board');
        $("#updateDashboard").click(function () {
                var data = $('#timeForm').serialize();
                sendForm(data, 'main_dash_board');
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
                google.charts.setOnLoadCallback(function () {
                    drawChart(result);
                });
            }
        });
    }
    function calculateLosses(result) {
        var lossesArray = [];
        $.each(result.delayList, function (i, obj) {
            lossesArray.push([new Date(obj.time), obj.currentDelay * 681126.13 * 95.08, obj.history_delay]);
        });
    }

    function drawChart(result) {

        /*КІЛЬКІСТЬ*/
        var amount = new google.visualization.DataTable();
        amount.addColumn('date', 'Час');
        amount.addColumn('number', 'Кількість');
        var amountArray = [];
        $.each(result.amountList, function (i, obj) {
            amountArray.push([new Date(obj.startTime), obj.amount]);
        });
        amount.addRows(amountArray);
        var currentAmount = result.currentAmount;

        /*ШВИДКІСТЬ*/
        var speed = new google.visualization.DataTable();
        speed.addColumn('date', 'Час');
        speed.addColumn('number', 'Швидкість');
        var sum = 0;
        var speedArray = [];
        $.each(result.speedList, function (i, obj) {
            speedArray.push([new Date(obj.startTime), obj.vehiclesSpeed]);
            sum += obj.vehiclesSpeed;
        });
        speed.addRows(speedArray);
        /*        var average = Math.round(sum / speedArray.length);*/
        var currentSpeed = result.currentSpeed;

        /*ЗАТРИМКА*/
        var delay = new google.visualization.DataTable();
        delay.addColumn('date', 'Час');
        delay.addColumn('number', 'Затримка');
        delay.addColumn('number', 'Середня затримка 2019');

        var delayArray = [];
        $.each(result.delayList, function (i, obj) {
            delayArray.push([new Date(obj.time), obj.currentDelay, obj.history_delay]);
        });
        delay.addRows(delayArray);
        var currentDelay = result.currentDelay;

        /*ТРАНЗИТ*/
        var transit = new google.visualization.DataTable();
        transit.addColumn('date', 'Час');
        transit.addColumn('number', 'Кілкість ТЗ');

        var transitArray = [];
        $.each(result.transitList, function (i, obj) {
            transitArray.push([new Date(obj.startTime), obj.amount]);
        });
        transit.addRows(transitArray);
        var currentTransit = transitArray[transitArray.length - 1][1];

        if(chartHeight == undefined && chartWidth == undefined){
            chartHeight = document.getElementsByClassName('card h-100')[0].offsetHeight * 2;
            chartWidth = document.getElementsByClassName('chart')[0].offsetWidth;
        }

        var amount_options = {
            title: 'Кількість транспортних засобів',
            width: chartWidth,
            height: chartHeight,
            legend: 'none',
            hAxis: {
                title: "Дата/час",
                textStyle: {
                    fontSize: 9
                }
            },
            trendlines: {0: {}}
        };

        var speed_options = {
            title: 'Швидкість індивідуального транспорту',
            width: chartWidth,
            height: chartHeight,
            legend: 'none',
            curveType: 'function',
            colors: ['#e49307'],
            hAxis: {
                title: "Дата/час",
                textStyle: {
                    fontSize: 9
                }
            },
            trendlines: {0: {}}
        };


        var delay_options = {
            title: 'Затримка індивідуального транспорту',
            width: chartWidth,
            height: chartHeight,
            legend: {position: 'none'},
            curveType: 'function',
            colors: ['#e4000b', '#FFA500'],
            hAxis: {
                title: "Дата/час",
                textStyle: {
                    fontSize: 9
                }
            },
            trendlines: {
                0: {color: '#e49307'}
            }
        };

        var transit_options = {
            title: 'Транзит',
            width: chartWidth,
            height: chartHeight,
            legend: {position: 'none'},
            curveType: 'function',
            colors: ['green', '#FFA500'],
            hAxis: {
                title: "Дата/час",
                textStyle: {
                    fontSize: 9
                }
            },
        };


        var speedLast = google.visualization.arrayToDataTable([
            ['Label', 'Value'],
            ['Швидкість', currentSpeed],
        ]);

        var speedGaugeOption = {
            width: document.getElementsByClassName('gauge')[0].offsetWidth,
            height: document.getElementsByClassName('gauge')[0].offsetWidth,
            redFrom: 0, redTo: 20,
            yellowFrom: 20, yellowTo: 40,
            greenFrom: 40, greenTo: 70,
        };


        var delayLast = google.visualization.arrayToDataTable([
            ['Label', 'Value'],
            ['Затримка', currentDelay],
        ]);

        var delayGaugeOption = {
            width: document.getElementsByClassName('gauge')[0].offsetWidth,
            height: document.getElementsByClassName('gauge')[0].offsetWidth,
            redFrom: 0.8, redTo: 1.5,
            yellowFrom: 0.5, yellowTo: 0.8,
            max: 1.5,

        };


        var amountLast = google.visualization.arrayToDataTable([
            ['Label', 'Value'],
            ['К-ть авто', currentAmount],
        ]);

        var amountGaugeOption = {
            width: document.getElementsByClassName('gauge')[0].offsetWidth,
            height: document.getElementsByClassName('gauge')[0].offsetWidth,
            redFrom: 750000, redTo: 1000000,
            yellowFrom: 650000, yellowTo: 750000,
            max: 1000000,

        };

        var transitLast = google.visualization.arrayToDataTable([
            ['Label', 'Value'],
            ['Транзит к-ть', currentTransit],
        ]);

        var transitGaugeOption = {
            width: document.getElementsByClassName('gauge')[0].offsetWidth,
            height: document.getElementsByClassName('gauge')[0].offsetWidth,
            max: 100000,
        };


        var amountChart = new google.visualization.ColumnChart(document
            .getElementById('amount'));
        amountChart.draw(amount, amount_options);

        var speedChart = new google.visualization.LineChart(document
            .getElementById('speed'));
        speedChart.draw(speed, speed_options);

        var delayChart = new google.visualization.LineChart(document
            .getElementById('delay'));
        delayChart.draw(delay, delay_options);

        var transitChart = new google.visualization.ColumnChart(document
            .getElementById('transit'));
        transitChart.draw(transit, transit_options);

        var speedGauge = new google.visualization.Gauge(document
            .getElementById('speedGauge'));
        speedGauge.draw(speedLast, speedGaugeOption);

        var delayGauge = new google.visualization.Gauge(document
            .getElementById('delayGauge'));
        delayGauge.draw(delayLast, delayGaugeOption);

        var amountGauge = new google.visualization.Gauge(document
            .getElementById('amountGauge'));
        amountGauge.draw(amountLast, amountGaugeOption);

        var transitGauge = new google.visualization.Gauge(document
            .getElementById('transitGauge'));
        transitGauge.draw(transitLast, transitGaugeOption);
    }

</script>
<div class="container-fluid">
    <div class="row">
        <div class="centered-top">
            <div class="col-sm">
                <form id="timeForm" method="post">
                    <select id="period" name="period">
                        <option value="last_half">Пів року</option>
                        <option value="last_month">Місяць</option>
                        <option value="last_week">Тиждень</option>
                    </select>
                    <input id="updateDashboard" type="button" value="Оновити">
                </form>
            </div>
        </div>
    </div>

    <%--Карточки--%>
    <%--Cкорость--%>
    <div class="row">
        <div class="col-md-2 mb-4">
            <div class="card bg-light h-100">
                <div class="card-header">
                    Поточна швидкість
                </div>
                <div class="card-body">
                    <div class="gauge" id="speedGauge"></div>
                </div>
                <div class="card-footer">
                    <small class="text-muted">Оновлено: </small>
                </div>
            </div>
        </div>
        <div class="col-md-10 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <div class="chart" id="speed"></div>
                </div>

            </div>
        </div>
    </div>
    <%--Количество--%>
    <div class="row">
        <div class="col-md-2 mb-4">
            <div class="card bg-light h-100">
                <div class="card-header">
                    Поточна кі-ть автомобілів
                </div>
                <div class="card-body">
                    <div class="gauge" id="amountGauge"></div>
                </div>
            </div>
        </div>
        <div class="col-md-10 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <div class="chart" id="amount"></div>
                </div>
            </div>
        </div>
    </div>
    <%--Задержка--%>
    <div class="row">
        <div class="col-md-2 mb-4">
            <div class="card bg-light h-100">
                <div class="card-header">
                    Поточна затримка
                </div>
                <div class="card-body">
                    <div class="gauge" id="delayGauge"></div>
                </div>
            </div>
        </div>
        <div class="col-md-10 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <div class="chart" id="delay"></div>
                </div>
            </div>
        </div>
    </div>
    <%--Транзит--%>
    <div class="row">
        <div class="col-md-2 mb-4">
            <div class="card bg-light h-100">
                <div class="card-header">
                    Поточний транзит
                </div>
                <div class="card-body">
                    <div class="gauge" id="transitGauge"></div>
                </div>
            </div>
        </div>
        <div class="col-md-10 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <div class="chart" id="transit"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>