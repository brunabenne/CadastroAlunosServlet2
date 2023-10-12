package com.jp.senac.controller;

import java.io.IOException;
import java.util.List;

import com.jp.senac.dao.AlunoJDBCdao;
import com.jp.senac.model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ConfirmarAlteracaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String idade = request.getParameter("idade");
		String genero = request.getParameter("genero");
		String semestre = request.getParameter("semestre");
		Integer id = Integer.parseInt(request.getParameter("id"));
		String matricula = request.getParameter("matricula");

		AlunoJDBCdao dao = new AlunoJDBCdao();
		dao.alterarAluno(new Aluno (id,nome,idade, semestre, genero,matricula));

		request.getRequestDispatcher("ListarServlet").forward(request, response);

	}

}
