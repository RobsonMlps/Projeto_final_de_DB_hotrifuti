package hotifruti.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import hotifruti.model.Telefone;
import hotifruti.util.Conexao;

public class TelefoneDAO {

    public TelefoneDAO() {}

    public void salvar(Telefone telefone) throws Exception {
        String sql = "INSERT INTO TELEFONE_CLIENTE (ID_Cliente, Numero_Telefone) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, telefone.getIdCliente());
            ps.setString(2, telefone.getNumeroTelefone());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    telefone.setIdTelefone(rs.getInt(1));
                }
            }
        }
    }

    public void atualizar(Telefone telefone) throws Exception {
        String sql = "UPDATE TELEFONE_CLIENTE SET ID_Cliente = ?, Numero_Telefone = ? WHERE ID_Telefone = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, telefone.getIdCliente());
            ps.setString(2, telefone.getNumeroTelefone());
            ps.setInt(3, telefone.getIdTelefone());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM TELEFONE_CLIENTE WHERE ID_Telefone = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Telefone buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM TELEFONE_CLIENTE WHERE ID_Telefone = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Telefone t = new Telefone();
                    t.setIdTelefone(rs.getInt("ID_Telefone"));
                    t.setIdCliente(rs.getString("ID_Cliente"));
                    t.setNumeroTelefone(rs.getString("Numero_Telefone"));
                    return t;
                }
            }
        }
        return null;
    }

    public List<Telefone> listar() throws Exception {
        String sql = "SELECT * FROM TELEFONE_CLIENTE";
        List<Telefone> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Telefone t = new Telefone();
                t.setIdTelefone(rs.getInt("ID_Telefone"));
                t.setIdCliente(rs.getString("ID_Cliente"));
                t.setNumeroTelefone(rs.getString("Numero_Telefone"));
                lista.add(t);
            }
        }
        return lista;
    }
}
