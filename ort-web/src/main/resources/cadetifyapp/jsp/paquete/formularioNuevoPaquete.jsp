<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <form:form modelAttribute="paquete" action="paqueteAgregado" method="post">
            <form:errors path="*" />
            <fieldset>
                <legend>Agregar nuevo paquete</legend>
                <p>
                    <form:label for="codigo" path="codigo">Codigo</form:label><br/>
                    <form:input path="codigo" required= "required" />
                </p>
                <p>
                    <form:label for="fechaCreacionString" path="fechaCreacionString">Fecha de creacion</form:label><br/>
                    <form:input path="fechaCreacionString" required= "required" type="date" />
                </p>
                <p>
                    <form:label for="costo" path="costo">Costo</form:label><br/>
                    <form:input path="costo" required= "required" type="number" min="0" />
                </p>
                <p>
                    <form:label for="peso" path="peso">Peso (Kg)</form:label><br/>
                    <form:input path="peso" required= "required" type="number" min="0" />
                </p>
                <p>
                    <form:label for="descripcion" path="descripcion">Descripcion</form:label><br/>
                    <form:input path="descripcion" required= "required" />
                </p>
                <p>
                    <form:label for="descuento" path="descuento">Descuento</form:label><br/>
                    <form:input path="descuento" required= "required" type="number" min="0" />
                </p>
                <p>
                    <form:label for="idCliente" path="idCliente">Id del cliente</form:label><br/>
                    <form:input path="idCliente" required= "required" type="number" min="0" />
                </p>
                <p>
                    <input type="submit" value="Agregar Paquete" />
                </p>
            </fieldset>
        </form:form>
        <form:form action="listadoPaquetes" method="get">
            <input type="submit" value="Volver"/>
        </form:form>
    </div>
    <div>
        <h2>Clientes</h2>
        <table border="1">
            <thead>
            <th>Id</th>
            <th>Nombre de contacto</th>
            <th>Nombre de la empresa</th>
            <th>Direccion</th>
            <th>Telefono</th>

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
                </tr>
            </c:forEach>
        </table>
        <br/>
    </div>
</html>





