package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import models.Reserva;

public class ReservaDAO {
	
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas (fecha_entrada,fecha_salida,valor,forma_pago) "
					+ "VALUES (?,?,?,?)";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				
				pstm.setDate(1, reserva.getFecha_entrada());
				pstm.setDate(2, reserva.getFecha_salida());
				pstm.setDouble(3, reserva.getValor());
				pstm.setString(4, reserva.getForma_pago());
				
				pstm.executeUpdate();
				
				try (ResultSet rst = pstm.getGeneratedKeys()){
					while(rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
		
	public List<Reserva> buscarReserva(String criterioBusqueda){
		
		List<Reserva> listaReservas = new ArrayList<>();
		
		try {
			
			String sql = "SELECT * FROM reservas WHERE id=?";
			
			try (PreparedStatement pstm = connection.prepareStatement(sql)){
				
				
				pstm.setInt(1, Integer.valueOf(criterioBusqueda));
				
				
				
				ResultSet rst = pstm.executeQuery();
				
							
					while(rst.next()) {
						
						listaReservas.add(new Reserva(rst.getInt(0), rst.getDate(1), rst.getDate(2), rst.getDouble(3), rst.getString(4)));
						
					}
			} catch (SQLException e) {
			
				throw new RuntimeException(e);
			}
			
			return listaReservas;
			
		} catch (Exception e) {
		
			throw new RuntimeException(e);
		}
	}
	
	public void eliminarReserva(Integer id_reserva) {
		
		
		String sql = "DELETE FROM reservas WHERE id=?;";
		
		
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			
			pstm.setInt(1, id_reserva);
			
			pstm.executeUpdate();
			
							
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		
	}

	public void actualizarReserva(Reserva reserva) {
		
		String sql = "UPDATE reservas SET fecha_entrada=?, fecha_salida=?, valor=?, forma_pago=? WHERE id=?;";
		
try (PreparedStatement pstm = connection.prepareStatement(sql)){
			
			pstm.setDate(1, reserva.getFecha_entrada());
			pstm.setDate(2, reserva.getFecha_salida());
			pstm.setDouble(3, reserva.getValor());
			pstm.setString(4, reserva.getForma_pago());
			pstm.setInt(5, reserva.getId());
			
			
			pstm.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
