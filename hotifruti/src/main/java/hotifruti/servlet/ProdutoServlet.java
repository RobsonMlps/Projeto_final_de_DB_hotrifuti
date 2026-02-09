package hotifruti.servlet;

import java.io.IOException;
import java.util.List;
import java.math.BigDecimal; // IMPORTANTE: Faltava esse import para o peso

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import hotifruti.dao.ProdutoDAO;
import hotifruti.dao.CategoriaDAO;
import hotifruti.model.Produto;

@WebServlet("/produto")
public class ProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String acao = req.getParameter("acao");
        if (acao == null) {
            acao = "listar";
        }

        try {
            switch (acao) {
                case "listar":
                    listar(req, resp);
                    break;
                case "form":
                    abrirFormulario(req, resp);
                    break;
                case "editar":
                    carregarParaEdicao(req, resp);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        try {
            salvar(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // --- MÉTODOS ---

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> lista = dao.listar();
        
        // CORREÇÃO: "listaProdutos" (Plural) é o padrão
        req.setAttribute("listaProdutos", lista);
        
        // CORREÇÃO: Verifique se o nome do seu arquivo é lista-produtos.jsp
        req.getRequestDispatcher("lista-produtos.jsp").forward(req, resp);
    }

    private void abrirFormulario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        CategoriaDAO catDao = new CategoriaDAO();
        // Carrega o combo box de categorias
        req.setAttribute("listaCategorias", catDao.listar());
        
        req.getRequestDispatcher("form-produto.jsp").forward(req, resp);
    }

    private void carregarParaEdicao(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        ProdutoDAO dao = new ProdutoDAO();
        Produto p = dao.buscarPorId(id);
        
        req.setAttribute("produto", p);
        
        // Carrega o combo box novamente para a edição
        CategoriaDAO catDao = new CategoriaDAO();
        req.setAttribute("listaCategorias", catDao.listar());
        
        req.getRequestDispatcher("form-produto.jsp").forward(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        ProdutoDAO dao = new ProdutoDAO();
        dao.excluir(id);
        resp.sendRedirect("produto?acao=listar");
    }

    private void salvar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. Recebe os dados como STRING (Texto)
        String idCateStr = req.getParameter("idCategoria");
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao"); // Sem acento
        String pesoStr = req.getParameter("peso"); // Simplifiquei o nome
        String idStr = req.getParameter("id"); 

        Produto p = new Produto();
        p.setNome(nome);
        p.setDescricao(descricao);

        // 2. CONVERSÕES (A parte crítica)
        
        // Converte Categoria (Texto -> Inteiro)
        if (idCateStr != null && !idCateStr.isEmpty()) {
            p.setIdCategoria(Integer.parseInt(idCateStr));
        }

        // Converte Peso (Texto -> BigDecimal)
        if (pesoStr != null && !pesoStr.isEmpty()) {
            // Troca vírgula por ponto (ex: 1,50 -> 1.50) para o Java não dar erro
            String pesoFormatado = pesoStr.replace(",", ".");
            p.setPesoKg(new BigDecimal(pesoFormatado));
        }

        ProdutoDAO dao = new ProdutoDAO();

        // 3. Salvar ou Atualizar
        if (idStr == null || idStr.isEmpty()) {
            dao.salvar(p);
        } else {
            p.setIdProduto(Integer.parseInt(idStr));
            dao.atualizar(p);
        }

        resp.sendRedirect("produto?acao=listar");
    }
}