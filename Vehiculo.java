package estacionamiento;

public class Vehiculo {
	private String matricula;
	private Sector sector;
	
	public Vehiculo(String matricula, Sector sector) {
		if(!this._IREPIsValid(matricula)) {
			throw new RuntimeException("El objeto vehiculo no se puede crear");
		}
		this.matricula = matricula;
		this.sector = sector;
	}
	
	private boolean _IREPIsValid(String matricula) {
		if(matricula == "") return false;
		
		return true;
	}

	public String getMatricula() {
		return matricula;
	}

	public Sector getSector() {
		return sector;
	}
	
}
