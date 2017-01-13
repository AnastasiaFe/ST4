<%@ taglib prefix="u" uri="http://knure.kharkov.ua/ST4/user" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<u:permit role="client"/>
<html>
<%@ include file="/WEB-INF/jspf/main_head.jspf" %>
<body>

<%@ include file="/WEB-INF/jspf/client_header.jspf"%>
<form action="controller" method="post">
    <input type="hidden" name="command" value="send"/>
    <input type="submit" value="send"></form>
</body>
</html>
