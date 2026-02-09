package hotifruti.src.main.servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import hotifruti.dao.CategoriaDAO; // Importante para o combo box
import hotifruti.model.Categoria;

// Mapeamos para uma URL única "/produto"
@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {

    // doGet: Gerencia a NAVEGAÇÃO (Links e Botões de ir)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // Pega o parâmetro "acao" da URL (ex: /produto?acao=editar&id=5)
        String acao = req.getParameter("acao");
        
        // Se não tiver ação, o padrão é listar
        if (acao == null) {
            acao = "listar";
        }

        try {
            switch (acao) {
                case "listar":
                    listar(req, resp);
                    break;
                case "form":
                    abrirFormulario(req, resp); // Abre tela vazia para Novo
                    break;
                case "editar":
                    carregarParaEdicao(req, resp); // Abre tela preenchida
                    break;
                case "excluir":
                    excluir(req, resp);
                    break;
                default:
                    listar(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // doPost: Gerencia o SALVAR (O formulário envia para cá)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        try {
            // Aqui você chama o método de salvar/atualizar
            salvar(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // --- MÉTODOS AJUDANTES (Para não bagunçar o switch) ---

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CategoriaDAO dao = new CategoriaDAO();
        List<Categoria> lista = dao.listar();
        req.setAttribute("listaCategoria", lista);
        req.getRequestDispatcher("lista-categoria.jsp").forward(req, resp);
    }

    private void abrirFormulario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // O formulário precisa das categorias para o <select>
        req.getRequestDispatcher("form-categoria.jsp").forward(req, resp);
    }

    private void carregarParaEdicao(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        CategoriaDAO dao = new CategoriaDAO();
        Categoria c = dao.buscarPorId(id);
        
        req.setAttribute("categoria", c);
        req.getRequestDispatcher("form-categoria.jsp").forward(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        CategoriaDAO dao = new CategoriaDAO();
        dao.excluir(id);
        resp.sendRedirect("categoria?acao=listar");
    }

    private void salvar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. Recebe dados
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descrição");
        // ... pega resto dos campos ...
        String idStr = req.getParameter("id"); // Campo hidden no form

        Categoria c = new Categoria();
        c.setNome(nome);
        c.setDescricao(descricao);
        // ... preenche p ...

        CategoriaDAO dao = new CategoriaDAO();

        // 2. Decide se é NOVO ou ATUALIZAÇÃO
        if (idStr == null || idStr.isEmpty()) {
            dao.salvar(c);
        } else {
            c.setIdCategoria(Integer.parseInt(idStr));
            dao.atualizar(c);
        }

        // 3. Volta pra lista
        resp.sendRedirect("categoria?acao=listar");
    }
}