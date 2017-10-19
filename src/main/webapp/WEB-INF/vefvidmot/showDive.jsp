<!DOCTYPE html>

<!-- Skilgreiningar á tögum sem hægt er að nota í HTML kóða --> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
    <head>
        <title>Enter new dive</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/daginn.css"/>"/>
    </head>
    <body>
<%@ include file="navigation.jsp" %>
        <div class="container" style="max-width: 600px;">
            <div class="jumbotron">
                <div class="input-group input-group">
                    <span class="input-group-addon w-50">Location</span>
                    <input type="text" class="form-control" value="${location}" disabled="disabled">
                </div><br>
                <div class="input-group input-group">
                    <span class="input-group-addon w-50">Time</span>
                    <input type="text" class="form-control" value="${time}" disabled="disabled">
                </div><br>
                <div class="input-group input-group">
                    <span class="input-group-addon w-50">Max depth</span>
                    <input type="text" class="form-control" value="${depth}" disabled="disabled">
                </div><br>
                <div class="input-group input-group">
                    <span class="input-group-addon w-50">Decompression</span>
                    <input type="text" class="form-control" value="${decompression}" disabled="disabled">
                </div><br>
                <div class="input-group input-group">
                    <span class="input-group-addon w-50">Letter</span>
                    <input type="text" class="form-control" value="${letter}" disabled="disabled">
                </div>

                <br>
                <a href="showDiver?">
                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">< Back To Menu</button>
                </a>

            </div>
        </div>
    </body>
</html>