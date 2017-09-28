<!DOCTYPE html>

<!-- Skilgreiningar á tögum sem hægt er að nota í HTML kóða --> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html lang="en">

<head>
    <title>Please log in - Kafari</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/daginn.css"/>"/>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <a class="navbar-brand" href="#">Kafari</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    </nav>
    <div class="container" style="max-width: 600px;">
        <div class="jumbotron">
<form method="POST" commandName="dive" action="/logDive">
    <table><tr>
            <td>Location:</td><td> <input name="location" type="text" placeholder="Location" /> </td>
            </tr> 
            <tr>
            <td>Total time (minutes):</td><td> <input name="time" type="text" 
                                              placeholder="Time (minutes)" /> </td>
            </tr>
            <td>Max depth:</td><td> <input name="depth" type="text" placeholder="Max depth" /> </td>
            </tr> 
            <td>Decompression:</td><td> <input name="decompression" type="text" placeholder="Decompression" /> </td>
            </tr> 
            <td>Letter:</td><td> <input name="letter" type="text" placeholder="Letter" /> </td>
            </tr> 
    </table>            
    <input type="submit" value="Add new dive"/> 
</form>
    </div>
        
</body>
</html>