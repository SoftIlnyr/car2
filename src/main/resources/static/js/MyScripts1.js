findTrips = function (request, response) {
    $.ajax({
        url: "/trips/find",
        data: {"departure": $("#departure").val(), "destination": $("#destination").val()},
        dataType: "json",
        success: function (response_data) {
            //info = $('#info');
            if (response_data.result == false) {
                //$("#wres").html("");
                //info.removeClass('hidden');
                //info.html("<h1>Писателей не найдено :(</h1>")
            } else {
                $("#trips").html("");
                for (var i = 0; i < response_data.trips.length; i++) {
                    $('#trips').append("<div class=\"col-md-6\">" +
                        "<div class=\"panel panel-default\">" +
                        "<div class=\"panel-body\">" +
                        "<div class=\"row\">" +
                        "<div class=\"col-xs-12 col-sm-12\"> " +
                        " <p><strong>Место отправления: </strong> " + response_data.trips[i].departure + " </p>" +
                        "<p><strong>Место назначения: </strong> " + response_data.trips[i].destination + " </p> " +
                        "<p><strong>Дата и время выезда: </strong> " + response_data.trips[i].date + " </p>" +
                        "<p><strong>Водитель: </strong> " + response_data.trips[i].driver.user.firstname + " " + response_data.trips[i].driver.user.firstname + "" +
                        "</p>" +
                        " <p><strong>Автомобиль: </strong> " + response_data.trips[i].auto.brand + " " + response_data.trips[i].auto.model + "" +
                        " - " + response_data.trips[i].auto.licensePlate + " </p>" +
                        " <a href=\"/trips/\"" + response_data.trips[i].date + ">" +
                        "   <button class=\"btn btn-success\">Записаться</button>" +
                        "   </a>" +
                        "    </div>" +
                        "    </div>" +
                        "    </div>" +
                        "    </div>" +
                        "    </div>"
                    );

                }

            }
        }
    });
};

findCritics = function (request, response) {
    $.ajax({
        url: "/reg1",
        data: {"data": $("#fw").val(), "subject": "critics"},
        dataType: "json",
        success: function (response_data) {
            info = $('#info');
            if (response_data.result == false) {
                $("#wres").html("");
                info.removeClass('hidden');
                info.html("<h1>Критиков не найдено :(</h1>")
            } else {
                info.addClass('hidden');
                $("#wres").html("");
                for (var i = 0; i < response_data.critics.length; i++) {
                    $('#wres').append("<div class=\"panel panel-default\" style=\"display: inline-block; padding: 10px\">\n" +
                        "                <a href=\"/users/" + response_data.critics[i].id + "\" ><img src=\"../images/writer.jpg\" class=\"img-rounded\"\n" +
                        "                                 style=\"max-width: 200px; max-height: 400px; float: right; margin-left: 10px\"></a>\n" +
                        "                <p style=\"font-size: 1.5em\">" + response_data.critics[i].name + "</p>\n" +
                        "                <p class=\"text-justify\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi a pellentesque\n" +
                        "                    nibh, id venenatis ligula.\n" +
                        "                    Nam mi libero, pellentesque sed libero vel, tempus aliquam augue. Nunc a ipsum pulvinar, tincidunt\n" +
                        "                    purus\n" +
                        "                    eu, dictum turpis. Morbi sed ipsum porta amet.</p>\n" +
                        "            </div>");
                }

            }
        }
    });
};

findBooks = function (request, response) {
    $.ajax({
        url: "/reg1",
        data: {"data": $("#fw").val(), "subject": "books"},
        dataType: "json",
        success: function (response_data) {
            info = $('#info');
            if (response_data.result == false) {
                $("#wres").html("");
                info.removeClass('hidden');
                +
                    info.html("<h1>Книг не найдено :(</h1>")
            } else {
                info.addClass('hidden');
                $("#wres").html("");
                for (var i = 0; i < response_data.books.length; i++) {
                    $('#wres').append("            <div class=\"panel panel-default\" style=\"display: inline-block; padding: 10px;\">\n" +
                        "                <a href=\"/books/" + response_data.books[i].id + "\"><img src=\"/" + response_data.books[i].coverpath + "\" class=\"img-rounded\" style=\"max-width: 200px; max-height: 400px; float: left; margin-right: 10px\"></a>\n" +
                        "                <p style=\"font-size: 1.5em\">" + response_data.books[i].name + "</p>\n" +
                        "                <p class=\"text-justify\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi a pellentesque nibh, id venenatis ligula.\n" +
                        "                    Nam mi libero, pellentesque sed libero vel, tempus aliquam augue. Nunc a ipsum pulvinar, tincidunt purus\n" +
                        "                    eu, dictum turpis. Morbi sed ipsum porta amet.</p>\n" +
                        "            </div>");
                }

            }
        }
    });
};

checkFields = function () {
    flagEmail = checkEmail();
    flagLogin = checkName();
    flagPassword = valPassword();
    flagConfirm = checkPasswords();
    var result = flagEmail && flagLogin && flagPassword && flagConfirm;
    if (result == false) {
        $('#info').removeClass('hidden');
    }
    return result;
};

checkChangeFields = function () {
    flagEmail = changeEmail();
    flagLogin = changeName();
    flagOldPas = valOldPassword();
    flagPassword = changePassword();
    flagConfirm = checkPasswords();
    var result = flagEmail && flagLogin && flagOldPas && flagPassword && flagConfirm;
    if (result == false) {
        $('#info').removeClass('hidden');
    }
    return result;
};

checkAuth = function (request, response) {
    var result = false;
    $.ajax({
        url: "/reg1",
        data: {"data": $("#login").val(), "data1": $("#password").val(), "subject": "auth"},
        async: false,
        dataType: "json",
        method: "post",
        success: function (response_data) {
            info = $('#info');
            info1 = $('#info1');
            result = false;
            if (response_data.result == false) {
                result = false;
                if (response_data.code == 1) {
                    info1.addClass('hidden');
                    info.removeClass('hidden');
                    $("#password").val("");
                } else if (response_data.code == 2) {
                    info.addClass('hidden');
                    info1.removeClass('hidden');
                    $("#login").val("");
                    $("#password").val("");
                }
            } else {
                result = true;
            }
        }
    });
    return result;
};

BooknameVal = function () {
    var filename = $('#bookname').val();
    reFilename = new RegExp('(\\w|[.,-])+');
    var result = reFilename.test(filename);
    formGroup = $('#bookname').parents('.form-group');
    if (result == false) {
        $('#booknameinfo').removeClass('hidden');
        formGroup.addClass('has-warning').removeClass('has-success');
    } else {
        $('#booknameinfo').addClass('hidden');
        formGroup.addClass('has-success').removeClass('has-warning');
    }
    return result;
};

GenreVal = function () {
    var filename = $('#genre').val();
    reFilename = new RegExp('((\\w)+)|((\\w){6,}(\\w|[.,/-])+)');
    var result = reFilename.test(filename);
    formGroup = $('#genre').parents('.form-group');
    if (result == false) {
        $('#genreinfo').removeClass('hidden');
        formGroup.addClass('has-warning').removeClass('has-success');
    } else {
        $('#genreinfo').addClass('hidden');
        formGroup.addClass('has-success').removeClass('has-warning');
    }
    return result;
};

TextfileVal = function () {
    var filename = $('#textfile').val();
    reFilename = new RegExp('.+?[/\\\\]\\w+.(txt|doc|docx|fb2|epub|jar)');
    var result = reFilename.test(filename);
    formGroup = $('#textfile').parents('.form-group');
    if (result == false) {
        $('#textfileinfo').removeClass('hidden');
        formGroup.addClass('has-warning').removeClass('has-success');
    } else {
        $('#textfileinfo').addClass('hidden');
        formGroup.addClass('has-success').removeClass('has-warning');
    }
    return result;
};

CoverVal = function () {
    var filename = $('#cover').val();
    reFilename = new RegExp('.+?[/\\\\]\\w+.(jpg|png|jpeg|gif)');
    var result = reFilename.test(filename);
    formGroup = $('#cover').parents('.form-group');
    if (result == false) {
        $('#coverinfo').removeClass('hidden');
        formGroup.addClass('has-warning').removeClass('has-success');
    } else {
        $('#coverinfo').addClass('hidden');
        formGroup.addClass('has-success').removeClass('has-warning');
    }
    return result;
};

CheckBookFields = function () {
    flagName = BooknameVal();
    flagGenre = GenreVal();
    flagTextfile = TextfileVal();
    flagCover = CoverVal();
    var result = flagName && flagGenre && flagTextfile && flagCover;
    if (result == false) {
        $('#info').removeClass('hidden');
    }
    return result;
};