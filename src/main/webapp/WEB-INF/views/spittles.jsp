<%--
  Created by IntelliJ IDEA.
  User: tianyunz
  Date: 7/27/18
  Time: 9:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spittles</title>
</head>
<body>
<c:forEach items="${spittleList}" var="spittle" >
    <li id="spittle_<c:out value="spittle.id"/>">
        <div class="spittleMessage">
            <c:out value="${spittle.message}" />
        </div> <div>
        <span class="spittleTime"><c:out value="${spittle.time}" /></span>
        <span class="spittleLocation">
          (<c:out value="${spittle.latitude}" />,
          <c:out value="${spittle.longitude}" />)</span>
    </div>
    </li>
</c:forEach>
</body>
</html>
