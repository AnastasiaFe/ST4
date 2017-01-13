<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Settings" scope="page" />

<%@ include file="/WEB-INF/jspf/main_head.jspf" %>

<body>
<c:set var="user" value="${user}"/>
<c:if test="${user.role=='ADMIN'}">
<%@ include file="/WEB-INF/jspf/admin_header.jspf" %>
</c:if>
<c:if test="${user.role=='CLIENT'}">
    <%@ include file="/WEB-INF/jspf/client_header.jspf" %>
</c:if>
<table>

    <tr>
        <td class="content"  id="settings">
            <%-- CONTENT --%>

                <form action="../changeLocale.jsp" method="post">
                    <fmt:message key="settings_jsp.label.set_locale"/>:
                    <select name="locale">
                        <c:forEach items="${applicationScope.locales}" var="locale">
                            <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                            <option value="${locale.key}" ${selected}>${locale.value}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">

                </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>
