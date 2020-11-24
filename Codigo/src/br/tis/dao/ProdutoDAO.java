package br.tis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.tis.bd.ConnectionFactorySqlServer;
import br.tis.entidades.Produto;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ProdutoDAO implements DAO<Produto, Integer> {

	private final Connection connection;
	private Produto produto;

	public ProdutoDAO() {
		new ConnectionFactorySqlServer();
		this.connection = ConnectionFactorySqlServer.getConnection();
	}

	public ProdutoDAO(Produto produto) {
		new ConnectionFactorySqlServer();
		this.connection = ConnectionFactorySqlServer.getConnection();
		this.produto = produto;
	}

	@Override
	public boolean add() {

		boolean result = false;

		String sqlAdd = "insert into produtos (nome, descricao, precoVenda) values (?, ?, ?)";

		PreparedStatement stmtProd = null;

		try {

			stmtProd = connection.prepareStatement(sqlAdd);

			stmtProd.setString(1, produto.getNome());
			stmtProd.setString(2, produto.getDescricao());
			stmtProd.setDouble(3, produto.getPrecoVenda());

			stmtProd.execute();
			
			result = true;

		} catch (SQLException e) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		} finally {

			if (stmtProd != null)
				try {
					stmtProd.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return result;
	}

	@Override
	public boolean update() {
		boolean result = false;

		String sqlUpdate = "Update produtos SET (nome = ?, descricao = ?, precoVenda = ?) where idProduto = ?";

		PreparedStatement stmtCli = null;

		try {

			stmtCli = connection.prepareStatement(sqlUpdate);

			stmtCli.setString(1, produto.getNome());
			stmtCli.setString(2, produto.getDescricao());
			stmtCli.setDouble(3, produto.getPrecoVenda());
			stmtCli.setLong(4, produto.getIdProduto());

			int updateCount = stmtCli.executeUpdate(sqlUpdate);

			if (updateCount == 0)
				throw new SQLException("Falha ao atualizar o cliente");

			result = true;

		} catch (SQLException e) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		} finally {

			if (stmtCli != null)
				try {
					stmtCli.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return result;

	}

	@Override
	public boolean remove(Integer idProduto) {
		boolean result = false;

		String sqlDelete = "DELETE from dbo.produtos where idProduto = ?";

		PreparedStatement stmtCli = null;

		try {

			stmtCli = connection.prepareStatement(sqlDelete);

			stmtCli.setLong(1, idProduto);

			if (stmtCli.executeUpdate() == 0)
				throw new SQLException("Falha ao excluir o cliente");

			result = true;

		} catch (SQLException e) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		} finally {

			if (stmtCli != null)
				try {
					stmtCli.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return result;

	}

	@Override
	public List<Produto> getAll() {

		String sqlGetAll = "Select * from dbo.produtos order by idProduto";
		List<Produto> produtos = new ArrayList<>();
		PreparedStatement stmtCli = null;

		try {

			stmtCli = connection.prepareStatement(sqlGetAll);

			ResultSet rs = stmtCli.executeQuery();

			// if(rs.next())
			// throw new SQLException("Falha ao buscar os clientes");

			while (rs.next()) {

				Produto newProduto = new Produto();

				newProduto.setIdProduto(rs.getLong("idProduto"));
				newProduto.setNome(rs.getString("nome"));
				newProduto.setDescricao(rs.getString("descricao"));
				newProduto.setPrecoVenda(rs.getDouble("precoVenda"));

				produtos.add(newProduto);

			}

		} catch (SQLException e) {

			e.printStackTrace();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha ao buscar a lista de clientes");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		} finally {

			if (stmtCli != null)
				try {
					stmtCli.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return produtos;

	}

	public Produto get(long idProduto) {

		String sqlGetAll = "Select * from dbo.produtos where idProduto = ?";
		PreparedStatement stmtCli = null;
		Produto newProduto = new Produto();

		try {

			stmtCli = connection.prepareStatement(sqlGetAll);

			stmtCli.setLong(1, idProduto);

			ResultSet rs = stmtCli.executeQuery();

			while (rs.next()) {

				newProduto.setIdProduto(rs.getLong("idProduto"));
				newProduto.setNome(rs.getString("nome"));
				newProduto.setDescricao(rs.getString("descricao"));
				newProduto.setPrecoVenda(rs.getDouble("precoVenda"));

			}

		} catch (SQLException e) {

			e.printStackTrace();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha ao buscar a lista de clientes");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		} finally {

			if (stmtCli != null)
				try {
					stmtCli.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return newProduto;
	}

	public void CloseConnetion() {

		if (connection != null)
			try {
				connection.close();

			} catch (SQLException logOrIgnore) {

			}

	}

}
