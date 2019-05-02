package br.com.clima.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.clima.Cidade;
import br.com.clima.ClimaCidade;

public class CidadeDao {
	
	//a conexão com o banco de dados
	private Connection connection;
	
	//Construtor ContatoDao para abertura de conexão diretamente na classe
	public CidadeDao() throws SQLException{
		this.connection = ConnectionFactory.getInstance().getConnection();
	}
	
	//método para adição de tarefas
	public void adiciona(Cidade cidade) {
		String sql = "insert into cidade" + 
					"(codPais, cidade)" +
					"values (?,?)";
		try {
			//prepara statement para inclusão
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//seta os valores para inclusão
			stmt.setString(1, cidade.getCodPais());
			stmt.setString(2, cidade.getCidade());
			
			//executa
			stmt.execute();
			stmt.close();
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//método para listar tarefas
	public List<Cidade> getLista() throws Exception{
		
		String sql = "select * from cidade";
		
		try {
			List<Cidade> cidades = new ArrayList<Cidade>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				//criando o objeto Cidades
				Cidade cidade = new Cidade();
				cidade.setCodPais(rs.getString("codPais"));
				cidade.setCidade(rs.getString("cidade")!=null?rs.getString("cidade"):"");
				
				cidades.add(cidade);
			}
			
			rs.close();
			stmt.close();
			return cidades;
			
		}catch (SQLException e){
			//teste classe DAOException
			throw new Exception(e);
		}
		
	}

	//método para remover tarefa
	public void remove(ClimaCidade tarefa) {
		String sql = "delete from tarefas where id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, tarefa.getId());

			//executa query
			stmt.execute();
			stmt.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}

	public ClimaCidade buscaPorId(Long id) {
		String sql = "select * from tarefas where id = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()){
				ClimaCidade tarefa = new ClimaCidade();
				//obtem dados
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescrição(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));
				
				//montando a data atraves do Calendar
				Calendar data = Calendar.getInstance();
				Date datad = rs.getDate("dataFinalizacao");
				if(datad !=null) {
					data.setTime(datad);
					tarefa.setDataFinalizacao(data);
				}				
				
				//executa
				rs.close();
				return tarefa;
			}else{
				return null;
			}			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void altera(ClimaCidade tarefa) throws SQLException {
		
		String sql = "update tarefas "
				+ "set descricao = ?, finalizado = ?, dataFinalizacao = ? "
				+ "where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		try {
			stmt.setString(1, tarefa.getDescrição());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
			stmt.setLong(4, tarefa.getId());
			stmt.execute();
			stmt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void finaliza(Long id) throws SQLException {
		String sql = "update tarefas "
				+ "set finalizado = ?, dataFinalizacao = ?"
				+ "where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		try {
			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.execute();
			stmt.close();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
