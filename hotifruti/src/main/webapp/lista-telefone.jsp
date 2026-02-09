<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hortifruti - Lista de Telefones</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background-color: white; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #0277bd; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 8px 12px; text-decoration: none; border-radius: 4px; color: white; display: inline-block; }
        .btn-add { background-color: #03a9f4; margin-bottom: 20px; }
        .btn-edit { background-color: #FF9800; font-size: 12px; }
        .btn-del { background-color: #f44336; font-size: 12px; }
    </style>
</head>
<body>
    <h1>Contatos Telefônicos</h1>
    
    <a href="telefone?acao=form" class="btn btn-add">＋ Adicionar Novo Telefone</a>
    <a href="index.jsp" style="margin-left: 20px;">Voltar ao Painel</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Número</th>
                <th>Cliente (CPF/ID)</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="t" items="${listaTelefone}">
                <tr>
                    <td>${t.idTelefone}</td>
                    <td>${t.numeroTelefone}</td>
                    <td>${t.idClienteCpf}</td>
                    <td>
                        <a href="telefone?acao=editar&id=${t.idTelefone}" class="btn btn-edit">Editar</a>
                        <a href="telefone?acao=excluir&id=${t.idTelefone}" class="btn btn-del" onclick="return confirm('Excluir este telefone?')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>