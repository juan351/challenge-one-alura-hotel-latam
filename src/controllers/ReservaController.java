package controllers;

import java.sql.Connection;
import java.util.List;


import dao.ReservaDAO;
import factory.ConnectionFactory;
import models.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		
		Connection connection = new ConnectionFactory().recuperarConexion();
		this.reservaDAO = new ReservaDAO(connection);
		
	}
	
	
	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}
	
	public List<Reserva> buscarReserva(String criterioBusqueda) {
		return reservaDAO.buscarReserva(criterioBusqueda);
	}
	
	public void eliminarReserva(Integer id_reserva) {
		reservaDAO.eliminarReserva(id_reserva);
	}
	
	public void actualizarReserve(Reserva reserva) {
		reservaDAO.actualizarReserva(reserva);
	}

}
