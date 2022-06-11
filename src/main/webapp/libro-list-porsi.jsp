<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<style type="text/css" media="screen">
body {
	background-image: linear-gradient(rgba(255, 255, 255, 0.5), rgba(255, 255, 255, 0.5)),
		url("https://static.guiaongs.org/wp-content/uploads/2016/07/librer%C3%ADasolidaria_FB.jpg");
	background-size: cover;
	background-attachment: fixed;
}

header {
	background-color: #dee2e6;
}

li {
	margin-right: 20px;
}

.titulo {
	background-color: #dee2e6;
	border-radius: 10px;
	width: 500px;
	margin: auto;
}

h3 {
	color: #003049;
	margin: 30px;
	padding: 10px;
}

.btn {
	background-color: #dee2e6;
	color: #003049;
	border: 2px solid #495057;
}

.btn:hover {
	background-color: #00a8e8;
	color: white;
	border: 2px solid #495057;
}

th {
	background: #adb5bd;
	text-align: center;
	color: #003049;
}

tbody tr:nth-child(odd){
	background: #e9ecef;
}

tbody tr:nth-child(even){
	background: #dee2e6;
}

tbody, thead{
	border: 2px solid #000;
}

.iconos {
	text-align: center;
}

.editar, .eliminar {
	width: 30px;
}

.eliminar {
	margin-left: 20px;
}
</style>

<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark">
			<div>
				<a href="<%=request.getContextPath()%>/listlibro"
					class="navbar-brand"><font color="#003049">ADMINISTRAR LIBROS</font></a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/newlibro"
					class="nav-link"><font color="#003049">INGRESAR LIBRO</font></a></li>
			</ul>
			<div>
				<a href="<%=request.getContextPath()%>/listusuario"
					class="navbar-brand"> <font color="#003049">ADMINISTRAR USUARIOS </font></a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/newusuario"
					class="nav-link"><font color="#003049">INGRESAR USUARIO</font></a></li>
			</ul>
			<div>
				<a href="<%=request.getContextPath()%>/listprestamo"
					class="navbar-brand"> <font color="#003049">ADMINISTRAR PRESTAMOS</font></a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/newprestamo"
					class="nav-link"><font color="#003049">INGRESAR PRESTAMO</font></a></li>
			</ul>
		</nav>
	</header>
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
		<div class="container">
			<div class='titulo'>
				<h3 class="text-center">LISTA DE LIBROS</h3>
			</div>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/newlibro" class="btn">AÑADIR</a>
			</div>
			<br>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>NOMBRE</th>
						<th>AUTOR</th>
						<th>No PÁGINAS</th>
						<th>EDICIÓN</th>
						<th>ACCIONES</th>
					</tr>
				</thead>
				<tbody>
					<!-- for (Todo todo: todos) { -->
					<c:forEach var="libro" items="${listLibro}">
						<tr>
							<td><c:out value="${libro.id}" /></td>
							<td><c:out value="${libro.nombre}" /></td>
							<td><c:out value="${libro.autor}" /></td>
							<td><c:out value="${libro.num_pag}" /></td>
							<td><c:out value="${libro.edicion}" /></td>
							<td class='iconos'><a href="editlibro?id=<c:out value='${libro.id}' />"><img
									class="editar"
									src="https://cdn-icons-png.flaticon.com/512/1160/1160515.png"></a>
								<a href="deletelibro?id=<c:out value='${libro.id}' />"><img
									class="eliminar"
									src="https://cdn-icons-png.flaticon.com/512/1214/1214926.png"></a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>