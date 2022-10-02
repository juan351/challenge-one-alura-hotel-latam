package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Usuario;
import utils.Encrypter;

public class UsuarioDAO {
	
	private Connection connection;
	
	public UsuarioDAO(Connection connection) {
		
		this.connection = connection;		
	}

	public Usuario obtenerUsuario(String nombre_usuario, String password) {
		
		Usuario usuario = new Usuario(null, null);
		
		try {
			String sql = "SELECT * FROM usuario WHERE nombre_usuario=?";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				
				pstm.setString(1, nombre_usuario);
				
								
				try (ResultSet rst = pstm.executeQuery()){
					while(rst.next()) {
						usuario.setId(rst.getInt(1));
						usuario.setNombreUsuario(rst.getString(2));
						usuario.setPassword(rst.getString(3));
					}
				}
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return usuario;
	}

	public void guardarUsuario(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuario (nombre_usuario,password) "
					+ "VALUES (?,?)";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				
				pstm.setString(1, usuario.getNombreUsuario());
				pstm.setString(2, Encrypter.simpleJeringosoEncrypt(usuario.getPassword()));
				
				pstm.executeUpdate();
				
				try (ResultSet rst = pstm.getGeneratedKeys()){
					while(rst.next()) {
						usuario.setId(rst.getInt(1));
					}
				}
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}		
	
	
}
