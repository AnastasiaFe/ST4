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
<form id="addClientForm" action="controller" method="post">
    <input type="hidden" name="command" value="addClient">
    <table>
        <tr>
            <td>Login</td>
            <td><input type="text" name="login">  </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"> </td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Surname</td>
            <td><input type="text" name="surname"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="email" name="email"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Add"></td>
        </tr>
    </table>
</form>
<table>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
</table>
</body>
</html>
