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
    google.charts.load("current", {packages: ["corechart"]});
    google.charts.setOnLoadCallback(drawChart);


    function drawChart(result) {
        var typeArray = [
            ['AA/KA Київ', 381377],
            ['AI/KI Київська обл.', 102583],
            ['Інші області', 228339],
            ['Інші автономера', 121446],

        ];


        var typeArrayDetailed = [
            [' AЕ/KE Дніпровська обл.', 17395],
            ['AP/KP Запорізька обл.', 10828],
            ['AX/KX Харківська обл.', 9703],
            ['AO/KO Закарпатьська обл.', 5256],
            ['AT/KT Івано-Франківська обл.' , 5157],
            ['AC/KC Волинська обл.', 7639],
            ['BO/HO Тернопільська обл.', 4781],
            ['CE/IE Чернівецька обл.', 3622],
            ['BK/HK Рівненьська обл.', 6752],
            ['BX/HX Хмельницька обл.', 6434],
            ['AB/KB Вінницька обл.', 13139],
            ['BH/HH Одеська обл.', 7988],
            ['CA/IA Черкаська обл.', 14722],
            ['CB/IB Чернігівська обл.', 12910],
            ['BE/HE Миколаївська обл.', 6507],
            ['BA/HA Кіровоградська обл.', 6135],
            ['BI/HI Полтавська обл.', 10229],
            ['BM/HM Сумська обл.' , 10133],
            ['BB/HB Луганська обл.', 10300],
            ['АН/KH Донецька обл.', 22832],
            ['AK/KK АР Крим	', 5298],
            ['CH/IH Севастопіль', 1811],
            ['AM/KM Житомирська обл.', 14816],
            ['BT/HT Херсонська обл.', 4170],
            ['BC/HC Львівська обл.', 9782],

        ];

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
            title: 'Гістограма за типом номерних знаків',
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