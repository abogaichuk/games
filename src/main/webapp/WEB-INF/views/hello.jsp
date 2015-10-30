<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
</head>
<body>
<div align="center">
    <a href="?language=en"><spring:message code="english"/> </a> | <a href="?language=uk_UA"><spring:message code="ukrainian"/> </a>
</div>
<div align="center">
    <h1><spring:message code="gamerList"/></h1>
    <h2><a href="/games/new"><spring:message code="gamerNew"/></a></h2>
    <table border="1">
        <th><spring:message code="account"/></th>
        <th><spring:message code="login"/></th>
        <th><spring:message code="password"/></th>
        <th><spring:message code="info"/></th>
        <th><spring:message code="blackJack"/></th>
        <th><spring:message code="delete"/></th>

        <c:forEach var="gamer" items="${gamers}">
            <tr>
                <td>${gamer.account}</td>
                <td>${gamer.login}</td>
                <td>${gamer.password}</td>
                <td>
                    <a href="/games/info/${gamer.account}"><spring:message code="info"/></a>
                </td>
                <td>
                    <a href="/games/blackjack/play/${gamer.account}"><spring:message code="play"/></a>
                </td>
                <td>
                    <a href="/games/delete/${gamer.account}"><spring:message code="delete"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>