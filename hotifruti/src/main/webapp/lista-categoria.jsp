<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hortifruti - Categorias</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background-color: white; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f57c00; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 8px 12px; text-decoration: none; border-radius: 4px; color: white; display: inline-block; }
        .btn-add { background-color: #ff9800; margin-bottom: 20px; }
        .btn-edit { background-color: #ffa726; font-size: 12px; }
        .btn-del { background-color: #f44336; font-size: 12px; }
    </style>
</head>
<body>
    <h1>Categorias de Produtos</h1>
    
    <a href="categoria?acao=form" class="btn btn-add">＋ Nova Categoria</a>
    <a href="index.jsp" style="margin-left: 20px;">Voltar ao Painel</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cat" items="${listaCategoria}">
                <tr>
                    <td>${cat.idCategoria}</td>
                    <td>${cat.nome}</td>
                    <td>${cat.descricao}</td>
                    <td>
                        <a href="categoria?acao=editar&id=${cat.idCategoria}" class="btn btn-edit">Editar</a>
                        <a href="categoria?acao=excluir&id=${cat.idCategoria}" class="btn btn-del" onclick="return confirm('Excluir esta categoria? Isso pode afetar os produtos vinculados.')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>