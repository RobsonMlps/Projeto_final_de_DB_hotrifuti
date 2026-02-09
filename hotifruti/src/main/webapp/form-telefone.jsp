<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulário de Telefone</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f4; }
        .container { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        label { font-weight: bold; display: block; margin-top: 15px; }
        input, select { width: 100%; padding: 10px; margin: 5px 0 10px 0; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        button { background-color: #0277bd; color: white; padding: 12px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px; font-weight: bold; }
        button:hover { background-color: #01579b; }
        .link-voltar { display: inline-block; margin-top: 15px; text-decoration: none; color: #666; font-size: 14px; }
    </style>
</head>
<body>
<div class="container">
    <h2>${not empty telefone.idTelefone ? 'Editar Telefone' : 'Novo Telefone'}</h2>
    
    <form action="telefone" method="post">
        <input type="hidden" name="id" value="${telefone.idTelefone}">

        <label>Número do Telefone:</label>
        <input type="text" name="numero" value="${telefone.numeroTelefone}" placeholder="(00) 00000-0000" required>

        <label>Vincular ao Cliente:</label>
        <select name="idCliente" required>
            <option value="">Selecione um Cliente</option>
            <c:forEach var="cli" items="${listaCliente}">
                <option value="${cli.idCliente}" ${cli.idCliente == telefone.idClienteCpf ? 'selected="selected"' : ''}>
                    ${cli.nome} (ID: ${cli.idCliente})
                </option>
            </c:forEach>
        </select>

        <button type="submit">Salvar Telefone</button>
    </form>
    
    <a href="telefone?acao=listar" class="link-voltar">← Cancelar e Voltar para Lista</a>
</div>
</body>
</html>