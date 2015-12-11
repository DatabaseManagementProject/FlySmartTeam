<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<title>Create a User</title>
</head>
<body>
	<h1>Create User</h1>
	<form action="usercreate" method="post">
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="">
		</p>
			
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="">
		</p>
		<p>
			<label for="lastname">LastName</label>
			<input id="lastname" name="lastname" value="">
		</p>
		
		<p>
			<label for="password">Password</label>
			<input id="password" name="password" value="">
		</p>
		
		
		<p>
			<label for="email">Email Address</label>
			<input id="email" name="email" value="">
		</p>
		
		
		<p>
			<label for="phone">Phone Number</label>
			<input id="phone" name="phone" value="">
		</p>
		

		<p>
			<input type="submit" class="button button-block">
		</p>
	</form>
	
	
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>

</html>