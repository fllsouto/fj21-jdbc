package br.com.caelum.jdbc.teste;

import java.util.Optional;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.util.ContatoPresenter;

public class TestaBuscaPorId {

	public static void main(String[] args) {
		Long id = 1l;
		ContatoDao dao = new ContatoDao();
		
		Optional<Contato> contatoOpcional = dao.getById(id);
		ContatoPresenter presenter = new ContatoPresenter();
		
		System.out.println(presenter.present(contatoOpcional));
	}
}
