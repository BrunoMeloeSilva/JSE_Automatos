package bms.afd;

import java.util.List;
import java.util.Set;

import bms.util.Util;


public class Quintupla {
	private Set<String> alfabeto;
	private Set<Estado> estados;
	private Estado estadoInicial;
	private Set<Estado> estadosFinais;

	public Quintupla(String alfabeto, String estados, String estadoInicial, String estadosFinais,
			String funcaoTransicao) {
		this.alfabeto = Util.extraiValores(alfabeto);
		this.estados = Util.getConjuntoEstado(estados);
		this.estadoInicial = Util.getEstadoInicial(this.estados, estadoInicial);
		this.estadosFinais = Util.getEstadosFinais(this.estados, estadosFinais);
		this.complementaEstados(funcaoTransicao);
	}

	private void complementaEstados(String funcaoTransicao) {
		Estado estadoBunda = null;
		Estado estadoCabeca = null;
		Set<String> simbolos = null;
		int posicaoEstadoInicial = 0;
		int posicaoUltimaSeta = 0;
		String subString = null;
		char[] v = funcaoTransicao.toCharArray();

		while ((posicaoUltimaSeta = funcaoTransicao.indexOf('>', posicaoUltimaSeta + 1)) > 0) {
			subString = funcaoTransicao.substring(posicaoEstadoInicial + 2, posicaoUltimaSeta - 1);
			estadoBunda = Util.getEstado(this.estados, "" + v[posicaoEstadoInicial]);
			estadoCabeca = Util.getEstado(this.estados, "" + v[posicaoUltimaSeta + 1]);
			simbolos = Util.extraiValores(subString);
			estadoBunda.addAresta(new Aresta(estadoBunda, estadoCabeca, simbolos));
			posicaoEstadoInicial = posicaoUltimaSeta + 3;
		}
	}

	private boolean isFinal(Estado estado) {
		for (Estado e : this.estadosFinais) {
			if (estado.equals(e))
				return true;
		}
		return false;
	}

	private Estado getTransicao(Estado estadoEntrada, String simbolo) {
		for (Aresta a : estadoEntrada.getArestas()) {
			for (String s : a.getSimbolos()) {
				if (s.equals(simbolo)) {
					return a.getEstadoSaida();
				}
			}
		}
		return null;
	}

	public boolean funcaoTransicao(List<String> fitaEntrada) {
		Estado e = estadoInicial;
		for (String simbolo : fitaEntrada) {
			e = getTransicao(e, simbolo);
			if (e == null) {
				break;
			}
		}

		if (e != null && isFinal(e)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		// *** Start ***
		Quintupla q = new Quintupla("a-z,0-9,_,$,%,(*,*)", "A,B,C", "A", "C", "A-(*->B;B-a-z,_,$,%,0-9->B;B-*)->C");
		// Entrada do usuário
		List<String> entrada = Util.entradaAutomato(q.alfabeto);
		// Processamento da palavra dada
		if (q.funcaoTransicao(entrada))
			System.out.println("Aceita");
		else
			System.out.println("Rejeita");
	}
}
