<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="hotifruti.dao.ClienteDAO" %>
<%@ page import="hotifruti.model.Cliente" %>

<%
    request.setCharacterEncoding("UTF-8");

    String acao = request.getParameter("acao");
    if (acao == null) acao = "listar";

    ClienteDAO dao = new ClienteDAO();
    Cliente clienteEdit = null;

    // ===== EXCLUIR =====
    if ("excluir".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.excluir(id);
        response.sendRedirect("cliente.jsp");
        return;
    }

    // ===== SALVAR / ATUALIZAR =====
    if ("salvar".equals(acao)) {
        String idStr = request.getParameter("id");
        String nome = request.getParameter("nome");
        String cpf  = request.getParameter("cpf");

        Cliente c = new Cliente();
        c.setNome(nome);
        c.setCpf(cpf);

        if (idStr == null || idStr.isEmpty()) {
            dao.salvar(c);
        } else {
            c.setIdCliente(Integer.parseInt(idStr));
            dao.atualizar(c);
        }

        response.sendRedirect("cliente.jsp");
        return;
    }

    // ===== EDITAR =====
    if ("editar".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        clienteEdit = dao.buscarPorId(id);
    }

    // ===== LISTAR =====
    List<Cliente> lista = dao.listar();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cliente</title>

<style>
    body { font-family: Arial; }
    table { border-collapse: collapse; width: 70%; }
    th, td { border: 1px solid #ccc; padding: 8px; }
    th { background: #eee; }
</style>
</head>

<body>

<h2>Cadastro de Cliente</h2>

<form action="cliente.jsp?acao=salvar" method="post">

    <input type="hidden" name="id"
        value="<%= clienteEdit != null ? clienteEdit.getIdCliente() : "" %>">

    Nome:<br>
    <input type="text" name="nome" required
        value="<%= clienteEdit != null ? clienteEdit.getNome() : "" %>">
    <br><br>

    CPF:<br>
    <input type="text" name="cpf" required
        value="<%= clienteEdit != null ? clienteEdit.getCpf() : "" %>">
    <br><br>

    <button type="submit">
        <%= clienteEdit != null ? "Atualizar" : "Salvar" %>
    </button>

    <a href="cliente.jsp">Cancelar</a>
</form>

<hr>

<h2>Lista de Clientes</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>CPF</th>
        <th>Ações</th>
    </tr>

<%
    for (Cliente c : lista) {
%>
    <tr>
        <td><%= c.getIdCliente() %></td>
        <td><%= c.getNome() %></td>
        <td><%= c.getCpf() %></td>
        <td>
            <a href="cliente.jsp?acao=editar&id=<%= c.getIdCliente() %>">Editar</a>
            |
            <a href="cliente.jsp?acao=excluir&id=<%= c.getIdCliente() %>"
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
