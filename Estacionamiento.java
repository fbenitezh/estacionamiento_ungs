package estacionamiento;

import java.util.HashMap;

public class Estacionamiento {
	private int capacidadMaxima; // capacidad maxima del estacionamiento
	private HashMap<String, Sector> sectores; // doccionario con los sectores del estacionamiento
	private HashMap<String, Tupla<Integer,Integer>> movimientos; // diccionario con las tuplas que representan los movimientos de cada vehiculo
	private int recaudacion; // acumulador que almacena la recaudacion del estacionamiento
	private HashMap<String, Vehiculo> vehiculos; // Diccionario con los vehiculos en el estacionamiento
	
	
	public Estacionamiento(int capacidadMaxima, HashMap<String, Sector> sectores, int recaudacion) {
		if(!this._IREPIsValid(capacidadMaxima, recaudacion)) {
			throw new RuntimeException("El objeto Estacionamiento no se puede crear");
		}
		this.capacidadMaxima = capacidadMaxima;
		this.sectores = sectores;
		this.movimientos = new HashMap<String,Tupla<Integer,Integer>>();
		this.recaudacion = recaudacion;
		this.vehiculos = new HashMap<String, Vehiculo>();
	}
	
	
	public boolean estaVehiculoEnHorario(String matricula, int horario) {
		return false;
	}
	
	/**
	 * Ingresa el vehiculo al estacionamiento
	 * @param matricula
	 * @param horario_entrada
	 * @param sector
	 */
	public void ingresarVehiculo(String matricula, int horario_entrada, String sector) {
		Sector sector_vehiculo_ingresante = this.sectores.get(sector);
		Vehiculo vehiculo_ingresante = new Vehiculo(matricula, sector_vehiculo_ingresante);
		
		if(!this._sePuedeIngresar(vehiculo_ingresante)) {
			throw new RuntimeException("No se puede ingresar el vehiculo");
		}

		// guardamos el auto en el listado
		this.vehiculos.put(vehiculo_ingresante.getMatricula(), vehiculo_ingresante);
		
		// incrementamos el contador de autos en el sector del estacionamiento
		sector_vehiculo_ingresante.incrementarContador();
		
		// guardar el movimiento (la entrada del vehiculo en el horario correspondiente)
		Tupla<Integer, Integer> movimiento = new Tupla<Integer, Integer>(horario_entrada, null);
		this.movimientos.put(vehiculo_ingresante.getMatricula(), movimiento);
	}
	
	
	/**
	 * Elimina el vehiculo del estacionamiento
	 * cobrando la tarifa correspondiente de acuerdo al sector
	 * y el tiempo que estuvo en el estacionamiento
	 * @param matricula
	 * @param horario_salida
	 */
	public void sacarVehiculo(String matricula, int horario_salida) {
		if (!this._estaVehiculo(matricula)) {
			throw new RuntimeException("No se encuentra el vehiculo en el estacionamiento");
		}
		
		// obtenemos el vehiculo a sacar
		Vehiculo vehiculo_a_retirar = this.vehiculos.get(matricula);
		
		// obtenemos el sector del vehiculo a retirar
		// para calcular cuanto es la tarifa a pagar
		Sector sector_vehiculo_a_retirar = this.sectores.get(vehiculo_a_retirar.getSector().getCodigo());
		
		// ahora guardamos el horario de salida del auto
		// esto esta en movimientos
		Tupla<Integer, Integer> movimiento = this.movimientos.get(vehiculo_a_retirar.getMatricula());
		movimiento.setSalida(horario_salida);

		// cobramos la estadia e incrementamos la recaudacion
		this._cobrar(sector_vehiculo_a_retirar, movimiento);
		
		// actualizamos el movimiento con el horario de salida
		this.movimientos.put(vehiculo_a_retirar.getMatricula(), movimiento);
		
		// sacamos el vehiculo del listado
		this.vehiculos.remove(vehiculo_a_retirar.getMatricula());
		
		// decrementamos el contador de autos en el sector del estacionamiento
		sector_vehiculo_a_retirar.decrementarContador();
	}
	
	/**
	 * Devuelve la recaudacion del estacionamiento
	 * @return
	 */
	public int recaudacion() {
		return this.recaudacion; // O(1)
	}
	
	/**
	 * Evalua los criterios para determinar si un vehiculo
	 * puede ingresar al estacionamiento
	 * @param matricula
	 * @return
	 */
	private boolean _sePuedeIngresar(Vehiculo vehiculo) {
		boolean estaVehiculoEnEstacionamiento = this._estaVehiculo(vehiculo.getMatricula());
		boolean hayLugarEnSector = vehiculo.getSector().hayLugar();
		
		return !estaVehiculoEnEstacionamiento && hayLugarEnSector;
	}
	
	
	/**
	 * Verifica si el vehiculo esta en el estacionamiento
	 * @param matricula
	 * @return
	 */
	private boolean _estaVehiculo(String matricula) {
		return this.vehiculos.containsKey(matricula);
	}
	
	
	/**
	 * Cobra la tarifa correspondiente segun sector y tiempo en el estacionamiento
	 * @param sector_vehiculo_saliente
	 * @param estadia
	 */
	private void _cobrar(Sector sector_vehiculo_saliente, Tupla<Integer, Integer> estadia) {
		int tarifa_por_hora = sector_vehiculo_saliente.getPrecio();
		int horas_de_estadia = estadia.getSalida() - estadia.getEntrada();
		
		int total = tarifa_por_hora * horas_de_estadia;
		
		this.recaudacion += total;
	}
	
	private boolean _IREPIsValid(int capacidadMaxima, int recaudacion) {
		if(this.capacidadMaxima < 0) return false;
		if(this.recaudacion < 0) return false;
		
		return true;
	}
	
	
}
