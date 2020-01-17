package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.exception.DAOException;
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
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
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
	
	public void altera(Contato contato) {
		String sql = "update contatos set nome=?, email=?," +
				"endereco=?, dataNascimento=? where id = ?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(
					contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			
			
			// executa
			stmt.execute();
			stmt.close();
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

	public boolean remove(Contato contato) {
		String sql = "DELETE from contatos where id = ?";
		Optional<Contato> contatoOpcional = getById(contato.getId());
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();

			return contatoOpcional.isPresent();
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


	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contato contato = extractContato(rs);

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

	public Optional<Contato> getByNome(String nome) {
		String sql = "select * from contatos " +
				"where nome = ?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();

			return extractContatoIfExists(rs);

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

	public Optional<Contato> getById(Long id) {
		String sql = "select * from contatos " +
				"where id = ?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			return extractContatoIfExists(rs);

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

	private Optional<Contato> extractContatoIfExists(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Contato contato = extractContato(rs);
			return Optional.ofNullable(contato);
		} else {
			return Optional.ofNullable(null);
		}
	}

	private Contato extractContato(ResultSet rs) throws SQLException {
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
		return contato;
	}
}
