package estacionamiento;

public class Sector {
	private String codigo;
	private int capacidadMaxima;
	private int precio;
	private int vehiculos_en_sector;
	
	public Sector(String codigo, int capacidadMaxima, int precio) {
		if(!this._IREPIsValid(codigo, capacidadMaxima, precio)) {
			throw new RuntimeException("El objeto Sector no se puede crear");
		}
		this.codigo = codigo;
		this.capacidadMaxima = capacidadMaxima;
		this.precio = precio;
		this.vehiculos_en_sector = 0;
	}
	
	public boolean hayLugar() {
		return this.vehiculos_en_sector < this.capacidadMaxima;
	}
	
	public void incrementarContador() {
		this.vehiculos_en_sector++;
	}
	
	public void decrementarContador() {
		this.vehiculos_en_sector--;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public int getPrecio() {
		return this.precio;
	}
	
	public int getVehiculosEnSector() {
		return this.vehiculos_en_sector;
	}

	private boolean _IREPIsValid(String codigo, int capacidadMaxima, int precio) {
		if(codigo == "") return false;
		if(capacidadMaxima < 0) return false;
		if(precio < 0) return false;
		
		return true;
	}
	
}
