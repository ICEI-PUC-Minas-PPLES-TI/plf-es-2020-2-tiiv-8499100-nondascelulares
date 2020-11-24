package br.tis.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.tis.bd.ConnectionFactorySqlServer;
import br.tis.entidades.OrdemVenda;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class OrdemVendaDAO implements DAO<OrdemVenda,Integer>{

	private final Connection connection;
	long inicio, fim;
	private OrdemVenda ordemVenda;
	
	public  OrdemVendaDAO() {
		new ConnectionFactorySqlServer();
		this.connection = ConnectionFactorySqlServer.getConnection();
	}
	
	public  OrdemVendaDAO(OrdemVenda ordemVenda) {
		new ConnectionFactorySqlServer();
		this.connection = ConnectionFactorySqlServer.getConnection();
		this.ordemVenda = ordemVenda;
	}

	@Override
	public boolean add() {
		
		boolean result = false; 
		
		String sqlAdd = "insert into ordemVendas (idOrdemVenda, valorTotal, cpfCnpjCliente, observacao, dataVenda) values (?, ?, ?, ?, ?)";
		
		PreparedStatement stmtCli = null;
		
		
		try {
			
			stmtCli = connection.prepareStatement(sqlAdd);
			
			stmtCli.setFloat(1, ordemVenda.getIdOrdemVenda());
			stmtCli.setFloat(2, ordemVenda.getValorTotal());
			stmtCli.setString(3, ordemVenda.getCpfCnpjCliente());
			stmtCli.setString(4, ordemVenda.getObservacao());
			stmtCli.setDate(5,ordemVenda.getData());
			
			stmtCli.execute();

			result = true; 
			
		} catch(SQLException e) {
		
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			
		}finally {
			
			if (stmtCli != null )
				try {
					stmtCli.close();
					
				} catch (SQLException logOrIgnore) {
					
				}
		}
		return result;
		
	}

	@Override
	public boolean update() {
		
		boolean result = false; 
		
	String sqlUpdate = "Update ordemVendas SET (valorTotal = ?, cpfCnpjCliente = ?, observacao = ?) where idOrdemVenda = ?";
		
		PreparedStatement stmtCli = null;
		
		try {
			
		    stmtCli = connection.prepareStatement(sqlUpdate);
			
			stmtCli.setFloat(1, ordemVenda.getValorTotal());
			stmtCli.setString(2, ordemVenda.getCpfCnpjCliente());
			stmtCli.setString(3, ordemVenda.getObservacao());
			stmtCli.setLong(4, ordemVenda.getIdOrdemVenda());
		
			int updateCount = stmtCli.executeUpdate(sqlUpdate);

			if(updateCount == 0)
				throw new SQLException("Falha ao atualizar o Ordem de venda");
			
			result = true; 
			
		} catch(SQLException e) {
		
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			
		}finally {
			
			if (stmtCli != null )
				try {
					stmtCli.close();
					
				} catch (SQLException logOrIgnore) {
					
				}
		}
		return result;
		
	}

	@Override
	public boolean remove(Integer chave) {
		
	boolean result = false; 
		
		String sqlDelete = "DELETE from dbo.ordemVendas where idOrdemVenda = ?";
		
		PreparedStatement stmtCli = null;
		
		
		try {
			
			stmtCli = connection.prepareStatement(sqlDelete);
			
			stmtCli.setLong(1, Long.valueOf(chave));
		
			
			if(stmtCli.executeUpdate() == 0)
				throw new SQLException("Falha ao excluir o cliente");
			
			result = true; 
			
		} catch(SQLException e) {
		
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			
		}finally {
			
			if (stmtCli != null)
				try {
					stmtCli.close();
					
				} catch (SQLException logOrIgnore) {
					
				}
		}
		return result;
	}
	
	@Override
	public List<OrdemVenda> getAll() {
		
		String sqlGetAll = "Select * from dbo.ordemVendas order by idOrdemVenda";
		List<OrdemVenda> ordens = new ArrayList<>();
		PreparedStatement stmtCli = null;
		
		
		try {
			
			stmtCli = connection.prepareStatement(sqlGetAll);
	
			ResultSet rs = stmtCli.executeQuery();
			
			 while(rs.next()) {
			
				 OrdemVenda newOV = new OrdemVenda();
				 
				 newOV.setIdOrdemVenda(rs.getLong("idOrdemVenda"));
				 newOV.setCpfCnpjCliente(rs.getString("CpfCnpjCliente"));
				 newOV.setValorTotal(rs.getFloat("valorTotal"));
				 newOV.setObservacao("observacao");
				 newOV.setData(rs.getDate("dataVenda"));
				
				 ordens.add(newOV);
				 
			 }
			
		} catch(SQLException e) {
		
			
			e.printStackTrace();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha ao buscar a lista de Ordens de Venda");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			
		}finally {
			
			if (stmtCli != null)
				try {
					stmtCli.close();
					
				} catch (SQLException logOrIgnore) {
					
				}
		}
		return ordens;
		

	}

	
	
}
