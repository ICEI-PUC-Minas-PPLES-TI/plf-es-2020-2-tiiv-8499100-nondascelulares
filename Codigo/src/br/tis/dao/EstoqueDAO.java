package br.tis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.tis.bd.ConnectionFactorySqlServer;
import br.tis.entidades.Estoque;
import br.tis.entidades.TipoLancamento;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class EstoqueDAO {

	private final Connection connection;
	private Estoque estoque;

	public EstoqueDAO() {
		new ConnectionFactorySqlServer();
		this.connection = ConnectionFactorySqlServer.getConnection();
	}

	public EstoqueDAO(Estoque estoque) {
		new ConnectionFactorySqlServer();
		this.connection = ConnectionFactorySqlServer.getConnection();
		this.estoque = estoque;
	}

	public boolean addLancamento() {
		boolean result = false;

		String sqlAdd = "insert into estoque (dataLancamento, idProduto, nomeProduto, custoUnitario, precoVendaUnitario, quantidade, tipoLancamento, documento) values (?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmtEstoque = null;

		try {

			stmtEstoque = connection.prepareStatement(sqlAdd);

			stmtEstoque.setDate(1, estoque.getDataLancamento());
			stmtEstoque.setLong(2, estoque.getIdproduto());
			stmtEstoque.setString(3, estoque.getNomeProduto());
			stmtEstoque.setDouble(4, estoque.getCustoUnitario());
			stmtEstoque.setDouble(5, estoque.getPrecoVendaUnitario());
			stmtEstoque.setInt(6, estoque.getQuantidade());
			stmtEstoque.setString(7, String.valueOf(estoque.getTipoLancamento()));
			stmtEstoque.setString(8, estoque.getDocumento());

			stmtEstoque.execute();

			result = true;

		} catch (SQLException e) {

			e.printStackTrace();

			// Alert alert = new Alert(Alert.AlertType.INFORMATION);
			// alert.initStyle(StageStyle.UTILITY);
			// alert.setTitle("Falha");
			// alert.setHeaderText(null);
			// alert.setContentText(e.getMessage());
			// alert.showAndWait();

		} finally {

			if (stmtEstoque != null || connection != null)
				try {
					stmtEstoque.close();
					connection.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return result;

	}

	public boolean removeLancamento(long idEstoque) {
		boolean result = false;

		String sqlDelete = "DELETE from dbo.estoque where idEstoque = ?";

		PreparedStatement stmtLanc = null;

		try {

			stmtLanc = connection.prepareStatement(sqlDelete);

			stmtLanc.setLong(1, idEstoque);

			if (stmtLanc.executeUpdate() == 0)
				throw new SQLException("Falha ao excluir o Lancamento");

			result = true;

		} catch (SQLException e) {

			e.printStackTrace();

			// Alert alert = new Alert(Alert.AlertType.INFORMATION);
			// alert.initStyle(StageStyle.UTILITY);
			// alert.setTitle("Falha");
			// alert.setHeaderText(null);
			// alert.setContentText(e.getMessage());
			// alert.showAndWait();

		} finally {

			if (stmtLanc != null || connection != null)
				try {
					stmtLanc.close();
					connection.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return result;
	}

	public List<Estoque> getAll() {

		String sqlGetAll = "Select * from dbo.estoque";
		List<Estoque> estoque = new ArrayList<>();
		PreparedStatement stmtLanc = null;

		try {

			stmtLanc = connection.prepareStatement(sqlGetAll);

			ResultSet rs = stmtLanc.executeQuery();

			// if(rs.next())

			// throw new SQLException("Falha ao buscar os clientes");

			while (rs.next()) {

				Estoque lancamentos = new Estoque();

				lancamentos.setIdEstoque(rs.getLong("idEstoque"));
				lancamentos.setTipoLancamento(TipoLancamento.valueOf(rs.getString("tipoLancamento")));
				lancamentos.setDataLancamento(rs.getDate("dataLancamento"));
				lancamentos.setDocumento(rs.getString("documento"));
				lancamentos.setIdproduto(rs.getLong("idProduto"));
				lancamentos.setNomeProduto(rs.getString("nomeProduto"));
				lancamentos.setCustoUnitario(rs.getDouble("custoUnitario"));
				lancamentos.setPrecoVendaUnitario(rs.getDouble("precoVendaUnitario"));
				lancamentos.setQuantidade(rs.getInt("quantidade"));

				estoque.add(lancamentos);

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

			if (stmtLanc != null || connection != null)
				try {
					stmtLanc.close();
					connection.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return estoque;
	}

	public double getCustoMedio(long idProduto) {
		double custoMedio = 0;

		String sqlCusto = "SELECT AVG(custoUnitario) AS 'Custo M�dio' FROM [dbo].[estoque] where tipoLancamento = 'ENTRADA' and idProduto = ?";
		PreparedStatement stmtCustoMedio = null;

		try {

			stmtCustoMedio = connection.prepareStatement(sqlCusto);

			stmtCustoMedio.setLong(1, idProduto);

			ResultSet rs = stmtCustoMedio.executeQuery();

			rs.next();

			custoMedio = rs.getDouble("Custo M�dio");

		} catch (SQLException e) {

			e.printStackTrace();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha ao buscar a lista de clientes");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		} finally {

			if (stmtCustoMedio != null || connection != null)
				try {
					stmtCustoMedio.close();
					connection.close();

				} catch (SQLException logOrIgnore) {

				}
		}

		return custoMedio;
	}

	public int getQuantidadeDisponivel(long idProduto) {

		int quantDisp = 0;

		String sqlEntrada = "	SELECT TOP (1)((SELECT SUM(quantidade) FROM [dbo].[estoque] where tipoLancamento = 'ENTRADA' and idProduto = ?) -	(SELECT SUM(quantidade) FROM [dbo].[estoque] where tipoLancamento = 'SAIDA' and idProduto = ?))from [dbo].[estoque] ";

		PreparedStatement stmtQuantDispEntrada = null;

		try {

			stmtQuantDispEntrada = connection.prepareStatement(sqlEntrada);

			stmtQuantDispEntrada.setLong(1, idProduto);
			stmtQuantDispEntrada.setLong(2, idProduto);

			ResultSet rs = stmtQuantDispEntrada.executeQuery();

			rs.next();

			quantDisp = rs.getInt(1);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			if (stmtQuantDispEntrada != null || connection != null)
				try {
					stmtQuantDispEntrada.close();
					connection.close();

				} catch (SQLException logOrIgnore) {

				}
		}

		return quantDisp;
	}

	public int getPrecoVendaMedio(long idProduto) {
		int quantidade = 0;

		return quantidade;
	}

}
