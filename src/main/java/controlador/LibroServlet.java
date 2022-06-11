package controlador;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LibroDAO;
import modelo.Libro;
import modelo.Usuario;
import modelo.Prestamo;
import modelo.Rol;

@WebServlet("/")
public class LibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibroDAO libroDAO;

	public void init() {
		libroDAO = new LibroDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/login":
				login(request, response);
				break;
			case "/registrarse":
				registrarse(request, response);
				break;
			case "/cerrarsesion":
				cerrarSesion(request, response);
				break;
			case "/newlibro":
				showNewLibroForm(request, response);
				break;
			case "/newusuario":
				showNewUsuarioForm(request, response);
				break;
			case "/newprestamo":
				showNewPrestamoForm(request, response);
				break;
			case "/insertlibro":
				insertLibro(request, response);
				break;
			case "/insertusuario":
				insertUsuario(request, response);
				break;
			case "/insertprestamo":
				insertPrestamo(request, response);
				break;
			case "/deletelibro":
				deleteLibro(request, response);
				break;
			case "/deleteusuario":
				deleteUsuario(request, response);
				break;
			case "/deleteprestamo":
				deletePrestamo(request, response);
				break;
			case "/editlibro":
				showEditLibroForm(request, response);
				break;
			case "/editusuario":
				showEditUsuarioForm(request, response);
				break;
			case "/editprestamo":
				showEditPrestamoForm(request, response);
				break;
			case "/updatelibro":
				updateLibro(request, response);
				break;
			case "/updateusuario":
				updateUsuario(request, response);
				break;
			case "/updateprestamo":
				updatePrestamo(request, response);
				break;
			case "/listlibro":
				listLibro(request, response);
				break;
			case "/listusuario":
				listUsuario(request, response);
				break;
			case "/listprestamo":
				listPrestamo(request, response);
				break;
			case "/listlibrocli":
				listLibroCliente(request, response);
				break;
			case "/listprestamocli":
				listPrestamoCliente(request, response);
				break;
			case "/newprestamocli":
				showNewPrestamoFormCliente(request, response);
				break;
			case "/insertprestamocli":
				insertPrestamoCliente(request, response);
				break;
			case "/editprestamocli":
				showEditPrestamoFormCliente(request, response);
				break;
			case "/updateprestamocli":
				updatePrestamoCliente(request, response);
				break;
			case "/infousuariocli":
				showMiPerfil(request, response);
				break;
			case "/editusuariocli":
				showEditMiPerfil(request, response);
				break;
			case "/updateusuariocli":
				updateUsuarioCliente(request, response);
				break;
			case "/editcontracli":
				showEditContra(request, response);
				break;
			case "/updatecontracli":
				updateContraCliente(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	// LIBROS
	private void listLibro(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Libro> listLibro = libroDAO.selectAllLibro();
		request.setAttribute("listLibro", listLibro);
		RequestDispatcher dispatcher = request.getRequestDispatcher("libro-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewLibroForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("libro-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditLibroForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Libro existingLibro = libroDAO.selectLibro(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("libro-form.jsp");
		request.setAttribute("libro", existingLibro);
		dispatcher.forward(request, response);
	}

	private void insertLibro(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		String nombre = request.getParameter("nombre");
		String autor = request.getParameter("autor");
		int num_pag = Integer.parseInt(request.getParameter("num_pag"));
		int edicion = Integer.parseInt(request.getParameter("edicion"));
		Libro newLibro = new Libro(nombre, autor, num_pag, edicion);
		libroDAO.insertLibro(newLibro);
		response.sendRedirect("listlibro");
	}

	private void updateLibro(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String autor = request.getParameter("autor");
		int num_pag = Integer.parseInt(request.getParameter("num_pag"));
		int edicion = Integer.parseInt(request.getParameter("edicion"));
		Libro book = new Libro(id, nombre, autor, num_pag, edicion);
		libroDAO.updateLibro(book);
		response.sendRedirect("listlibro");
	}

	private void deleteLibro(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		libroDAO.deleteLibro(id);
		response.sendRedirect("listlibro");
	}

	// USUARIOS
	private void listUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Usuario> listUsuario = libroDAO.selectAllUsuario();
		request.setAttribute("listUsuario", listUsuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewUsuarioForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Rol> listaRol = libroDAO.selectIdNombreRol();
		request.setAttribute("listaRol", listaRol);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditUsuarioForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession sesion = request.getSession();
		sesion.setAttribute("id_vieja", id);
		Usuario existingUsuario = libroDAO.selectUsuario(id);
		request.setAttribute("usuario", existingUsuario);
		List<Rol> listaRol = libroDAO.selectIdNombreRol();
		request.setAttribute("listaRol", listaRol);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String password = convertirSHA256(request.getParameter("password"));
		String correo = request.getParameter("correo");
		String direccion = request.getParameter("direccion");
		String celular = request.getParameter("celular");
		int id_rol = Integer.parseInt(request.getParameter("id_rol"));
		Usuario newUsuario = new Usuario(id, nombre, password, correo, direccion, celular, id_rol);
		libroDAO.insertUsuario(newUsuario);
		response.sendRedirect("listusuario");
	}

	private void updateUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		HttpSession sesion = request.getSession();
		String id_vieja = sesion.getAttribute("id_vieja").toString();
		int id = Integer.parseInt(id_vieja);
		int id_nueva = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		String correo = request.getParameter("correo");
		String direccion = request.getParameter("direccion");
		String celular = request.getParameter("celular");
		int id_rol = Integer.parseInt(request.getParameter("id_rol"));
		Usuario book = new Usuario(id, nombre, password, correo, direccion, celular, id_rol, id_nueva);
		libroDAO.updateUsuario(book);
		response.sendRedirect("listusuario");
	}

	private void deleteUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		libroDAO.deleteUsuario(id);
		response.sendRedirect("listusuario");
	}

	// PRESTAMO
	private void listPrestamo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Prestamo> listPrestamo = libroDAO.selectAllPrestamo();
		request.setAttribute("listPrestamo", listPrestamo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("prestamo-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewPrestamoForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Libro> listaLibro = libroDAO.selectIdNombreLibro();
		request.setAttribute("listaLibro", listaLibro);
		List<Usuario> listaUsuario = libroDAO.selectIdNombreUsuario();
		request.setAttribute("listaUsuario", listaUsuario);
		String minfecha = libroDAO.fechaMinima();
		request.setAttribute("minfecha", minfecha);
		String maxfecha = libroDAO.fechaMaxima();
		request.setAttribute("maxfecha", maxfecha);
		RequestDispatcher dispatcher = request.getRequestDispatcher("prestamo-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditPrestamoForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id_prestamo = Integer.parseInt(request.getParameter("id_prestamo"));
		Prestamo existingPrestamo = libroDAO.selectPrestamo(id_prestamo);
		request.setAttribute("prestamo", existingPrestamo);
		List<Libro> listaLibro = libroDAO.selectIdNombreLibro();
		request.setAttribute("listaLibro", listaLibro);
		List<Usuario> listaUsuario = libroDAO.selectIdNombreUsuario();
		request.setAttribute("listaUsuario", listaUsuario);
		String minfecha = libroDAO.fechaMinima();
		request.setAttribute("minfecha", minfecha);
		String maxfecha = libroDAO.fechaMaxima();
		request.setAttribute("maxfecha", maxfecha);
		RequestDispatcher dispatcher = request.getRequestDispatcher("prestamo-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertPrestamo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		int id_libro = Integer.parseInt(request.getParameter("id_libro"));
		String fecha_entrega = request.getParameter("fecha_entrega");
		Prestamo newPrestamo = new Prestamo(id_usuario, id_libro, fecha_entrega);
		libroDAO.insertPrestamo(newPrestamo);
		response.sendRedirect("listprestamo");
	}

	private void updatePrestamo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		int id_prestamo = Integer.parseInt(request.getParameter("id_prestamo"));
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		int id_libro = Integer.parseInt(request.getParameter("id_libro"));
		String fecha_entrega = request.getParameter("fecha_entrega");
		Prestamo book = new Prestamo(id_prestamo, id_usuario, id_libro, fecha_entrega);
		libroDAO.updatePrestamo(book);
		response.sendRedirect("listprestamo");
	}

	private void deletePrestamo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id_prestamo = Integer.parseInt(request.getParameter("id_prestamo"));
		libroDAO.deletePrestamo(id_prestamo);
		response.sendRedirect("listprestamo");
	}

	// LOGIN
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String nombre = request.getParameter("nombre");
		String pass = convertirSHA256(request.getParameter("pass"));

		Usuario usuario = libroDAO.loginByNombre(nombre);
		if (usuario != null) {
			String id = String.valueOf(usuario.getId());
			String password = usuario.getPassword();
			String correo = usuario.getCorreo();
			String direccion = usuario.getDireccion();
			String celular = usuario.getCelular();
			String id_rol = String.valueOf(usuario.getId_rol());

			HttpSession sesion = request.getSession();
			sesion.setAttribute("id", id);
			sesion.setAttribute("nombre", nombre);
			sesion.setAttribute("password", password);
			sesion.setAttribute("correo", correo);
			sesion.setAttribute("direccion", direccion);
			sesion.setAttribute("celular", celular);
			sesion.setAttribute("id_rol", id_rol);
			sesion.setAttribute("id_vieja", "");

			if (pass.equals(password) && id_rol.equals("1")) {
				listLibro(request, response);
			} else if (pass.equals(password) && id_rol.equals("2")) {
				response.sendRedirect("infousuariocli");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	//REGISTRARSE
	private void registrarse(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String password = convertirSHA256(request.getParameter("password"));
		String correo = request.getParameter("correo");
		String direccion = request.getParameter("direccion");
		String celular = request.getParameter("celular");
		int id_rol = Integer.parseInt(request.getParameter("id_rol"));
		String id_s = String.valueOf(id);
		String id_rol_s = String.valueOf(id_rol);
		Usuario newUsuario = new Usuario(id, nombre, password, correo, direccion, celular, id_rol);
		libroDAO.insertUsuario(newUsuario);
		Usuario usuario = libroDAO.loginByNombre(nombre);
		if (usuario != null) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("id", id_s);
			sesion.setAttribute("nombre", nombre);
			sesion.setAttribute("password", password);
			sesion.setAttribute("correo", correo);
			sesion.setAttribute("direccion", direccion);
			sesion.setAttribute("celular", celular);
			sesion.setAttribute("id_rol", id_rol_s);
			response.sendRedirect("infousuariocli");
		} else {
			response.sendRedirect("registrarse.jsp");
		}
	}

	// CERRAR SESION
	public void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		sesion.invalidate();
		response.sendRedirect("login.jsp");
	}

	// ENCRIPTAR CONTRASEÑA
	public String convertirSHA256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();

		for (byte b : hash) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}

	// CLIENTE LIBROS
	private void listLibroCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Libro> listLibro = libroDAO.selectAllLibro();
		request.setAttribute("listLibro", listLibro);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente/libro-list-cli.jsp");
		dispatcher.forward(request, response);
	}

	// CLIENTE PRESTAMOS
	private void listPrestamoCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession sesion = request.getSession();
		String id1 = sesion.getAttribute("id").toString();
		int id = Integer.parseInt(id1);
		List<Prestamo> listPrestamo = libroDAO.selectPrestamoByUsuario(id);
		request.setAttribute("listPrestamo", listPrestamo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente/prestamo-list-cli.jsp");
		dispatcher.forward(request, response);
	}

	// FORM NUEVO PRESTAMO PARA CLIENTE
	private void showNewPrestamoFormCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Libro> listaLibro = libroDAO.selectIdNombreLibro();
		request.setAttribute("listaLibro", listaLibro);
		String minfecha = libroDAO.fechaMinima();
		request.setAttribute("minfecha", minfecha);
		String maxfecha = libroDAO.fechaMaxima();
		request.setAttribute("maxfecha", maxfecha);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente/prestamo-form-cli.jsp");
		dispatcher.forward(request, response);
	}

	// INSERTAR NUEVO PRESTAMO
	private void insertPrestamoCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		int id_libro = Integer.parseInt(request.getParameter("id_libro"));
		String fecha_entrega = request.getParameter("fecha_entrega");
		Prestamo newPrestamo = new Prestamo(id_usuario, id_libro, fecha_entrega);
		libroDAO.insertPrestamo(newPrestamo);
		response.sendRedirect("listprestamocli");
	}

	// FORM EDIT PRESTAMO PARA CLIENTE
	private void showEditPrestamoFormCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id_prestamo = Integer.parseInt(request.getParameter("id_prestamo"));
		Prestamo existingPrestamo = libroDAO.selectPrestamo(id_prestamo);
		request.setAttribute("prestamo", existingPrestamo);
		String minfecha = libroDAO.fechaMinima();
		request.setAttribute("minfecha", minfecha);
		String maxfecha = libroDAO.fechaMaxima();
		request.setAttribute("maxfecha", maxfecha);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente/prestamo-form-cli.jsp");
		dispatcher.forward(request, response);
	}

	// UPDATE PRESTAMO CLIENTE
	private void updatePrestamoCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		int id_prestamo = Integer.parseInt(request.getParameter("id_prestamo"));
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		int id_libro = Integer.parseInt(request.getParameter("id_libro"));
		String fecha_entrega = request.getParameter("fecha_entrega");
		Prestamo book = new Prestamo(id_prestamo, id_usuario, id_libro, fecha_entrega);
		libroDAO.updatePrestamo(book);
		response.sendRedirect("listprestamocli");
	}

	// MOSTRAR INFO DEL CLIENTE
	private void showMiPerfil(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		String id_cli = sesion.getAttribute("id").toString();
		int id_c = Integer.parseInt(id_cli);
		Usuario usuario = libroDAO.selectUsuario(id_c);
		if (usuario != null) {
			String id = String.valueOf(usuario.getId());
			String nombre = usuario.getNombre();
			String password = usuario.getPassword();
			String correo = usuario.getCorreo();
			String direccion = usuario.getDireccion();
			String celular = usuario.getCelular();
			String id_rol = String.valueOf(usuario.getId_rol());

			sesion.setAttribute("id", id);
			sesion.setAttribute("nombre", nombre);
			sesion.setAttribute("password", password);
			sesion.setAttribute("correo", correo);
			sesion.setAttribute("direccion", direccion);
			sesion.setAttribute("celular", celular);
			sesion.setAttribute("id_rol", id_rol);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente/usuario-info-cli.jsp");
		dispatcher.forward(request, response);
	}

	// EDITAR INFO DEL CLIENTE
	private void showEditMiPerfil(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente/usuario-edit-cli.jsp");
		dispatcher.forward(request, response);
	}

	// ACTUALIZAR INFO CLIENTE
	private void updateUsuarioCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		HttpSession sesion = request.getSession();
		String id_cli = sesion.getAttribute("id").toString();
		int id = Integer.parseInt(id_cli);
		int id_nueva = Integer.parseInt(request.getParameter("id"));
		sesion.setAttribute("id", id_nueva);
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		String correo = request.getParameter("correo");
		String direccion = request.getParameter("direccion");
		String celular = request.getParameter("celular");
		int id_rol = Integer.parseInt(request.getParameter("id_rol"));
		Usuario book = new Usuario(id, nombre, password, correo, direccion, celular, id_rol, id_nueva);
		libroDAO.updateUsuarioCliente(book);
		response.sendRedirect("infousuariocli");
	}

	// EDITAR CONTRA DEL CLIENTE
	private void showEditContra(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cliente/usuario-edit-contra-cli.jsp");
		dispatcher.forward(request, response);
	}

	// ACTUALIZAR CONTRA CLIENTE
	private void updateContraCliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// convertir caracteres a UTF-8
		request.setCharacterEncoding("UTF-8");
		HttpSession sesion = request.getSession();
		String pass = sesion.getAttribute("password").toString();
		String pass2 = convertirSHA256(request.getParameter("password_old"));
		
		if (pass.equals(pass2)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String password = convertirSHA256(request.getParameter("password"));
			sesion.setAttribute("password", password);
			int id_rol = Integer.parseInt(request.getParameter("id_rol"));
			Usuario book = new Usuario(id, password, id_rol);
			libroDAO.updateContraCli(book);
			response.sendRedirect("infousuariocli");
		} else {
			response.sendRedirect("editcontracli");
		}
	}

}