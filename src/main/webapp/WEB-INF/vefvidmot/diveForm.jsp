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
    <div class="container" style="max-width:600px;">
        <div class="jumbotron">
            <h1>Log new dive</h1>
            <br>
            <form method="POST" commandName="dive" action="/calculateDecompression">
                <input name="location" type="text" placeholder="Location of dive" required class="form-control">
                <br>
                <input name="time" type="text" placeholder="Time in minutes" required class="form-control">
                <br>
                <input name="depth" type="text" placeholder="Max depth in meters" required class="form-control">
                <br>
                <input type="submit" value="Calculate and log dive" class="btn btn-primary" style="width:100%;"> 
                <br>
            </form>
                <br>
                <a href="showDiver?">
                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">< Back To Menu</button>
                </a>
        </div>
    </div>
</body>
</html>