package br.com.caelum.jdbc.teste;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.util.ContatoPresenter;

public class TestaLista {

	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		List<Contato> contatos = dao.getLista();
		ContatoPresenter presenter = new ContatoPresenter();
		
		for (Contato contato : contatos) {
			System.out.println(presenter.present(contato));
		}
	}
}
