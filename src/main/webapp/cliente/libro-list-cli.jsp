<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<%
HttpSession sesion = request.getSession();
String id;
String nombre;
String password;
String correo;
String direccion;
String celular;
String id_rol;

if (sesion.getAttribute("nombre") != null && sesion.getAttribute("password") != null
		&& sesion.getAttribute("id_rol").equals("2")) {
	id = sesion.getAttribute("id").toString();
	nombre = sesion.getAttribute("nombre").toString();
	password = sesion.getAttribute("password").toString();
	correo = sesion.getAttribute("correo").toString();
	direccion = sesion.getAttribute("direccion").toString();
	celular = sesion.getAttribute("celular").toString();
	id_rol = sesion.getAttribute("id_rol").toString();
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
<link rel="stylesheet" href="css/estilo.css" type="text/css">
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
	align-items: center;
	padding: 0.1rem 0;
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
}

a {
	text-decoration: none;
}

.logo {
	margin-left: 80px;
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

.header a {
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
	margin-left: 25px;
	font-size: 13px;
}

.nav li:hover>ul {
	display: block;
}

.nav li ul a:hover {
	background-color: #adb5bd;
	color: #003049;
}

.row {
	margin-top: 80px;
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

.container {
	margin: auto;
	width: 100%;
}

table {
	margin: auto;
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

.colum {
	text-align: center;
}
</style>
<body>
	<header class="header">
		<img src="https://i.postimg.cc/NGmFWQ1K/Logo.png" class="logo">
		<ul class="nav">
			<li><a href="">PRESTAMOS<img class="flechita"
					src="https://cdn-icons-png.flaticon.com/512/25/25224.png"></a>
				<ul>
					<li><a href="<%=request.getContextPath()%>/listprestamocli">MIS
							PRESTAMOS</a></li>
					<li><a href="<%=request.getContextPath()%>/newprestamocli">NUEVO
							PRESTAMO</a></li>
				</ul></li>
			<li><a href="<%=request.getContextPath()%>/infousuariocli">MI
					USUARIO<img class="flechita"
					src="https://cdn-icons-png.flaticon.com/512/25/25224.png">
			</a></li>
			<li><a href="<%=request.getContextPath()%>/listlibrocli">LIBROS<img
					class="flechita"
					src="https://cdn-icons-png.flaticon.com/512/25/25224.png"></a></li>
		</ul>
		<a href="cerrarsesion"><img class="cerrar"
			src="https://cdn-icons-png.flaticon.com/512/6807/6807188.png"></a>
	</header>
	<div class="row">
		<div class="container">
			<div class='titulo'>
				<h3>LISTA DE LIBROS</h3>
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
					</tr>
				</thead>
				<tbody>
					<!-- for (Todo todo: todos) { -->
					<c:forEach var="libro" items="${listLibro}">
						<tr>
							<td class="colum"><c:out value="${libro.id}" /></td>
							<td><c:out value="${libro.nombre}" /></td>
							<td><c:out value="${libro.autor}" /></td>
							<td class="colum"><c:out value="${libro.num_pag}" /></td>
							<td class="colum"><c:out value="${libro.edicion}" /></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>