package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controllers.HuespedController;
import controllers.ReservaController;
import models.Huesped;
import models.Listable;
import models.Reserva;


public class BusquedaDAO {
	
	private Connection connection;
	private HuespedController huespedController;
	private ReservaController reservaController;
	
	public BusquedaDAO(Connection connection) {
		this.connection = connection;
		this.huespedController = new HuespedController();
		this.reservaController = new ReservaController();
	}
	
	public List<List<Listable>> buscarHuespedesYReservas(String criterioBusqueda){
		
		return buscarRegistros(criterioBusqueda);
		
	}
	
	private List<List<Listable>> buscarRegistros(String criterioBusqueda){
		
		return buscar(criterioBusqueda, esNumerico(criterioBusqueda));
		
	}
	
	private List<List<Listable>> buscar(String criterioBusqueda, boolean numerico) {
		
		List<List<Listable>> listaHuespedesConReservas = new ArrayList<>();
				
		try {
			
			String sql;
			
		
			
			if(numerico == true) {
				
				sql = "SELECT * FROM huespedes JOIN reservas R ON id_reserva = R.id WHERE R.id=?;";
				
			}else {
				sql = "SELECT * FROM huespedes JOIN reservas R ON id_reserva = R.id WHERE apellido=?;";
			}
		
			try (PreparedStatement pstm = connection.prepareStatement(sql)){
				
				if(numerico == true) {
					pstm.setInt(1, Integer.valueOf(criterioBusqueda)); 
					
				}else {
					pstm.setString(1, criterioBusqueda);
				}
				
				
				ResultSet rst = pstm.executeQuery();
				
				while(rst.next()) {
					
					List<Listable> huespedReserva = new ArrayList<>();
					
					Listable huesped = new Huesped(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(4),rst.getString(5),rst.getString(6),rst.getInt(7));
					Listable reserva = new Reserva(rst.getInt(8),
	                        rst.getDate(9),
	                        rst.getDate(10),
	                        rst.getDouble(11),
	                        rst.getString(12));
					
					
					
					huespedReserva.add(huesped);
					huespedReserva.add(reserva);
					listaHuespedesConReservas.add((List<Listable>) huespedReserva);
					
				}
				
							
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
			return listaHuespedesConReservas;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void eliminarHuespedyReserva(Integer id_reserva) {
		
		reservaController.eliminarReserva(id_reserva);
		huespedController.eliminarHuesped(id_reserva);
		
		
	}
	
	public void modificarReserva(Reserva reserva) {
		reservaController.actualizarReserve(reserva);
	}

	public void modificarHuesped(Huesped huesped) {
		huespedController.actualizarHuesped(huesped);
	}
	
	
	private static boolean esNumerico(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
	

}
