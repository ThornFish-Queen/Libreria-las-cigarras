<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<%
HttpSession sesion = request.getSession();
String id;
String nombre;

if (sesion.getAttribute("nombre") != null && sesion.getAttribute("password") != null
		&& sesion.getAttribute("id_rol").equals("1")) {
	id = sesion.getAttribute("id").toString();
	nombre = sesion.getAttribute("nombre").toString();
} else {
%><script type="text/javascript">
	location.replace("login.jsp")
</script>
<%
}
%>
<html>
<head>
<title>User Management Application</title>
</head>
<style type="text/css" media="screen">

* {
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		"Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji",
		"Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
	line-height: 1.15;
	margin: 0px;
	padding: 0px;
}

body {
	background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)),
		url("https://static.guiaongs.org/wp-content/uploads/2016/07/librer%C3%ADasolidaria_FB.jpg");
	background-size: cover;
	background-attachment: fixed;
}

.header {
	background-color: #ced4da;
	display: flex;
	justify-content: space-between;
	align-items:center;
	padding: 0.1rem 0;
	position: fixed;
	top:0;
	left:0;
	right:0;	
}

a {
	text-decoration: none;
}

.logo{
	margin-left:80px;
	width: 250px;
}

.flechita {
	width: 10px;
	margin-left: 10px;
}

.cerrar {
	width: 40px;
	margin-right: 25px;
}

.header a{
	font-size: 20px;
	color: #003049;
}

ul, ol {
	list-style: none;
}

.nav {
	margin-right: -400px;
}

.nav li a {
	padding: 0px 30px;
	display: block;
	transition: all 0.4s linear;
}

.nav li a:hover {
	color: #00a8e8;
}

.nav>li {
	float: right;
}

.nav li ul {
	padding: 24px 0px;
	display: none;
	position: absolute;
}

.nav li ul a {
	background-color: #ced4da;
	padding: 10px 10px;
	margin-left: 10px;
	font-size: 13px;
}

.nav li:hover>ul {
	display: block;
}

.nav li ul a:hover {
	background-color: #adb5bd;
	color: #003049;
}

.row{
	margin-top:80px;
	display: flex;
}

.titulo {
	background-color: #dee2e6;
	border-radius: 10px;
	width: 500px;
	margin: auto;
}

h3 {
	text-align: center;
	color: #003049;
	margin-top: 50px;
	margin-bottom: 30px;
	padding: 11px;
	font-size: 1.75rem;
	font-weight: 500;
}

.btn {
	background-color: #dee2e6;
	color: #003049;
	border: 2px solid #000;
	display: inline-block;
	padding: 0.475rem 0.75rem;
	margin-left: 160px;
	font-size: 1rem;
	border-radius: 0.5rem;
}

.btn:hover {
	background-color: #00a8e8;
	color: white;
	border: 2px solid #000;
}

.container {
	margin:auto;
	width: 100%;
}

table {
	margin:auto;
	width: 80%;
	margin-bottom: 1rem;
	border-collapse: collapse;
}

table td, table th {
	padding: 0.75rem;
}

th {
	background: #adb5bd;
	text-align: center;
	color: #003049;
	font-weight: bold;
}

tbody tr:nth-child(odd) {
	background: #e9ecef;
}

tbody tr:nth-child(even) {
	background: #dee2e6;
}

tbody, thead {
	border: 2px solid #000;
}

thead {
	border-bottom: 2px solid #dee2e6;
}

.colum{
	text-align: center;
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
	<header class="header">
		<img src="https://i.postimg.cc/NGmFWQ1K/Logo.png" class="logo">
		<ul class="nav">
			<li><a href="">PRESTAMOS<img class="flechita"
					src="https://cdn-icons-png.flaticon.com/512/25/25224.png"></a>
				<ul>
					<li><a href="<%=request.getContextPath()%>/listprestamo">ADMINISTRAR
							PRESTAMOS</a></li>
					<li><a href="<%=request.getContextPath()%>/newprestamo">INGRESAR
							PRESTAMOS</a></li>
				</ul></li>
			<li><a href="">USUARIOS<img class="flechita"
					src="https://cdn-icons-png.flaticon.com/512/25/25224.png"></a>
				<ul>
					<li><a href="<%=request.getContextPath()%>/listusuario">ADMINISTRAR
							USUARIOS</a></li>
					<li><a href="<%=request.getContextPath()%>/newusuario">INGRESAR
							USUARIOS</a></li>
				</ul></li>
			<li><a href="">LIBROS<img class="flechita"
					src="https://cdn-icons-png.flaticon.com/512/25/25224.png"></a>
				<ul>
					<li><a href="<%=request.getContextPath()%>/listlibro">ADMINISTRAR
							LIBROS</a></li>
					<li><a href="<%=request.getContextPath()%>/newlibro">INGRESAR
							LIBRO</a></li>
				</ul></li>
		</ul>
		<a href="cerrarsesion"><img class="cerrar"
			src="https://cdn-icons-png.flaticon.com/512/6807/6807188.png"></a>
	</header>
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
		<div class="container">
			<div class='titulo'>
				<h3 class="text-center">LISTA DE PRESTAMOS</h3>
			</div>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/newprestamo" class="btn">AÑADIR</a>
			</div>
			<br>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>USUARIO</th>
						<th>LIBRO</th>
						<th>FECHA PRESTAMO</th>
						<th>FECHA ENTREGA</th>
						<th>ACCIONES</th>
					</tr>
				</thead>
				<tbody>
					<!-- for (Todo todo: todos) { -->
					<c:forEach var="prestamo" items="${listPrestamo}">
						<tr>
							<td class="colum"><c:out value="${prestamo.id_prestamo}" /></td>
							<td><c:out value="${prestamo.nombre_usuario}" /></td>
							<td><c:out value="${prestamo.nombre_libro}" /></td>
							<td class="colum"><c:out value="${prestamo.fecha_prestamo}" /></td>
							<td class="colum"><c:out value="${prestamo.fecha_entrega}" /></td>
							<td class='iconos'><a
								href="editprestamo?id_prestamo=<c:out value='${prestamo.id_prestamo}' />"><img
									class="editar"
									src="https://cdn-icons-png.flaticon.com/512/1160/1160515.png"></a>
								<a
								href="deleteprestamo?id_prestamo=<c:out value='${prestamo.id_prestamo}' />"><img
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