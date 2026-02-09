<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulário de Produto</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f4; }
        .container { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 500px; }
        input, select, textarea { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }
        button { background-color: #4CAF50; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; width: 100%; }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
<div class="container">
    <h2>${produto != null ? 'Editar Produto' : 'Novo Produto'}</h2>
    
    <form action="produto" method="post">
        <input type="hidden" name="id" value="${produto.idProduto}">

        <label>Nome do Produto:</label>
        <input type="text" name="nome" value="${produto.nome}" required>

        <label>Descrição:</label>
        <textarea name="descricao" rows="3">${produto.descricao}</textarea>

        <label>Peso (kg):</label>
        <input type="text" name="peso" value="${p.pesoKg}" placeholder="Ex: 1.50">

        <label>Categoria:</label>
        <select name="idCategoria" required>
            <option value="">Selecione uma Categoria</option>
            <c:forEach var="cat" items="${listaCategorias}">
                <option value="${cat.idCategoria}" ${cat.idCategoria == produto.idCategoria ? 'selected' : ''}>
                    ${cat.nome}
                </option>
            </c:forEach>
        </select>

        <button type="submit">Salvar Produto</button>
    </form>
    <br>
    <a href="produto?acao=listar">Voltar para a lista</a>
</div>
</body>
</html>