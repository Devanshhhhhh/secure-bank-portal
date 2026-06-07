<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<div class="navbar">
    <h1>SecureBank</h1>
    <div>
        <a href="TransactionServlet">Transactions</a>
        <a href="LogoutServlet">Logout</a>
    </div>
</div>

<div class="container">

    <div class="card balance">
        <h3>Account Number: ${sessionScope.user.accountNumber}</h3>
        <h1>₹ ${sessionScope.user.balance}</h1>
    </div>

    <div class="card">
        <h2>Deposit Money</h2>
        <form action="DepositServlet" method="post">
            <input type="number" name="amount" placeholder="Enter amount" required>
            <button type="submit">Deposit</button>
        </form>
    </div>

    <div class="card">
        <h2>Withdraw Money</h2>
        <form action="WithdrawServlet" method="post">
            <input type="number" name="amount" placeholder="Enter amount" required>
            <button type="submit">Withdraw</button>
        </form>
    </div>

    <div class="card">
        <h2>Transfer Money</h2>
        <form action="TransferServlet" method="post">
            <input type="number" name="receiverAccount" placeholder="Receiver Account Number" required>
            <input type="number" name="amount" placeholder="Enter amount" required>
            <button type="submit">Transfer</button>
        </form>
    </div>

</div>

</body>
</html>