<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat"%>

<%
    
    double principal = Double.parseDouble(request.getParameter("principal"));
    double rate = Double.parseDouble(request.getParameter("rate"));
    int time = Integer.parseInt(request.getParameter("time"));

    // Calculate interest
    double interest = (principal * rate * time) / 100;

    // Format the result
    DecimalFormat df = new DecimalFormat("#.##");
    String formattedInterest = df.format(interest);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Interest Calculation Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 20px;
        }

        h2 {
            color: #007bff;
        }

        p {
            margin: 5px 0;
        }
    </style>
</head>
<body>

    <h2>Interest Calculation Result</h2>

    <p>Principal Amount: <%= principal %></p>
    <p>Interest Rate: <%= rate %></p>
    <p>Time (in years): <%= time %></p>

    <p>Simple Interest: <%= formattedInterest %></p>

</body>
</html>
