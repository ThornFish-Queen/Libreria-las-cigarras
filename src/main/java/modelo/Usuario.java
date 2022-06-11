package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int id;
	protected String nombre;
	protected String password;
	protected String correo;
	protected String direccion;
	protected String celular;
	protected int id_rol;
	protected int id_nueva;

	public Usuario() {
	}

	public Usuario(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Usuario(int id, String password, int id_rol) {
		this.id = id;
		this.password = password;
		this.id_rol = id_rol;
	}

	public Usuario(int id, String nombre, String password, String correo, String direccion, String celular,
			int id_rol) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.correo = correo;
		this.direccion = direccion;
		this.celular = celular;
		this.id_rol = id_rol;
	}

	public Usuario(int id, String nombre, String password, String correo, String direccion, String celular, int id_rol,
			int id_nueva) {
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.correo = correo;
		this.direccion = direccion;
		this.celular = celular;
		this.id_rol = id_rol;
		this.id_nueva = id_nueva;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	public int getId_nueva() {
		return id_nueva;
	}

	public void setId_nueva(int id_nueva) {
		this.id_nueva = id_nueva;
	}

}
