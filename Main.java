package estacionamiento;

import java.util.HashMap;

public class Main {
	
	static HashMap<String, Sector> sectores;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sector sectorDocente = new Sector("docente", 50, 10);
		Sector sectorAlumnos = new Sector("alumno", 50, 5);
		Sector sectorGeneral = new Sector("general", 100, 20);
		
		sectores = new HashMap<String, Sector>();
		
		sectores.put("docente", sectorDocente);
		sectores.put("alumno", sectorAlumnos);
		sectores.put("general", sectorGeneral);
		
		Estacionamiento estacionamiento = new Estacionamiento(200, sectores, 0);
		
		
		estacionamiento.ingresarVehiculo("AAA111", 11, "docente");
		estacionamiento.ingresarVehiculo("OYU392", 12, "alumno");
		estacionamiento.ingresarVehiculo("CCC111", 10, "general");
		
		
		estacionamiento.sacarVehiculo("AAA111", 13);
		estacionamiento.sacarVehiculo("OYU392", 15);
		estacionamiento.sacarVehiculo("CCC111", 13);
		
		
		System.out.println(estacionamiento.recaudacion());
		
		
		
		
	}

}
