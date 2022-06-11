package modelo;

import java.io.Serializable;

public class Prestamo implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int id_prestamo;
	protected int id_usuario;
	protected int id_libro;
	protected String fecha_prestamo;
	protected String fecha_entrega;
	protected String nombre_usuario;
	protected String nombre_libro;
	
	public Prestamo() {
	}
	
	public Prestamo(int id_usuario, int id_libro, String fecha_entrega) {
		this.id_usuario = id_usuario;
		this.id_libro = id_libro;
		this.fecha_entrega = fecha_entrega;
	}
	
	public Prestamo(int id_prestamo,int id_usuario, int id_libro, String fecha_entrega) {
		this.id_prestamo = id_prestamo;
		this.id_usuario = id_usuario;
		this.id_libro = id_libro;
		this.fecha_entrega = fecha_entrega;
	}
	
	public Prestamo(int id_prestamo,int id_usuario, String nombre_usuario,int id_libro, String nombre_libro, String fecha_entrega) {
		this.id_prestamo = id_prestamo;
		this.id_usuario = id_usuario;
		this.nombre_usuario = nombre_usuario;
		this.id_libro = id_libro;
		this.nombre_libro = nombre_libro;
		this.fecha_entrega = fecha_entrega;
	}

	public Prestamo(int id_prestamo, String nombre_usuario, String nombre_libro, String fecha_prestamo, String fecha_entrega) {
		this.id_prestamo = id_prestamo;
		this.nombre_usuario = nombre_usuario;
		this.nombre_libro = nombre_libro;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_entrega = fecha_entrega;
	}

	public int getId_prestamo() {
		return id_prestamo;
	}

	public void setId_prestamo(int id_prestamo) {
		this.id_prestamo = id_prestamo;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public String getFecha_prestamo() {
		return fecha_prestamo;
	}

	public void setFecha_prestamo(String fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public String getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getNombre_libro() {
		return nombre_libro;
	}

	public void setNombre_libro(String nombre_libro) {
		this.nombre_libro = nombre_libro;
	}
}
