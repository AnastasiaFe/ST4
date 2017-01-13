<%@ taglib prefix="u" uri="http://knure.kharkov.ua/ST4/user" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<u:permit role="client"/>
<html>
<c:set var="title" value="Services"/>
<%@ include file="/WEB-INF/jspf/main_head.jspf" %>
<body>

<%@ include file="/WEB-INF/jspf/client_header.jspf" %>


<div id="services">
    <ul>

        <c:forEach var="service" items="${services}">

            <li><a href="controller?command=viewPage&page=tariffs&serviceId=${service.serviceId}">${service.name}</a></li>

        </c:forEach>

    </ul>
</div>


<table>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
</table>

</body>
</html>

