<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/styles.css">
<title>TGS | View Basket</title>
</head>
<body>
	<h1 class="basketHeading">All the groceries in your basket</h1>
	<div>${message}</div>

	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Description</th>
			<th>Producer</th>
			<th>Price</th>
			<th>Remove From Basket</th>
		</tr>

		<c:forEach items="${allGroceries}" var="grocery">
			<tr>
				<td>${grocery.groceryId}</td>
				<td>${grocery.name}</td>
				<td>${grocery.description}</td>
				<td>${grocery.producer.name}</td>
				<td>£ ${grocery.price}</td>
				<td><a class="removeButton"
					href="removeFromBasket/${customerId}/${grocery.groceryId}">Remove</a></th>
			</tr>
		</c:forEach>
	</table>
	<br>

	<h3>Total Cost: £${totalCost}</h3>
	
	<br>
	
	<button>Check Out</button>

</body>
</html>