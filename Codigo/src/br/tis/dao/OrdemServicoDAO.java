package br.tis.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.tis.bd.ConnectionFactorySqlServer;
import br.tis.entidades.OrdemServico;
import br.tis.entidades.OrdemVenda;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class OrdemServicoDAO implements DAO<OrdemServico,Integer>{

	private final Connection connection;
	long inicio, fim;
	private OrdemServico ordemServico;
	
	public  OrdemServicoDAO() {
		new ConnectionFactorySqlServer();
		this.connection = ConnectionFactorySqlServer.getConnection();
	}
	
	public  OrdemServicoDAO(OrdemServico ordemServico) {
		new ConnectionFactorySqlServer();
		this.connection = ConnectionFactorySqlServer.getConnection();
		this.ordemServico = ordemServico;
	}

    public OrdemServicoDAO(OrdemVenda ordem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public boolean add() {
		
		boolean result = false; 
		
		String sqlAdd = "insert into ordemServicos (idOrdemServico, data, marca, modelo, numSerie, status, semChip, semCartaoMemoria, semBateria, semTampaTraseira, desbloqueio, trocaBateria, desoxidacao, atualizacao, limpeza, slotChip, conectorCarga, trocaTouch, valorTotal, idCliente, defeitos, observacao) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmtOs = null;
		
		
		try {
			
			stmtOs = connection.prepareStatement(sqlAdd);
			
			stmtOs.setLong(1,ordemServico.getIdOrdemServico());
			stmtOs.setDate(2, ordemServico.getData());
			stmtOs.setString(3, ordemServico.getMarca());
			stmtOs.setString(4, ordemServico.getModelo());
			stmtOs.setString(5, ordemServico.getNumSerie());
			stmtOs.setString(6, ordemServico.getStatus());
			stmtOs.setBoolean(7, ordemServico.isSemChip());
			stmtOs.setBoolean(8, ordemServico.isSemBateria());
			stmtOs.setBoolean(9, ordemServico.isSemTampaTraseira());
			stmtOs.setBoolean(10, ordemServico.isDesbloqueio());
			stmtOs.setBoolean(11, ordemServico.isTrocaBateria());
			stmtOs.setBoolean(12, ordemServico.isDesoxidacao());
			stmtOs.setBoolean(13, ordemServico.isAtualizacao());
			stmtOs.setBoolean(14, ordemServico.isLimpeza());
			stmtOs.setBoolean(15, ordemServico.isSlotChip());
			stmtOs.setBoolean(16, ordemServico.isConectorCarga());
			stmtOs.setBoolean(17, ordemServico.isTrocaTouch());
			stmtOs.setDouble(18, ordemServico.getValorTotal());
			stmtOs.setString(19, ordemServico.getIdCliente());
			stmtOs.setString(20, ordemServico.getDefeitos());
			stmtOs.setString(21, ordemServico.getObservacao());
						
			stmtOs.execute();

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
			
			if (stmtOs != null )
				try {
					stmtOs.close();
					
				} catch (SQLException logOrIgnore) {
					
				}
		}
		return result;
		
	}

	@Override
	public boolean update() {
		
		boolean result = false; 
		
	String sqlUpdate = "Update ordemServico SET (marca = ?, modelo = ?, numSerie = ?, status = ?, semChip = ?, semCartaoMemoria = ?, semBateria = ?, semTampaTraseira = ?, desbloqueio = ?, trocaBateria = ?, desoxidacao = ?, atualizacao = ?, limpeza = ?, slotChip = ?, conectorCarga = ?, trocaTouch = ?, valorTotal = ?, idCliente = ?, defeitos = ?, observacao = ?  ) where idOrdemServico = ?";
		
		PreparedStatement stmtOs = null;
		
		try {
			
		    stmtOs = connection.prepareStatement(sqlUpdate);
			
	
			stmtOs.setString(1, ordemServico.getMarca());
			stmtOs.setString(2, ordemServico.getModelo());
			stmtOs.setString(3, ordemServico.getNumSerie());
			stmtOs.setString(4, ordemServico.getStatus());
			stmtOs.setBoolean(5, ordemServico.isSemChip());
			stmtOs.setBoolean(6, ordemServico.isSemBateria());
			stmtOs.setBoolean(7, ordemServico.isSemTampaTraseira());
			stmtOs.setBoolean(8, ordemServico.isDesbloqueio());
			stmtOs.setBoolean(9, ordemServico.isTrocaBateria());
			stmtOs.setBoolean(10, ordemServico.isDesoxidacao());
			stmtOs.setBoolean(11, ordemServico.isAtualizacao());
			stmtOs.setBoolean(12, ordemServico.isLimpeza());
			stmtOs.setBoolean(13, ordemServico.isSlotChip());
			stmtOs.setBoolean(14, ordemServico.isConectorCarga());
			stmtOs.setBoolean(15, ordemServico.isTrocaTouch());
			stmtOs.setDouble(16, ordemServico.getValorTotal());
			stmtOs.setString(17, ordemServico.getIdCliente());
			stmtOs.setString(18, ordemServico.getDefeitos());
			stmtOs.setString(19, ordemServico.getObservacao());
			
		    stmtOs.setLong(20,ordemServico.getIdOrdemServico());
			
			
			int updateCount = stmtOs.executeUpdate(sqlUpdate);

			if(updateCount == 0)
				throw new SQLException("Falha ao atualizar o Ordem de servico");
			
			result = true; 
			
		} catch(SQLException e) {
		
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			
		}finally {
			
			if (stmtOs != null )
				try {
					stmtOs.close();
					
				} catch (SQLException logOrIgnore) {
					
				}
		}
		return result;
		
	}

	@Override
	public boolean remove(Integer chave) {
		
	boolean result = false; 
		
		String sqlDelete = "DELETE from dbo.ordemServico where idOrdemServico = ?";
		
		PreparedStatement stmtCli = null;
		
		
		try {
			
			stmtCli = connection.prepareStatement(sqlDelete);
			
			stmtCli.setLong(1, Long.valueOf(chave));
		
			
			if(stmtCli.executeUpdate() == 0)
				throw new SQLException("Falha ao excluir o ordemServico");
			
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
	public List<OrdemServico> getAll() {
		
		String sqlGetAll = "Select * from dbo.ordemServico";
		List<OrdemServico> ordens = new ArrayList<>();
		PreparedStatement stmtCli = null;
		
		
		try {
			
			stmtCli = connection.prepareStatement(sqlGetAll);
	
			ResultSet rs = stmtCli.executeQuery();
			
			 while(rs.next()) {
			
				 OrdemServico newOS = new OrdemServico();
				 
				 newOS.setIdOrdemServico(rs.getLong("idOrdemServico"));
				 newOS.setData(rs.getDate("data"));
				 newOS.setMarca(rs.getString("marca"));
				 newOS.setModelo(rs.getString("modelo"));
				 newOS.setNumSerie(rs.getString("numSerie"));
				 newOS.setStatus(rs.getString("status"));
				 newOS.setSemChip(rs.getBoolean("semChip"));
				 newOS.setSemCartaoMemoria(rs.getBoolean("semCartaoMemoria"));
				 newOS.setSemBateria(rs.getBoolean("semBateria"));
				 newOS.setSemTampaTraseira(rs.getBoolean("semTampaTraseira"));
				 newOS.setDesbloqueio(rs.getBoolean("desbloqueio"));
				 newOS.setTrocaBateria(rs.getBoolean("trocaBateria"));
				 newOS.setDesoxidacao(rs.getBoolean("desoxidacao"));
				 newOS.setAtualizacao(rs.getBoolean("atualizacao"));
				 newOS.setLimpeza(rs.getBoolean("limpeza"));
				 newOS.setSlotChip(rs.getBoolean("slotChip"));
				 newOS.setConectorCarga(rs.getBoolean("conectorCarga"));
				 newOS.setTrocaTouch(rs.getBoolean("trocaTouch"));
				 newOS.setValorTotal(rs.getDouble("valorTotal"));
				 newOS.setIdCliente(rs.getString("idCliente"));
				 newOS.setDefeitos(rs.getString("defeitos"));
				 newOS.setObservacao(rs.getString("observacao"));
				 
				 ordens.add(newOS);
				 
			 }
			
		} catch(SQLException e) {
		
			
			e.printStackTrace();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha ao buscar a lista de Ordens de Servico");
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
