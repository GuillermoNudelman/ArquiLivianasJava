<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <form:form modelAttribute="entrega" action="entregaAgregada" method="post">
            <form:errors path="*" />
            <fieldset>
                <legend>Agregar nueva entrega</legend>
                <p>
                    <form:label for="codigo" path="codigo">Codigo</form:label><br/>
                    <form:input path="codigo" required= "required" />
                </p>
                <p>
                    <form:label for="fechaEntregaString" path="fechaEntregaString">Fecha de creacion</form:label><br/>
                    <form:input path="fechaEntregaString" required= "required" type="date" />
                </p>
                <p>
                    <form:label for="distanciaRecorrerKm" path="distanciaRecorrerKm">Distancia a recorrer (Km)</form:label><br/>
                    <form:input path="distanciaRecorrerKm" required= "required" type="number" min="0" />
                </p>
                <p>
                    <form:label for="idCamioneta" path="idCamioneta">Id de la camioneta</form:label><br/>
                    <form:input path="idCamioneta" required= "required" type="number" min="0" />
                </p>
                <p>
                    <form:label for="idChofer" path="idChofer">Id del chofer</form:label><br/>
                    <form:input path="idChofer" required= "required" type="number" min="0" />
                </p>
                <p>
                    <form:label for="listaPaquetesString" path="listaPaquetesString">Id de paquetes de la entrega (Debe ingresarse cada id separado por un guión. Ej: 1-2-3) </form:label><br/>
                    <form:input path="listaPaquetesString" required= "required"/>
                </p>
                
                <p>
                    <input type="submit" value="Agregar Entrega" />
                </p>
            </fieldset>
        </form:form>
        <form:form action="listadoEntregas" method="get">
            <input type="submit" value="Volver"/>
        </form:form>
    </div>
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
                </tr>
            </c:forEach>
        </table>
        <br/>
    </div>
    <div>
        <h2>Choferes</h2>
        <table border="1">
            <thead>
            <th>Id</th>
            <th>Nombre de contacto</th>
            <th>Nombre de la empresa</th>
            <th>Direccion</th>

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
                </tr>
            </c:forEach>
        </table>
        <br/>
    </div>
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
                </tr>
            </c:forEach>
        </table>
        <br/>
    </div>
</html>
