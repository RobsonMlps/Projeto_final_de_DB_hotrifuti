package hotifruti.dao;

import java.sql.*;
import java.util.*;
import hotifruti.model.Venda;
import hotifruti.util.Conexao;
import java.time.LocalDateTime;

public class VendaDAO {
    
    public VendaDAO(){}

    public void salvar(Venda venda) throws Exception {
        String sql = "INSERT INTO VENDA (ID_Cliente, Data_Hora) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, venda.getIdCliente());
            ps.setObject(2, venda.getDataHora());
            ps.executeUpdate();
        }
    }

    public void atualizar(Venda venda) throws Exception {
        String sql = "UPDATE VENDA SET ID_Cliente = ?, Data_hora = ? WHERE ID_Venda = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, venda.getIdCliente());
            ps.setObject(2, venda.getDataHora());
            ps.setInt(3, venda.getIdVenda());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM VENDA WHERE ID_Venda = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Venda buscarPorId(int id) throws Exception {
        String sql = "SELECT * FROM VENDA WHERE ID_Venda = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Venda venda = new Venda();
                    // CORREÇÃO AQUI: de setIdClienteCpf para setIdCliente
                    venda.setIdCliente(rs.getString("ID_Cliente"));
                    venda.setDataHora(rs.getObject("Data_Hora", LocalDateTime.class));
                    venda.setIdVenda(rs.getInt("ID_Venda"));
                    return venda;
                }
            }
        }
        return null;
    }

    public List<Venda> listar() throws Exception {
        String sql = "SELECT * FROM VENDA";
        List<Venda> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(rs.getInt("ID_Venda"));
                venda.setIdCliente(rs.getString("ID_Cliente"));
                venda.setDataHora(rs.getObject("Data_Hora", LocalDateTime.class));
                lista.add(venda);
            }
        }
        return lista;
    }
}