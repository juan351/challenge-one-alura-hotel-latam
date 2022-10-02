package models;

import java.sql.Date;

public class Reserva implements Listable {
	
	private Integer id;
	private Date fecha_entrada;
	private Date fecha_salida;
	private Double valor;
	private String forma_pago;
	
	
	
	public Reserva(Integer id, Date fecha_entrada, Date fecha_salida, Double valor, String forma_pago) {
		super();
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}
	
	public Reserva(Date fecha_entrada, Date fecha_salida, Double valor, String forma_pago) {
		super();
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}
	


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFecha_entrada() {
		return fecha_entrada;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}
	public Double getValor() {
		return valor;
	}
	public String getForma_pago() {
		return forma_pago;
	}
	
	

}
