package hotifruti.dao;

import java.sql.*;
import hotifruti.model.Lote;
import hotifruti.util.Conexao;

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
}
