package br.com.caelum.jdbc.teste;

import java.util.Optional;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.util.ContatoPresenter;

public class TestaBuscaPorNome {

	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		
		String nome = "fellipe";
		Optional<Contato> contatoOpcional = dao.getByNome(nome);
		
		ContatoPresenter presenter = new ContatoPresenter();
		System.out.println(presenter.present(contatoOpcional));
	}
}
