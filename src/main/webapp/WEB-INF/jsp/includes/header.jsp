<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#"><!--Портал моніторингу параметрів міського трафіку--><img src="img/logo.png" width="30%"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Головна/Main dashboard<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Аналітика індивідуальний транспорт
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="delay">Затримка</a>
                    <a class="dropdown-item" href="speed">Швидкість</a>
                    <a class="dropdown-item" href="/amount">Кількість унікальних ТЗ</a>
                    <a class="dropdown-item" href="/type">Кількість ТЗ за типом</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/transit">Транзит</a>
                    <a class="dropdown-item" href="traffic_jam">Рейтинг заторів/теплокарта</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/public_transport" tabindex="-1" aria-disabled="true">Громадський транспорт</a>
            </li>
            <li class="nav-item"><a class="nav-link" href="/crossing_points" tabindex="-1" aria-disabled="true">Теплокарта транспортних засобів</a>
            </li>
        </ul>
    </div>
</nav>