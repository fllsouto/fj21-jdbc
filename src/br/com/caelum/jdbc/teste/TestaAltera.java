package br.com.caelum.jdbc.teste;

import java.util.Optional;
import java.util.Random;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;
import br.com.caelum.jdbc.util.ContatoPresenter;

public class TestaAltera {

	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		
		Long id = 20l;
		Optional<Contato> contatoOpcional = dao.getById(id);
		ContatoPresenter presenter = new ContatoPresenter();
		if (contatoOpcional.isPresent()) {
			Contato contato = contatoOpcional.get();
			
			System.out.println("Antes: ");
			System.out.println(presenter.present(contato));
			
			Random random = new Random();
			Integer randomInteger = random.nextInt();
			String novoNome = "Caelum -- " + randomInteger.toString();
			contato.setNome(novoNome);
			
			dao.altera(contato);
			contatoOpcional = dao.getByNome(novoNome);

			System.out.println("\n Depois: ");
			System.out.println(presenter.present(contatoOpcional));
		} else {
			System.out.println(contatoOpcional);
		}
	}
}
