package hotifruti.src.main.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import hotifruti.dao.ItemVendaDAO;
import hotifruti.dao.ProdutoDAO;
import hotifruti.dao.VendaDAO;
import hotifruti.model.ItemVenda;

@WebServlet("/itemvenda")
public class ItemVendaServlet extends HttpServlet {

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
        // ATENÇÃO: Listar TODOS os itens de TODAS as vendas pode ficar pesado.
        // O ideal seria listar por Venda, mas para o CRUD simples, faremos geral.
        ItemVendaDAO dao = new ItemVendaDAO();
        List<ItemVenda> lista = dao.listar();
        
        req.setAttribute("listaItens", lista);
        req.getRequestDispatcher("lista-itens.jsp").forward(req, resp);
    }

    private void abrirFormulario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // Precisamos carregar DUAS listas para os <select> (comboboxes)
        VendaDAO vendaDao = new VendaDAO();
        ProdutoDAO produtoDao = new ProdutoDAO();

        req.setAttribute("listaVendas", vendaDao.listar());
        req.setAttribute("listaProdutos", produtoDao.listar());

        req.getRequestDispatcher("form-item.jsp").forward(req, resp);
    }

    private void carregarParaEdicao(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // DIFERENÇA 1: Precisamos de DOIS parâmetros para achar o item
        int idVenda = Integer.parseInt(req.getParameter("idVenda"));
        int idProduto = Integer.parseInt(req.getParameter("idProduto"));

        ItemVendaDAO dao = new ItemVendaDAO();
        ItemVenda item = dao.buscarPorId(idVenda, idProduto);

        req.setAttribute("item", item);

        // Carregar as listas novamente para os selects funcionarem
        VendaDAO vendaDao = new VendaDAO();
        ProdutoDAO produtoDao = new ProdutoDAO();
        req.setAttribute("listaVendas", vendaDao.listar());
        req.setAttribute("listaProdutos", produtoDao.listar());
        
        // Flag para avisar o JSP que é uma EDIÇÃO (Isso ajuda no formulário)
        req.setAttribute("acao", "editar"); 

        req.getRequestDispatcher("form-item.jsp").forward(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // DIFERENÇA 2: Para excluir, também precisamos dos dois IDs
        int idVenda = Integer.parseInt(req.getParameter("idVenda"));
        int idProduto = Integer.parseInt(req.getParameter("idProduto"));

        ItemVendaDAO dao = new ItemVendaDAO();
        dao.excluir(idVenda, idProduto);

        resp.sendRedirect("itemvenda?acao=listar");
    }

    private void salvar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. Recebe os dados
        String idVendaStr = req.getParameter("idVenda");
        String idProdutoStr = req.getParameter("idProduto");
        String qtdStr = req.getParameter("quantidade");
        String precoStr = req.getParameter("preco");
        
        // Campo hidden para sabermos se é update (já que não temos um ID único)
        String tipoAcao = req.getParameter("tipoAcao"); 

        ItemVenda item = new ItemVenda();
        item.setIdVenda(Integer.parseInt(idVendaStr));
        item.setIdProduto(Integer.parseInt(idProdutoStr));
        item.setQuantidadeVendida(Integer.parseInt(qtdStr));
        
        // Conversão do Preço (troca vírgula por ponto)
        if (precoStr != null) {
            item.setPrecoUnidade(new BigDecimal(precoStr.replace(",", ".")));
        }

        ItemVendaDAO dao = new ItemVendaDAO();

        // 2. Decide se é INSERT ou UPDATE
        // Como a chave é composta, usamos um campo auxiliar "tipoAcao"
        if ("editar".equals(tipoAcao)) {
            dao.atualizar(item);
        } else {
            dao.salvar(item);
        }

        resp.sendRedirect("itemvenda?acao=listar");
    }
}