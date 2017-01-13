<%@ taglib prefix="u" uri="http://knure.kharkov.ua/ST4/user" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<u:permit role="admin"/>
<html>
<%@ include file="/WEB-INF/jspf/main_head.jspf" %>
<body>
<table>
<%@ include file="/WEB-INF/jspf/admin_header.jspf"%>
</table>
<table>
    <%@include file="/WEB-INF/jspf/footer.jspf"%>
</table>

</body>
</html>
