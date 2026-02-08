<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hortifruti - Registro de Vendas</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background-color: white; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #7b1fa2; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 8px 12px; text-decoration: none; border-radius: 4px; color: white; display: inline-block; }
        .btn-add { background-color: #9c27b0; margin-bottom: 20px; }
        .btn-del { background-color: #f44336; font-size: 12px; }
    </style>
</head>
<body>
    <h1>Vendas Realizadas</h1>
    
    <a href="venda?acao=form" class="btn btn-add">＋ Registrar Nova Venda</a>
    <a href="index.jsp" style="margin-left: 20px;">Voltar ao Painel</a>

    <table>
        <thead>
            <tr>
                <th>ID Venda</th>
                <th>ID Cliente (CPF)</th>
                <th>Data e Hora</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="v" items="${listaVenda}">
                <tr>
                    <td>${v.idVenda}</td>
                    <td>${v.idCliente}</td>
                    <td>
                        ${v.dataHora}
                    </td>
                    <td>
                        <a href="venda?acao=excluir&id=${v.idVenda}" class="btn btn-del" onclick="return confirm('Excluir registro de venda?')">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>