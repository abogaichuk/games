<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <form:form action="/games/blackjack/play" modelAttribute="bet">
        Rate : <form:input path="rate"/>
        <input type="submit" value="play"/>
        <form:errors path="rate" cssClass="error"/>
    </form:form>
    <h2><a href="/games/info/${bet.account}">Go to info page</a></h2>
</div>
</body>
</html>