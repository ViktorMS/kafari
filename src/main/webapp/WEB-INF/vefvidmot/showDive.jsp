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
        <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
          <form class="form-inline my-2 my-lg-0" method="GET" action="/showDiver">
          <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Home</button>
        </form>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        </nav>
        <div class="container" style="max-width: 600px;">
            <div class="jumbotron">
                <form method="POST" commandName="dive" action="/logDive">
                <table>
                    <tr>
                        <td>Location:</td><td> <input name="location" type="text" value="${location}" disabled="disabled" size="35"/> </td>
                    </tr> 
                    <tr>
                        <td>Total time (minutes):</td><td> <input name="time" type="text" value="${time}" disabled="disabled" size="35"/> </td>
                    </tr>
                    <tr>
                        <td>Max depth:</td><td> <input name="depth" type="text" value="${depth}" disabled="disabled" size="35"/> </td>
                    </tr> 
                    <tr>
                        <td>Decompression:</td><td> <input name="decompression" type="text" value="${decompression}" disabled="disabled" size="35"/> </td>
                    </tr> 
                    <tr>
                        <td>Letter:</td><td> <input name="letter" type="text" value="${letter}" disabled="disabled" size="35"/> </td>
                    </tr> 
                </table>            
                <input type="submit" value="Return to menu"/> 
                </form>
            </div>
        </div>
    </body>
</html>