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
    <p>Fill the score for</p>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="fillScore">
        <input type="text" name="money">
        <input type="submit" value="Fill">
    </form>
</div>


<table>
    <%@include file="/WEB-INF/jspf/footer.jspf" %>
</table>

</body>
</html>
