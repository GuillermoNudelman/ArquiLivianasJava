<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <form:form modelAttribute="entrega" action="listadoEntregas" method="get">
            <fieldset>
                <legend>El entrega fue agregado correctamente</legend>
                <p>
                    <form:label for="codigo" path="codigo">Codigo</form:label><br/>
                    <form:input path="codigo" disabled="true" />
                </p>
                <p>
                    <form:label for="fechaEntrega" path="fechaEntrega">Fecha de entrega</form:label><br/>
                    <form:input path="fechaEntrega" disabled="true" />
                </p>
                <p>
                    <form:label for="importeEntrega" path="importeEntrega">Importe de la entrega</form:label><br/>
                    <form:input path="importeEntrega" disabled="true" />
                </p>
                <p>
                    <form:label for="idCamioneta" path="idCamioneta">Id de la camioneta</form:label><br/>
                    <form:input path="idCamioneta" disabled="true" />
                </p>
                <p>
                    <form:label for="idChofer" path="idChofer">Id del chofer</form:label><br/>
                    <form:input path="idChofer" disabled="true" />
                </p>
                <p>
                    <form:label for="listaPaquetesString" path="listaPaquetesString">Ids de el/los paquete/s</form:label><br/>
                    <form:input path="listaPaquetesString" disabled="true" />
                </p>
                <p>
                    <input type="submit" value="Volver" />
                </p>
            </fieldset>
        </form:form>
    </div>
</html>




