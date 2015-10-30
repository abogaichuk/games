<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BlackJack</title>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
<div align="center">
    <c:choose>
        <c:when test="${0 == fn:length(blackJackProcess.game.playerCards)}">
            <form:form action="/games/blackjack/play" modelAttribute="blackJackProcess">
                Rate : <form:input path="rate"/>
                <input type="submit" value="play"/>
                <form:errors path="rate" cssClass="error"/>
            </form:form>
            <h2><a href="/games/info/${blackJackProcess.account}">Go to info page</a></h2>
        </c:when>
        <c:otherwise>
            <h1>balance: ${blackJackProcess.balance}   rate: ${blackJackProcess.rate}</h1>
            <h2>dealer cards: </h2>
            <table border="1">
                <c:forEach var="cardUrl" items="${blackJackProcess.game.dealerCards}" varStatus="rowCounter">
                    <c:if test="${rowCounter.count == 1}">
                        <tr>
                    </c:if>
                    <td><img src="<c:url value="../images/${cardUrl}" />" /></td>
                    <c:if test="${rowCounter.count == fn:length(blackJackProcess.game.dealerCards)}">
                        </tr>
                    </c:if>
                </c:forEach >
            </table>
            <h2>player cards: </h2>
            <table border="1">
                <c:forEach var="cardUrl" items="${blackJackProcess.game.playerCards}" varStatus="rowCounter">
                    <c:if test="${rowCounter.count == 1}">
                        <tr>
                    </c:if>
                    <td><img src="<c:url value="../images/${cardUrl}" />" /></td>
                    <c:if test="${rowCounter.count == fn:length(blackJackProcess.game.playerCards)}">
                        </tr>
                    </c:if>
                </c:forEach >
            </table>
            <c:choose>
                <c:when test="${blackJackProcess.game.end}">
                    <h4 style="color: darkcyan">${blackJackProcess.resultMessage}
                        <a href="/games/blackjack/play/${blackJackProcess.account}"> try again</a>
                    </h4>
                </c:when>
                <c:otherwise>
                    <h4>
                        <form:form action="/games/blackjack/hit" modelAttribute="blackJackProcess">
                            <input width="150px" type="submit" value="hit"/>
                        </form:form>
                        <form:form action="/games/blackjack/stand" modelAttribute="blackJackProcess">
                            <input width="150px" type="submit" value="stand"/>
                        </form:form>
                    </h4>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>