<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link href="default.css" rel="stylesheet">
<title>Success Page</title>
<script>
fetch('/v2/api-docs')
 .then((response) => response.json())
 .then((data) => document.getElementById("json").innerHTML = JSON.stringify(data, null, 4));

</script>
</head>

<body>
<h3> Facebook REST API </h3>
<p id="header"> Hi ${member.name}, API key ${apiKey}</p>
<p><a href="/swagger-ui.html">Explore Swagger UI </a></p>
<div id="details">
<h5> API Documentation </h5>
<pre id="json"></pre>
</div>

</body>
</html>
