<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles.css">
<title>TGS | Add Grocery</title>
</head>
<body>
	<h1>Add a new grocery to store</h1>
	<div>${message}</div>

	<sf:form method="post" action="addGrocerySubmit"
		modelAttribute="grocery">
		<div>
			<sf:label path="name">Name</sf:label>
			<br>
			<sf:input path="name" type="text" required="required" />
		</div>
		<br>
		<div>
			<sf:label path="description">Description</sf:label>
			<br>
			<sf:input path="description" type="text" required="required" />
		</div>
		<br>
		<div>
			<sf:label path="producer">Producer</sf:label>
			<br>
			<sf:select path="producer" items="${allProducers}"
				itemLabel="name" required="required" />
		</div>
		<br>
		<div>
			<sf:label path="price">Price</sf:label>
			<br>
			<sf:input path="price" type="number" required="required" />
		</div>
		<br>
		<div>
			<sf:label path="quantity">Quantity</sf:label>
			<br>
			<sf:input path="quantity" type="number" required="required" />
		</div>
		<br>
		<input type="submit" value="add grocery" />
	</sf:form>

</body>
</html>