package com.jp.senac.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.jp.senac.dao.AlunoJDBCdao;
import com.jp.senac.model.Aluno;

/**
 * Servlet implementation class RecuperarServlet
 */
public class RecuperarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AlunoJDBCdao dao = new AlunoJDBCdao();
		try {
			List<Aluno> listaAlunos = dao.listarAlunos();
			request.setAttribute("listaAlunos", listaAlunos);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("alterarAluno.jsp").forward(request, response);
	}


}
