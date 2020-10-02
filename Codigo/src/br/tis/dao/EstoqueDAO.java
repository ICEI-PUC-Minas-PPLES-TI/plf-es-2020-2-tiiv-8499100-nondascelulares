package br.tis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.tis.bd.ConnectionFactorySqlServer;
import br.tis.entidades.Cliente;
import br.tis.entidades.Estoque;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;


public class EstoqueDAO implements DAO<Estoque>{

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
	
	
	@Override
	public boolean add() {
	boolean result = false; 
		
		String sqlAdd = "insert into estoque (dataLancamento, idProduto, nomeProduto, custoUnitario, precoVendaUnitario, quantidade, tipoLancamento, documento) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmtEstoque = null;
		
		try {
			
			stmtEstoque = connection.prepareStatement(sqlAdd);
			
			stmtEstoque.setDate(1, estoque.getDataLancamento());
			stmtEstoque.setLong(2, estoque.getProduto().getIdProduto());
			stmtEstoque.setString(3, estoque.getProduto().getNome());
			stmtEstoque.setDouble(4, estoque.getCustoUnitario());
			stmtEstoque.setDouble(5,estoque.getPrecoVendaUnitario());
			stmtEstoque.setInt(6, estoque.getQuantidade());
			stmtEstoque.setString(6, String.valueOf(estoque.getTipoLancamento()));
			stmtEstoque.setString(7, estoque.getDocumento());
			
			result = true; 
			
		} catch(SQLException e) {
		
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			
		}finally {
			
			if (stmtEstoque != null || connection != null)
				try {
					stmtEstoque.close();
					connection.close();
					
				} catch (SQLException logOrIgnore) {
					
				}
		}
		return result;
		
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Estoque> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getPrecoMedio(long idProduto) {
		double precoMedio = 0; 
		
		return precoMedio;
	}
	
	public double RecalculaPrecoMedio(long idProduto) {
		double precoMedio = 0; 
		
		return precoMedio;
	}
	
	public boolean baixaEstoque(long idProduto, int quantidade) {
		return false;
	}
	
	
}
