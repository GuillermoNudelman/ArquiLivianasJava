<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <form:form modelAttribute="convenio" action="listadoConvenios" method="get">
            <fieldset>
                <legend>El convenio fue agregado correctamente</legend>
                <p>
                    <form:label for="codigo" path="codigo">Codigo</form:label><br/>
                    <form:input path="codigo" disabled="true" />
                </p>
                <p>
                    <form:label for="fechaCreacion" path="fechaCreacion">Fecha de creacion</form:label><br/>
                    <form:input path="fechaCreacion" disabled="true" />
                </p>
                <p>
                    <form:label for="importeInicialConvenio" path="importeInicialConvenio">Importe inicial del convenio</form:label><br/>
                    <form:input path="importeInicialConvenio" disabled="true" />
                </p>
                <p>
                    <form:label for="importeActualConvenio" path="importeActualConvenio">Importe actual del convenio</form:label><br/>
                    <form:input path="importeActualConvenio" disabled="true" />
                </p>
                <p>
                    <input type="submit" value="Volver" />
                </p>
            </fieldset>
        </form:form>
    </div>
</html>


