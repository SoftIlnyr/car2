<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Car 2 Bla </title>

    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/css/business-frontpage.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<#include "header.ftl">

<!-- Image Background Page Header -->
<!-- Note: The background image is set within the business-casual.css file. -->
<header class="business-header">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="tagline">Больше путешествий! Больше друзей!</h1>
            </div>
        </div>
    </div>
</header>

<!-- Page Content -->
<div class="container">

    <hr>

    <div class="row">
        <div class="col-sm-12">
            <h2>Что такое Car 2 Bla?</h2>
            <p>Car 2 Bla - сервис, в котором вы можете найти путь до любого места, при этом провести поездку в хорошей
                компании!</p>
            <p>
                <a class="btn btn-default btn-lg" href="/registration">Начните прямо сейчас! &raquo;</a>
            </p>
        </div>
    </div>
    <!-- /.row -->

    <hr>

    <div class="row">
        <div class="col-sm-12">
            <h2 style="text-align: center;">Лучшие водители</h2>
        <#list drivers as driver>
            <div class="col-sm-4">
                <img class="img-circle img-responsive img-center"
                     src="/files/${driver.user.avatar}" alt="">
                <a href="/users/${driver.user.id}"><h2>${driver.user.nickname}</h2></a>
                <p><strong>Рейтинг</strong> ${driver.rating}</p>
                <p><strong>Поездок:</strong> ${driver.trips?size}</p>
            </div>
        </#list>
        </div>
    </div>
    <!-- /.row -->

    <hr>

<#include "footer.ftl">

</div>
<!-- /.container -->

<!-- jQuery -->
<script src="/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

</body>

</html>
