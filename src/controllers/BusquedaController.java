package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import dao.BusquedaDAO;
import factory.ConnectionFactory;
import models.Huesped;
import models.Listable;
import models.Reserva;

public class BusquedaController {
	
	
	private BusquedaDAO busquedaDAO;
	
	public BusquedaController() {
		
		Connection connection = new ConnectionFactory().recuperarConexion();
		this.busquedaDAO = new BusquedaDAO(connection);
		
	}
	
	public List<List<Listable>> buscarHuespedesYReservas(String criterioBusqueda) {
		return busquedaDAO.buscarHuespedesYReservas(criterioBusqueda);
	}
	
	public void eliminarHuespedYReserva(Integer id_reserva) {
		busquedaDAO.eliminarHuespedyReserva(id_reserva);
	}

	public void modificarReserva(Reserva reserva) {
		busquedaDAO.modificarReserva(reserva);
	}
	
	public void modificarHuesped(Huesped huesped) {
		busquedaDAO.modificarHuesped(huesped);
	}
	

}
