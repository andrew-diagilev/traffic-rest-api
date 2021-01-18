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
            ['АН/KH Донецька обл.', 22832],
            ['AЕ/KE Дніпровська обл.', 17395],
            ['AM/KM Житомирська обл.', 14816],
            ['CA/IA Черкаська обл.', 14722],
            ['AB/KB Вінницька обл.', 13139],
            ['CB/IB Чернігівська обл.', 12910],
            ['AP/KP Запорізька обл.', 10828],
            ['BB/HB Луганська обл.', 10300],
            ['BI/HI Полтавська обл.', 10229],
            ['BM/HM Сумська обл.', 10133],
            ['BC/HC Львівська обл.', 9782],
            ['AX/KX Харківська обл.', 9703],
            ['AO/KO Закарпатьська обл.', 5256],
            ['AT/KT Івано-Франківська обл.', 5157],
            ['BH/HH Одеська обл.', 7988],
            ['AC/KC Волинська обл.', 7639],
            ['BK/HK Рівненьська обл.', 6752],
            ['BE/HE Миколаївська обл.', 6507],
            ['BX/HX Хмельницька обл.', 6434],
            ['BA/HA Кіровоградська обл.', 6135],
            ['AK/KK АР Крим	', 5298],
            ['BO/HO Тернопільська обл.', 4781],
            ['BT/HT Херсонська обл.', 4170],
            ['CE/IE Чернівецька обл.', 3622],
            ['CH/IH Севастопіль', 1811],
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