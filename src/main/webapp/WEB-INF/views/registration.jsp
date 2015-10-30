<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration Page</title>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
<div align="center">
    <h2><spring:message code="registrationForm"/></h2>
    <form:form method="POST" modelAttribute="gamer">
        <form:input type="hidden" path="account" id="account"/>
        <table>
            <tr>
                <td><label for="login"><spring:message code="login"/>: </label> </td>
                <td><form:input path="login" id="login"/></td>
                <td><form:errors path="login" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label for="password"><spring:message code="password"/>: </label> </td>
                <td><form:password path="password" id="password"/></td>
                <td><form:errors path="password" cssClass="error"/></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="<spring:message code="register"/>"/>
                </td>
            </tr>
        </table>
    </form:form>
    <h2><a href="/games/"><spring:message code="back"/></a></h2>
</div>
</body>
</html>