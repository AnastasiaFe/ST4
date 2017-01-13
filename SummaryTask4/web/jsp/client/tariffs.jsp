<%@ taglib prefix="u" uri="http://knure.kharkov.ua/ST4/user" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<u:permit role="client"/>
<html>
<c:set var="title" value="Tariffs"/>
<%@ include file="/WEB-INF/jspf/main_head.jspf" %>
<body>
<table>
    <%@ include file="/WEB-INF/jspf/client_header.jspf" %></table>
<p id="blockP">${message}</p>
<table id="tariffs">
    <thead>
   <%@include file="/WEB-INF/jspf/tariffsTable.jspf"%>
    </thead>
    <tbody>
  <u:client_tariffs tariffs="${tariffs}"/>
    </tbody>
</table>
<table>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
