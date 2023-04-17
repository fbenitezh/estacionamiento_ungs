package estacionamiento;

/**
 * Esta tupla representa el horario de entrada y de salida de un vehiculo
 * @author franco
 *
 * @param <K> horario de entrada
 * @param <V> horario de salida
 */
public class Tupla<K, V> {
	private K entrada;
	private V salida;
	
	public Tupla(K in, V out) {
		entrada = in;
		salida = out;
	}

	public K getEntrada() {
		return entrada;
	}

	public void setEntrada(K entrada) {
		this.entrada = entrada;
	}

	public V getSalida() {
		return salida;
	}

	public void setSalida(V salida) {
		this.salida = salida;
	}
	
}
