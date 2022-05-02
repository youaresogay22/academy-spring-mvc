<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>index</title>
</head>
<body>
    <h2><fmt:message key="greeting" /></h2>
<%--    <h2><spring:message code="greeting" /></h2>--%>
    <br />

    <a href="?locale=ko">ko</a><br />
    <a href="?locale=en">en</a><br />
    <br />
</body>
</html>
