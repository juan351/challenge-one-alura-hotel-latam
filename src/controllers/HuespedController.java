package controllers;

import java.sql.Connection;
import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import models.Huesped;

public class HuespedController {
	
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		
		Connection connection = new ConnectionFactory().recuperarConexion();
		this.huespedDAO = new HuespedDAO(connection);
		
	}
	
	
	public void guardar(Huesped huesped) {
		huespedDAO.guardarHuesped(huesped);
	}
	
	public List<Huesped> buscarHuespedPorId(String criterioBusqueda){
		return huespedDAO.buscarHuespedPorId(criterioBusqueda);
	}
	
	public List<Huesped> buscarHuespedPorApellido(String criterioBusqueda){
		return huespedDAO.buscarHuespedPorApellido(criterioBusqueda);
	}
	
	public void eliminarHuesped(Integer id_reserva) {
		huespedDAO.eliminarHuesped(id_reserva);
	}
	
	public void actualizarHuesped(Huesped huesped) {
		huespedDAO.actualizarHuesped(huesped);
	}
}
