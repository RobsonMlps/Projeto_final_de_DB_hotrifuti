<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulário de Cliente</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f4; }
        .container { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        input { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        button { background-color: #2e7d32; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-weight: bold; }
        button:hover { background-color: #1b5e20; }
    </style>
</head>
<body>
<div class="container">
    <h2>${cliente != null ? 'Editar Cliente' : 'Novo Cliente'}</h2>
    
    <form action="cliente" method="post">
        <input type="hidden" name="id" value="${cliente.idCliente}">

        <label>Nome Completo:</label>
        <input type="text" name="nome" value="${cliente.nome}" required>

        <label>CPF:</label>
        <input type="text" name="cpf" value="${cliente.cpf}" placeholder="000.000.000-00" required>

        <button type="submit">Confirmar Cadastro</button>
    </form>
    <br>
    <a href="cliente?acao=listar">Cancelar e Voltar</a>
</div>
</body>
</html>