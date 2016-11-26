<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <form:form modelAttribute="chofer" action="listadoChoferes" method="get">
            <fieldset>
                <legend>La chofer fue agregada correctamente</legend>
                <p>
                    <form:label for="codigo" path="codigo">Codigo</form:label><br/>
                    <form:input path="codigo" disabled="true" />
                </p>
                <p>
                    <form:label for="nombre" path="nombre">Nombre</form:label><br/>
                    <form:input path="nombre" disabled="true" />
                </p>
                <p>
                    <form:label for="libretaDeConducir" path="libretaDeConducir">Libreta de conducir</form:label><br/>
                    <form:input path="libretaDeConducir" disabled="true" />
                </p>
                <p>
                    <input type="submit" value="Volver" />
                </p>
            </fieldset>
        </form:form>
    </div>
</html>


