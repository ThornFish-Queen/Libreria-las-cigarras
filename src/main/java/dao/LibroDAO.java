package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import modelo.Libro;
import modelo.Usuario;
import modelo.Prestamo;
import modelo.Rol;

public class LibroDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false";
	private String jdbcUsername = "adminbiblioteca";
	private String jdbcPassword = "Biblioteca@2022";

	// LIBROS
	private static final String INSERT_LIBRO_SQL = "INSERT INTO libro" + " (nombre, autor, num_pag, edicion) VALUES "
			+ " (?, ?, ?, ?);";
	private static final String SELECT_LIBRO_BY_ID = "select id,nombre,autor,num_pag,edicion from libro where id =?";
	private static final String SELECT_ALL_LIBRO = "select * from libro order by nombre";
	private static final String DELETE_LIBRO_SQL = "delete from libro where id = ?;";
	private static final String UPDATE_LIBRO_SQL = "update libro set nombre = ?,autor= ?, num_pag =?,edicion= ? where id = ?;";

	// USUARIOS
	private static final String INSERT_USUARIO_SQL = "INSERT INTO usuario"
			+ " (id,nombre,password, correo, direccion, celular, id_rol) VALUES " + " (?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_USUARIO_BY_ID = "select id,nombre,password,correo,direccion,celular,id_rol from usuario where id =?";
	private static final String SELECT_ALL_USUARIO = "select * from usuario order by nombre";
	private static final String DELETE_USUARIO_SQL = "delete from usuario where id = ?;";
	private static final String UPDATE_USUARIO_SQL = "update usuario set id = ?, nombre = ?,password= ?,correo= ?, direccion =?,celular= ?, id_rol= ? where id = ?;";

	// PRESTAMOS
	private static final String INSERT_PRESTAMO_SQL = "INSERT INTO prestamo"
			+ " (id_usuario, id_libro, fecha_entrega) VALUES " + " (?, ?, ?);";
	private static final String SELECT_PRESTAMO_BY_ID = "select id_prestamo,id_usuario,usuario.nombre,id_libro,libro.nombre,fecha_entrega "
			+ "from prestamo,libro,usuario where id_usuario=usuario.id and id_libro=libro.id and id_prestamo =?";
	private static final String SELECT_ALL_PRESTAMO = "select id_prestamo, usuario.nombre, libro.nombre, fecha_prestamo, fecha_entrega "
			+ "from prestamo, usuario, libro where id_usuario=usuario.id and id_libro=libro.id order by usuario.nombre";
	private static final String DELETE_PRESTAMO_SQL = "delete from prestamo where id_prestamo = ?;";
	private static final String UPDATE_PRESTAMO_SQL = "update prestamo set id_prestamo = ?, id_usuario = ?,id_libro= ?, fecha_entrega =? where id_prestamo = ?;";

	// CONSULTAS LIST
	private static final String SELECT_LIST_LIBRO = "select id,nombre from libro where id not in (select id_libro from prestamo) order by nombre";
	private static final String SELECT_LIST_USUARIO = "select id,nombre from usuario where id_rol=2 order by nombre";
	private static final String SELECT_LIST_ROL = "select id,nombre from rol";

	// CONSULTA PARA LOGIN
	private static final String SELECT_PASS_USUARIO = "select id,nombre,password,correo,direccion,celular,id_rol from usuario where nombre= ?";

	// CONSULTA PARA PRESTAMOS POR NOMBRE USUARIO
	private static final String SELECT_LIST_PRESTAMO_BY_ID = "select id_prestamo,id_usuario,usuario.nombre,id_libro,libro.nombre,fecha_prestamo,fecha_entrega "
			+ "from prestamo,libro,usuario where id_usuario=usuario.id and id_libro=libro.id and id_usuario =? order by fecha_entrega";
	
	//CONSULTA PARA ACTUALIZAR CONTRASEÑA
	private static final String UPDATE_CONTRA_SQL = "update usuario set id = ?,password= ?, id_rol= ? where id = ?;";
	
	public LibroDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			// registra el driver de conexión para la base de datos
			// Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// LIBROS
	public void insertLibro(Libro libro) throws SQLException {
		System.out.println(INSERT_LIBRO_SQL);
		// Se conecta a la base de datos
		try (Connection connection = getConnection();
				// Prepara la sentencia sql a ejecutar con el objeto conexion
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIBRO_SQL)) {
			preparedStatement.setString(1, libro.getNombre());
			preparedStatement.setString(2, libro.getAutor());
			preparedStatement.setInt(3, libro.getNum_pag());
			preparedStatement.setInt(4, libro.getEdicion());
			// System.out.println(preparedStatement);
			// Ejecuta la consulta
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// METODO PARA BUSCAR POR LIBRO (ID)
	public Libro selectLibro(int id) {
		Libro libro = null;
		// Se conecta a la base de datos
		try (Connection connection = getConnection();
				// Prepara la sentencia sql a ejecutar con el objeto conexion
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIBRO_BY_ID);) {
			preparedStatement.setInt(1, id);
			// System.out.println(preparedStatement);
			// Ejecuta la consulta
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre los resultados y los devuelve en el objeto libro
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String autor = rs.getString("autor");
				int num_pag = rs.getInt("num_pag");
				int edicion = rs.getInt("edicion");
				libro = new Libro(id, nombre, autor, num_pag, edicion);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return libro;
	}

	// METODO PARA MOSTRAR TODA LA LISTA DE LIBRO
	public List<Libro> selectAllLibro() {
		// crea el array para mostrar los resultados
		List<Libro> libro = new ArrayList<>();
		// Conecta con la bd
		try (Connection connection = getConnection();
				// Prepara la consulta sql
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIBRO);) {
			// System.out.println(preparedStatement);
			// Ejecuta la consulta y la almacena en un objeto ResultSet
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre el resultado y lo almacena en el objeto libro
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String autor = rs.getString("autor");
				int num_pag = rs.getInt("num_pag");
				int edicion = rs.getInt("edicion");
				libro.add(new Libro(id, nombre, autor, num_pag, edicion));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return libro;
	}

	// METODO PARA BORRAR UN LIBRO DE LA BD
	public boolean deleteLibro(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_LIBRO_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	// METODO PARA ACTUALIZAR DATOS DE UN LIBRO GUARDADO EN BD
	public boolean updateLibro(Libro libro) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LIBRO_SQL);) {
			statement.setString(1, libro.getNombre());
			statement.setString(2, libro.getAutor());
			statement.setInt(3, libro.getNum_pag());
			statement.setInt(4, libro.getEdicion());
			statement.setInt(5, libro.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	// USUARIOS
	public void insertUsuario(Usuario usuario) throws SQLException {
		System.out.println(INSERT_USUARIO_SQL);
		// Se conecta a la base de datos
		try (Connection connection = getConnection();
				// Prepara la sentencia sql a ejecutar con el objeto conexion
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USUARIO_SQL)) {
			preparedStatement.setInt(1, usuario.getId());
			preparedStatement.setString(2, usuario.getNombre());
			preparedStatement.setString(3, usuario.getPassword());
			preparedStatement.setString(4, usuario.getCorreo());
			preparedStatement.setString(5, usuario.getDireccion());
			preparedStatement.setString(6, usuario.getCelular());
			preparedStatement.setInt(7, usuario.getId_rol());
			// System.out.println(preparedStatement);
			// Ejecuta la consulta
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// METODO PARA BUSCAR POR USUARIO (ID)
	public Usuario selectUsuario(int id) {
		Usuario usuario = null;
		// Se conecta a la base de datos
		try (Connection connection = getConnection();
				// Prepara la sentencia sql a ejecutar con el objeto conexion
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USUARIO_BY_ID);) {
			preparedStatement.setInt(1, id);
			// System.out.println(preparedStatement);
			// Ejecuta la consulta
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre los resultados y los devuelve en el objeto usuario
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String password = rs.getString("password");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				String celular = rs.getString("celular");
				int id_rol = rs.getInt("id_rol");
				usuario = new Usuario(id, nombre, password, correo, direccion, celular, id_rol);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return usuario;
	}

	// METODO PARA MOSTRAR TODA LA LISTA DE USUARIOS
	public List<Usuario> selectAllUsuario() {
		// crea el array para mostrar los resultados
		List<Usuario> usuario = new ArrayList<>();
		// Conecta con la bd
		try (Connection connection = getConnection();
				// Prepara la consulta sql
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USUARIO);) {
			// System.out.println(preparedStatement);
			// Ejecuta la consulta y la almacena en un objeto ResultSet
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre el resultado y lo almacena en el objeto usuario
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String password = rs.getString("password");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				String celular = rs.getString("celular");
				int id_rol = rs.getInt("id_rol");
				usuario.add(new Usuario(id, nombre, password, correo, direccion, celular, id_rol));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return usuario;
	}

	// METODO PARA BORRAR UN USUARIO DE LA BD
	public boolean deleteUsuario(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USUARIO_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	// METODO PARA ACTUALIZAR DATOS DE UN USUARIO GUARDADO EN BD
	public boolean updateUsuario(Usuario usuario) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USUARIO_SQL);) {
			statement.setInt(1, usuario.getId_nueva());
			statement.setString(2, usuario.getNombre());
			statement.setString(3, usuario.getPassword());
			statement.setString(4, usuario.getCorreo());
			statement.setString(5, usuario.getDireccion());
			statement.setString(6, usuario.getCelular());
			statement.setInt(7, usuario.getId_rol());
			statement.setInt(8, usuario.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	// PRESTAMOS
	public void insertPrestamo(Prestamo prestamo) throws SQLException {
		System.out.println(INSERT_PRESTAMO_SQL);
		// Se conecta a la base de datos
		try (Connection connection = getConnection();
				// Prepara la sentencia sql a ejecutar con el objeto conexion
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRESTAMO_SQL)) {
			preparedStatement.setInt(1, prestamo.getId_usuario());
			preparedStatement.setInt(2, prestamo.getId_libro());
			preparedStatement.setString(3, prestamo.getFecha_entrega());
			// System.out.println(preparedStatement);
			// Ejecuta la consulta
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	// METODO PARA BUSCAR POR PRESTAMO (ID_PRESTAMO)
	public Prestamo selectPrestamo(int id_prestamo) {
		Prestamo prestamo = null;
		// Se conecta a la base de datos
		try (Connection connection = getConnection();
				// Prepara la sentencia sql a ejecutar con el objeto conexion
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRESTAMO_BY_ID);) {
			preparedStatement.setInt(1, id_prestamo);
			// System.out.println(preparedStatement);
			// Ejecuta la consulta
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre los resultados y los devuelve en el objeto prestamo
			while (rs.next()) {
				int id_usuario = rs.getInt("id_usuario");
				String nombre_usuario = rs.getString("usuario.nombre");
				int id_libro = rs.getInt("id_libro");
				String nombre_libro = rs.getString("libro.nombre");
				String fecha_entrega = rs.getString("fecha_entrega");
				prestamo = new Prestamo(id_prestamo, id_usuario, nombre_usuario, id_libro, nombre_libro, fecha_entrega);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prestamo;
	}

	// METODO PARA MOSTRAR TODA LA LISTA DE PRESTAMOS
	public List<Prestamo> selectAllPrestamo() {
		// crea el array para mostrar los resultados
		List<Prestamo> prestamo = new ArrayList<>();
		// Conecta con la bd
		try (Connection connection = getConnection();
				// Prepara la consulta sql
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRESTAMO);) {
			// System.out.println(preparedStatement);
			// Ejecuta la consulta y la almacena en un objeto ResultSet
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre el resultado y lo almacena en el objeto prestamo
			while (rs.next()) {
				int id_prestamo = rs.getInt("id_prestamo");
				String nombre_usuario = rs.getString("usuario.nombre");
				String nombre_libro = rs.getString("libro.nombre");
				String fecha_prestamo = rs.getString("fecha_prestamo");
				String fecha_entrega = rs.getString("fecha_entrega");
				prestamo.add(new Prestamo(id_prestamo, nombre_usuario, nombre_libro, fecha_prestamo, fecha_entrega));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prestamo;
	}

	// METODO PARA BORRAR UN PRESTAMO DE LA BD
	public boolean deletePrestamo(int id_prestamo) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PRESTAMO_SQL);) {
			statement.setInt(1, id_prestamo);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	// METODO PARA ACTUALIZAR DATOS DE UN PRESTAMOS GUARDADO EN BD
	public boolean updatePrestamo(Prestamo prestamo) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRESTAMO_SQL);) {
			statement.setInt(1, prestamo.getId_prestamo());
			statement.setInt(2, prestamo.getId_usuario());
			statement.setInt(3, prestamo.getId_libro());
			statement.setString(4, prestamo.getFecha_entrega());
			statement.setInt(5, prestamo.getId_prestamo());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	// OPTION LIBRO
	public List<Libro> selectIdNombreLibro() {
		// crea el array para mostrar los resultados
		List<Libro> libro = new ArrayList<>();
		// Conecta con la bd
		try (Connection connection = getConnection();
				// Prepara la consulta sql
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIST_LIBRO);) {
			// System.out.println(preparedStatement);
			// Ejecuta la consulta y la almacena en un objeto ResultSet
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre el resultado y lo almacena en el objeto libro
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				libro.add(new Libro(id, nombre));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return libro;
	}

	// OPTION USUARIO
	public List<Usuario> selectIdNombreUsuario() {
		// crea el array para mostrar los resultados
		List<Usuario> usuario = new ArrayList<>();
		// Conecta con la bd
		try (Connection connection = getConnection();
				// Prepara la consulta sql
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIST_USUARIO);) {
			// System.out.println(preparedStatement);
			// Ejecuta la consulta y la almacena en un objeto ResultSet
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre el resultado y lo almacena en el objeto usuario
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				usuario.add(new Usuario(id, nombre));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return usuario;
	}

	// OPTION ROL
	public List<Rol> selectIdNombreRol() {
		// crea el array para mostrar los resultados
		List<Rol> rol = new ArrayList<>();
		// Conecta con la bd
		try (Connection connection = getConnection();
				// Prepara la consulta sql
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIST_ROL);) {
			// System.out.println(preparedStatement);
			// Ejecuta la consulta y la almacena en un objeto ResultSet
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre el resultado y lo almacena en el objeto usuario
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				rol.add(new Rol(id, nombre));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return rol;
	}

	// FECHA MINIMA
	public String fechaMinima() {
		LocalDate date_of_today = LocalDate.now();
		String minfecha = "" + date_of_today;
		return minfecha;
	}

	// FECHA MAXIMA
	public String fechaMaxima() {
		LocalDate date_of_today = LocalDate.now();
		Period periodo = Period.ofDays(30);
		String maxfecha = "" + date_of_today.plus(periodo);
		return maxfecha;
	}

	// COMPROBAR CONTRASEÑA LOGIN
	public Usuario loginByNombre(String nombre) {
		// crea el array para mostrar los resultados
		Usuario usuario = null;
		// Conecta con la bd
		try (Connection connection = getConnection();
				// Prepara la consulta sql
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PASS_USUARIO);) {
			preparedStatement.setString(1, nombre);
			// System.out.println(preparedStatement);
			// Ejecuta la consulta y la almacena en un objeto ResultSet
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre el resultado y lo almacena en el objeto usuario
			while (rs.next()) {
				int id = rs.getInt("id");
				String password = rs.getString("password");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				String celular = rs.getString("celular");
				int id_rol = rs.getInt("id_rol");
				usuario = new Usuario(id, nombre, password, correo, direccion, celular, id_rol);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return usuario;
	}

	// METODO PARA BUSCAR POR PRESTAMO (NOMBRE USUARIO)
	public List<Prestamo> selectPrestamoByUsuario(int id) {
		List<Prestamo> prestamo = new ArrayList<>();
		// Conecta con la bd
		try (Connection connection = getConnection();
				// Prepara la consulta sql
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIST_PRESTAMO_BY_ID);) {
			preparedStatement.setInt(1, id);
			// System.out.println(preparedStatement);
			// Ejecuta la consulta y la almacena en un objeto ResultSet
			ResultSet rs = preparedStatement.executeQuery();
			// Recorre el resultado y lo almacena en el objeto prestamo
			while (rs.next()) {
				int id_prestamo = rs.getInt("id_prestamo");
				String nombre_usuario = rs.getString("usuario.nombre");
				String nombre_libro = rs.getString("libro.nombre");
				String fecha_prestamo = rs.getString("fecha_prestamo");
				String fecha_entrega = rs.getString("fecha_entrega");
				prestamo.add(new Prestamo(id_prestamo, nombre_usuario, nombre_libro, fecha_prestamo, fecha_entrega));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prestamo;
	}

	// ACTUALIZAR INFO CLIENTE
	public boolean updateUsuarioCliente(Usuario usuario) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USUARIO_SQL);) {
			statement.setInt(1, usuario.getId_nueva());
			statement.setString(2, usuario.getNombre());
			statement.setString(3, usuario.getPassword());
			statement.setString(4, usuario.getCorreo());
			statement.setString(5, usuario.getDireccion());
			statement.setString(6, usuario.getCelular());
			statement.setInt(7, usuario.getId_rol());
			statement.setInt(8, usuario.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	// ACTUALIZAR CONTRA CLIENTE
	public boolean updateContraCli(Usuario usuario) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_CONTRA_SQL);) {
			statement.setInt(1, usuario.getId());
			statement.setString(2, usuario.getPassword());
			statement.setInt(3, usuario.getId_rol());
			statement.setInt(4, usuario.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}

	}

}