
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <form:form modelAttribute="paquete" action="listadoPaquetes" method="get">
            <fieldset>
                <legend>El paquete fue agregado correctamente</legend>
                <p>
                    <form:label for="codigo" path="codigo">Codigo</form:label><br/>
                    <form:input path="codigo" disabled="true" />
                </p>
                <p>
                    <form:label for="fechaCreacion" path="fechaCreacion">Fecha de creacion</form:label><br/>
                    <form:input path="fechaCreacion" disabled="true" />
                </p>
                <p>
                    <form:label for="costo" path="costo">Costo</form:label><br/>
                    <form:input path="costo" disabled="true" />
                </p>
                <p>
                    <form:label for="peso" path="peso">Peso (Kg)</form:label><br/>
                    <form:input path="peso" disabled="true" />
                </p>
                <p>
                    <form:label for="descripcion" path="descripcion">Descripcion</form:label><br/>
                    <form:input path="descripcion" disabled="true" />
                </p>
                <p>
                    <form:label for="idCliente" path="idCliente">Id del cliente</form:label><br/>
                    <form:input path="idCliente" disabled="true" />
                </p>
                <p>
                    <input type="submit" value="Volver" />
                </p>
            </fieldset>
        </form:form>
    </div>
</html>


