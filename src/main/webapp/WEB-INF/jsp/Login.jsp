<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link href="default.css" rel="stylesheet">
<title>Login Form</title>
<script>
</script>
</head>

<body>
<h3> Facebook REST API </h3>
<div class="parent">
    <div class="child">
        <form:form method="POST" action="/save"
          modelAttribute="loginform">
            <form:label path="username">Username  </form:label> <br>
            <form:input path="username" /> <br> <br>
            <form:label path="password">Password</form:label> <br>
            <form:input path="password" type="password"/> <br> <br>
            <input id="submit" type="submit" value="Submit" />
        </form:form>
        <p> ${error} </p>
    </div>
</div>
</body>
</html>
