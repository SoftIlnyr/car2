<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${userinfo.nickname}</title>

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
                <h1>Профиль пользователя ${userinfo.nickname}</h1>
            </div>
        </div>
    </div>
</header>

<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-md-12">

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-8">
                            <h2>${userinfo.firstname} ${userinfo.surname}</h2>
                            <p><strong>Пассажир: </strong> Да </p>
                            <p><strong>Водитель: </strong> Да </p>
                        <#if userinfo.driver?exists>
                            <h4><strong>Автомобили: </strong></h4>
                            <#list userinfo.driver.automobileList as auto>
                                <p>${auto.brand} ${auto.model}</p>
                            </#list>

                        </#if>


                        </div><!--/col-->
                        <div class="col-xs-12 col-sm-4 text-center">
                            <img src="http://api.randomuser.me/portraits/men/49.jpg" alt=""
                                 class="center-block img-circle img-responsive">

                        </div><!--/col-->

                        <div class="col-xs-12 col-sm-6" style="background: #bce8f1">
                            <h3 style="text-align: center">Водитель </h3>
                            <h4>Рейтинг: <strong> </strong></h4>
                            <h4>Количество поездок: <strong> XXX </strong></h4>
                        <#if user.id == userinfo.id>
                            <h4 style="text-align: center">
                                <a href="/newauto">
                                    <button class="btn btn-info btn-block"><span class="fa fa-plus-circle"></span>
                                        Добавить авто
                                    </button>
                                </a>
                            </h4>
                        </#if>
                        <#--<p>-->
                        <#--<small>Followers</small>-->
                        <#--</p>-->
                        </div><!--/col-->
                        <div class="col-xs-12 col-sm-6" style="background: #c1e2b3">
                            <h3 style="text-align: center">Пассажир</h3>
                            <h4>Рейтинг: <strong> XXX </strong></h4>
                            <h4>Количество поездок: <strong> XXX </strong></h4>
                        <#if user.id == userinfo.id>
                            <h4 style="text-align: center">
                                <a href="/newtrip">
                                    <button class="btn btn-success btn-block"><span class="fa fa-plus-circle"></span>
                                        Предложить
                                        поездку
                                    </button>
                                </a>
                            </h4>
                        </#if>

                        <#--<p>-->
                        <#--<small>Пассажир</small>-->
                        <#--</p>-->
                        <#--<button class="btn btn-info btn-block"><span class="fa fa-user"></span> View Profile-->
                        <#--</button>-->

                        </div><!--/col-->

                    <#if bookings?has_content>
                        <div class="col-xs-12 col-sm-12">
                            <hr/>
                            <h3 style="text-align: center">Желающие поехать с вами</h3>
                        </div>

                        <div class="col-xs-12 col-sm-12" style="background: #bce8f1; padding-top: 20px">
                            <#list bookings as b>
                                <div class="col-xs-6 col-sm-6">
                                    <p>${b.trip.departure} - ${b.trip.destination} : ${b.trip.date} </p>
                                    <h4>Пассажиры: ${b.passenger.user.firstname} ${b.passenger.user.firstname}
                                        (${b.count}
                                        пассажиров)</h4>
                                    <p>Комментарий пассажира: ${b.info}</p>
                                </div>
                                <div class="col-xs-3 col-sm-3">

                                    <a href="/bookings/${b.id}/conf">
                                        <button class="btn btn-success btn-block"><span
                                                class="fa fa-plus-circle"></span>
                                            Принять
                                        </button>
                                    </a>
                                </div>
                                <div class="col-xs-3 col-sm-3">

                                    <a href="/bookings/${b.id}/deny">
                                        <button class="btn btn-danger btn-block"><span class="fa fa-plus-circle"></span>
                                            Отклонить
                                        </button>
                                    </a>
                                </div>
                                <hr/>

                            </#list>
                        </div>
                    </#if>

                        <div class="col-xs-12 col-sm-12">
                            <hr/>
                            <h3 style="text-align: center">Предстоящие поездки</h3>
                        </div>

                        <div class="col-xs-12 col-sm-6" style="background: #bce8f1">
                        <#if driverTrips?exists>
                            <#list driverTrips as dt>
                                <h4>${dt.departure} - ${dt.destination} : ${dt.date} </h4>
                                <#if user.id != dt.driver.user.id>
                                    <a href="/trips/${dt.id}">Записаться</a>
                                </#if>
                                <hr/>
                            </#list>
                        </#if>


                        </div><!--/col-->
                        <div class="col-xs-12 col-sm-6" style="background: #c1e2b3">
                        <#if pasTrips?exists>
                            <#list pasTrips as pt>
                                <h4>${pt.departure} - ${pt.destination} : ${pt.date}</h4>
                                <hr/>
                            </#list>
                        </#if>
                        </div><!--/col-->

                        <div class="col-xs-12 col-sm-12">
                            <hr/>
                            <h3 style="text-align: center">Последние поездки</h3>
                        </div>

                        <div class="col-xs-12 col-sm-6" style="background: #bce8f1">
                            <h4>Количество поездок: <strong> XXX </strong></h4>
                        <#--<p>-->
                        <#--<small>Followers</small>-->
                        <#--</p>-->
                        <#--<button class="btn btn-success btn-block"><span class="fa fa-plus-circle"></span> Предложить-->
                        <#--поездку-->
                        <#--</button>-->
                        </div><!--/col-->
                        <div class="col-xs-12 col-sm-6" style="background: #c1e2b3">
                            <h4>Количество поездок: <strong> XXX </strong></h4>
                        <#--<p>-->
                        <#--<small>Пассажир</small>-->
                        <#--</p>-->
                        <#--<button class="btn btn-info btn-block"><span class="fa fa-user"></span> View Profile-->
                        <#--</button>-->

                        </div><!--/col-->

                    </div><!--/row-->
                </div><!--/panel-body-->
            </div><!--/panel-->


        </div>
    </div>
<#include "footer.ftl">
</div>
<!-- /.container -->

<!-- jQuery -->
<script src="/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

</body>
</html>