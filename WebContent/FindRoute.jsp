<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Route</title>
</head>
<body>
	<form action="findroute" method="post">
		<h1>Search for a Route by RouteId</h1>
		<p>
			<label for="routeid">RouteId</label>
			<input id="routeid" name="routeid" value="${fn:escapeXml(param.routeid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="routeCreate"><a href="routecreate">Create Route</a></div>
	<br/>
	<h1>Matching Route</h1>
        <table border="1">
            <tr>
                <th>RouteId</th>
                <th>OriginAirportId</th>
                <th>DestinationAirportId</th>
                <th>Distance</th>
                <th>Delete Route</th>
                <th>Update Route</th>
                
            </tr>
            <c:forEach items="${Routes}" var="route" >
                <tr>
                    <td><c:out value="${route.getRouteId()}" /></td>
                    <td><c:out value="${route.getOriginAirportId()}" /></td>
                    <td><c:out value="${route.getDestinationAirportId()}" /></td>
                    <td><c:out value="${route.getDistance()}" /></td>
                    <td><a href="routedelete?routeid=<c:out value="${route.getRouteId()}"/>">Delete</a></td>
                    <td><a href="routeupdate?routeid=<c:out value="${route.getRouteId()}"/>">Update</a></td>
                   </tr>
            </c:forEach>
       </table>
</body>
</html>
