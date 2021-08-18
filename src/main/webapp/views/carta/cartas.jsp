<%--
  Created by IntelliJ IDEA.
  User: Araceli
  Date: 17/08/2021
  Time: 05:11 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Cartas</title>
</head>
<title>Listado de videojuegos</title>
<link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${context}/assets/dist/css/styles.css">
<link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/tabla.css">
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
<body>
<table id="container" class="table table-info table-striped border-">
    <thead class="table-danger">
    <tr id="tabla">
        <th>#</th>
        <th>Nombre</th>
        <th>Fecha</th>
        <th>Estado</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>

<%-- MODAL REGISTRO --%>
<div id="main" >
    <div class="abs-center">
        <form center>
            <input type="text" id="nombre" name="nombre" placeholder="Nombre"/>
            <input type="date" id="fecha"  name="fecha" placeholder="Fecha de Estreno"/>
            <label>Tipo</label>
            <select id="type_id"></select>
            <div style="display: block;width: 70%;margin:10px auto">
                <button type="button" id="btn-registrar" class="btn btn-success"  /><i class="fas fa-check-square"></i> Registrar</button>
                <button type="button" class="btn btn-danger " data-bs-dismiss="modal"><i class="fas fa-times"></i> Cerrar</button>
                <div id="status"></div>
            </div>
        </form>
    </div>
</div>

<script src="${context}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="${context}/assets/dist/js/main.js"></script>
</body>
</html>
