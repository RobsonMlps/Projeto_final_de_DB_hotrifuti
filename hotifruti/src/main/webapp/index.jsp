<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hortifruti - Painel Administrativo</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; text-align: center; padding: 30px; background-color: #f9f9f9; }
        .container { max-width: 800px; margin: auto; border: 1px solid #ddd; padding: 30px; border-radius: 15px; background-color: white; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
        h3 { color: #2e7d32; }
        .grid-botoes { display: grid; grid-template-columns: 1fr 1fr; gap: 15px; margin-top: 20px; }
        .estiloBotaoLink {
            display: block;
            padding: 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-weight: bold;
            transition: background 0.3s;
        }
        .estiloBotaoLink:hover { background-color: #388e3c; transform: translateY(-2px); }
        .secao-titulo { grid-column: span 2; margin-top: 20px; border-top: 1px solid #eee; padding-top: 10px; color: #666; font-size: 0.9em; }
    </style>
</head>
<body>

    <div class="container">
        <img src="https://cdn-icons-png.flaticon.com/128/2321/2321837.png" width="80" alt="Logo Hortifruti">
        <h3>Painel Administrativo Hortifruti</h3>
        <p>Selecione o módulo que deseja gerenciar:</p>

        <div class="grid-botoes">
            <a href="${pageContext.request.contextPath}/produto?acao=listar" class="estiloBotaoLink">📦 Gerenciar Produtos</a>
            <a href="${pageContext.request.contextPath}/cliente?acao=listar" class="estiloBotaoLink">👥 Gerenciar Clientes</a>
            <a href="categoria?acao=listar" class="estiloBotaoLink">📂 Gerenciar Categorias</a>
            <a href="fornecedor?acao=listar" class="estiloBotaoLink">🚚 Gerenciar Fornecedores</a>

            <div class="secao-titulo">Vendas e Estoque</div>
            
            <a href="venda?acao=listar" class="estiloBotaoLink">💰 Realizar Vendas</a>
            <a href="itemvenda?acao=listar" class="estiloBotaoLink">📋 Itens Vendidos</a>
            <a href="lote?acao=listar" class="estiloBotaoLink">🏗️ Controle de Lotes</a>
            <a href="telefone?acao=listar" class="estiloBotaoLink">📞 Contatos/Telefones</a>
        </div>

        <br />
        <p style="color: #888; font-size: 0.8em;">Sistema conectado ao Banco de Dados MySQL</p>
    </div>

</body>
</html>