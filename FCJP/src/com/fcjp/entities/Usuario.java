package com.fcjp.entities;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellido;
	private String userName;
	private String contrasenia;
	private String email;
	
	
	public Usuario( int unId, String unNombre, String unApellido, String unUserName,
					String unaContrasenia, String unEmail){
		this.id = unId;
		this.nombre = unNombre;
		this.apellido = unApellido;
		this.userName = unUserName;
		this.contrasenia = unaContrasenia;
		this.email = unEmail;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
