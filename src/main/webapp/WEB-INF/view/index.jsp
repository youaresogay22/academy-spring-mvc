<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>index</title>
</head>
<body>
    <%-- TODO #5: locale을 참고하여 다국어가 지원되는 메세지 --%>
    <h2><fmt:message key="greeting" /></h2>
<%--    <h2><spring:message code="greeting" /></h2>--%>
    <br />

    <%-- TODO #6: `locale` 파라미터를 추가한 링크 추가 --%>
    <a href="?locale=ko">ko</a><br />
    <a href="?locale=en">en</a><br />
    <br />
</body>
</html>
