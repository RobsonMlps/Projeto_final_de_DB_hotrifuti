<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %>

<%@ page import="hotifruti.dao.VendaDAO" %>
<%@ page import="hotifruti.dao.ClienteDAO" %>

<%@ page import="hotifruti.model.Venda" %>
<%@ page import="hotifruti.model.Cliente" %>

<%
    request.setCharacterEncoding("UTF-8");

    String acao = request.getParameter("acao");
    if (acao == null) acao = "listar";

    VendaDAO vendaDao = new VendaDAO();
    ClienteDAO clienteDao = new ClienteDAO();

    Venda vendaEdit = null;

    // ===== EXCLUIR =====
    if ("excluir".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        vendaDao.excluir(id);
        response.sendRedirect("venda.jsp");
        return;
    }

    // ===== SALVAR / ATUALIZAR =====
    if ("salvar".equals(acao)) {
        String idStr = request.getParameter("id");
        String idClienteStr = request.getParameter("idCliente");
        String dataHoraStr = request.getParameter("dataHora");

        Venda v = new Venda();

        if (idClienteStr != null && !idClienteStr.isEmpty()) {
            v.setIdCliente(Integer.parseInt(idClienteStr));
        }

        if (dataHoraStr != null && !dataHoraStr.isEmpty()) {
            v.setDataHora(LocalDateTime.parse(dataHoraStr));
        } else {
            v.setDataHora(LocalDateTime.now());
        }

        if (idStr == null || idStr.isEmpty()) {
            vendaDao.salvar(v);
        } else {
            v.setIdVenda(Integer.parseInt(idStr));
            vendaDao.atualizar(v);
        }

        response.sendRedirect("venda.jsp");
        return;
    }

    // ===== EDITAR =====
    if ("editar".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        vendaEdit = vendaDao.buscarPorId(id);
    }

    // ===== LISTAS =====
    List<Venda> listaVendas = vendaDao.listar();
    List<Cliente> listaClientes = clienteDao.listar();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Venda</title>

<style>
    body { font-family: Arial; }
    table { border-collapse: collapse; width: 95%; }
    th, td { border: 1px solid #ccc; padding: 8px; }
    th { background: #eee; }
</style>
</head>

<body>

<h2>Cadastro de Venda</h2>

<form action="venda.jsp?acao=salvar" method="post">

    <input type="hidden" name="id"
        value="<%= vendaEdit != null ? vendaEdit.getIdVenda() : "" %>">

    Cliente:<br>
    <select name="idCliente" required>
        <option value="">Selecione</option>
        <%
            for (Cliente c : listaClientes) {
        %>
        <option value="<%= c.getIdCliente() %>"
            <%= vendaEdit != null && vendaEdit.getIdCliente() == c.getIdCliente() ? "selected" : "" %>>
            <%= c.getNome() %>
        </option>
        <%
            }
        %>
    </select>

    <br><br>

    Data e Hora:<br>
    <input type="datetime-local" name="dataHora"
        value="<%= vendaEdit != null && vendaEdit.getDataHora() != null
                ? vendaEdit.getDataHora().toString().substring(0,16)
                : "" %>">

    <br><br>

    <button type="submit">
        <%= vendaEdit != null ? "Atualizar" : "Salvar" %>
    </button>

    <a href="venda.jsp">Cancelar</a>
</form>

<hr>

<h2>Lista de Vendas</h2>

<table>
<tr>
    <th>ID</th>
    <th>Cliente</th>
    <th>Data/Hora</th>
    <th>Ações</th>
</tr>

<%
    for (Venda v : listaVendas) {
%>
<tr>
    <td><%= v.getIdVenda() %></td>
    <td><%= v.getIdCliente() %></td>
    <td><%= v.getDataHora() %></td>
    <td>
        <a href="venda.jsp?acao=editar&id=<%= v.getIdVenda() %>">Editar</a>
        |
        <a href="venda.jsp?acao=excluir&id=<%= v.getIdVenda() %>"
           onclick="return confirm('Deseja excluir esta venda?')">Excluir</a>
    </td>
</tr>
<%
    }
%>

</table>

</body>
</html>
