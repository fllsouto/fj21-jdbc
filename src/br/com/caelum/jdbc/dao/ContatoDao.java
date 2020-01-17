package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDao {

	private Connection connection;
	
	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Contato contato) {
		String sql = "insert into contatos " +
				"(nome, email, endereco, dataNascimento)" +
				" values (?,?,?,?)";
		
		try {
			// prepared statement para inserção
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getNome());
			stmt.setString(3, contato.getNome());
			stmt.setDate(4, new Date(
					contato.getDataNascimento().getTimeInMillis()));
			
			// executa
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new DAOException(
					"Erro SQLException ao executar o método adiciona da classe ContatoDao",
					e);
		} catch (Exception e) {
			throw new DAOException(
					"Erro genérico ao executar o método adiciona da classe ContatoDao",
					e);
		}
	}

	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contato contato = new Contato();

				// criando o objeto Contato
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// monstando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// adiciona o objeto a lista
				contatos.add(contato);
			}

			rs.close();
			stmt.close();
			return contatos;

		} catch (SQLException e) {
			throw new DAOException(
					"Erro SQLException ao executar o método getLista da classe ContatoDao",
					e);
		}  catch (Exception e) {
			throw new DAOException(
					"Erro genérico ao executar o método adiciona da classe ContatoDao",
					e);
		}
	}
}
