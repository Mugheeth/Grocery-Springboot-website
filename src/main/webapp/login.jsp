<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<meta charset="ISO-8859-1">
<title>TGS | Login</title>
</head>
<body>

	<h1 class="heading">Login In To Your Account</h1>

	<form action="loginSubmit" method="post">
		<div class="alert">${message}</div>
		<br>
		<div>
			<label class="label" for="email">Email</label> <br> <input
				class="input" type="email" name="email" required="required" />
		</div>
		<br>
		<div>
			<label class="label" for="password">Password</label> <br> <input
				class="input" type="password" name="password" required="required" />
		</div>
		<br>
		<button class="button" type="submit">Login</button>
	</form>

</body>
</html>