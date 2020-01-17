package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaRemove {

	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		
		Long id = 21l;
		Contato contato = new Contato();
		contato.setId(id);
		
		boolean removido = dao.remove(contato);
		
		if (removido) 
			System.out.println("Contato removido com sucesso!");
		else
			System.out.println("Não foi possível remover o contato");
	}
}
