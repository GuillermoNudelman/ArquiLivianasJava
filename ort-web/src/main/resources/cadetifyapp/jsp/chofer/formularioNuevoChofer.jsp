<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <form:form modelAttribute="chofer" action="choferAgregado" method="post">
            <form:errors path="*" />
            <fieldset>
                <legend>Agregar nuevo chofer</legend>
                <p>
                    <form:label for="codigo" path="codigo">Codigo</form:label><br/>
                    <form:input path="codigo" required= "required" />
                </p>
                <p>
                    <form:label for="nombre" path="nombre">Nombre</form:label><br/>
                    <form:input path="nombre" required= "required" />
                </p>
                <p>
                    <form:label for="libretaDeConducir" path="libretaDeConducir">Libreta de conducir</form:label><br/>
                    <form:input path="libretaDeConducir" required= "required" />
                </p>
                <p>
                    <input type="submit" value="Agregar Chofer" />
                </p>
            </fieldset>
        </form:form>
        <form:form action="listadoChoferes" method="get">
            <input type="submit" value="Volver"/>
        </form:form>
    </div>

</html>




