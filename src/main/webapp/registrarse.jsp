<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

.container {
	background-color: #dee2e6;
	border-radius: 20px;
	width: 35%;
	margin: auto;
	margin-top: 130px;
	color: #003049;
}

h2 {
	text-align: center;
	padding-top: 15px;
}

.logo {
	width: 270px;
}

input {
	display: block;
	width: 85%;
	margin: auto;
	padding: 0.375rem 0.75rem;
	font-size: 1rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
	border: 1px solid #ced4da;
	border-radius: 0.5rem;
	transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
	padding: 0.375rem 0.75rem;
}

input:focus {
	border-color: #80bdff;
	outline: 0;
}

label {
	display: inline-block;
	margin-bottom: 0.7rem;
	margin-left: 1.8rem;
}

.label {
	margin-top: 1.2rem;
}

.redi a {
	text-decoration: none;
}

.redi {
	text-align: center;
	margin-top: 2rem;
	padding-bottom: 2rem;
}

.registrarse {
	background-color: #003049;
	color: white;
	border: 2px solid #000;
	padding: 0.57rem 0.75rem;
	font-size: 1rem;
	border-radius: 0.5rem;
	cursor: pointer;
}

.registrarse:focus {
	background-color: #00a8e8;
	color: white;
	border: 2px solid #000;
}

.ingresar {
	background-color: #003049;
	color: white;
	width: 110px;
	border: 2px solid #000;
	display: inline-block;
	padding: 0.475rem 0.75rem;
	margin-left: 40px;
	font-size: 1rem;
	border-radius: 0.5rem;
	cursor: pointer;
}

.ingresar:focus {
	background-color: #00a8e8;
	color: white;
	border: 2px solid #000;
}
</style>
<body>
	<div class="container">
		<form action="registrarse" method="post">
			<h2>
				<img src="https://i.postimg.cc/PxwyJc5t/Logo.png" class="logo">
			</h2>
			<label>CEDULA</label> <input type="number" name="id"
				required="required"> <label class="label">NOMBRE</label> <input
				type="text" name="nombre" required="required"> <label
				class="label">CONTRASEÑA</label> <input type="password"
				name="password" required="required"> <label class="label">CORREO</label>
			<input type="email" name="correo" required="required"> <label
				class="label">DIRECCION</label> <input type="text" name="direccion"
				required="required"> <label class="label">CELULAR</label> <input
				type="number" name="celular" required="required"> <input
				type="hidden" name="id_rol" value="2">
			<div class="redi">
				<a href="login.jsp" class="registrarse">Iniciar Sesion</a> <input
					type="submit" class="ingresar" value="Registrarse"></input>
			</div>
		</form>
	</div>
</body>
</html>