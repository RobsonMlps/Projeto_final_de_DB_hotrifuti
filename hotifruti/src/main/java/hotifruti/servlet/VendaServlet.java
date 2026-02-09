package hotifruti.servlet;

import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter; // Útil para formatar datas

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import hotifruti.dao.VendaDAO; // Apenas um import basta
import hotifruti.dao.ClienteDAO;
import hotifruti.model.Venda;
import hotifruti.model.Cliente;

@WebServlet("/venda")
public class VendaServlet extends HttpServlet {

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
        VendaDAO dao = new VendaDAO();
        List<Venda> lista = dao.listar();
        
        // Use plural para listas
        req.setAttribute("listaVenda", lista);
        
        req.getRequestDispatcher("lista-venda.jsp").forward(req, resp);
    }

    private void abrirFormulario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // CORREÇÃO: Venda depende de Cliente, não de Categoria!
        ClienteDAO cliDao = new ClienteDAO();
        
        // Carrega a lista de clientes para o <select>
        req.setAttribute("listaClientes", cliDao.listar());
        
        req.getRequestDispatcher("form-venda.jsp").forward(req, resp);
    }

    private void carregarParaEdicao(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        VendaDAO dao = new VendaDAO();
        Venda v = dao.buscarPorId(id);
        
        req.setAttribute("venda", v);
        
        // Carrega a lista de clientes novamente para edição
        ClienteDAO cliDao = new ClienteDAO();
        req.setAttribute("listaClientes", cliDao.listar());
        
        req.getRequestDispatcher("form-venda.jsp").forward(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        VendaDAO dao = new VendaDAO();
        dao.excluir(id);
        resp.sendRedirect("venda?acao=listar");
    }

    private void salvar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. RECEBE OS DADOS COMO STRING
        String idCliStr = req.getParameter("idCliente");
        String dataHoraStr = req.getParameter("dataHora"); // Vem do input type="datetime-local"
        String idStr = req.getParameter("id"); // CORREÇÃO: Faltava pegar o ID da venda

        Venda v = new Venda();

        // 2. CONVERSÃO: CLIENTE (String -> Int)
        if (idCliStr != null && !idCliStr.isEmpty()) {
            int idCliente = Integer.parseInt(idCliStr);
            v.setIdCliente(String.valueOf(idCliente)); // Só atribui DEPOIS de converter
        }

        // 3. CONVERSÃO: DATA (String -> LocalDateTime)
        // Se o HTML enviar "2023-10-20T14:30", o parse funciona direto.
        if (dataHoraStr != null && !dataHoraStr.isEmpty()) {
            v.setDataHora(LocalDateTime.parse(dataHoraStr));
        } else {
            // Se não vier data, usa a data de AGORA
            v.setDataHora(LocalDateTime.now());
        }

        VendaDAO dao = new VendaDAO();

        // 4. DECISÃO: SALVAR OU ATUALIZAR
        if (idStr == null || idStr.isEmpty()) {
            dao.salvar(v);
        } else {
            // CORREÇÃO: Trocado 'p' por 'v'
            v.setIdVenda(Integer.parseInt(idStr));
            dao.atualizar(v);
        }

        resp.sendRedirect("venda?acao=listar");
    }
}