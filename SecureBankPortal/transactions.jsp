<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><meta charset="UTF-8">
<title>Transactions</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<div class="container">
<h2>Transaction History</h2>

<table border="1" width="100%">
<tr>
<th>Type</th>
<th>Amount</th>
<th>Receiver</th>
<th>Date</th>
</tr>

<c:forEach var="t" items="${transactions}">
<tr>
<td>${t.type}</td>
<td>${t.amount}</td>
<td>${t.receiverAccount}</td>
<td>${t.transactionDate}</td>
</tr>
</c:forEach>

</table>

<br>
<a href="dashboard.jsp">Back to Dashboard</a>
</div>

</body>
</html>