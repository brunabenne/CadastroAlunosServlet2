package com.jp.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jp.senac.model.Aluno;

public class AlunoJDBCdao {

	public Connection getConexao () throws ClassNotFoundException {

		//Driver
		String driver = "com.mysql.jdbc.Driver";

		//Banco de Dados
		String url = "jdbc:mysql://localhost:3306/cadastroalunos?useTimezone=true&serverTimezone=UTC";

		//Variáveis de Login e Senha

		String user = "root";
		String password = "root";
		
		//CONECXAO BD

		Connection con =null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			System.out.println("Conectado ao Banco de Dados.");
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return con;
	}
	
	//SELECIONANDO ALUNO NO BD
	public List<Aluno> listarAlunos () throws SQLException {

		List<Aluno> alunos = new ArrayList<>();
		String query = "select * from alunos";

		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String idade = rs.getString(3);
				String semestre = rs.getString(4);
				String genero = rs.getString(5);
				String matricula = rs.getString(6);
				alunos.add(new Aluno (id,nome,idade,semestre,genero,matricula));
			}
			rs.close();
			pst.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return alunos;
	}
	
	//PESQUISAR ALUNO NO DB POR ID
	public Aluno pesquisarPorID(Integer id) {
        String query = "Select * from alunos where id =?";
        Aluno aluno = null;
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String nome = rs.getString(2);
				String idade = rs.getString(3);
				String semestre = rs.getString(4);
				String genero = rs.getString(5);
				String matricula = rs.getString(6);
				aluno = new Aluno (id,nome,idade,semestre,genero,matricula);
			}
			
			pst.close();
			con.close();
			return aluno;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
	}
		return null;
		}
	
	//EXCLUINDO ALUNOS PELO ID NO BD
	public void excluirAluno(Integer id) {
		String query = "delete from alunos where id =?";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			
			pst.executeUpdate();
			pst.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	//ALTERANDO ALUNOS NO BD
	public void alterarAluno(Aluno aluno) {
		String query = "update alunos set nome =?,idade=?,semestre=?,genero=?,matricula=? where id =?";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getIdade());
			pst.setString(3, aluno.getSemestre());
			pst.setString(4, aluno.getGenero());
			pst.setString(5, aluno.getMatricula());
			pst.setInt(6, aluno.getId());
			pst.executeUpdate();
			pst.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	//INSERINDO NOVO ALUNO NO BD
	public Aluno cadastrarAluno(Aluno aluno) {
		String query = "insert into alunos (nome, idade, semestre,genero,matricula) values (?,?,?,?,?)";
		try {
			Connection con = getConexao();
			PreparedStatement pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getIdade());
			pst.setString(3, aluno.getSemestre());
			pst.setString(4, aluno.getGenero());
			pst.setString(5, aluno.getMatricula());
			pst.executeUpdate();
			
			// Criando a ação para pegar o ID
			ResultSet rs = pst.getGeneratedKeys();
			while (rs.next()) {
				int chaveGerada = rs.getInt(1); //ID na posição 1 do banco de dados
				System.out.println("ID Gerado foi " + chaveGerada);
				aluno.setId(chaveGerada);
			}

			pst.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return aluno;
	}
	
	public List<Aluno> pesquisa(String valor, String operacao) {
		String query = "Select * from alunos where nome like %?%";
		String query2 = "Select * from alunos where matricula like %?%";
		return null;
		
	}

}
