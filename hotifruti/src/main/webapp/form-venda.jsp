<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Venda</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f4; }
        .container { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        label { font-weight: bold; display: block; margin-top: 15px; }
        select, input { width: 100%; padding: 10px; margin: 5px 0 10px 0; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        button { background-color: #7b1fa2; color: white; padding: 12px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px; font-weight: bold; }
        button:hover { background-color: #6a1b9a; }
    </style>
</head>
<body>
<div class="container">
    <h2>Nova Venda</h2>
    
    <form action="venda" method="post">
        <input type="hidden" name="id" value="${venda.idVenda}">

        <label>Selecione o Cliente:</label>
        <select name="idCliente" required>
            <option value="">-- Escolha o Cliente --</option>
            <c:forEach var="cli" items="${listaCliente}">
                <option value="${cli.idCliente}" ${cli.idCliente == venda.idCliente ? 'selected="selected"' : ''}>
                    ${cli.nome} (CPF: ${cli.idCliente})
                </option>
            </c:forEach>
        </select>

        <label>Data e Hora:</label>
        <input type="text" name="dataHora" value="${venda.dataHora}" readonly>
        <p style="font-size: 0.8em; color: #666;">* A data é registrada automaticamente pelo sistema.</p>

        <button type="submit">Finalizar Registro de Venda</button>
    </form>
    <br>
    <a href="venda?acao=listar" style="text-decoration: none; color: #666;">Cancelar e Voltar</a>
</div>
</body>
</html>