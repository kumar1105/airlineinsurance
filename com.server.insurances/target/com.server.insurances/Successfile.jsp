<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Success Page</title>
    <style>
    	table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}

th, td {
  padding: 15px;
}
    </style>
</head>
<body>
    <h2>Insurance Details:</h2>
    <table>
        <tr>
            <th>Aviation_id</th>
            <th>Plane_id</th>
            <th>Company_name</th>
            <th>Amount</th>
            <th>Tenure</th>
            
        </tr>
        <tr>
            <td><%= request.getParameter("Aviation_id") %></td>
            <td><%= request.getParameter("Plane_id") %></td>           
            <td><%= request.getParameter("Company_name") %></td>
            <td><%= request.getParameter("Amount") %></td>
        	<th><%= request.getParameter("Tenure") %></th>
        </tr>
    </table>
    
<div class="bottom-bar">
<button ><a href="index.html">Back to Homepage</a></button>


</div>    
</body>
</html>l>