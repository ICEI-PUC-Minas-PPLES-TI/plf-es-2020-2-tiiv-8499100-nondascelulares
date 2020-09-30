/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TIS_4;

import com.jfoenix.controls.JFXButton;

import DAO.ClienteDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import Entidades.Cliente;
import Entidades.ExcecaoValorInvalido;
import Entidades.Produto;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Augusto
 */
public class FXMLController implements Initializable {

	private static ClienteDao cliDAO;

	// Array cliDAO.getAll();
	// --------- Menu ------------
	@FXML
	public JFXButton btnMenuCliente;
	@FXML
	public AnchorPane dropDown_Cliente;
	@FXML
	public JFXButton btnMenuProduto;
	@FXML
	public AnchorPane dropDown_Produto;
	@FXML
	public AnchorPane panelCadastrarProduto;
	@FXML
	public JFXButton btnNovoProduto;
	@FXML
	public JFXButton btnNovoCliente;
	@FXML
	public JFXButton btnBuscarCliente;
	@FXML
	public AnchorPane panelCadastrarCliente;
	@FXML
	public AnchorPane panelBuscarCliente;
	@FXML
	private Button btnExcluirCliente;
	@FXML
	private Button btnEditarCliente;
	@FXML
	public AnchorPane panelBuscarProduto;
	@FXML
	private Button btnExcluirProduto;
	@FXML
	private Button btnEditarProduto;

	// --------- Table Buscar Produto --------

	@FXML
	private TableView<?> table_Produto;
	@FXML
	private TableColumn<?, ?> column_IdProduto;
	@FXML
	private TableColumn<?, ?> column_NomeProduto;
	@FXML
	private TableColumn<?, ?> column_PrecoVendaProduto;

	@FXML
	public void fecharTodosMenus(MouseEvent event) {
		dropDown_Cliente.setVisible(false);
		dropDown_Produto.setVisible(false);
	}

	@FXML
	public void fecharTodosPanel(ActionEvent event) {
		panelCadastrarProduto.setVisible(false);
		panelCadastrarCliente.setVisible(false);
		panelBuscarCliente.setVisible(false);
		panelBuscarCliente.setVisible(false);
		panelBuscarProduto.setVisible(false);

	}

	@FXML
	public void menuClienteVisible(MouseEvent event) {
		if (dropDown_Cliente.isVisible() == true) {
			dropDown_Cliente.setVisible(false);
		} else {
			dropDown_Cliente.setVisible(true);
			dropDown_Produto.setVisible(false);
		}
	}

	@FXML
	public void menuProdutoVisible(MouseEvent event) {
		if (dropDown_Produto.isVisible() == true) {
			dropDown_Produto.setVisible(false);
		} else {
			dropDown_Produto.setVisible(true);
			dropDown_Cliente.setVisible(false);
		}
	}

	@FXML
	public void menuClienteVisibleOnClick(ActionEvent event) {
		if (dropDown_Cliente.isVisible() == true) {
		} else {
			dropDown_Cliente.setVisible(true);
			dropDown_Produto.setVisible(false);
		}
	}

	@FXML
	public void menuProdutoVisibleOnClick(ActionEvent event) {
		if (dropDown_Produto.isVisible() == true) {
		} else {
			dropDown_Produto.setVisible(true);
			dropDown_Cliente.setVisible(false);
		}
	}

	@FXML
	public void panelNovoProdutoVisible(ActionEvent event) {
		if (panelCadastrarProduto.isVisible() == true) {
			panelCadastrarProduto.setVisible(false);
			dropDown_Produto.setVisible(false);
		} else {
			fecharTodosPanel(event);
			panelCadastrarProduto.setVisible(true);
		}
	}

	@FXML
	public void panelNovoClienteVisible(ActionEvent event) {
		if (panelCadastrarCliente.isVisible() == true) {
			panelCadastrarCliente.setVisible(false);
			dropDown_Cliente.setVisible(false);
		} else {
			fecharTodosPanel(event);
			panelCadastrarCliente.setVisible(true);

		}
	}

	@FXML
	public void panelBuscarClienteVisible(ActionEvent event) throws IOException {
		if (panelBuscarCliente.isVisible() == true) {
			panelBuscarCliente.setVisible(false);
			dropDown_Produto.setVisible(false);
		} else {
			fecharTodosPanel(event);
                        carregarClienteTabela(event);
			panelBuscarCliente.setVisible(true);
		}
	}

	@FXML
	public void panelBuscarProdutoVisible(ActionEvent event) throws IOException {
		if (panelBuscarProduto.isVisible() == true) {
			panelBuscarProduto.setVisible(false);
			dropDown_Cliente.setVisible(false);
		} else {
			fecharTodosPanel(event);
			carregarProdutoTabela(event);
			panelBuscarProduto.setVisible(true);
		}
	}

	// -------- Cadastro Cliente ------------

	@FXML
	public TextField cadastroCliente_nome;
	@FXML
	public TextField cadastroCliente_Cpf;
	@FXML
	public TextField cadastroCliente_Telefone;
	@FXML
	public TextField cadastroCliente_Email;
	@FXML
	public TextField cadastroCLiente_Rua;
	@FXML
	public TextField cadastroCliente_numRes;
	@FXML
	public TextField cadastroCliente_Bairro;
	@FXML
	public TextField cadastroCliente_Cidade;
	
	@FXML
	public Button btnCadastrarCliente;
	

	@FXML
	public void cadastrarCliente(ActionEvent event) throws IOException {

		Cliente novoCliente = new Cliente();
		novoCliente.setNome(cadastroCliente_nome.getText());
		novoCliente.setCpfCnpj(cadastroCliente_Cpf.getText());
		novoCliente.setEmail(cadastroCliente_Email.getText());
		novoCliente.setTelefone(cadastroCliente_Telefone.getText());
		novoCliente.setRua(cadastroCLiente_Rua.getText());
		novoCliente.setNumero(cadastroCliente_numRes.getText());
		novoCliente.setBairro(cadastroCliente_Bairro.getText());
		novoCliente.setCidade(cadastroCliente_Cidade.getText());

		cliDAO = new ClienteDao(novoCliente);

		cliDAO.add();

		cliDAO = new ClienteDao();

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("Cliente cadastrado com sucesso!");
		alert.showAndWait();

		
	}
	

	// --------- Table Buscar Cliente --------
	@FXML
	private TableColumn<Cliente, String> column_Nome;
	@FXML
	private TableColumn<Cliente, String> column_CpfCnpj;
	@FXML
	private TableColumn<Cliente, String> column_Email;
	@FXML
	private TableColumn<Cliente, String> column_Telefone;
	@FXML
	private TableColumn<Cliente, String> column_Rua;
	@FXML
	private TableColumn<Cliente, String> column_Numero;
	@FXML
	private TableColumn<Cliente, String> column_Bairro;
	@FXML
	private TableColumn<Cliente, String> column_Cidade;
	@FXML
	private TableColumn<Cliente, String> column_Estado;
	
	
	@FXML
	private TableView<Cliente> table_Cliente;

	@FXML
	public void carregarClienteTabela(ActionEvent event) throws IOException {

		column_Nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		column_CpfCnpj.setCellValueFactory(new PropertyValueFactory<>("cpfCnpj"));
		column_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
		column_Telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		column_Rua.setCellValueFactory(new PropertyValueFactory<>("rua"));
		column_Numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
		column_Bairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
		column_Cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
		column_Estado.setCellValueFactory(new PropertyValueFactory<>("estado"));

		table_Cliente.setItems(listaDeClientes());

	}

	private ObservableList<Cliente> listaDeClientes() {

		cliDAO = new ClienteDao();

		return FXCollections.observableArrayList(cliDAO.getAll());
	}

	// -------- Cadastro Produto ------------

	@FXML
	public TextField cadastroProdutoName;
	@FXML
	public TextField cadastroProdutoDescri;
	@FXML
	public TextField quantidadeProd;
	@FXML
	public TextField precoVendaProd;
	@FXML
	public TextField precoCusto;	
	@FXML
	public Button bntIncluirProd;


	@FXML
	public void cadastrarProduto(ActionEvent event) throws IOException, NumberFormatException, ExcecaoValorInvalido {

		Produto novoProduto = new Produto();
<<<<<<< HEAD
<<<<<<< HEAD
		novoProduto.setNome(cadastroProdutoName.getText());
		novoProduto.setDescricao(cadastroProdutoDescri.getText());

		prodDAO = new ProdutoDAO(novoProduto);

		prodDAO.add();

		GeraAlerta("Sucesso", "Produto cadastrado com sucesso!");

	}
	
	
	// --------- Table Buscar Produto --------

		@FXML
		private TableView<Produto> table_Produto;
		@FXML
		private TableColumn<Produto, Long> column_IdProduto;
		@FXML
		private TableColumn<Produto, String> column_NomeProduto;
		@FXML
		private TableColumn<Produto, String> column_DescriProduto;
		
		@FXML
		public void carregarProdutoTabela(ActionEvent event) throws IOException {

			column_IdProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
			column_NomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
			column_DescriProduto.setCellValueFactory(new PropertyValueFactory<>("descricao"));
	
			table_Produto.setItems(listaDeProdutos());

		}

		private ObservableList<Produto> listaDeProdutos() {

			ProdutoDAO ProDao = new ProdutoDAO();
			
			return FXCollections.observableArrayList(ProDao.getAll());
		}

	
	
	

	private void GeraAlerta(String titulo, String conteudoTexto) {
=======
		novoProduto.setIdProduto(456L);
>>>>>>> parent of e9ec400... DAO Produtos
=======
		novoProduto.setIdProduto(456L);
>>>>>>> parent of e9ec400... DAO Produtos

		novoProduto.setPrecoCompra(Float.parseFloat(precoCusto.getText()));
		novoProduto.setPrecoVenda(Float.parseFloat(precoVendaProd.getText()));
		novoProduto.setNome(cadastroProdutoName.getText());

		JOptionPane.showMessageDialog(null, novoProduto.getNome() + " inserido com sucesso!");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

}
