<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulário de Categoria</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f4; }
        .container { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        label { font-weight: bold; display: block; margin-top: 15px; }
        input, textarea { width: 100%; padding: 10px; margin: 5px 0 10px 0; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        textarea { height: 100px; resize: vertical; }
        button { background-color: #f57c00; color: white; padding: 12px; border: none; border-radius: 4px; cursor: pointer; width: 100%; font-size: 16px; font-weight: bold; }
        button:hover { background-color: #e65100; }
    </style>
</head>
<body>
<div class="container">
    <h2>${categoria != null ? 'Editar Categoria' : 'Nova Categoria'}</h2>
    
    <form action="categoria" method="post">
        <input type="hidden" name="id" value="${categoria.idCategoria}">

        <label>Nome da Categoria:</label>
        <input type="text" name="nome" value="${categoria.nome}" placeholder="Ex: Frutas Tropicais" required>

        <label>Descrição:</label>
        <textarea name="descricao" placeholder="Breve detalhamento da categoria...">${categoria.descricao}</textarea>

        <button type="submit">Salvar Categoria</button>
    </form>
    <br>
    <a href="categoria?acao=listar" style="text-decoration: none; color: #666;">Cancelar e Voltar</a>
</div>
</body>
</html>