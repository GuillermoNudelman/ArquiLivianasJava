<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <h2>Convenios</h2>
        <table border="1">
            <thead>
            <th>Id</th>
            <th>Codigo</th>
            <th>Fecha de creacion</th>
            <th>Importe inicial del convenio</th>
            <th>Importe actual del convenio</th>
            <th>Editar</th>
            <th>Eliminar</th>

                <c:forEach var="convenio" items="${convenios}">
                <tr>
                    <td>
                        ${convenio.id}
                    </td>
                    <td>
                        ${convenio.codigo}
                    </td>
                    <td>
                        ${convenio.fechaCreacion}
                    </td>
                    <td>
                        ${convenio.importeInicialConvenio}
                    </td>
                    <td>
                        ${convenio.importeActualConvenio}
                    </td>
                    <td>
                        <spring:url value="editar?idConvenio={idConvenio}" var="editarUrl">
                            <spring:param name="idConvenio" value="${convenio.id}"/>
                        </spring:url>
                        <a href="${editarUrl}">
                            Editar
                        </a>
                    </td>
                    <td>
                        <spring:url value="eliminar?idConvenio={idConvenio}" var="eliminarUrl">
                            <spring:param name="idConvenio" value="${convenio.id}"/>
                        </spring:url>
                        <a href="${eliminarUrl}">
                            Eliminar
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <form:form action="formularioNuevoConvenio" method="get">
            <input type="submit" value="Agregar Convenio"/>
        </form:form>
    </div>
</html>