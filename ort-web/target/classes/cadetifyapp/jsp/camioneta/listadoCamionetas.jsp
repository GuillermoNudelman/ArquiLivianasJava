<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <h2>Camionetas</h2>
        <table border="1">
            <thead>
            <th>Id</th>
            <th>Codigo</th>
            <th>Placa</th>
            <th>Capacidad (Kg)</th>
            <th>Kms recorridos</th>
            <th>Kms faltantes para realizar el proximo service</th>
            <th>Editar</th>
            <th>Eliminar</th>
                <c:forEach var="camioneta" items="${camionetas}">
                <tr>
                    <td>
                        ${camioneta.id}
                    </td>
                    <td>
                        ${camioneta.codigo}
                    </td>
                    <td>
                        ${camioneta.placa}
                    </td>
                    <td>
                        ${camioneta.capacidadKgs}
                    </td>
                    <td>
                        ${camioneta.kmsRecorridos}
                    </td>
                    <td>
                        ${camioneta.kmsProxService}
                    </td>
                    <td>
                        <spring:url value="editar?idCamioneta={idCamioneta}" var="editarUrl">
                            <spring:param name="idCamioneta" value="${camioneta.id}"/>
                        </spring:url>
                        <a href="${editarUrl}">
                            Editar
                        </a>
                    </td>
                    <td>
                        <spring:url value="eliminar?idCamioneta={idCamioneta}" var="eliminarUrl">
                            <spring:param name="idCamioneta" value="${camioneta.id}"/>
                        </spring:url>
                        <a href="${eliminarUrl}">
                            Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <form:form action="formularioNuevaCamioneta" method="get">
            <input type="submit" value="Agregar Camioneta"/>
        </form:form>
    </div>
</html>