package br.tis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.tis.bd.ConnectionFactorySqlServer;
import br.tis.entidades.Estoque;
import br.tis.entidades.ListaAgregada;
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

	public boolean add() {
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

			geraAlerta("Falha ao registrar lancamento", e.getMessage());

		} finally {

			if (stmtEstoque != null)
				try {
					stmtEstoque.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return result;

	}

	public boolean remove(String documento) {
		boolean result = false;

		String sqlDelete = "DELETE from dbo.estoque where documento = ?";

		PreparedStatement stmtLanc = null;

		try {

			stmtLanc = connection.prepareStatement(sqlDelete);

			stmtLanc.setString(1, documento);

			if (stmtLanc.executeUpdate() == 0)
				throw new SQLException("Falha ao excluir o Lancamento");

			result = true;

		} catch (SQLException e) {

			e.printStackTrace();

			geraAlerta("Falha ao excluir Lancamento", e.getMessage());

		} finally {

			if (stmtLanc != null)
				try {
					stmtLanc.close();

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

			geraAlerta("Falha ao obter o estoque", e.getMessage());

		} finally {

			if (stmtLanc != null)
				try {
					stmtLanc.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return estoque;
	}

	public double getCustoMedio(long idProduto) {
		double custoMedio = 0;

		String sqlCusto = "SELECT AVG(custoUnitario) AS 'Custo Medio' FROM [dbo].[estoque] where tipoLancamento = 'ENTRADA' and idProduto = ?";
		PreparedStatement stmtCustoMedio = null;

		try {

			stmtCustoMedio = connection.prepareStatement(sqlCusto);

			stmtCustoMedio.setLong(1, idProduto);

			ResultSet rs = stmtCustoMedio.executeQuery();

			rs.next();

			custoMedio = rs.getDouble("Custo Medio");

		} catch (SQLException e) {

			e.printStackTrace();

			geraAlerta("Falha no Custo Medio", e.getMessage());

		} finally {

			if (stmtCustoMedio != null)
				try {
					stmtCustoMedio.close();

				} catch (SQLException logOrIgnore) {

				}
		}

		return custoMedio;
	}

	public int getQuantidadeDisponivel(long idProduto) {

		int quantDisp = 0;

		String sqlEntrada = "SELECT SUM(quantidade) AS 'quantidade' FROM [dbo].[estoque] WHere idProduto = ?";

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

			geraAlerta("Falha Quantidade Disponivel", e.getMessage());

		} finally {

			if (stmtQuantDispEntrada != null)
				try {
					stmtQuantDispEntrada.close();

				} catch (SQLException logOrIgnore) {

				}
		}

		return quantDisp;
	}

	public double getPrecoVendaMedio(long idProduto) {
		double precoMedio = 0;

		String sqlPreco = "SELECT AVG(precoVendaUnitario) AS 'Preco Medio' FROM [dbo].[estoque] where tipoLancamento = 'SAIDA' and idProduto = ?";
		PreparedStatement stmtPrecoMedio = null;

		try {

			stmtPrecoMedio = connection.prepareStatement(sqlPreco);

			stmtPrecoMedio.setLong(1, idProduto);

			ResultSet rs = stmtPrecoMedio.executeQuery();

			rs.next();

			precoMedio = rs.getDouble("Preco Medio");

		} catch (SQLException e) {

			e.printStackTrace();

			geraAlerta("Falha no Preco Medio", e.getMessage());

		} finally {

			if (stmtPrecoMedio != null)
				try {
					stmtPrecoMedio.close();

				} catch (SQLException logOrIgnore) {

				}
		}

		return precoMedio;
	}

	private void geraAlerta(String titulo, String descricao) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(descricao);
		alert.showAndWait();
	}

	public List<ListaAgregada> getEstoqueAgregado() {

		String sqlGetAll = "SELECT C.idProduto, C.nomeProduto, SUM(quantidade) AS 'quantidadeDisp', P.precoVenda AS 'precoVenda', Q.precoMedio AS 'precoMedio'FROM [dbo].[estoque] AS C join (SELECT idProduto, AVG(custoUnitario) AS 'precoMedio' from DBO.estoque where tipoLancamento = 'ENTRADA' GROUP BY idProduto)  AS Q on Q.idProduto = C.idProduto join produtos AS P on C.idProduto = P.idProduto group by  C.nomeProduto, C.idProduto, P.precoVenda, Q.precoMedio";
		List<ListaAgregada> estoqueAgregado = new ArrayList<>();
		PreparedStatement stmtLanc = null;

		try {

			stmtLanc = connection.prepareStatement(sqlGetAll);

			ResultSet rs = stmtLanc.executeQuery();

			while (rs.next()) {

				ListaAgregada resumoEstoque = new ListaAgregada();

				resumoEstoque.setIdProduto(rs.getLong("idProduto"));
				resumoEstoque.setNomeProduto(rs.getString("nomeProduto"));
				resumoEstoque.setPrecoVenda(rs.getFloat("precoVenda"));
				resumoEstoque.setQuantidadeDisp(rs.getInt("quantidadeDisp"));
				resumoEstoque.setPrecoMedio(rs.getDouble("precoMedio"));

				estoqueAgregado.add(resumoEstoque);

			}

		} catch (SQLException e) {

			e.printStackTrace();

			geraAlerta("Falha ao obter o estoque resumido", e.getMessage());

		} finally {

			if (stmtLanc != null)
				try {
					stmtLanc.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return estoqueAgregado;
	}

	public void CloseConnetion() {

		if (connection != null)
			try {
				connection.close();

			} catch (SQLException logOrIgnore) {

			}

	}

	public boolean addAll(List<Estoque> lancamentos) {
		boolean result = false;

		String sqlAdd = "insert into estoque (dataLancamento, idProduto, nomeProduto, custoUnitario, precoVendaUnitario, quantidade, tipoLancamento, documento) values (?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmtEstoque = null;

		try {

			connection.setAutoCommit(false);

			stmtEstoque = connection.prepareStatement(sqlAdd);

			for (Estoque est : lancamentos) {

				stmtEstoque.setDate(1, est.getDataLancamento());
				stmtEstoque.setLong(2, est.getIdproduto());
				stmtEstoque.setString(3, est.getNomeProduto());
				stmtEstoque.setDouble(4, est.getCustoUnitario());
				stmtEstoque.setDouble(5, est.getPrecoVendaUnitario());
				stmtEstoque.setInt(6, est.getQuantidade());
				stmtEstoque.setString(7, String.valueOf(est.getTipoLancamento()));
				stmtEstoque.setString(8, est.getDocumento());

				stmtEstoque.addBatch();
			}

			stmtEstoque.executeBatch();

			connection.commit();

			result = true;

		} catch (SQLException e) {

			e.printStackTrace();

			geraAlerta("Falha ao registrar lancamentos", e.getMessage());

		} finally {

			if (stmtEstoque != null)
				try {
					stmtEstoque.close();

				} catch (SQLException logOrIgnore) {

				}
		}
		return result;

	}

}
