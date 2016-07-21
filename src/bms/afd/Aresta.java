package bms.afd;

import java.util.Set;

public class Aresta {
	private Estado estadoEntrada; 
	private Estado estadoSaida; 
	private Set<String> simbolos;

	public Aresta(Estado estadoEntrada, Estado estadoSaida, Set<String> simbolos) {
		this.estadoEntrada = estadoEntrada;
		this.estadoSaida = estadoSaida;
		this.simbolos = simbolos;
	}

	public Set<String> getSimbolos() {
		return simbolos;
	}

	public Estado getEstadoSaida() {
		return estadoSaida;
	}
}
