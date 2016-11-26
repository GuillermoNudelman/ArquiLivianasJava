<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
    <div>
     <form:form modelAttribute="camioneta" action="modificar" method="post">
     <form:errors path="*" />
        <fieldset>
            <legend>Campos Camioneta</legend>
            <p>
                <form:hidden path="id" />
                <form:label for="codigo" path="codigo">Codigo</form:label><br/>
                <form:input path="codigo" required= "required"/>
            </p>
            <p>
                <form:label for="placa" path="placa">Placa</form:label><br/>
                <form:input path="placa" required= "required"/>
            </p>
            <p>
                <form:label for="capacidadKgs" path="capacidadKgs">Capacidad (Kg)</form:label><br/>
                <form:input path="capacidadKgs" type="number" min="0" required= "required"/>
            </p>
            <p>
                <form:label for="kmsRecorridos" path="kmsRecorridos">Kms recorridos</form:label><br/>
                <form:input path="kmsRecorridos" type="number" min="0" required= "required"/>
            </p>
            <p>
                <form:label for="kmsProxService" path="kmsProxService">Kms faltantes para realizar el proximo service</form:label><br/>
                <form:input path="kmsProxService" type="number" min="0" required= "required"/>
            </p>
            <p>
                <input type="submit" value="Modificar Camioneta" />
            </p>
        </fieldset>
     </form:form>
    </div>
</html>
