<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a City</title>
</head>
<body>
	<form action="findcities" method="post">
		<h1>Search for a City by City Name</h1>
		<p>
			<label for="cityname">CityName</label>
			<input id="cityname" name="cityname" value="${fn:escapeXml(param.cityname)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="cityCreate"><a href="citycreate">Create City</a></div>
	<br/>
	<h1>Matching Cities</h1>
        <table border="1">
            <tr>
            	<th>CityId</th>
                <th>CityName</th>
                <th>State</th>
                <th>Delete City</th>
                <th>Update City</th>
            </tr>
            <c:forEach items="${cities}" var="city" >
                <tr>
                	<td><c:out value="${city.getCityId()}" /></td>
                    <td><c:out value="${city.getCityName()}" /></td>
                    <td><c:out value="${city.getState()}" /></td>
                    <td><a href="citydelete?cityid=<c:out value="${city.getCityId()}"/>">Delete</a></td>
                    <td><a href="cityupdate?cityid=<c:out value="${city.getCityId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
