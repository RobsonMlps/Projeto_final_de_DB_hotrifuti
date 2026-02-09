<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>

<%@ page import="hotifruti.dao.TelefoneDAO" %>
<%@ page import="hotifruti.dao.ClienteDAO" %>

<%@ page import="hotifruti.model.Telefone" %>
<%@ page import="hotifruti.model.Cliente" %>

<%
    request.setCharacterEncoding("UTF-8");

    String acao = request.getParameter("acao");
    if (acao == null) acao = "listar";

    TelefoneDAO telefoneDao = new TelefoneDAO();
    ClienteDAO clienteDao = new ClienteDAO();

    Telefone telefoneEdit = null;

    // ===== EXCLUIR =====
    if ("excluir".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        telefoneDao.excluir(id);
        response.sendRedirect("telefone.jsp");
        return;
    }

    // ===== SALVAR / ATUALIZAR =====
    if ("salvar".equals(acao)) {
        String idStr = request.getParameter("id");
        String idCliente = request.getParameter("idCliente");
        String numero = request.getParameter("numero");

        Telefone t = new Telefone();
        t.setIdClienteCpf(idCliente);
        t.setNumeroTelefone(numero);

        if (idStr == null || idStr.isEmpty()) {
            telefoneDao.salvar(t);
        } else {
            t.setIdTelefone(Integer.parseInt(idStr));
            telefoneDao.atualizar(t);
        }

        response.sendRedirect("telefone.jsp");
        return;
    }

    // ===== EDITAR =====
    if ("editar".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        telefoneEdit = telefoneDao.buscarPorId(id);
    }

    // ===== LISTAS =====
    List<Telefone> listaTelefones = telefoneDao.listar();
    List<Cliente> listaClientes = clienteDao.listar();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Telefone</title>

<style>
    body { font-family: Arial; }
    table { border-collapse: collapse; width: 95%; }
    th, td { border: 1px solid #ccc; padding: 8px; }
    th { background: #eee; }
</style>
</head>

<body>

<h2>Cadastro de Telefone</h2>

<form action="telefone.jsp?acao=salvar" method="post">

    <input type="hidden" name="id"
        value="<%= telefoneEdit != null ? telefoneEdit.getIdTelefone() : "" %>">

    Cliente:<br>
    <select name="idCliente" required>
        <option value="">Selecione</option>
        <%
            for (Cliente c : listaClientes) {
        %>
        <option value="<%= c.getCpf() %>"
            <%= telefoneEdit != null && telefoneEdit.getIdClienteCpf().equals(c.getCpf()) ? "selected" : "" %>>
            <%= c.getNome() %>
        </option>
        <%
            }
        %>
    </select>

    <br><br>

    Número:<br>
    <input type="text" name="numero" required
        value="<%= telefoneEdit != null ? telefoneEdit.getNumeroTelefone() : "" %>">

    <br><br>

    <button type="submit">
        <%= telefoneEdit != null ? "Atualizar" : "Salvar" %>
    </button>

    <a href="telefone.jsp">Cancelar</a>
</form>

<hr>

<h2>Lista de Telefones</h2>

<table>
<tr>
    <th>ID</th>
    <th>Cliente (CPF)</th>
    <th>Número</th>
    <th>Ações</th>
</tr>

<%
    for (Telefone t : listaTelefones) {
%>
<tr>
    <td><%= t.getIdTelefone() %></td>
    <td><%= t.getIdClienteCpf() %></td>
    <td><%= t.getNumeroTelefone() %></td>
    <td>
        <a href="telefone.jsp?acao=editar&id=<%= t.getIdTelefone() %>">Editar</a>
        |
        <a href="telefone.jsp?acao=excluir&id=<%= t.getIdTelefone() %>"
           onclick="return confirm('Deseja excluir este telefone?')">Excluir</a>
    </td>
</tr>
<%
    }
%>

</table>

</body>
</html>
