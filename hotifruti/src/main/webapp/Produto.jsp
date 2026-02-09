<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="hotifruti.dao.ProdutoDAO" %>
<%@ page import="hotifruti.dao.CategoriaDAO" %>

<%@ page import="hotifruti.model.Produto" %>
<%@ page import="hotifruti.model.Categoria" %>

<%
    request.setCharacterEncoding("UTF-8");

    String acao = request.getParameter("acao");
    if (acao == null) acao = "listar";

    ProdutoDAO produtoDao = new ProdutoDAO();
    CategoriaDAO categoriaDao = new CategoriaDAO();

    Produto produtoEdit = null;

    // ===== EXCLUIR =====
    if ("excluir".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        produtoDao.excluir(id);
        response.sendRedirect("produto.jsp");
        return;
    }

    // ===== SALVAR / ATUALIZAR =====
    if ("salvar".equals(acao)) {
        String idStr = request.getParameter("id");
        String idCategoriaStr = request.getParameter("idCategoria");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String pesoStr = request.getParameter("peso");

        Produto p = new Produto();
        p.setNome(nome);
        p.setDescricao(descricao);

        if (idCategoriaStr != null && !idCategoriaStr.isEmpty()) {
            p.setIdCategoria(Integer.parseInt(idCategoriaStr));
        }

        if (pesoStr != null && !pesoStr.isEmpty()) {
            p.setPesoKg(new BigDecimal(pesoStr.replace(",", ".")));
        }

        if (idStr == null || idStr.isEmpty()) {
            produtoDao.salvar(p);
        } else {
            p.setIdProduto(Integer.parseInt(idStr));
            produtoDao.atualizar(p);
        }

        response.sendRedirect("produto.jsp");
        return;
    }

    // ===== EDITAR =====
    if ("editar".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        produtoEdit = produtoDao.buscarPorId(id);
    }

    // ===== LISTAS =====
    List<Produto> listaProdutos = produtoDao.listar();
    List<Categoria> listaCategorias = categoriaDao.listar();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produto</title>

<style>
    body { font-family: Arial; }
    table { border-collapse: collapse; width: 95%; }
    th, td { border: 1px solid #ccc; padding: 8px; }
    th { background: #eee; }
</style>
</head>

<body>

<h2>Cadastro de Produto</h2>

<form action="produto.jsp?acao=salvar" method="post">

    <input type="hidden" name="id"
        value="<%= produtoEdit != null ? produtoEdit.getIdProduto() : "" %>">

    Nome:<br>
    <input type="text" name="nome" required
        value="<%= produtoEdit != null ? produtoEdit.getNome() : "" %>">

    <br><br>

    Descrição:<br>
    <input type="text" name="descricao"
        value="<%= produtoEdit != null ? produtoEdit.getDescricao() : "" %>">

    <br><br>

    Categoria:<br>
    <select name="idCategoria" required>
        <option value="">Selecione</option>
        <%
            for (Categoria c : listaCategorias) {
        %>
        <option value="<%= c.getIdCategoria() %>"
            <%= produtoEdit != null && produtoEdit.getIdCategoria() == c.getIdCategoria() ? "selected" : "" %>>
            <%= c.getNome() %>
        </option>
        <%
            }
        %>
    </select>

    <br><br>

    Peso (kg):<br>
    <input type="text" name="peso"
        value="<%= produtoEdit != null ? produtoEdit.getPesoKg() : "" %>">

    <br><br>

    <button type="submit">
        <%= produtoEdit != null ? "Atualizar" : "Salvar" %>
    </button>

    <a href="produto.jsp">Cancelar</a>
</form>

<hr>

<h2>Lista de Produtos</h2>

<table>
<tr>
    <th>ID</th>
    <th>Nome</th>
    <th>Categoria</th>
    <th>Peso</th>
    <th>Ações</th>
</tr>

<%
    for (Produto p : listaProdutos) {
%>
<tr>
    <td><%= p.getIdProduto() %></td>
    <td><%= p.getNome() %></td>
    <td><%= p.getIdCategoria() %></td>
    <td><%= p.getPesoKg() %></td>
    <td>
        <a href="produto.jsp?acao=editar&id=<%= p.getIdProduto() %>">Editar</a>
        |
        <a href="produto.jsp?acao=excluir&id=<%= p.getIdProduto() %>"
           onclick="return confirm('Deseja excluir este produto?')">Excluir</a>
    </td>
</tr>
<%
    }
%>

</table>

</body>
</html>
