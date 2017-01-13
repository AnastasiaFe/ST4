<%@ taglib prefix="u" uri="http://knure.kharkov.ua/ST4/user" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<u:permit role="admin"/>
<html>
<c:set var="title" value="Users"/>
<%@ include file="/WEB-INF/jspf/main_head.jspf" %>
<body>
<table>
    <%@ include file="/WEB-INF/jspf/admin_header.jspf" %>
</table>
<table id="users_list">

    <thead>
    <tr>
        <td>Login</td>
        <td>Full name</td>
        <td>Bill</td>
    </tr>
    </thead>
    <u:users users="${users}"/>
</table>


<table>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
</table>
<a id="addClient" href="controller?command=viewPage&page=addClient">Add client</a>
</body>
</html>
