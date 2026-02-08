<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hortifruti - Lista de Clientes</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #2e7d32; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 8px 12px; text-decoration: none; border-radius: 4px; color: white; display: inline-block; }
        .btn-add { background-color: #2196F3; margin-bottom: 20px; }
        .btn-edit { background-color: #FF9800; font-size: 12px; }
        .btn-del { background-color: #f44336; font-size: 12px; }
    </style>
</head>
<body>
    <h1>Gerenciamento de Clientes</h1>
    
    <a href="cliente?acao=form" class="btn btn-add">＋ Cadastrar Novo Cliente</a>
    <a href="index.jsp" style="margin-left: 20px;">Voltar ao Painel</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${listaCliente}">
                <tr>
                    <td>${c.idCliente}</td>
                    <td>${c.nome}</td>
                    <td>${c.cpf}</td>
                    <td>
                        <a href="cliente?acao=editar&id=${c.idCliente}" class="btn btn-edit">Editar</a>
                        <a href="cliente?acao=excluir&id=${c.idCliente}" class="btn btn-del" onclick="return confirm('Excluir cliente?')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>