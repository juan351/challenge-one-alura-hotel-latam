package models;

public class Usuario {
	
	
	private int id;
	private String nombreUsuario;
	private String password;
	
	public Usuario(String nombreUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
	}
	
	

	public Usuario(String nombreUsuario, String password) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}



	public int getId() {
		return id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public void setId(int id) {
		this.id = id;
		
	}



	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
		
	}
	
	
	
	
	

}
