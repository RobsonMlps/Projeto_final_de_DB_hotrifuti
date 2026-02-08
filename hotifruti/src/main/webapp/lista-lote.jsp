<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hortifruti - Controle de Lotes</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background-color: white; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #455a64; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 8px 12px; text-decoration: none; border-radius: 4px; color: white; display: inline-block; }
        .btn-add { background-color: #607d8b; margin-bottom: 20px; }
        .btn-edit { background-color: #FF9800; font-size: 12px; }
        .btn-del { background-color: #f44336; font-size: 12px; }
    </style>
</head>
<body>
    <h1>Estoque e Lotes</h1>
    
    <a href="lote?acao=form" class="btn btn-add">＋ Registrar Novo Lote</a>
    <a href="index.jsp" style="margin-left: 20px;">Voltar ao Painel</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Produto</th>
                <th>Fornecedor</th>
                <th>Custo (R$)</th>
                <th>Entrada</th>
                <th>Validade</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="l" items="${listaLote}">
                <tr>
                    <td>${l.idLote}</td>
                    <td>${l.nomeProduto}</td>
                    <td>${l.nomeFornecedor}</td>
                    <td>${l.custo}</td>
                    <td>${l.dataEntrada}</td>
                    <td>${l.dataValidade}</td>
                    <td>
                        <a href="lote?acao=editar&id=${l.idLote}" class="btn btn-edit">Editar</a>
                        <a href="lote?acao=excluir&id=${l.idLote}" class="btn btn-del" onclick="return confirm('Excluir este lote?')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>