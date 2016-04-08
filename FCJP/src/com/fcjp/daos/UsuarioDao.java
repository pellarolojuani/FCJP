package com.fcjp.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.fcjp.entities.Usuario;
import com.fcjp.resources.ConfigurationDataBase;

public class UsuarioDao {

	final String INSERT_QUERY = "INSERT INTO `usuarios`" +
								" (`id_usuario`,`usuario`,`nombre`,`apellido`,`mail`,`contrasenia`,`creation_date`)" +
								" VALUES (?,?,?,?,?,?,?);";
	
	Usuario usuario;
	Connection conn;
	
	public UsuarioDao(){
		this.conn = ConfigurationDataBase.newConnection().getConn();		
	}
	
	public void insertUser() throws SQLException{
		//Este metodo solo va a andar si el usuario no es null
		
		Calendar calendar = Calendar.getInstance();
	    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		
		PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY);
		stmt.setInt(1, usuario.getId());
		stmt.setString(2, usuario.getUserName());
		stmt.setString(3, usuario.getNombre());
		stmt.setString(4, usuario.getApellido());
		stmt.setString(5, usuario.getEmail());
		stmt.setString(6, usuario.getContrasenia());
		stmt.setTimestamp(7, ourJavaTimestampObject);
		
		stmt.execute();
		
	}
	
	public void setUsuario(Usuario unUsuario){
		this.usuario = unUsuario;
	}
	public Usuario getusuario(){
		return this.usuario;
	}
}
