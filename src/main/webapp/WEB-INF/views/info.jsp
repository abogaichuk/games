<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Info page</title>
</head>
<body>
<div align="center">
    <h4>Hi ${gamer.login}, your balance: ${balance}</h4>
    <%--<c:if test="${balance < 10}">
        <a href="/games/info/deposit/${gamer.account}">Take 1000</a>
    </c:if>--%>
    <c:choose>
        <c:when test="${balance <= 0}">
            <a href="/games/info/deposit/${gamer.account}">Take 1000</a>
        </c:when>
        <c:otherwise>
            <a href="/games/blackjack/play/${gamer.account}">Play</a>
        </c:otherwise>
    </c:choose>
    <table border="1">
        <th>id</th>
        <th>win</th>
        <th>value</th>
        <th>transactionDate</th>
        <th>totalBalance</th>

        <c:forEach var="operation" items="${gamer.operations}">
            <tr>
                <td>${operation.id}</td>
                <td>${operation.win}</td>
                <td>${operation.value}</td>
                <td>
                    <fmt:formatDate value="${operation.transactionDate}" pattern="yyyy-MM-dd HH:mm" />
                </td>
                <td>${operation.balance}</td>
            </tr>
        </c:forEach>
    </table>
    <h2><a href="/games/"><spring:message code="back"/></a></h2>
</div>
</body>
</html>