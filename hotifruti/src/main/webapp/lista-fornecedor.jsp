<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hortifruti - Lista de Fornecedores</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background-color: white; }
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
    <h1>Gerenciamento de Fornecedores</h1>
    
    <a href="fornecedor?acao=form" class="btn btn-add">＋ Cadastrar Novo Fornecedor</a>
    <a href="index.jsp" style="margin-left: 20px;">Voltar ao Painel</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome/Empresa</th>
                <th>CNPJ</th>
                <th>E-mail</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="f" items="${listaFornecedor}">
                <tr>
                    <td>${f.idFornecedor}</td>
                    <td>${f.nome}</td>
                    <td>${f.cnpj}</td>
                    <td>${f.email}</td>
                    <td>
                        <a href="fornecedor?acao=editar&id=${f.idFornecedor}" class="btn btn-edit">Editar</a>
                        <a href="fornecedor?acao=excluir&id=${f.idFornecedor}" class="btn btn-del" onclick="return confirm('Excluir fornecedor?')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>