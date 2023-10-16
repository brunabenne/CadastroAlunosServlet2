<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.jp.senac.model.Aluno" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Alunos</title>
</head>
<body>

<%
	String usuario = (String) session.getAttribute("usuario");

	if (usuario == null ) {
		response.sendRedirect("index.jsp");
	}	
	out.print("Bem vindo, "+usuario+"<br>");
%>

Clique <a href="cadastrarAluno.jsp">aqui</a> para cadastrar um novo aluno

<%List<Aluno> listaAlunos = (List<Aluno>) request.getAttribute("listaAlunos");  %>

<% if (listaAlunos == null) { %>
		<h3>Nenhum aluno cadastrado</h3>
<% } else { %>
		<h2>Alunos cadastrados</h2>
		<table border="1">
			<tr>
				<th>Detalhar</th>
				<th>Matrícula</th>
				<th>Nome</th>
				<th>Idade</th>
				<th>Sexo</th>
				<th>Semestre</th>
				<th>Excluir</th>
			</tr>
			<% for (Aluno aluno : listaAlunos) { %>
			<tr>
				<td><a href="DetalharServlet?id=<%=aluno.getId()%>"><%=aluno.getId() %></a></td>
				<td><%=aluno.getMatricula()%> </td>
				<td><%=aluno.getNome()%> </td>
				<td><%=aluno.getIdade()%> </td>
				<td><%=aluno.getGenero()%> </td>
				<td><%=aluno.getSemestre()%> </td>
				<td><a href="ExcluirServlet?id=<%=aluno.getId()%>">Excluir</a></td>
			</tr>
			<% } %>
		</table>
		<br>
		<form action="PesquisarServlet"  method="post">
			<select name="tipoPesquisa">
				<option value="nome">Nome</option>
				<option value="matricula">Matricula</option>
			</select>
			<input type="text" name="valor">
			<input type="submit">
		</form>
		<br>
<% } %>
<a href="ListarServlet">Voltar</a>
<a href="LogoutServlet">Sair</a>
</body>
</html>