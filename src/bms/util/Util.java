package bms.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import bms.afd.Estado;
import bms.afd.Estado.TipoEstado;

public class Util {

	/*
	 * Recebe um Regex e desmembra seus elementos e insere-os em um conjunto,
	 * que é retornado.
	 */
	/*public static Set<String> extraiValores(String regex) {
		char[] vetor = regex.toCharArray();
		Set<String> simbolos = new HashSet<String>();
		char valPos1;
		char valPos2;
		// ASC
		// | 0 - 9 | = 49 - 58
		// | A - Z | = 66 - 91
		// | a - z | = 98 - 123
		for (int i = 0; i < vetor.length; i++) {
			valPos1 = vetor[i];

			if (i + 1 < vetor.length) {
				valPos2 = vetor[i + 1];
				if (valPos2 == '-') {
					valPos2 = vetor[i + 2];
					for (int j = valPos1; j <= valPos2; j++) {
						simbolos.add("" + (char) j);
					}
					i = i + 3;
				} else if (valPos2 == ',') {
					simbolos.add("" + valPos1);
					i = i + 1;
				}
			} else {
				simbolos.add("" + valPos1);
				break;
			}
		}
		return simbolos;
	}*/

	// para considerar mais de um caracter por simbolo
	// ASC
	// | 0 - 9 | = 49 - 58
	// | A - Z | = 66 - 91
	// | a - z | = 98 - 123
	public static Set<String> extraiValores(String regex) {
		char[] vetor = regex.toCharArray();
		Set<String> simbolos = new HashSet<String>();
		char valPos1;
		char valPos2;

		StringBuilder simbolo = new StringBuilder();
		for (int i = 0; i < vetor.length; i++) {
			
			if((valPos1 = vetor[i]) == ','){
				if (simbolo.length() > 0) {
					simbolos.add(simbolo.toString());
					simbolo.delete(0, simbolo.length());
				}
				continue;
			}

			if (i + 1 < vetor.length) {
				valPos2 = vetor[i + 1];
				if (valPos2 == '-') {
					valPos2 = vetor[i + 2];
					for (int j = valPos1; j <= valPos2; j++) {
						simbolos.add("" + (char) j);
					}
					i = i + 3;
				} else if (valPos2 == ',') {
					simbolos.add("" + valPos1);
					i = i + 1;
				} else {
					simbolo.append("" + vetor[i]);
					simbolo.append("" + vetor[i + 1]);
					i = i + 1;
					if (i + 1 >= vetor.length) {
						simbolos.add(simbolo.toString());
					}
				}
			} else {
				simbolos.add("" + valPos1);
				break;
			}
		}
		return simbolos;
	}

	// Retorna um conjunto de estados com os nomes passados
	public static Set<Estado> getConjuntoEstado(String estados) {
		Set<String> conjuntoNomeEstado = extraiValores(estados);
		Set<Estado> conjuntoEstado = new HashSet<Estado>();
		Estado estado;
		for (String string : conjuntoNomeEstado) {
			estado = new Estado(string, TipoEstado.INTERMEDIARIO);
			conjuntoEstado.add(estado);
		}
		return conjuntoEstado;
	}

	public static Estado getEstadoInicial(Set<Estado> estados, String nomeEstadoInicial) {
		Estado estadoInicial = null;
		for (Estado e : estados) {
			if (e.getNome().equals(nomeEstadoInicial)) {
				e.setTipo(TipoEstado.INICIAL);
				estadoInicial = e;
				break;
			}
		}
		return estadoInicial;
	}

	public static Set<Estado> getEstadosFinais(Set<Estado> estados, String nomeEstadosFinais) {
		Set<Estado> estadosFinais = new HashSet<Estado>();
		Set<String> conjuntoNomeEstado = extraiValores(nomeEstadosFinais);

		for (String nomeEstado : conjuntoNomeEstado) {
			for (Estado e : estados) {
				if (e.getNome().equals(nomeEstado)) {
					e.setTipo(TipoEstado.FINAL);
					estadosFinais.add(e);
				}
			}
		}
		return estadosFinais;
	}

	public static Estado getEstado(Set<Estado> estados, String nomeEstado) {
		for (Estado e : estados) {
			if (e.getNome().equals(nomeEstado)) {
				return e;
			}
		}
		return null;
	}

	public static int getPosicaoProxPontaSeta(char[] vetor, int posicaoUltimaSeta) {
		int retorno = 0;
		for (int i = posicaoUltimaSeta + 3; i < vetor.length; i++) {
			if (vetor[i] == '>') {
				retorno = i;
				break;
			}
		}
		return retorno;
	}

	//A entrada deve ser separada em elementos conforme o alfabeto...
	public static List<String> entradaAutomato(Set<String> alfabeto) {
		Scanner ler = new Scanner(System.in);
		System.out.printf("Informe a entrada do automato: ");
		String s = ler.next();
		char[] entrada = s.toCharArray();
		List<String> l = new ArrayList<String>();
		
		int tamSimbolo=0;
		String simbol = "";
		for(String simbolo : alfabeto){
			tamSimbolo=simbolo.length();
			for (int i = 0; i < entrada.length; i++) {
				for (int j = i; j < i+tamSimbolo && i+tamSimbolo <= entrada.length; j++) {
					simbol+=entrada[j];
				}
				if(simbolo.equals(simbol)){
					l.add(simbol);
					simbol="";
				}else{
					simbol="";
				}
			}
			
		}
		//ta mudando a ordem na saida, por isso o erro.
		System.out.println(l);
		return l;
	}
}
