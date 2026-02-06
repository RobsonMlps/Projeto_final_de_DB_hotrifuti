package hotifruti.dao;

import java.sql.*;
import hotifruti.model.Lote;
import hotifruti.dto.LoteDTO;
import hotifruti.util.Conexao;
import java.util.*;

public class LoteDAO {
    
    public LoteDAO(){}

    public void salvar(Lote lote) throws Exception {
		String sql = "INSERT INTO LOTE (ID_Produto, ID_Fornecedor, Data_Entrada, Data_Validade, Custo) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = Conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, lote.getIdProduto());
			ps.setInt(2, lote.getIdFornecedor());
            ps.setDate(3, lote.getDataEntrada());
            ps.setDate(4, lote.getDataValidade());
            ps.setBigDecimal(5, lote.getCusto());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					lote.setIdLote(rs.getInt(1));
				}
			}
		}
	}

    public void atualizar(Lote lote) throws Exception {
		String sql = "UPDATE LOTE SET ID_Produto = ?, ID_Fornecedor = ?, Data_Entrada = ?, Data_Validade = ?, Custo = ? WHERE ID_Lote = ?";
		try (Connection conn = Conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, lote.getIdProduto());
			ps.setInt(2, lote.getIdFornecedor());
            ps.setDate(3, lote.getDataEntrada());
            ps.setDate(4, lote.getDataValidade());
            ps.setBigDecimal(5, lote.getCusto());
			ps.setInt(6, lote.getIdLote());
			ps.executeUpdate();
		}
	}

    public void excluir(int id) throws Exception {
		String sql = "DELETE FROM LOTE WHERE ID_Lote = ?";
		try (Connection conn = Conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

    public Categoria buscarPorId(int id) throws Exception {
		String sql = "SELECT ID_Lote, ID_Produto, ID_Fornecedor, Data_Entrada, Data_Validade, Custo FROM CATEGORIA WHERE ID_Lote = ?";
		try (Connection conn = Conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Lote lote = new Lote();
            
                    lote.setIdLote(rs.getInt("ID_Lote"));
                    lote.setIdProduto(rs.getInt("ID_Produto"));
                    lote.setIdFornecedor(rs.getInt("ID_Fornecedor"));
                    lote.setCusto(rs.getBigDecimal("Custo")); 
                    lote.setDataEntrada(rs.getDate("Data_Entrada"));
                    lote.setDataValidade(rs.getDate("Data_Validade"));
                    return lote;
				}
			}
		}
		return null;
	}

    public List<Lote> listar() throws Exception {
		String sql = "SELECT * FROM LOTE";
		List<Lote> lista = new ArrayList<>();
		try (Connection conn = Conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(new Lote(
                    rs.getInt("ID_Lote"),
                    rs.getInt("ID_Produto"),
                    rs.getInt("ID_Fornecedor"),
                    rs.getBigDecimal("Custo"),
                    rs,getDate("Data_Entrada"),
                    rs.getDate("Data_Validade")));
			}
		}
		return lista;
	}

    public List<LoteDTO> listaLote() throws Exception {
        String sql = "SELECT l.ID_Lote, p.Nome AS Nome_Produto, f.Nome AS Nome_Fornecedor FROM LOTE l" +
        "INNER JOIN PRODUTO p ON l.ID_Produto = p.ID_Produto" + 
        "INNER JOIN FORNECEDOR f ON l.ID_Fornecedor = f.ID_Fornecedor;";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaLote.add(new LoteDAO(
                        rs.getInt("ID_Lote"),
                        rs.getString("Nome_Produto"),
                        rs.getString("Nome_Fornecedor"),
                        rs.getBigDecimal("Custo"),
                        rs.getDate("Data_Entrada"),
                        rs.getDate("Data_Validade")));
                }
            }
    }
}
