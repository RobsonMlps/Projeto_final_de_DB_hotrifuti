<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hortifruti - Itens Vendidos</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background-color: white; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #e65100; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .btn { padding: 8px 12px; text-decoration: none; border-radius: 4px; color: white; display: inline-block; }
        .btn-add { background-color: #ff9800; margin-bottom: 20px; }
        .btn-del { background-color: #f44336; font-size: 12px; }
    </style>
</head>
<body>
    <h1>Detalhes de Itens Vendidos</h1>
    
    <a href="itemvenda?acao=form" class="btn btn-add">＋ Adicionar Item à Venda</a>
    <a href="index.jsp" style="margin-left: 20px;">Voltar ao Painel</a>

    <table>
        <thead>
            <tr>
                <th>ID Venda</th>
                <th>ID Produto</th>
                <th>Qtd Vendida</th>
                <th>Preço Unitário</th>
                <th>Subtotal</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${listaItemVenda}">
                <tr>
                    <td>${item.idVenda}</td>
                    <td>${item.idProduto}</td>
                    <td>${item.quantidadeVendida}</td>
                    <td>R$ ${item.precoUnidade}</td>
                    <td><strong>R$ ${item.quantidadeVendida * item.precoUnidade}</strong></td>
                    <td>
                        <a href="itemvenda?acao=excluir&idVenda=${item.idVenda}&idProduto=${item.idProduto}" 
                           class="btn btn-del" onclick="return confirm('Remover este item?')">Remover</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>