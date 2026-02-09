<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Menu Principal - Hortifruti</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; padding: 40px; }
        h1 { color: #333; }
        .menu-container {
            display: inline-block;
            text-align: left;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
        a {
            display: block; /* Faz o link virar um bloco (um embaixo do outro) */
            margin: 10px 0;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-size: 16px;
        }
        a:hover { background-color: #0056b3; }
    </style>
</head>
<body>

    <h1>🍎 Sistema Hortifruti</h1>
    <p>Bem-vindo! Escolha uma opção abaixo:</p>

    <div class="menu-container">
        <a href="categoria?acao=listar">📁 Gerenciar Categorias</a>
        <a href="fornecedor?acao=listar">🚛 Gerenciar Fornecedores</a>
        <a href="cliente?acao=listar">👥 Gerenciar Clientes</a>
        <a href="telefone?acao=listar">📞 Gerenciar Telefones</a>
        
        <hr> <a href="produto?acao=listar">📦 Gerenciar Produtos</a>
        <a href="lote?acao=listar">🏭 Gerenciar Lotes (Estoque)</a>
        
        <hr>
        
        <a href="venda?acao=listar">💰 Gerenciar Vendas</a>
        <a href="itemvenda?acao=listar">🧾 Itens de Venda</a>
    </div>

</body>
</html>