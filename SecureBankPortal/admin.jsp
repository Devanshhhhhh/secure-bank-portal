<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><meta charset="UTF-8">
<title>Admin Panel</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<div class="navbar">
    <h1>SecureBank Admin</h1>
    <a href="LogoutServlet">Logout</a>
</div>

<div class="container">
    <div class="card">
        <h2>All Users</h2>

        <table>
            <tr>
                <th>Account</th>
                <th>Username</th>
                <th>Balance</th>
                <th>Role</th>
                <th>Delete</th>
            </tr>

            <c:forEach var="u" items="${users}">
                <tr>
                    <td>${u.accountNumber}</td>
                    <td>${u.username}</td>
                    <td>₹ ${u.balance}</td>
                    <td>${u.role}</td>
                    <td>
                        <form action="DeleteAccountServlet" method="post">
                            <input type="hidden" name="account" value="${u.accountNumber}">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>

</body>
</html>