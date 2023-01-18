package aplicacion.modelo.dto;

public class UsuarioDTO {
	private String username;
	private String nombre;
	private String password;

	public UsuarioDTO() {
	}

	public UsuarioDTO(String nombre, String username, String password) {
		this.nombre=nombre;
		this.username = username;
		this.password = password;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
