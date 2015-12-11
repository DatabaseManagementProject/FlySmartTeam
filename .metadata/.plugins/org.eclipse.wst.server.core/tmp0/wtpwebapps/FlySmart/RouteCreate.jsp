<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create a Route</title>
</head>
<body>
	<h1>Create Route</h1>
	<form action="routecreate" method="post">
		<p>
			<label for="routeid">RouteId</label>
			<input id="routeid" name="routeid" value="">
		</p>
		<p>
			<label for="origin">OriginAiportId</label>
			<input id="origin" name="origin" value="">
		</p>
		<p>
			<label for="destination">DestinationAirportId</label>
			<input id="destination" name="destination" value="">
		</p>
		<p>
			<label for="distance">Distance</label>
			<input id="distance" name="distance" value="">
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