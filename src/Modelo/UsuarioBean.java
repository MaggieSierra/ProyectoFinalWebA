package Modelo;

public class UsuarioBean{
	private int id_usuario;
	private int id_rol;
	private int id_departamento;
	private String clave_usuario;
	private String prefijo;
	private String nombre;
	private String primer_apellido;
	private String segundo_apellido;
	private String correo;
	private String telefono;
	private String usuario;
	private String contraseña;
	private int hrs_trabajo;
	private String nombre_rol;
	private String nombre_departamento;
	
	public int getId_usuario() {
		return id_usuario;
	}
	
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public int getId_rol() {
		return id_rol;
	}
	
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	
	public int getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
	}
	
	public String getClave_usuario() {
		return clave_usuario;
	}
	
	public void setClave_usuario(String clave_usuario) {
		this.clave_usuario = clave_usuario;
	}
	
	public String getPrefijo() {
		return prefijo;
	}
	
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPrimer_apellido() {
		return primer_apellido;
	}
	
	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}
	
	public String getSegundo_apellido() {
		return segundo_apellido;
	}
	
	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public int getHrs_trabajo() {
		return hrs_trabajo;
	}
	
	public void setHrs_trabajo(int hrs_trabajo) {
		this.hrs_trabajo = hrs_trabajo;
	}

	public String getNombre_rol() {
		return nombre_rol;
	}

	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}

	public String getNombre_departamento() {
		return nombre_departamento;
	}

	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento = nombre_departamento;
	}
	
}
