<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <form:form modelAttribute="convenio" action="convenioAgregado" method="post">
            <form:errors path="*" />
            <fieldset>
                <legend>Agregar nuevo convenio</legend>
                <p>
                    <form:label for="codigo" path="codigo">Codigo</form:label><br/>
                    <form:input path="codigo" required= "required" />
                </p>
                <p>
                    <form:label for="fechaCreacionString" path="fechaCreacionString">Fecha de creacion</form:label><br/>
                    <form:input path="fechaCreacionString" required= "required" type="date" />
                </p>
                <p>
                    <form:label for="importeInicialConvenio" path="importeInicialConvenio">Importe inicial del convenio</form:label><br/>
                    <form:input path="importeInicialConvenio" required= "required" type="number" min="0" />
                </p>
                <p>
                    <form:label for="importeActualConvenio" path="importeActualConvenio">Importe actual del convenio</form:label><br/>
                    <form:input path="importeActualConvenio" required= "required" type="number" min="0" />
                </p>
                <p>
                    <label for="idCliente">Ingrese el id del cliente</label>
                    <br/>
                    <input id="idCliente" type="text" name="idCliente" required= "required" type="number" min="0" />
                    <br/>
                </p>
                <p>
                    <input type="submit" value="Agregar Convenio" />
                </p>
            </fieldset>
        </form:form>
        <form:form action="listadoConvenios" method="get">
            <input type="submit" value="Volver"/>
        </form:form>
    </div>

</html>




