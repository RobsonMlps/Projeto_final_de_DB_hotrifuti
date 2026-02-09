<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="hotifruti.dao.FornecedorDAO" %>
<%@ page import="hotifruti.model.Fornecedor" %>

<%
    request.setCharacterEncoding("UTF-8");

    String acao = request.getParameter("acao");
    if (acao == null) acao = "listar";

    FornecedorDAO dao = new FornecedorDAO();
    Fornecedor fornecedorEdit = null;

    // ===== EXCLUIR =====
    if ("excluir".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.excluir(id);
        response.sendRedirect("fornecedor.jsp");
        return;
    }

    // ===== SALVAR / ATUALIZAR =====
    if ("salvar".equals(acao)) {
        String idStr = request.getParameter("id");
        String nome  = request.getParameter("nome");
        String cnpj  = request.getParameter("cnpj");
        String email = request.getParameter("email");

        Fornecedor f = new Fornecedor();
        f.setNome(nome);
        f.setCnpj(cnpj);
        f.setEmail(email);

        if (idStr == null || idStr.isEmpty()) {
            dao.salvar(f);
        } else {
            f.setIdFornecedor(Integer.parseInt(idStr));
            dao.atualizar(f);
        }

        response.sendRedirect("fornecedor.jsp");
        return;
    }

    // ===== EDITAR =====
    if ("editar".equals(acao)) {
        int id = Integer.parseInt(request.getParameter("id"));
        fornecedorEdit = dao.buscarPorId(id);
    }

    // ===== LISTAR =====
    List<Fornecedor> lista = dao.listar();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fornecedor</title>

<style>
    body { font-family: Arial; }
    table { border-collapse: collapse; width: 80%; }
    th, td { border: 1px solid #ccc; padding: 8px; }
    th { background: #eee; }
</style>
</head>

<body>

<h2>Cadastro de Fornecedor</h2>

<form action="fornecedor.jsp?acao=salvar" method="post">

    <input type="hidden" name="id"
        value="<%= fornecedorEdit != null ? fornecedorEdit.getIdFornecedor() : "" %>">

    Nome:<br>
    <input type="text" name="nome" required
        value="<%= fornecedorEdit != null ? fornecedorEdit.getNome() : "" %>">
    <br><br>

    CNPJ:<br>
    <input type="text" name="cnpj" required
        value="<%= fornecedorEdit != null ? fornecedorEdit.getCnpj() : "" %>">
    <br><br>

    Email:<br>
    <input type="email" name="email" required
        value="<%= fornecedorEdit != null ? fornecedorEdit.getEmail() : "" %>">
    <br><br>

    <button type="submit">
        <%= fornecedorEdit != null ? "Atualizar" : "Salvar" %>
    </button>

    <a href="fornecedor.jsp">Cancelar</a>
</form>

<hr>

<h2>Lista de Fornecedores</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>CNPJ</th>
        <th>Email</th>
        <th>Ações</th>
    </tr>

<%
    for (Fornecedor f : lista) {
%>
    <tr>
        <td><%= f.getIdFornecedor() %></td>
        <td><%= f.getNome() %></td>
        <td><%= f.getCnpj() %></td>
        <td><%= f.getEmail() %></td>
        <td>
            <a href="fornecedor.jsp?acao=editar&id=<%= f.getIdFornecedor() %>">
                Editar
            </a>
            |
            <a href="fornecedor.jsp?acao=excluir&id=<%= f.getIdFornecedor() %>"
               onclick="return confirm('Deseja excluir este fornecedor?')">
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
