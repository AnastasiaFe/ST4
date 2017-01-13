<%@ taglib prefix="u" uri="http://knure.kharkov.ua/ST4/user" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<u:permit role="admin"/>
<html>
<c:set var="title" value="Tariffs"/>
<%@ include file="/WEB-INF/jspf/main_head.jspf" %>
<body>
<table>
    <%@ include file="/WEB-INF/jspf/admin_header.jspf" %>
</table>
<div id="confirmDelete">
<form action="controller" method="post">
    <input type="hidden" name="command" value="deleteTariff">
    <input type="hidden" name="tariffId" value="${tariff.tariffId}">
    <input type="hidden" name="serviceId" value="${tariff.service.serviceId}">
    <p>Are you sure you want to delete this tariff?</p>
    <button type="submit">Yes</button>
    <button>Cancel</button>
</form>
</div>
<table>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>