<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="hotifruti.dao.ItemVendaDAO" %>
<%@ page import="hotifruti.dao.VendaDAO" %>
<%@ page import="hotifruti.dao.ProdutoDAO" %>

<%@ page import="hotifruti.model.ItemVenda" %>
<%@ page import="hotifruti.model.Venda" %>
<%@ page import="hotifruti.model.Produto" %>

<%
    request.setCharacterEncoding("UTF-8");

    String acao = request.getParameter("acao");
    if (acao == null) acao = "listar";

    ItemVendaDAO itemDao = new ItemVendaDAO();
    VendaDAO vendaDao = new VendaDAO();
    ProdutoDAO produtoDao = new ProdutoDAO();

    ItemVenda itemEdit = null;

    // ===== EXCLUIR =====
    if ("excluir".equals(acao)) {
        int idVenda = Integer.parseInt(request.getParameter("idVenda"));
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));

        itemDao.excluir(idVenda, idProduto);
        response.sendRedirect("itemvenda.jsp");
        return;
    }

    // ===== SALVAR / ATUALIZAR =====
    if ("salvar".equals(acao)) {
        int idVenda = Integer.parseInt(request.getParameter("idVenda"));
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String precoStr = request.getParameter("preco");
        String tipoAcao = request.getParameter("tipoAcao");

        ItemVenda item = new ItemVenda();
        item.setIdVenda(idVenda);
        item.setIdProduto(idProduto);
        item.setQuantidadeVendida(quantidade);

        if (precoStr != null) {
            item.setPrecoUnidade(new BigDecimal(precoStr.replace(",", ".")));
        }

        if ("editar".equals(tipoAcao)) {
            itemDao.atualizar(item);
        } else {
            itemDao.salvar(item);
        }

        response.sendRedirect("itemvenda.jsp");
        return;
    }

    // ===== EDITAR =====
    if ("editar".equals(acao)) {
        int idVenda = Integer.parseInt(request.getParameter("idVenda"));
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));

        itemEdit = itemDao.buscarPorId(idVenda, idProduto);
    }

    // ===== LISTAS =====
    List<ItemVenda> listaItens = itemDao.listar();
    List<Venda> listaVendas = vendaDao.listar();
    List<Produto> listaProdutos = produtoDao.listar();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item da Venda</title>

<style>
    body { font-family: Arial; }
    table { border-collapse: collapse; width: 90%; }
    th, td { border: 1px solid #ccc; padding: 8px; }
    th { background: #eee; }
</style>
</head>

<body>

<h2>Cadastro de Item da Venda</h2>

<form action="itemvenda.jsp?acao=salvar" method="post">

    <input type="hidden" name="tipoAcao"
        value="<%= itemEdit != null ? "editar" : "novo" %>">

    Venda:<br>
    <select name="idVenda" required <%= itemEdit != null ? "disabled" : "" %>>
        <option value="">Selecione</option>
        <%
            for (Venda v : listaVendas) {
        %>
        <option value="<%= v.getIdVenda() %>"
            <%= itemEdit != null && itemEdit.getIdVenda() == v.getIdVenda() ? "selected" : "" %>>
            Venda #<%= v.getIdVenda() %>
        </option>
        <%
            }
        %>
    </select>

    <%-- workaround para disabled --%>
    <% if (itemEdit != null) { %>
        <input type="hidden" name="idVenda" value="<%= itemEdit.getIdVenda() %>">
    <% } %>

    <br><br>

    Produto:<br>
    <select name="idProduto" required <%= itemEdit != null ? "disabled" : "" %>>
        <option value="">Selecione</option>
        <%
            for (Produto p : listaProdutos) {
        %>
        <option value="<%= p.getIdProduto() %>"
            <%= itemEdit != null && itemEdit.getIdProduto() == p.getIdProduto() ? "selected" : "" %>>
            <%= p.getNome() %>
        </option>
        <%
            }
        %>
    </select>

    <% if (itemEdit != null) { %>
        <input type="hidden" name="idProduto" value="<%= itemEdit.getIdProduto() %>">
    <% } %>

    <br><br>

    Quantidade:<br>
    <input type="number" name="quantidade" required
        value="<%= itemEdit != null ? itemEdit.getQuantidadeVendida() : "" %>">

    <br><br>

    Preço Unitário:<br>
    <input type="text" name="preco" required
        value="<%= itemEdit != null ? itemEdit.getPrecoUnidade() : "" %>">

    <br><br>

    <button type="submit">
        <%= itemEdit != null ? "Atualizar" : "Salvar" %>
    </button>

    <a href="itemvenda.jsp">Cancelar</a>
</form>

<hr>

<h2>Lista de Itens de Venda</h2>

<table>
<tr>
    <th>Venda</th>
    <th>Produto</th>
    <th>Quantidade</th>
    <th>Preço Unit.</th>
    <th>Ações</th>
</tr>

<%
    for (ItemVenda iv : listaItens) {
%>
<tr>
    <td><%= iv.getIdVenda() %></td>
    <td><%= iv.getIdProduto() %></td>
    <td><%= iv.getQuantidadeVendida() %></td>
    <td><%= iv.getPrecoUnidade() %></td>
    <td>
        <a href="itemvenda.jsp?acao=editar&idVenda=<%= iv.getIdVenda() %>&idProduto=<%= iv.getIdProduto() %>">
            Editar
        </a>
        |
        <a href="itemvenda.jsp?acao=excluir&idVenda=<%= iv.getIdVenda() %>&idProduto=<%= iv.getIdProduto() %>"
           onclick="return confirm('Deseja excluir este item?')">
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
