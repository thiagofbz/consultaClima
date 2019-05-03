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

}
