package br.com.ifpb.gerenciador.entity;

import java.util.ArrayList;
import java.util.List;

public class BancoContato {

	private static List<Contato> listaContatos = new ArrayList<Contato>();

	public static void adiciona(Contato Contato) {
		listaContatos.add(Contato);

	}

	public static List<Contato> getContatos() {
		return BancoContato.listaContatos;
	}
	
	public static void atualizaContato (Contato novoContato) {
		for (Contato contato : listaContatos) {
		    if (contato.getEmail().equals(novoContato.getEmail())) {
		        contato.setNome(novoContato.getNome());
		        contato.setTelefone(novoContato.getTelefone());
		        return;
		    }
		}
	}

	public static void removerContato(String email) {
		listaContatos.removeIf(contato -> contato.getEmail().equals(email));
		
	}
}
