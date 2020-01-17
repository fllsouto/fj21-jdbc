package br.com.caelum.jdbc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import br.com.caelum.jdbc.modelo.Contato;

public class ContatoPresenter {

	public String present(Contato contato) {
		StringBuilder builder = new StringBuilder();
		builder.append("Id: " + contato.getId() + "\n");
		builder.append("Nome: " + contato.getNome() + "\n");
		builder.append("Email: " + contato.getEmail() + "\n");
		builder.append("Endereço: " + contato.getEndereco() + "\n");

		Date data = contato.getDataNascimento().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		builder.append("Data de Nascimento: " + 
				formatter.format(data) + "\n");
		
		return builder.toString();
	}

	public String present(Optional<Contato> contatoOpcional) {
		if (contatoOpcional.isPresent())
			return present(contatoOpcional.get());
		else
			return "Contato não encontrado!";
	}
}
