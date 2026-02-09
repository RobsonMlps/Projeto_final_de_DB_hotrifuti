<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hortifruti - Lista de Produtos</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 8px 12px; text-decoration: none; border-radius: 4px; color: white; }
        .btn-add { background-color: #2196F3; margin-bottom: 20px; display: inline-block; }
        .btn-edit { background-color: #FF9800; font-size: 12px; }
        .btn-del { background-color: #f44336; font-size: 12px; }
    </style>
</head>
<body>
    <h1>Gerenciamento de Produtos</h1>
    
    <a href="produto?acao=form" class="btn btn-add">＋ Cadastrar Novo Produto</a>
    <a href="index.jsp" style="margin-left: 20px;">Voltar ao Início</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Peso (kg)</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${listaProdutos}">
                <tr>
                    <td>${p.idProduto}</td>
                    <td>${p.nome}</td>
                    <td>${p.descricao}</td>
                    <td>${p.pesoKg}</td>
                    <td>
                        <a href="produto?acao=editar&id=${p.idProduto}" class="btn btn-edit">Editar</a>
                        <a href="produto?acao=excluir&id=${p.idProduto}" class="btn btn-del" onclick="return confirm('Tem certeza?')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>