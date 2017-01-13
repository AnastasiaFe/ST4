<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="tariffs" type="java.util.List" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" uri="http://knure.kharkov.ua/ST4/user" %>

<c:forEach var="tariff" items="${tariffs}">
    <tr>
        <td id="tariffName">
                ${tariff.name}
        </td>
        <td id="descColumn">
                ${tariff.desc}
        </td>
        <td>
                ${tariff.price}
        </td>
        <td>
            <a href="controller?command=viewPage&page=editTariff&tariffId=${tariff.tariffId}">Edit</a>
            <a href="controller?command=viewPage&page=confirmDelete&tariffId=${tariff.tariffId}">Delete</a>
            <a href="controller?command=download&tariffId=${tariff.tariffId}">Download</a>
        </td>
    </tr>
</c:forEach>
