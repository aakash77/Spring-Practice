<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Organization</title>
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
		<thead><tr>
				<th>Organization ID</th>
				<th>Organization Name</th>
				<th>Organization Address</th>
				<th>Organization Description</th>
		</tr></thead>

		<tr>
			<td>${organization.organization_id}</td>
			<td>${organization.name}</td>
			<td>${organization.address.street}, ${organization.address.city}, ${organization.address.state}, ${organization.address.zip}</td>
			<td>${organization.description}</td>
		</tr>

	</table>
</body>
</html>