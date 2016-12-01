
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
            <th>Id</th>
            <th>Codigo</th>
            <th>Fecha de entrega</th>
            <th>Importe de la entrega</th>
            <th>Id del chofer</th>
            <th>Id de la camioneta</th>
            <th>Ids de el/los paquete/s</th>    
                <c:forEach var="entrega" items="${entregas}">
                <tr>
                    <td>
                        ${entrega.id}
                    </td>
                    <td>
                        ${entrega.codigo}
                    </td>
                    <td>
                        ${entrega.fechaEntrega}
                    </td>
                    <td>
                        ${entrega.importeEntrega}
                    </td>
                    <td>
                        ${entrega.idCamioneta}
                    </td>
                    <td>
                        ${entrega.idChofer}
                    </td>
                    <td>
                        ${entrega.listaPaquetesString}
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br/>
    </div>
</html>
