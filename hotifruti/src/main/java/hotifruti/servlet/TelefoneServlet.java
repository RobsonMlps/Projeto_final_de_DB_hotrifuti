package hotifruti.servlet;

import java.io.IOException;
import java.util.List;
import java.math.BigDecimal; // IMPORTANTE: Faltava esse import para o peso

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import hotifruti.dao.TelefoneDAO;
import hotifruti.dao.ClienteDAO;
import hotifruti.model.Telefone;

@WebServlet("/telefone")
public class TelefoneServlet extends HttpServlet {

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
        TelefoneDAO dao = new TelefoneDAO();
        List<Telefone> lista = dao.listar();
        
        // CORREÇÃO: "listaProdutos" (Plural) é o padrão
        req.setAttribute("listaTelefone", lista);
        
        // CORREÇÃO: Verifique se o nome do seu arquivo é lista-produtos.jsp
        req.getRequestDispatcher("lista-telefone.jsp").forward(req, resp);
    }

    private void abrirFormulario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ClienteDAO cliDao = new ClienteDAO();
        // Carrega o combo box de categorias
        req.setAttribute("listaCliente", cliDao.listar());
        
        req.getRequestDispatcher("form-telefone.jsp").forward(req, resp);
    }

    private void carregarParaEdicao(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        TelefoneDAO dao = new TelefoneDAO();
        Telefone t = dao.buscarPorId(id);
        
        req.setAttribute("telefone", t);
        
        // Carrega o combo box novamente para a edição
        ClienteDAO cliDao = new ClienteDAO();
        req.setAttribute("listacliente", cliDao.listar());
        
        req.getRequestDispatcher("form-telefone.jsp").forward(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        TelefoneDAO dao = new TelefoneDAO();
        dao.excluir(id);
        resp.sendRedirect("telefone?acao=listar");
    }

    private void salvar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. Recebe os dados como STRING (Texto)
        String idCliStr = req.getParameter("idCliente");
        String numero = req.getParameter("numero"); // Simplifiquei o nome
        String idStr = req.getParameter("id"); 

        Telefone t = new Telefone();
        t.setIdCliente(idCliStr);
        t.setNumeroTelefone(numero);

        // 2. CONVERSÕES (A parte crítica)
        
        // 
        if (idCliStr != null && !idCliStr.isEmpty()) {
            t.setIdCliente(idCliStr);
        }


        TelefoneDAO dao = new TelefoneDAO();

        // Salvar ou Atualizar
        if (idStr == null || idStr.isEmpty()) {
            dao.salvar(t);
        } else {
            t.setIdTelefone(Integer.parseInt(idStr));
            dao.atualizar(t);
        }

        resp.sendRedirect("telefone?acao=listar");
    }
}