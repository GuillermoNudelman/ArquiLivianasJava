<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <h2>Clientes</h2>
        <table border="1">
            <thead>
            <th>Id</th>
            <th>Nombre de contacto</th>
            <th>Nombre de la empresa</th>
            <th>Direccion</th>
            <th>Telefono</th>
            <th>Editar</th>
            <th>Eliminar</th>

                <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>
                        ${cliente.id}
                    </td>
                    <td>
                        ${cliente.nombreContacto}
                    </td>
                    <td>
                        ${cliente.nombreEmpresa}
                    </td>
                    <td>
                        ${cliente.direccion}
                    </td>
                    <td>
                        ${cliente.telefono}
                    </td>
                    <td>
                        <spring:url value="editar?idCliente={idCliente}" var="editarUrl">
                            <spring:param name="idCliente" value="${cliente.id}"/>
                        </spring:url>
                        <a href="${editarUrl}">
                            Editar
                        </a>
                    </td>
                    <td>
                        <spring:url value="eliminar?idCliente={idCliente}" var="eliminarUrl">
                            <spring:param name="idCliente" value="${cliente.id}"/>
                        </spring:url>
                        <a href="${eliminarUrl}">
                            Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <form:form action="formularioNuevoCliente" method="get">
            <input type="submit" value="Agregar Cliente"/>
        </form:form>
    </div>
</html>