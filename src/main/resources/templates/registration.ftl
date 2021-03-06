<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Регистрация</title>

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
<#--<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>-->
<!-- Image Background Page Header -->
<!-- Note: The background image is set within the business-casual.css file. -->
<header class="classic-header">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1>Регистрация</h1>
            </div>
        </div>
    </div>
</header>

<!-- Page Content -->
<div class="container">

    <hr>

    <form action="/registration" method="POST" enctype="multipart/form-data">
        <div class="form-group row">
            <label for="nickname" class="col-sm-2 col-form-label">Никнейм</label>
            <div class="col-sm-10">
                <input type="text" name="nickname" class="form-control" id="nickname" placeholder="Никнейм"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="email" name="email" class="form-control" id="email" placeholder="Email"/>
            <#--<@form.errors path="email"></@form.errors>-->
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 control-label">Пароль:</label>
            <div class="col-sm-10">
                <input type="password" name="password" class="form-control"
                       placeholder="Введите пароль"/>
            <#--<@form.errors path="password"></@form.errors>-->
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 control-label">Подтверждение пароля:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control"
                       name="passwordConfirmation"
                       placeholder="Введите пароль еще раз"/>
            <#--<@form.errors path="passwordConfirmation"></@form.errors>-->
            </div>
        </div>


        <div class="form-group row">
            <label for="nickname" class="col-sm-2 col-form-label">Имя</label>
            <div class="col-sm-10">
                <input type="text" name="firstname" class="form-control" id="firstname"
                       placeholder="Имя"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="nickname" class="col-sm-2 col-form-label">Фамилия</label>
            <div class="col-sm-10">
                <input type="text" name="surname" class="form-control" id="surname"
                       placeholder="Фамилия"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Аватар:</label>
            <div class="col-sm-10">
                <input type="file" name="avatar"/>
            </div>
        </div>
        <div class="form-group row">
            <div class="offset-sm-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Регистрация</button>
            </div>
        </div>


    </form>


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