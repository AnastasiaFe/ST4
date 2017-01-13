<%@ taglib prefix="u" uri="http://knure.kharkov.ua/ST4/user" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<u:permit role="client"/>
<html>
<c:set var="title" value="Services"/>
<%@ include file="/WEB-INF/jspf/main_head.jspf" %>
<body>

<%@ include file="/WEB-INF/jspf/client_header.jspf" %>
<div id="score">
    <p>You current score is</p><span>${user.score}</span>
  <c:set var="selected" value="${selectedTariffs}" scope="session"/>
    <ul>
    <c:forEach var="tariff" items="${selected}">
        <li>${tariff.name}</li>
    </c:forEach>
        </ul>
</div>
<form action="controller">
    <input type="hidden" name="command" value="pay">
    <c:if test="${user.status=='UNBLOCKED'}">
    <input type="submit" value="Pay">
    </c:if>
    <c:if test="${user.status=='BLOCKED'}">
        <input disabled type="submit" value="Pay">
    </c:if>
</form>

<table>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
</table>

</body>
</html>
