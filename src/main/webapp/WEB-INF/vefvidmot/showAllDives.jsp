<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>
    <title>All dives </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/daginn.css"/>"/>
</head>
<body>
    <%@ include file="navigation.jsp" %>

        <div class="container">
      <div class="jumbotron">
          <h1>All dives</h1><br>
        <c:choose>
        <c:when test="${not empty dives}">
            <table class="dives table table-striped table-bordered table-hover table-responsive">
                <thead class="thead-inverse">
                    <tr>
                        <th>Date & Time</th>
                        <th>Location</th>
                        <th>Dive duration</th>
                        <th>Max depth</th>
                        <th>Decompression</th>
                        <th>Letter</th>
                    </tr>
                </thead>
                <c:forEach var ="dive" items="${dives}">
                    <tr>
                        <td>${dive.diveDate} </td>
                        <td>${dive.divingLocation}</td>
                        <td>${dive.totalTime}</td>
                        <td>${dive.maxDepth}</td>
                        <td>${dive.decompression}</td>
                        <td>${dive.letter}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <h3> No dives</h3>
        </c:otherwise>
    </c:choose>

            <br>
            <a href="showDiver?">
                <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">< Back To Menu</button>
            </a>
      </div>
      <button class="btn btn-primary my-2 my-sm-0" onclick="printFunction()"><span class="glyphicon glyphicon-print"></span> Print</button>
    </div>
    

    <script>
    function printFunction() {
        window.print();
    }
    </script>
        



</body>
<!-- method="POST" action="/demo/listiKennara" -->
</html>
    