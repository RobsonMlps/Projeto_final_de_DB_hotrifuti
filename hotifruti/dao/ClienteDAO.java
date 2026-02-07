package hotifruti.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import hotifruti.model.Cliente;
import hotifruti.util.Conexao;

public class ClienteDAO {

    public ClienteDAO() {}

    public void salvar(Cliente cliente) throws Exception {
        
        String sql = "INSERT INTO CLIENTE (Nome, CPF, Email, Telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefone());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                }
            }
        }
    }

    public void atualizar(Cliente cliente) throws Exception {
        String sql = "UPDATE CLIENTE SET Nome = ?, CPF = ?, Email = ?, Telefone = ? WHERE ID_Cliente = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefone());
            ps.setInt(5, cliente.getIdCliente());
            
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM CLIENTE WHERE ID_Cliente = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Cliente buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM CLIENTE WHERE ID_Cliente = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("ID_Cliente"));
                    c.setNome(rs.getString("Nome"));
                    c.setCpf(rs.getString("CPF"));
                    c.setEmail(rs.getString("Email"));
                    c.setTelefone(rs.getString("Telefone"));
                    return c;
                }
            }
        }
        return null;
    }

    public List<Cliente> listar() throws Exception {
        String sql = "SELECT * FROM CLIENTE ORDER BY Nome";
        List<Cliente> lista = new ArrayList<>();
        
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("ID_Cliente"));
                c.setNome(rs.getString("Nome"));
                c.setCpf(rs.getString("CPF"));
                c.setEmail(rs.getString("Email"));
                c.setTelefone(rs.getString("Telefone"));
                lista.add(c);
            }
        }
        return lista;
    }
}
