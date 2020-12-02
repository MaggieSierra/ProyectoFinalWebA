package Modelo;

public class CarreraBean {
	private int id_carrera;
	private int id_turno;
	private String clave_carrera;
	private String nombre;
	private String turno;
	
	public int getId_carrera() {
		return id_carrera;
	}
	
	public void setId_carrera(int id_carrera) {
		this.id_carrera = id_carrera;
	}
	
	public String getClave_carrera() {
		return clave_carrera;
	}
	
	public void setClave_carrera(String clave_carrera) {
		this.clave_carrera = clave_carrera;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdTurno() {
		return id_turno;
	}

	public void setIdTurno(int id_turno) {
		this.id_turno = id_turno;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	
}
