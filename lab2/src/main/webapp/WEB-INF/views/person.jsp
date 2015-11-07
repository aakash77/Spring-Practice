<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Persons</title>
<style type="text/css">
.myTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	width: 100%;
	border-collapse: collapse;
}

.myTable td, th {
	font-size: 1em;
	border: 1px solid #389CB3;
	padding: 3px 7px 2px 7px;
}

.myTable th {
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	background-color: #4269D8;
	color: #ffffff;
}

.myTable tr.alt td {
	color: #000000;
	background-color: #EAF2D3;
}
</style>
</head>
<body>

	<table class="myTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Address</th>
				<th>Description</th>
				<th>Organization ID</th>
				<th>Organization Name</th>
				<th>Organization Address</th>
				<th>Organization Description</th>
				<th>Friends Name</th>
			</tr>
		</thead>

		<tr>
			<td>${person.id}</td>
			<td>${person.firstname}</td>
			<td>${person.lastname}</td>
			<td>${person.email}</td>
			<td>${person.address.street},${person.address.city},
				${person.address.state}, ${person.address.zip}</td>
			<td>${person.description}</td>
			<td>${person.organization.organization_id}</td>
			<td>${person.organization.name}</td>
			<td>${person.organization.address.street},
				${person.organization.address.city},
				${person.organization.address.state},
				${person.organization.address.zip}</td>
			<td>${person.organization.description}</td>
			<td><c:forEach var="friend" items="${person.friends}">
					<li>${friend.firstname} ${friend.lastname}</li>
				</c:forEach></td>

		</tr>
	</table>

</body>
</html>