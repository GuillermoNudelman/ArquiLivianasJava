<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <h2>Choferes</h2>
        <table border="1">
            <thead>
            <th>Id</th>
            <th>Nombre de contacto</th>
            <th>Nombre de la empresa</th>
            <th>Direccion</th>
            <th>Editar</th>
            <th>Eliminar</th>

                <c:forEach var="chofer" items="${choferes}">
                <tr>
                    <td>
                        ${chofer.id}
                    </td>
                    <td>
                        ${chofer.codigo}
                    </td>
                    <td>
                        ${chofer.nombre}
                    </td>
                    <td>
                        ${chofer.libretaDeConducir}
                    </td>
                    <td>
                        <spring:url value="editar?idChofer={idChofer}" var="editarUrl">
                            <spring:param name="idChofer" value="${chofer.id}"/>
                        </spring:url>
                        <a href="${editarUrl}">
                            Editar
                        </a>
                    </td>
                    <td>
                        <spring:url value="eliminar?idChofer={idChofer}" var="eliminarUrl">
                            <spring:param name="idChofer" value="${chofer.id}"/>
                        </spring:url>
                        <a href="${eliminarUrl}">
                            Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <form:form action="formularioNuevoChofer" method="get">
            <input type="submit" value="Agregar Chofer"/>
        </form:form>
    </div>
</html>