package hotifruti.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import hotifruti.model.Fornecedor;
import hotifruti.util.Conexao;

public class FornecedorDAO {

    public FornecedorDAO() {}


    public void salvar(Fornecedor fornecedor) throws Exception {
        String sql = "INSERT INTO FORNECEDOR (Nome, CNPJ, Email) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEmail());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    fornecedor.setIdFornecedor(rs.getInt(1));
                }
            }
        }
    }

    
    public void atualizar(Fornecedor fornecedor) throws Exception {
        String sql = "UPDATE FORNECEDOR SET Nome = ?, CNPJ = ?, Email = ? WHERE ID_Fornecedor = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getEmail());
            ps.setInt(4, fornecedor.getIdFornecedor());
            
            ps.executeUpdate();
        }
    }

    
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM FORNECEDOR WHERE ID_Fornecedor = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    
    public Fornecedor buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM FORNECEDOR WHERE ID_Fornecedor = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Fornecedor f = new Fornecedor();
                    f.setIdFornecedor(rs.getInt("ID_Fornecedor"));
                    f.setNome(rs.getString("Nome"));
                    f.setCnpj(rs.getString("CNPJ"));
                    f.setEmail(rs.getString("Email"));
                    return f;
                }
            }
        }
        return null;
    }

    
    public List<Fornecedor> listar() throws Exception {
        String sql = "SELECT * FROM FORNECEDOR ORDER BY Nome";
        List<Fornecedor> lista = new ArrayList<>();
        
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setIdFornecedor(rs.getInt("ID_Fornecedor"));
                f.setNome(rs.getString("Nome"));
                f.setCnpj(rs.getString("CNPJ"));
                f.setEmail(rs.getString("Email"));
                lista.add(f);
            }
        }
        return lista;
    }
}