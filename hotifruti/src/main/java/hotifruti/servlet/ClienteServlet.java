package hotifruti.servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import hotifruti.dao.ClienteDAO; // Importante para o combo box
import hotifruti.model.Cliente;

// Mapeamos para uma URL única "/produto"
@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {

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
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = dao.listar();
        req.setAttribute("listaCliente", lista);
        req.getRequestDispatcher("lista-clientes.jsp").forward(req, resp);
    }

    private void abrirFormulario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // O formulário precisa das categorias para o <select>
        req.getRequestDispatcher("form-cliente.jsp").forward(req, resp);
    }

    private void carregarParaEdicao(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        ClienteDAO dao = new ClienteDAO();
        Cliente c = dao.buscarPorId(id);
        
        req.setAttribute("cliente", c);
        req.getRequestDispatcher("form-cliente.jsp").forward(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        ClienteDAO dao = new ClienteDAO();
        dao.excluir(id);
        resp.sendRedirect("cliente?acao=listar");
    }

    private void salvar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. Recebe dados
        String nome = req.getParameter("nome");
        String cpf = req.getParameter("cpf"); // <--- Adicionado o CPF
        String idStr = req.getParameter("id");

        Cliente c = new Cliente();
        c.setNome(nome);
        c.setCpf(cpf); // <--- Preenchendo o CPF no objeto
        ClienteDAO dao = new ClienteDAO();

        // 2. Decide se é NOVO ou ATUALIZAÇÃO
        if (idStr == null || idStr.isEmpty()) {
            dao.salvar(c);
        } else {
            c.setIdCliente(Integer.parseInt(idStr));
            dao.atualizar(c);
        }

        // 3. Volta pra lista
        resp.sendRedirect("cliente?acao=listar");
    }
}