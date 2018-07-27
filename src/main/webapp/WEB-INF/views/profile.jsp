<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tianyunz
  Date: 7/27/18
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h1>Your Profile</h1>
<c:out value="${spitter.username}"/><br/>
<c:out value="${spitter.firstName}"/>
<c:out value="${spitter.lastName}"/>
</body>
</html>
