<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="hotifruti.dao.CategoriaDAO" %>
<%@ page import="hotifruti.model.Categoria" %>

<%
    request.setCharacterEncoding("UTF-8");

    String acao = request.getParameter("acao");
    if (acao == null) acao = "listar";

    CategoriaDAO dao = new CategoriaDAO();
    Categoria categoriaEdit = null;

    // ===== EXCLUIR =====
    if ("excluir".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.excluir(id);
        response.sendRedirect("categoria.jsp");
        return;
    }

    // ===== SALVAR / ATUALIZAR =====
    if ("salvar".equals(acao)) {
        String idStr = request.getParameter("id");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        Categoria c = new Categoria(0, nome, descricao);

        if (idStr == null || idStr.isEmpty()) {
            dao.salvar(c);
        } else {
            c.setIdCategoria(Integer.parseInt(idStr));
            dao.atualizar(c);
        }

        response.sendRedirect("categoria.jsp");
        return;
    }

    // ===== EDITAR =====
    if ("editar".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        categoriaEdit = dao.buscarPorId(id);
    }

    // ===== LISTAR =====
    List<Categoria> lista = dao.listar();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categoria</title>

<style>
    body { font-family: Arial; }
    table { border-collapse: collapse; width: 70%; }
    th, td { border: 1px solid #ccc; padding: 8px; }
    th { background: #eee; }
</style>
</head>

<body>

<h2>Cadastro de Categoria</h2>

<form action="categoria.jsp?acao=salvar" method="post">

    <input type="hidden" name="id"
        value="<%= categoriaEdit != null ? categoriaEdit.getIdCategoria() : "" %>">

    Nome:<br>
    <input type="text" name="nome" required
        value="<%= categoriaEdit != null ? categoriaEdit.getNome() : "" %>">
    <br><br>

    Descrição:<br>
    <textarea name="descricao"><%= categoriaEdit != null ? categoriaEdit.getDescricao() : "" %></textarea>
    <br><br>

    <button type="submit">
        <%= categoriaEdit != null ? "Atualizar" : "Salvar" %>
    </button>

    <a href="categoria.jsp">Cancelar</a>
</form>

<hr>

<h2>Lista de Categorias</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Ações</th>
    </tr>

<%
    for (Categoria c : lista) {
%>
    <tr>
        <td><%= c.getIdCategoria() %></td>
        <td><%= c.getNome() %></td>
        <td><%= c.getDescricao() %></td>
        <td>
            <a href="categoria.jsp?acao=editar&id=<%= c.getIdCategoria() %>">Editar</a>
            |
            <a href="categoria.jsp?acao=excluir&id=<%= c.getIdCategoria() %>"
               onclick="return confirm('Deseja excluir?')">
               Excluir
            </a>
        </td>
    </tr>
<%
    }
%>

</table>

</body>
</html>
