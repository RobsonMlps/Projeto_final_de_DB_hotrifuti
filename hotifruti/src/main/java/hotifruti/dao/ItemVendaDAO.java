package hotifruti.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import hotifruti.model.ItemVenda;
import hotifruti.util.Conexao;

public class ItemVendaDAO {

    public ItemVendaDAO(){}

    public void salvar(ItemVenda item) throws Exception {
        String sql = "INSERT INTO ITEM_VENDA (ID_Venda, ID_Produto, Quantidade, Valor_Unitario) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, item.getIdVenda());
            ps.setInt(2, item.getIdProduto());
            ps.setInt(3, item.getQuantidadeVendida());
            // Uso do BigDecimal aqui faz o import ficar "aceso" (branco/azul)
            ps.setBigDecimal(4, item.getPrecoUnidade()); 
            ps.executeUpdate();
        }
    }

    public void atualizar(ItemVenda item) throws Exception {
        String sql = "UPDATE ITEM_VENDA SET Quantidade = ?, Valor_Unitario = ? WHERE ID_Venda = ? AND ID_Produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, item.getQuantidadeVendida());
            ps.setBigDecimal(2, item.getPrecoUnidade()); // Uso aqui também
            ps.setInt(3, item.getIdVenda());
            ps.setInt(4, item.getIdProduto());
            ps.executeUpdate();
        }
    }

    public void excluir(int idVenda, int idProduto) throws Exception {
        String sql = "DELETE FROM ITEM_VENDA WHERE ID_Venda = ? AND ID_Produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVenda);
            ps.setInt(2, idProduto);
            ps.executeUpdate();
        }
    }

    public ItemVenda buscarPorId(int idVenda, int idProduto) throws Exception {
        String sql = "SELECT ID_Venda, ID_Produto, Quantidade, Valor_Unitario FROM ITEM_VENDA WHERE ID_Venda = ? AND ID_Produto = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVenda);
            ps.setInt(2, idProduto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // O rs.getBigDecimal faz a ponte com o import
                    return new ItemVenda(
                        rs.getInt("ID_Venda"), 
                        rs.getInt("ID_Produto"), 
                        rs.getInt("Quantidade"), 
                        rs.getBigDecimal("Valor_Unitario") 
                    );
                }
            }
        }
        return null;
    }

    public List<ItemVenda> listar() throws Exception {
        String sql = "SELECT ID_Venda, ID_Produto, Quantidade, Valor_Unitario FROM ITEM_VENDA";
        List<ItemVenda> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new ItemVenda(
                    rs.getInt("ID_Venda"), 
                    rs.getInt("ID_Produto"), 
                    rs.getInt("Quantidade"), 
                    rs.getBigDecimal("Valor_Unitario")
                ));
            }
        }
        return lista;
    }
}