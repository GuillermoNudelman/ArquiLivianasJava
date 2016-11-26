<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <form:form modelAttribute="cliente" action="listadoClientes" method="get">
            <fieldset>
                <legend>El cliente fue agregado correctamente</legend>
                <p>
                    <form:label for="nombreContacto" path="nombreContacto">Nombre de contacto</form:label><br/>
                    <form:input path="nombreContacto" disabled="true" />
                </p>
                <p>
                    <form:label for="nombreEmpresa" path="nombreEmpresa">Nombre de la empresa</form:label><br/>
                    <form:input path="nombreEmpresa" disabled="true" />
                </p>
                <p>
                    <form:label for="direccion" path="direccion">Direccion</form:label><br/>
                    <form:input path="direccion" disabled="true" />
                </p>
                <p>
                    <form:label for="telefono" path="telefono">Telefono</form:label><br/>
                    <form:input path="telefono" disabled="true" />
                </p>
                <p>
                    <input type="submit" value="Volver" />
                </p>
            </fieldset>
        </form:form>
    </div>
</html>

