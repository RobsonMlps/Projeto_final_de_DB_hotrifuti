package hotifruti.servlet;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate; // Para data de validade
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import hotifruti.dao.LoteDAO;
import hotifruti.dao.ProdutoDAO;
import hotifruti.dao.FornecedorDAO;
import hotifruti.model.Lote;

@WebServlet("/lote")
public class LoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String acao = req.getParameter("acao");
        if (acao == null) acao = "listar";

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
        LoteDAO dao = new LoteDAO();
        List<Lote> lista = dao.listar();
        
        req.setAttribute("listaLotes", lista);
        req.getRequestDispatcher("lista-lote.jsp").forward(req, resp);
    }

    private void abrirFormulario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // AQUI ESTÁ O SEGREDO: Carregar DUAS listas para os selects
        ProdutoDAO prodDao = new ProdutoDAO();
        FornecedorDAO fornDao = new FornecedorDAO();

        req.setAttribute("listaProdutos", prodDao.listar());
        req.setAttribute("listaFornecedores", fornDao.listar());
        
        req.getRequestDispatcher("form-lote.jsp").forward(req, resp);
    }

    private void carregarParaEdicao(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        LoteDAO dao = new LoteDAO();
        Lote lote = dao.buscarPorId(id);
        
        req.setAttribute("lote", lote);
        
        // Recarregar as listas para a edição também!
        ProdutoDAO prodDao = new ProdutoDAO();
        FornecedorDAO fornDao = new FornecedorDAO();
        req.setAttribute("listaProdutos", prodDao.listar());
        req.setAttribute("listaFornecedores", fornDao.listar());
        
        req.getRequestDispatcher("form-lote.jsp").forward(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        LoteDAO dao = new LoteDAO();
        dao.excluir(id);
        resp.sendRedirect("lote?acao=listar");
    }

    private void salvar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. Recebe os dados como String
        String idStr = req.getParameter("id");
        String idProdStr = req.getParameter("idProduto");
        String idFornStr = req.getParameter("idFornecedor");
        String dataStr = req.getParameter("dataValidade"); // Vem yyyy-MM-dd do HTML
        
        String custoStr = req.getParameter("custo");

        Lote lote = new Lote();

        // 2. CONVERSÕES (Muita atenção aqui)

        // Converter Data (String -> LocalDate)
        if (dataStr != null && !dataStr.isEmpty()) {
            lote.setDataValidade(java.sql.Date.valueOf(req.getParameter("data_validade")));        }

        // Converter Inteiros (FKs e Quantidade)
        if (idProdStr != null) lote.setIdProduto(Integer.parseInt(idProdStr));
        if (idFornStr != null) lote.setIdFornecedor(Integer.parseInt(idFornStr));


        // Converter Dinheiro (String -> BigDecimal)
        if (custoStr != null && !custoStr.isEmpty()) {
            // Troca vírgula por ponto para evitar erro
            lote.setCusto(new BigDecimal(custoStr.replace(",", ".")));
        }

        LoteDAO dao = new LoteDAO();

        // 3. Salvar ou Atualizar
        if (idStr == null || idStr.isEmpty()) {
            dao.salvar(lote);
        } else {
            lote.setIdLote(Integer.parseInt(idStr));
            dao.atualizar(lote);
        }

        resp.sendRedirect("lote?acao=listar");
    }
}