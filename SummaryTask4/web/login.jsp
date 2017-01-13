
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<%@ include file="/WEB-INF/jspf/main_head.jspf" %>
<c:set var="title" value="Login" />
<style>
    <%@ include file="/style/login.css"%>
</style>
<body>
<table id="main-container">
    <tr >
        <td class="content center">

            <form  name="loginForm" class="login-block" id="login_form" action="controller" onsubmit="return validateForm()" method="post">
                <h1><fmt:message key="login_jsp.form.sign_in_header"/></h1>
                <input type="hidden" name="command" value="login"/>

                <fieldset >
                    <legend><fmt:message key="login_jsp.form.login"/></legend>
                    <input name="login"/><br/>
                    <span id="loginError" class="error">${loginError}</span>
                </fieldset><br/>


                <fieldset>
                    <legend><fmt:message key="login_jsp.form.password"/></legend>
                    <input type="password" name="password"/>
                    <span id="passwordError" class="error">${passwordError}</span>
                </fieldset><br/>
                <span class="error" >${error}</span>


                <p id="forget"><a href="controller?command=viewPage&page=forgetPassword"><fmt:message key="login_jsp.form.forget"/></a></p>

                <button type="submit" ><fmt:message key="login_jsp.form.sign_in"/></button>
            </form>


        </td>
    </tr>
    <script>
        function validateForm() {
            document.getElementById('loginError').innerHTML='';
            document.getElementById('passwordError').innerHTML='';
            var loginError=document.getElementById('loginError');
            var passwordError=document.getElementById('passwordError');
            var login = document.forms["loginForm"]["login"].value;
            var password=document.forms["loginForm"]["password"].value;
            if(login.length>=3 && password.length>=3)
            {
                return true;
            }
            else {
                if (login.length < 3) {
                    loginError.innerHTML = 'Login should have at least 3 symbols';

                }

                if (password.length < 3) {
                    passwordError.innerHTML = 'Password should have at least 3 symbols';

                }

                return false;
            }
        }

    </script>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>

</table>
</body>
</html>
