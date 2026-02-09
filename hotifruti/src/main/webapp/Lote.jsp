<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.time.LocalDate" %>

<%@ page import="hotifruti.dao.LoteDAO" %>
<%@ page import="hotifruti.dao.ProdutoDAO" %>
<%@ page import="hotifruti.dao.FornecedorDAO" %>

<%@ page import="hotifruti.model.Lote" %>
<%@ page import="hotifruti.model.Produto" %>
<%@ page import="hotifruti.model.Fornecedor" %>

<%
    request.setCharacterEncoding("UTF-8");

    String acao = request.getParameter("acao");
    if (acao == null) acao = "listar";

    LoteDAO loteDao = new LoteDAO();
    ProdutoDAO produtoDao = new ProdutoDAO();
    FornecedorDAO fornecedorDao = new FornecedorDAO();

    Lote loteEdit = null;

    // ===== EXCLUIR =====
    if ("excluir".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        loteDao.excluir(id);
        response.sendRedirect("lote.jsp");
        return;
    }

    // ===== SALVAR / ATUALIZAR =====
    if ("salvar".equals(acao)) {
        String idStr = request.getParameter("id");
        String idProdutoStr = request.getParameter("idProduto");
        String idFornecedorStr = request.getParameter("idFornecedor");
        String dataStr = request.getParameter("dataValidade");
        String qtdStr = request.getParameter("quantidade");
        String custoStr = request.getParameter("custo");

        Lote lote = new Lote();

        if (idProdutoStr != null)
            lote.setIdProduto(Integer.parseInt(idProdutoStr));

        if (idFornecedorStr != null)
            lote.setIdFornecedor(Integer.parseInt(idFornecedorStr));

        if (dataStr != null && !dataStr.isEmpty())
            lote.setDataValidade(LocalDate.parse(dataStr));

        if (qtdStr != null)
            lote.setQuantidade(Integer.parseInt(qtdStr));

        if (custoStr != null && !custoStr.isEmpty())
            lote.setCusto(new BigDecimal(custoStr.replace(",", ".")));

        if (idStr == null || idStr.isEmpty()) {
            loteDao.salvar(lote);
        } else {
            lote.setIdLote(Integer.parseInt(idStr));
            loteDao.atualizar(lote);
        }

        response.sendRedirect("lote.jsp");
        return;
    }

    // ===== EDITAR =====
    if ("editar".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        loteEdit = loteDao.buscarPorId(id);
    }

    // ===== LISTAS =====
    List<Lote> listaLotes = loteDao.listar();
    List<Produto> listaProdutos = produtoDao.listar();
    List<Fornecedor> listaFornecedores = fornecedorDao.listar();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lote</title>

<style>
    body { font-family: Arial; }
    table { border-collapse: collapse; width: 95%; }
    th, td { border: 1px solid #ccc; padding: 8px; }
    th { background: #eee; }
</style>
</head>

<body>

<h2>Cadastro de Lote</h2>

<form action="lote.jsp?acao=salvar" method="post">

    <input type="hidden" name="id"
        value="<%= loteEdit != null ? loteEdit.getIdLote() : "" %>">

    Produto:<br>
    <select name="idProduto" required>
        <option value="">Selecione</option>
        <%
            for (Produto p : listaProdutos) {
        %>
        <option value="<%= p.getIdProduto() %>"
            <%= loteEdit != null && loteEdit.getIdProduto() == p.getIdProduto() ? "selected" : "" %>>
            <%= p.getNome() %>
        </option>
        <%
            }
        %>
    </select>

    <br><br>

    Fornecedor:<br>
    <select name="idFornecedor" required>
        <option value="">Selecione</option>
        <%
            for (Fornecedor f : listaFornecedores) {
        %>
        <option value="<%= f.getIdFornecedor() %>"
            <%= loteEdit != null && loteEdit.getIdFornecedor() == f.getIdFornecedor() ? "selected" : "" %>>
            <%= f.getNome() %>
        </option>
        <%
            }
        %>
    </select>

    <br><br>

    Data de Validade:<br>
    <input type="date" name="dataValidade" required
        value="<%= loteEdit != null && loteEdit.getDataValidade() != null ? loteEdit.getDataValidade() : "" %>">

    <br><br>

    Quantidade:<br>
    <input type="number" name="quantidade" required
        value="<%= loteEdit != null ? loteEdit.getQuantidade() : "" %>">

    <br><br>

    Custo:<br>
    <input type="text" name="custo" required
        value="<%= loteEdit != null ? loteEdit.getCusto() : "" %>">

    <br><br>

    <button type="submit">
        <%= loteEdit != null ? "Atualizar" : "Salvar" %>
    </button>

    <a href="lote.jsp">Cancelar</a>
</form>

<hr>

<h2>Lista de Lotes</h2>

<table>
<tr>
    <th>ID</th>
    <th>Produto</th>
    <th>Fornecedor</th>
    <th>Validade</th>
    <th>Quantidade</th>
    <th>Custo</th>
    <th>Ações</th>
</tr>

<%
    for (Lote l : listaLotes) {
%>
<tr>
    <td><%= l.getIdLote() %></td>
    <td><%= l.getIdProduto() %></td>
    <td><%= l.getIdFornecedor() %></td>
    <td><%= l.getDataValidade() %></td>
    <td><%= l.getQuantidade() %></td>
    <td><%= l.getCusto() %></td>
    <td>
        <a href="lote.jsp?acao=editar&id=<%= l.getIdLote() %>">Editar</a>
        |
        <a href="lote.jsp?acao=excluir&id=<%= l.getIdLote() %>"
           onclick="return confirm('Deseja excluir este lote?')">Excluir</a>
    </td>
</tr>
<%
    }
%>

</table>

</body>
</html>
