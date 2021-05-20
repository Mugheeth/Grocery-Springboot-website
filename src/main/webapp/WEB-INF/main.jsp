<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles.css">
<title>TGS | Main</title>
</head>
<body>
	<div class="nav">
		<h1 class="headingMain">The Groceries Stop Main Page</h1>
		<a class="basketButton" href="viewBasket/${customerId}">Basket (--
			${numberOfItems} --)</a>
	</div>
	
	<input type="hidden" value="${customerId}" />
	<h3 class="slogan">All your groceries desires will be fulfilled
		here.</h3>

	<h4>All Available Groceries</h4>

	<div>${message}</div>
	<br>


	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Description</th>
			<th>Producer</th>
			<th>Price</th>
			<th>Quantity Remaining</th>
			<th>Add To Basket</th>
		</tr>

		<c:forEach items="${allGroceries}" var="grocery">
			<tr>
				<td>${grocery.groceryId}</td>
				<td>${grocery.name}</td>
				<td>${grocery.description}</td>
				<td>${grocery.producer.name}</td>
				<td>£ ${grocery.price}</td>
				<td>${grocery.quantity}</td>
				<td>
					<form action="addToBasket/${customerId}/${grocery.groceryId}"
						method="post">
						<input class="quantityInput" type="number" name="numberToAdd" required="required"
							placeholder="Number of items to add" min="0" />
						<button class="addButton" type="submit">Add</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a class="addGroceryButton" href="addGrocery">Add new grocery to store</a>

</body>
</html>