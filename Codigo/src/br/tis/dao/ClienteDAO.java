package br.tis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.tis.bd.ConnectionFactorySqlServer;
import br.tis.entidades.Cliente;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ClienteDAO implements DAO<Cliente,String>{

	private final Connection connection;
	long inicio, fim;
	private Cliente cliente;
	
	public  ClienteDAO() {
		new ConnectionFactorySqlServer();
		this.connection = ConnectionFactorySqlServer.getConnection();
	}
	

	public  ClienteDAO(Cliente cliente) {
		new ConnectionFactorySqlServer();
		this.connection = ConnectionFactorySqlServer.getConnection();
		this.cliente = cliente;
	}

	@Override
	public boolean add() {
		
		boolean result = false; 
		
		String sqlAdd = "insert into clientes (nome, cpfCnpj, email, telefone, rua, numero, bairro, cidade, estado) values (?, ?, ?, ?, ?, ?, ?, ? ,?)";
		
		PreparedStatement stmtCli = null;
		
		
		try {
			
			stmtCli = connection.prepareStatement(sqlAdd);
			
			stmtCli.setString(1, cliente.getNome());
			stmtCli.setString(2, cliente.getCpfCnpj());
			stmtCli.setString(3, cliente.getEmail());
			stmtCli.setString(4, cliente.getTelefone());
			stmtCli.setString(5, cliente.getRua());
			stmtCli.setString(6, cliente.getNumero());
			stmtCli.setString(7, cliente.getBairro());
			stmtCli.setString(8, cliente.getCidade());
			stmtCli.setString(9, cliente.getEstado());
			
			if(!stmtCli.execute())
				System.out.println("Passei aqui");
				//throw new SQLException("Falha ao inserir o cliente");
			
			result = true; 
			
		} catch(SQLException e) {
		
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
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
		return result;
		
	}

	@Override
	public boolean update() {
		
		boolean result = false; 
		
	String sqlUpdate = "Update clientes SET (nome = ?, email = ?, telefone = ?, endereco = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?) where cpfCnpj = ?";
		
		PreparedStatement stmtCli = null;
		
		try {
			
		    stmtCli = connection.prepareStatement(sqlUpdate);
			
			stmtCli.setString(1, cliente.getNome());
			stmtCli.setString(2, cliente.getEmail());
			stmtCli.setString(3, cliente.getTelefone());
			stmtCli.setString(4, cliente.getRua());
			stmtCli.setString(5, cliente.getNumero());
			stmtCli.setString(6, cliente.getBairro());
			stmtCli.setString(7, cliente.getCidade());
			stmtCli.setString(8, cliente.getEstado());
			stmtCli.setString(9, cliente.getCpfCnpj());
			
			int updateCount = stmtCli.executeUpdate(sqlUpdate);

			if(updateCount == 0)
				throw new SQLException("Falha ao atualizar o cliente");
			
			result = true; 
			
		} catch(SQLException e) {
		
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Falha");
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
		return result;
		
	}

	@Override
	public boolean remove(String nome) {
		
	boolean result = false; 
		
		String sqlDelete = "DELETE from dbo.clientes where nome = ?";
		
		PreparedStatement stmtCli = null;
		
		
		try {
			
			stmtCli = connection.prepareStatement(sqlDelete);
			
			stmtCli.setString(1, nome);
		
			
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
	public List<Cliente> getAll() {
		
		String sqlGetAll = "Select * from dbo.clientes";
		List<Cliente> clientes = new ArrayList<>();
		PreparedStatement stmtCli = null;
		
		
		try {
			
			stmtCli = connection.prepareStatement(sqlGetAll);
	
			ResultSet rs = stmtCli.executeQuery();
			
			//if(rs.next())
			//throw new SQLException("Falha ao buscar os clientes");
				
			 while(rs.next()) {
			
				 Cliente newCliente = new Cliente();
				 
				 newCliente.setNome(rs.getString("nome"));
				 newCliente.setCpfCnpj(rs.getString("cpfCnpj"));
				 newCliente.setEmail(rs.getString("email"));
				 newCliente.setTelefone(rs.getString("telefone"));
				 newCliente.setRua(rs.getString("rua"));
				 newCliente.setNumero(rs.getString("numero"));
				 newCliente.setBairro(rs.getString("bairro"));
				 newCliente.setCidade(rs.getString("cidade"));
				 newCliente.setEstado(rs.getString("estado"));
				 
				 clientes.add(newCliente);
				 
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
		return clientes;
		

	}
	
	
}
