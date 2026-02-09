package hotifruti.servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importante para o combo box
import hotifruti.dao.FornecedorDAO;
import hotifruti.model.Fornecedor;

// Mapeamos para uma URL única "/produto"
@WebServlet("/fornecedor")
public class FornecedorServlet extends HttpServlet {

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
        FornecedorDAO dao = new FornecedorDAO();
        List<Fornecedor> lista = dao.listar();
        req.setAttribute("listaFornecedor", lista);
        req.getRequestDispatcher("lista-fornecedor.jsp").forward(req, resp);
    }

    private void abrirFormulario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // O formulário precisa das categorias para o <select>
        req.getRequestDispatcher("form-fornecedor.jsp").forward(req, resp);
    }

    private void carregarParaEdicao(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        FornecedorDAO dao = new FornecedorDAO();
        Fornecedor f = dao.buscarPorId(id);
        
        req.setAttribute("fornecedor", f);
        req.getRequestDispatcher("form-fornecedor.jsp").forward(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        FornecedorDAO dao = new FornecedorDAO();
        dao.excluir(id);
        resp.sendRedirect("fornecedor?acao=listar");
    }

    private void salvar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 1. Recebe dados
        String nome = req.getParameter("nome");
        String cnpj = req.getParameter("cnpj");
        String email = req.getParameter("email"); // <--- Adicionado o CPF
        String idStr = req.getParameter("id");

        Fornecedor f = new Fornecedor();
        f.setNome(nome);
        f.setCnpj(cnpj);
        f.setEmail(email);
        FornecedorDAO dao = new FornecedorDAO();

        // 2. Decide se é NOVO ou ATUALIZAÇÃO
        if (idStr == null || idStr.isEmpty()) {
            dao.salvar(f);
        } else {
            f.setIdFornecedor(Integer.parseInt(idStr));
            dao.atualizar(f);
        }

        // 3. Volta pra lista
        resp.sendRedirect("fornecedor?acao=listar");
    }
}
