<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulário de Lote</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f4; }
        .container { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 550px; margin: auto; }
        label { font-weight: bold; display: block; margin-top: 15px; }
        select, input { width: 100%; padding: 10px; margin: 5px 0 10px 0; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        .grid { display: grid; grid-template-columns: 1fr 1fr; gap: 10px; }
        button { background-color: #455a64; color: white; padding: 12px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px; margin-top: 10px; }
        button:hover { background-color: #263238; }
    </style>
</head>
<body>
<div class="container">
    <h2>${lote != null ? 'Editar Lote' : 'Novo Lote'}</h2>
    
    <form action="lote" method="post">
        <input type="hidden" name="id" value="${lote.idLote}">

        <label>Produto:</label>
        <select name="idProduto" required>
            <option value="">-- Selecione o Produto --</option>
            <c:forEach var="p" items="${listaProdutos}">
                <option value="${p.idProduto}" ${p.idProduto == lote.idProduto ? 'selected' : ''}>
                    ${p.nome}
                </option>
            </c:forEach>
        </select>

        <label>Fornecedor:</label>
        <select name="idFornecedor" required>
            <option value="">-- Selecione o Fornecedor --</option>
            <c:forEach var="f" items="${listaFornecedor}">
                <option value="${f.idFornecedor}" ${f.idFornecedor == lote.idFornecedor ? 'selected' : ''}>
                    ${f.nome}
                </option>
            </c:forEach>
        </select>

        <div class="grid">
            <div>
                <label>Data de Entrada:</label>
                <input type="date" name="dataEntrada" value="${lote.dataEntrada}" required>
            </div>
            <div>
                <label>Data de Validade:</label>
                <input type="date" name="dataValidade" value="${lote.dataValidade}" required>
            </div>
        </div>

        <label>Custo do Lote (R$):</label>
        <input type="text" name="custo" value="${lote.custo}" placeholder="0.00" required>

        <button type="submit">Salvar Informações do Lote</button>
    </form>
    <br>
    <a href="lote?acao=listar" style="text-decoration: none; color: #666;">Voltar para Lista</a>
</div>
</body>
</html>