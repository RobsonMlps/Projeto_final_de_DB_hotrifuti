package hotifruti.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import hotifruti.model.Categoria;
import hotifruti.util.Conexao;

public class CategoriaDAO {

    public CategoriaDAO(){}

	public void salvar(Categoria categoria) throws Exception {
		String sql = "INSERT INTO CATEGORIA (Nome, Descricao) VALUES (?, ?)";
		try (Connection conn = Conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, categoria.getNome());
			ps.setString(2, categoria.getDescricao());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					categoria.setIdCategoria(rs.getInt(1));
				}
			}
		}
	}

	public void atualizar(Categoria categoria) throws Exception {
		String sql = "UPDATE CATEGORIA SET Nome = ?, Descricao = ? WHERE ID_Categoria = ?";
		try (Connection conn = Conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, categoria.getNome());
			ps.setString(2, categoria.getDescricao());
			ps.setInt(3, categoria.getIdCategoria());
			ps.executeUpdate();
		}
	}

	public void excluir(int id) throws Exception {
		String sql = "DELETE FROM CATEGORIA WHERE ID_Categoria = ?";
		try (Connection conn = Conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	public Categoria buscarPorId(int id) throws Exception {
		String sql = "SELECT ID_Categoria, Nome, Descricao FROM CATEGORIA WHERE ID_Categoria = ?";
		try (Connection conn = Conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Categoria(rs.getInt("ID_Categoria"), rs.getString("Nome"), rs.getString("Descricao"));
				}
			}
		}
		return null;
	}

	public List<Categoria> listar() throws Exception {
		String sql = "SELECT ID_Categoria, Nome, Descricao FROM CATEGORIA ORDER BY Nome";
		List<Categoria> lista = new ArrayList<>();
		try (Connection conn = Conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(new Categoria(rs.getInt("ID_Categoria"), rs.getString("Nome"), rs.getString("Descricao")));
			}
		}
		return lista;
	}

}
