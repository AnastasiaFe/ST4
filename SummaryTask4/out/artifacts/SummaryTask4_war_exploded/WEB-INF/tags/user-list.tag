<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="users" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" uri="http://knure.kharkov.ua/ST4/user" %>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                    ${user.login}
            </td>
            <td>
                    ${u:fullName(user)}
            </td>
            <td>
                ${user.score}
            </td>
            <c:if test="${user.status=='BLOCKED'}">
                <td><a href="controller?command=unblockUser&login=${user.login}">Unblock</a></td>
            </c:if>
            <c:if test="${user.status=='UNBLOCKED'}">
                <td><a href="controller?command=blockUser&login=${user.login}">Block</a></td>
            </c:if>
        </tr>
    </c:forEach>
