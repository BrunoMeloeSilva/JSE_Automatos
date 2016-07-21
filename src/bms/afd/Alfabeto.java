package bms.afd;

import java.util.Set;

import bms.util.Util;

public class Alfabeto {
	// Não existirá elementos repetidos
	private Set<String> alfabeto;

	public Alfabeto(String alfabeto) {
		this.alfabeto = Util.extraiValores(alfabeto);
	}

	public Set<String> getAlfabeto() {
		return alfabeto;
	}

	public boolean isExiste(String simbolo) {
		return this.alfabeto.contains(simbolo);
	}
	
	public static void main(String[] args) {
		Alfabeto a = new Alfabeto("A-Z");
		System.out.println(a.isExiste("g"));
	}
}