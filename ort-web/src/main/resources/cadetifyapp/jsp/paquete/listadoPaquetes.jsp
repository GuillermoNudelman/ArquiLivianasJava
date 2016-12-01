<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <h2>Paquetes</h2>
        <table border="1">
            <thead>
            <th>Id</th>
            <th>Codigo</th>
            <th>Fecha de creacion</th>
            <th>Costo</th>
            <th>Peso (Kg)</th>
            <th>Descripcion</th>
            <th>Descuento</th>
            <th>Id del cliente</th>
            <th>Editar</th>
            <th>Eliminar</th>

                <c:forEach var="paquete" items="${paquetes}">
                <tr>
                    <td>
                        ${paquete.id}
                    </td>
                    <td>
                        ${paquete.codigo}
                    </td>
                    <td>
                        ${paquete.fechaCreacion}
                    </td>
                    <td>
                        ${paquete.costo}
                    </td>
                    <td>
                        ${paquete.peso}
                    </td>
                    <td>
                        ${paquete.descripcion}
                    </td>
                    <td>
                        ${paquete.descuento}
                    </td>
                    <td>
                        ${paquete.idCliente}
                    </td>
                    <td>
                        <spring:url value="editar?idPaquete={idPaquete}" var="editarUrl">
                            <spring:param name="idPaquete" value="${paquete.id}"/>
                        </spring:url>
                        <a href="${editarUrl}">
                            Editar
                        </a>
                    </td>
                    <td>
                        <spring:url value="eliminar?idPaquete={idPaquete}" var="eliminarUrl">
                            <spring:param name="idPaquete" value="${paquete.id}"/>
                        </spring:url>
                        <a href="${eliminarUrl}">
                            Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <form:form action="formularioNuevoPaquete" method="get">
            <input type="submit" value="Agregar Paquete"/>
        </form:form>
    </div>
</html>
