
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <div>
        <html>
            <div>
                <h2>Reporte creado correctamente, revise en la carpeta del proyecto.</h2>
            </div>
        </html>
        <h2>Entregas</h2>
        <table border="1">
            <thead>
            <th>Periodo</th>
            <th>Camioneta</th>
            <th>Kilometros recorridos</th>
            <th>Peso transportado</th>
            <th>Valido</th> 
                <c:forEach var="entrega" items="${entregas}">
                <tr>
                    <td>
                        ${entrega.periodo}
                    </td>
                    <td>
                        ${entrega.camioneta}
                    </td>
                    <td>
                        ${entrega.kilometrosRecorridos}
                    </td>
                    <td>
                        ${entrega.pesoTransportado}
                    </td>
                    <td>
                        ${entrega.valido}
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
    </div>
</html>

