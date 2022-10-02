package controllers;

import java.sql.Connection;

import dao.UsuarioDAO;
import factory.ConnectionFactory;
import models.Usuario;

public class UsuarioController {
	
	private UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		
		Connection connection = new ConnectionFactory().recuperarConexion();
		this.usuarioDAO = new UsuarioDAO(connection);
		
	}
	
	
	public void guardarUsuario(Usuario usuario) {
		usuarioDAO.guardarUsuario(usuario);
	}
	
	public Usuario obtenerUsuario(String username, String password) {
		return usuarioDAO.obtenerUsuario(username, password);
	}


}
