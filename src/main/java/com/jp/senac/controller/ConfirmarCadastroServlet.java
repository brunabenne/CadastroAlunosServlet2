package com.jp.senac.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

import com.jp.senac.dao.AlunoJDBCdao;
import com.jp.senac.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ConfirmarCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// Recuperando os valores informados
		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String genero = request.getParameter("genero");
		String semestre = request.getParameter("semestre");
		
		
		
		String matricula = "";
		Random random = new Random();
		LocalDate dataAtual = LocalDate.now();
		int mes = dataAtual.getMonthValue();
		int ano = dataAtual.getYear();
		int semestre1 = (mes <7) ? 1:2;
		
		matricula = String.valueOf(ano) + String.valueOf(mes) + "0" + String.valueOf(semestre1) + idade;
		for (int i = 0; i < 4; i++) {
			matricula += random.nextInt(10);
		}
		
		// Guardar no objeto aluno
		Aluno aluno = new Aluno(nome, idade,semestre, genero, matricula);
		
		AlunoJDBCdao dao = new AlunoJDBCdao();
		Aluno alunoCadastrado = dao.cadastrarAluno(aluno);

		request.setAttribute("aluno", alunoCadastrado);
		
		// Encaminhar a requisição para o JSP
		request.getRequestDispatcher("detalharAluno.jsp").forward(request, response);
		
	}

}
