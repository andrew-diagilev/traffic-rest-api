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
        doRequest('amount_plate_type');
    });

    function doRequest(url) {
        $.ajax({
            type: 'GET',
            headers: {
                Accept: "application/json; charset=utf-8"
            },
            dataType: "json",
            url: url,
            success: function (result) {
                google.charts.load('current', {
                    'packages': ['corechart',]
                });
                google.charts.load("current", {packages: ["corechart"]});
                google.charts.setOnLoadCallback(function () {
                    drawChart(result);
                });
            }
        });
    }


    function drawChart(result) {
        var typeArray = [
            /*['Одеська обл.', 169846],
             ['Інші області', 26386],
             ['Інші автономера', 52526],*/
        ];

        var typeArrayDetailed = [
            /* ['AA/KA Київ', 6279],
             ['AI/KI Київська обл.', 1374],
             ['АН/KH Донецька обл.', 71],
             ['AЕ/KE Дніпровська обл.', 1889],
             ['AM/KM Житомирська обл.', 366],
             ['CA/IA Черкаська обл.', 871],
             ['AB/KB Вінницька обл.', 1686],
             ['CB/IB Чернігівська обл.', 348],
             ['AP/KP Запорізька обл.', 758],
             ['BB/HB Луганська обл.', 655],
             ['BI/HI Полтавська обл.', 1209],
             ['BM/HM Сумська обл.', 689],
             ['BC/HC Львівська обл.', 681],
             ['AX/KX Харківська обл.', 757],
             ['AO/KO Закарпатьська обл.', 133],
             ['AT/KT Івано-Франківська обл.', 240],
             ['AC/KC Волинська обл.', 323],
             ['BK/HK Рівненьська обл.', 373],
             ['BE/HE Миколаївська обл.', 3546],
             ['BX/HX Хмельницька обл.', 524],
             ['BA/HA Кіровоградська обл.', 1201],
             ['AK/KK АР Крим	', 94],
             ['BO/HO Тернопільська обл.', 235],
             ['BT/HT Херсонська обл.', 1344],
             ['CE/IE Чернівецька обл.', 246],
             ['CH/IH Севастопіль', 492],*/
        ];

        $.each(result, function (i, obj) {
            if (obj.region === 'Одеська обл.' || obj.region === 'Інші області' || obj.region === 'Інші автономера') {
                typeArray.push([obj.region, obj.amount]);
            }
            else typeArrayDetailed.push([obj.region, obj.amount]);
        });

        var otherTypes = 0;

        $.each(typeArrayDetailed, function (i, obj) {
            otherTypes += obj[1];
        });
        typeArray.push(['Інші області', otherTypes]);

        typeArrayDetailed.sort((a, b) => a[1] > b[1] ? 1 : -1);

        /*За типом номерів*/
        var plateType = new google.visualization.DataTable();
        plateType.addColumn('string', 'Регіональна ознака');
        plateType.addColumn('number', 'Кількість %');

        var plateTypeDetailed = new google.visualization.DataTable();
        plateTypeDetailed.addColumn('string', 'Регіональна ознака');
        plateTypeDetailed.addColumn('number', 'Кількість %');

        plateType.addRows(typeArray);
        plateTypeDetailed.addRows(typeArrayDetailed);
        var plateTypeOptions = {
            title: 'Гістограма за типом номерних знаків ' + new Date(result[0].startTime).toLocaleDateString(),
            is3D: true,
            width: 900,
            height: 800,
            pieSliceText: 'value',
        };

        var plateChart = new google.visualization.PieChart(document
            .getElementById('plateType'));
        plateChart.draw(plateType, plateTypeOptions);
        var detailedPlateChart = new google.visualization.PieChart(document
            .getElementById('plateTypeDetailed'));
        detailedPlateChart.draw(plateTypeDetailed, plateTypeOptions);
    }
</script>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-auto">
            <div id="plateType">
            </div>
        </div>
        <div class="col-md-auto">
            <div id="plateTypeDetailed"></div>
        </div>
    </div>
</div>
</body>
</html>