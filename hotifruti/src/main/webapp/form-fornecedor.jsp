<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulário de Fornecedor</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f4; }
        .container { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        label { font-weight: bold; display: block; margin-top: 10px; }
        input { width: 100%; padding: 10px; margin: 5px 0 15px 0; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        button { background-color: #2e7d32; color: white; padding: 12px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px; }
        button:hover { background-color: #1b5e20; }
    </style>
</head>
<body>
<div class="container">
    <h2>${fornecedor != null ? 'Editar Fornecedor' : 'Novo Fornecedor'}</h2>
    
    <form action="fornecedor" method="post">
        <input type="hidden" name="id" value="${fornecedor.idFornecedor}">

        <label>Nome do Fornecedor / Razão Social:</label>
        <input type="text" name="nome" value="${fornecedor.nome}" required>

        <label>CNPJ:</label>
        <input type="text" name="cnpj" value="${fornecedor.cnpj}" placeholder="00.000.000/0001-00">

        <label>E-mail de Contato:</label>
        <input type="email" name="email" value="${fornecedor.email}" placeholder="exemplo@empresa.com">

        <button type="submit">Salvar Fornecedor</button>
    </form>
    <br>
    <a href="fornecedor?acao=listar">Cancelar e Voltar</a>
</div>
</body>
</html>