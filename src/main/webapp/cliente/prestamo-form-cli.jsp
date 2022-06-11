<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<%
HttpSession sesion = request.getSession();
String id = null;
String nombre = "";

if (sesion.getAttribute("nombre") != null && sesion.getAttribute("password") != null
		&& sesion.getAttribute("id_rol").equals("2")) {
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

header {
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

.logo {
	margin-left: 80px;
	width: 250px;
}

a {
	text-decoration: none;
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
	margin-left:25px;
	font-size: 13px;
}

.nav li:hover>ul {
	display: block;
}

.nav li ul a:hover {
	background-color: #adb5bd;
	color: #003049;
}

.flechita {
	width: 10px;
	margin-left: 10px;
}

.cerrar {
	width: 40px;
	margin-right: 25px;
}

.contenedor {
	margin-top: 110px;
	width: 100%;
}

.container {
	background-color: #dee2e6;
	border-radius: 20px;
	width: 35%;
	margin: auto;
}

.card-body {
	padding: 2rem;
}

h2 {
	color: #003049;
	text-align: center;
	font-size: 2.1rem;
	font-weight: 500;
	padding-bottom: 15px;
}

fieldset {
	border: 0;
}

.form-group {
	margin-bottom: 1rem;
	color: #003049;
}

.form-control {
	display: block;
	width: 100%;
	padding: 0.375rem 0.75rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	border: 1px solid #ced4da;
	border-radius: 0.5rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
}

.form-control:focus {
	border-color: #80bdff;
	outline: 0;
}

label {
	display: inline-block;
	margin-bottom: 0.5rem;
}

.iconos {
	text-align: center;
}

.regresar, .guardar {
	width: 50px;
}

.boton {
	border: none;
	background: none;
	margin-left: 50px;
	cursor: pointer;
}

.boton:focus {
	outline: none;
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
	<div class="contenedor">
		<div class="container col-md-5">
			<div class="card-body">
				<c:if test="${prestamo != null}">
					<form action="updateprestamocli" method="post">
				</c:if>
				<c:if test="${prestamo == null}">
					<form action="insertprestamocli" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${prestamo != null}">
EDITAR PRESTAMO
</c:if>
						<c:if test="${prestamo == null}">
INGRESAR PRESTAMO
</c:if>
					</h2>
				</caption>
				<c:if test="${prestamo != null}">
					<input type="hidden" name="id_prestamo"
						value="<c:out value='${prestamo.id_prestamo}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>USUARIO</label> <select class="form-control"
						name="id_usuario" required="required">
						<option value=<%out.println(id);%> selected>
							<%
							out.println(nombre);
							%>
						</option>
					</select>
				</fieldset>
				<fieldset class="form-group">
					<label>LIBRO</label> <select class="form-control" name="id_libro"
						required="required">
						<c:choose>
							<c:when test="${prestamo != null}">
								<option value="${prestamo.id_libro}" selected>${prestamo.nombre_libro}</option>
							</c:when>
							<c:otherwise>
								<option value="">Seleccionar</option>
								<c:forEach var="libro" items="${listaLibro}">
									<option value="${libro.id}">${libro.nombre}</option>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</select>
				</fieldset>
				<fieldset class="form-group">
					<label>FECHA ENTREGA</label> <input type="date" required="required"
						value="<c:out value='${prestamo.fecha_entrega}' />"
						class="form-control" name="fecha_entrega"
						min="<%out.print(request.getAttribute("minfecha"));%>"
						max="<%out.print(request.getAttribute("maxfecha"));%>">
				</fieldset>
				<div class="iconos">
					<a href="<%=request.getContextPath()%>/listprestamocli"> <img
						class="regresar"
						src="https://cdn-icons-png.flaticon.com/512/7243/7243569.png"></a>
					<button type="submit" class="boton">
						<img class="guardar"
							src="https://cdn-icons-png.flaticon.com/512/526/526944.png">
					</button>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>