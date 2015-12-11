<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a City</title>
</head>
<body>
	<h1>Update City</h1>
	<form action="cityupdate" method="post">
		<p>
			<label for="cityid">CityId</label>
			<input id="cityid" name="cityid" value="${fn:escapeXml(param.cityid)}" >
		</p>
		<p>
			<label for="cityname">New CityName</label>
			<input id="cityname" name="cityname" value="">
		</p>
		<p>
			<label for="state">New State</label>
			<input id="state" name="state" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>