package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import models.Huesped;



public class HuespedDAO {
	
	private Connection connection;
	
	public HuespedDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardarHuesped(Huesped huesped) {
		try {
			String sql = "INSERT INTO huespedes (nombre,apellido,fecha_nacimiento,nacionalidad,telefono,id_reserva) "
					+ "VALUES (?,?,?,?,?,?)";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				
				pstm.setString(1, huesped.getNombre());
				pstm.setString(2, huesped.getApellido());
				pstm.setDate(3, huesped.getFecha_nacimiento());
				pstm.setString(4, huesped.getNacionalidad());
				pstm.setString(5, huesped.getTelefono());
				pstm.setInt(6, huesped.getId_reserva());
				
				pstm.executeUpdate();
				
				try (ResultSet rst = pstm.getGeneratedKeys()){
					while(rst.next()) {
						huesped.setId(rst.getInt(1));
					}
				}
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped>buscarHuespedPorId(String criterioBusqueda){
		
		String sql = "SELECT * FROM huespedes WHERE id=?";
		
		return buscarHuesped(criterioBusqueda, sql, true);
		
	}
	
	public List<Huesped>buscarHuespedPorApellido(String criterioBusqueda){
		
		String sql = "SELECT * FROM huespedes WHERE apellido=?";
		
		return buscarHuesped(criterioBusqueda.trim(), sql, false);
		
	}
	
	public List<Huesped> buscarHuesped(String criterioBusqueda, String sql, boolean buscarPorId){
		
		List<Huesped> listaHuespedes = new ArrayList<>();
		
		try {
		
			try (PreparedStatement pstm = connection.prepareStatement(sql)){
				
				if(buscarPorId == true) {
					pstm.setInt(1, Integer.valueOf(criterioBusqueda)); 
					
				}else {
					pstm.setString(1, criterioBusqueda);
				}
				
				
				ResultSet rst = pstm.executeQuery();
				
				while(rst.next()) {
						
					listaHuespedes.add(new Huesped(rst.getInt(0), rst.getString(1), rst.getString(2), rst.getDate(3), rst.getString(4), rst.getString(5), rst.getInt(6)));
				}
				
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
			return listaHuespedes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void eliminarHuesped(Integer id_reserva) {
		
		String sql = "DELETE FROM huespedes WHERE id_reserva=?;";
		
		
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			
			pstm.setInt(1, id_reserva);
			
			pstm.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	public void actualizarHuesped(Huesped huesped) {
		
		String sql = "UPDATE huespedes SET nombre=?, apellido=?, fecha_nacimiento=?,"
				+ "nacionalidad=?, telefono=? WHERE id_reserva=?;";
		
		
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			
			pstm.setString(1, huesped.getNombre());
			pstm.setString(2, huesped.getApellido());
			pstm.setDate(3, huesped.getFecha_nacimiento());
			pstm.setString(4, huesped.getNacionalidad());
			pstm.setString(5, huesped.getTelefono());
			pstm.setInt(6, huesped.getId_reserva());
			
			pstm.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}

}
