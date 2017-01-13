
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Forget Password" />
    <%@ include file="/WEB-INF/jspf/main_head.jspf" %>
<body>
<table id="main-container">

    <tr >
        <td class="content center">
            <form class="login-block" id="login_form" action="controller" method="post">
<input type="hidden" name="command" value="resetPassword">
                <h1><fmt:message key="forget_password_jsp.form.head"/></h1>
                <p><fmt:message key="forget_password_jsp.form.reset_text"/></p>
                <input type="hidden" name="command" value="reset_password"/>
                <fieldset>
                    <legend>Login</legend>
                    <input type="text" name="login"/>
                </fieldset><br/>
                <button type="submit" ><fmt:message key="forget_password_jsp.form.send"/></button>
            </form>
        </td>
    </tr>
    </table>
<table>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>
</table>
</body>
</html>
