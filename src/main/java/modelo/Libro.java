package modelo;

import java.io.Serializable;

public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int id;
	protected String nombre;
	protected String autor;
	protected int num_pag;
	protected int edicion;

	public Libro() {
	}

	public Libro(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Libro(String nombre, String autor, int num_pag, int edicion) {
		this.nombre = nombre;
		this.autor = autor;
		this.num_pag = num_pag;
		this.edicion = edicion;
	}

	public Libro(int id, String nombre, String autor, int num_pag, int edicion) {
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
		this.num_pag = num_pag;
		this.edicion = edicion;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getNum_pag() {
		return num_pag;
	}

	public void setNum_pag(int num_pag) {
		this.num_pag = num_pag;
	}

	public int getEdicion() {
		return edicion;
	}

	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}
}