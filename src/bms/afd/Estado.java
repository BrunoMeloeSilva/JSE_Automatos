package bms.afd;

import java.util.HashSet;
import java.util.Set;

public class Estado {
	private String nome;
	private TipoEstado tipo;
	private Set<Aresta> arestas;

	public Estado(String nome, TipoEstado tipo) {
		this.nome = nome;
		this.tipo = tipo;
		this.arestas = new HashSet<Aresta>();
	}

	public void addAresta(Aresta a) {
		if (a != null) {
			this.arestas.add(a);
		}
	}
	
	public enum TipoEstado {INICIAL, INTERMEDIARIO, FINAL}

	public TipoEstado getTipo() {
		return tipo;
	}

	public void setTipo(TipoEstado tipo) {
		this.tipo = tipo;
	}
	
	public Set<Aresta> getArestas() {
		return arestas;
	}
	
	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
