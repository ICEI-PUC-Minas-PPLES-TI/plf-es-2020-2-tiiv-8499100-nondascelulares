package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Produto;
import br.BD.ConnectionFactorySqlServer;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ProdutoDAO implements DAO<Produto> {

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

		String sqlAdd = "insert into produtos (nome, descricao) values (?, ?)";

		PreparedStatement stmtCli = null;

		try {

			stmtCli = connection.prepareStatement(sqlAdd);

			stmtCli.setString(1, produto.getNome());
			stmtCli.setString(2, produto.getDescricao());

			result = true;

		} catch (SQLException e) {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		} finally {

			if (stmtCli != null || connection != null)
				try {
					stmtCli.close();
					connection.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return result;
	}

	@Override
	public boolean update() {
		boolean result = false;

		String sqlUpdate = "Update produtos SET (nome = ?, descricao = ?) where idProduto = ?";

		PreparedStatement stmtCli = null;

		try {

			stmtCli = connection.prepareStatement(sqlUpdate);

			stmtCli.setString(1, produto.getNome());
			stmtCli.setString(2, produto.getDescricao());
			stmtCli.setLong(3, produto.getIdProduto());

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

			if (stmtCli != null || connection != null)
				try {
					stmtCli.close();
					connection.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return result;

	}

	@Override
	public boolean remove() {
		boolean result = false;

		String sqlDelete = "DELETE from dbo.produtos where idProduto = ?";

		PreparedStatement stmtCli = null;

		try {

			stmtCli = connection.prepareStatement(sqlDelete);

			stmtCli.setLong(1, produto.getIdProduto());

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

			if (stmtCli != null || connection != null)
				try {
					stmtCli.close();
					connection.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return result;

	}

	@Override
	public List<Produto> getAll() {
		
		String sqlGetAll = "Select * from dbo.produtos";
		List<Produto> produtos = new ArrayList<>();
		PreparedStatement stmtCli = null;
		
		
		try {
			
			stmtCli = connection.prepareStatement(sqlGetAll);
	
			ResultSet rs = stmtCli.executeQuery();
			
			//if(rs.next())
			//throw new SQLException("Falha ao buscar os clientes");
				
			 while(rs.next()) {
			
				 Produto newProduto = new Produto();
				 
				 newProduto.setIdProduto(rs.getLong("idProduto"));
				 newProduto.setNome(rs.getString("nome"));
				 newProduto.setDescricao(rs.getString("descricao"));;
				 
				 produtos.add(newProduto);
				 
			 }
			
		} catch(SQLException e) {
		
			
			e.printStackTrace();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha ao buscar a lista de clientes");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			
		}finally {
			
			if (stmtCli != null || connection != null)
				try {
					stmtCli.close();
					connection.close();
					
				} catch (SQLException logOrIgnore) {
					
				}
		}
		return produtos;

		
		
		
	}

}