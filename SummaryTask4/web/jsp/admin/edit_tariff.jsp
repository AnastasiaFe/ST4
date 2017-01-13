<%@ taglib prefix="u" uri="http://knure.kharkov.ua/ST4/user" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<u:permit role="admin"/>
<html>
<c:set var="title" value="Tariffs"/>
<%@ include file="/WEB-INF/jspf/main_head.jspf" %>
<body>
<table>
    <%@ include file="/WEB-INF/jspf/admin_header.jspf" %></table>
<form id="editTariffForm" action="controller" method="post">
<input type="hidden" name="command" value="editTariff">
    <c:set var="tariff" value="${tariff}"/>
    <input type="hidden" name="tariffId" value="${tariff.tariffId}">
    <input type="hidden" name="service" value="${tariff.service.serviceId}">
<table>
    <tr>
        <td>Name</td>
        <td><input type="text" name="tariffName" value="${tariff.name}">  </td>
    </tr>
    <tr>
        <td>Description</td>
        <td><textarea name="tariffDesc" rows="8" cols="50">${tariff.desc}</textarea></td>
    </tr>
    <tr>
        <td>Price</td>
        <td><input type="text" name="tariffPrice" value="${tariff.price}"></td>
    </tr>
    <tr>
        <td><input type="submit" value="Save"></td>
    </tr>
</table>
</form>
<table>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
</table>
<button id="addTariffButton">Add</button>
</body>
</html>
