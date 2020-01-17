package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaDAOException {

	public static void main(String[] args) {
		// contato incompleto
		Contato contato = new Contato();
		
		// lançamento de exception customizada
		new ContatoDao().adiciona(contato);
	}

}
