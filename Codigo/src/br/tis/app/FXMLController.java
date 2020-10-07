
package br.tis.app;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import br.tis.dao.ClienteDAO;
import br.tis.dao.EstoqueDAO;
import br.tis.dao.ProdutoDAO;
import br.tis.entidades.Cliente;
import br.tis.entidades.Estoque;
import br.tis.entidades.ListaAgregada;
import br.tis.entidades.OrdemVenda;
import br.tis.entidades.Produto;
import br.tis.entidades.TipoLancamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Augusto
 */
public class FXMLController implements Initializable {

	private static ProdutoDAO prodDAO;

	// ----------------- Metodos gerais ----------------
	@FXML
	public void fecharTodosMenus(MouseEvent event) {
		dropDown_Cliente.setVisible(false);
		dropDown_Produto.setVisible(false);
		dropDown_Ordens.setVisible(false);
		dropDown_Estoque.setVisible(false);
	}

	@FXML
	public void fecharTodosPanel(ActionEvent event) {
		panelCadastrarProduto.setVisible(false);
		panelCadastrarCliente.setVisible(false);
		panelBuscarCliente.setVisible(false);
		panelBuscarCliente.setVisible(false);
		panelBuscarProduto.setVisible(false);
		panelNovaOrdemVenda.setVisible(false);
		panelBuscarOrdemVenda.setVisible(false);
		panelLancamentoEstoque.setVisible(false);
		panelConsultaEstoque.setVisible(false);
                panelIncluirProduto.setVisible(false);

	}

	private void GeraAlerta(String titulo, String conteudoTexto) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(conteudoTexto);
		alert.showAndWait();

	}

	// --------- CLIENTE ------------

	private static ClienteDAO cliDAO;

	@FXML
	public JFXButton btnMenuCliente;
	@FXML
	public AnchorPane dropDown_Cliente;
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
	public void menuClienteVisible(MouseEvent event) {
		if (dropDown_Cliente.isVisible() == true) {
			dropDown_Cliente.setVisible(false);
		} else {
			dropDown_Cliente.setVisible(true);
			dropDown_Produto.setVisible(false);
			dropDown_Ordens.setVisible(false);
			dropDown_Estoque.setVisible(false);
		}
	}

	@FXML
	public void menuClienteVisibleOnClick(ActionEvent event) {
		if (dropDown_Cliente.isVisible() == true) {
		} else {
			dropDown_Cliente.setVisible(true);
			dropDown_Produto.setVisible(false);
			dropDown_Ordens.setVisible(false);
			dropDown_Estoque.setVisible(false);
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

	// --------- Atributos Cadastro Clientes --------
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

		cliDAO = new ClienteDAO(novoCliente);

		cliDAO.add();

		cliDAO = new ClienteDAO();

		GeraAlerta("Sucesso", "Cliente cadastrado com sucesso!");

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

		cliDAO = new ClienteDAO();

		return FXCollections.observableArrayList(cliDAO.getAll());
	}

	// --------- FIM METODOS CLIENTE ------------

	// ---------- PRODUTOS ---------------------

	@FXML
	public JFXButton btnMenuProduto;
	@FXML
	public AnchorPane dropDown_Produto;
	@FXML
	public AnchorPane panelCadastrarProduto;
	@FXML
	public JFXButton btnNovoProduto;
	@FXML
	public AnchorPane panelBuscarProduto;
	@FXML
	private Button btnExcluirProduto;

	@FXML
	public void menuProdutoVisible(MouseEvent event) {
		if (dropDown_Produto.isVisible() == true) {
			dropDown_Produto.setVisible(false);
		} else {
			dropDown_Produto.setVisible(true);
			dropDown_Cliente.setVisible(false);
			dropDown_Ordens.setVisible(false);
			dropDown_Estoque.setVisible(false);
		}
	}

	@FXML
	public void menuProdutoVisibleOnClick(ActionEvent event) {
		if (dropDown_Produto.isVisible() == true) {
		} else {
			dropDown_Produto.setVisible(true);
			dropDown_Cliente.setVisible(false);
			dropDown_Ordens.setVisible(false);
			dropDown_Estoque.setVisible(false);
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

	@FXML
	public void excluirCliente(ActionEvent event) throws IOException {
		if (table_Cliente.getSelectionModel().getSelectedItem() != null) {
			String nomeClienteExcluir;
			nomeClienteExcluir = table_Cliente.getSelectionModel().getSelectedItem().getNome();

			cliDAO = new ClienteDAO();
			cliDAO.remove(nomeClienteExcluir);
			GeraAlerta("Excluido com sucesso!", "Cliente excluido com sucesso!");
			panelBuscarClienteVisible(event);
			panelBuscarClienteVisible(event);
		} else {
			GeraAlerta("Selecione um cliente!", "Nenhum cliente selecionado!");
		}
	}

	// ----- Search Bar Cliente -----------

	@FXML
	private TextField filterFieldCliente;
	private final ObservableList<Cliente> dataListCliente = FXCollections.observableArrayList();

	FilteredList<Cliente> filteredDataCliente = new FilteredList<>(dataListCliente, b -> true);

	/*
	 * filterFieldCliente.textProperty().addListener((observable, oldValue,
	 * newValue) -> { filteredDataCliente.setPredicate(cliente -> { // If filter
	 * text is empty, display all persons.
	 * 
	 * if (newValue == null || newValue.isEmpty()) { return true; }
	 * 
	 * // Compare first name and last name of every person with filter text. String
	 * lowerCaseFilter = newValue.toLowerCase();
	 * 
	 * if (cliente.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	 * return true; // Filter matches first name. } else if
	 * (cliente.getDepartment().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	 * return true; // Filter matches last name. } else if
	 * (String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter)!=-1) return
	 * true; else return false; // Does not match. }); });
	 * 
	 * // 3. Wrap the FilteredList in a SortedList. SortedList<Cliente>
	 * sortedDataCliente = new SortedList<>(dataListCliente);
	 * 
	 * // 4. Bind the SortedList comparator to the TableView comparator. //
	 * Otherwise, sorting the TableView would have no effect.
	 * sortedDataCliente.comparatorProperty().bind(tableview.comparatorProperty());
	 * 
	 * // 5. Add sorted (and filtered) data to the table.
	 * table_Cliente.setItems(sortedData);
	 * 
	 */

	// -------- Atributos cadastro Produto ------------

	@FXML
	public TextField cadastroProdutoName;
	@FXML
	public TextField cadastroProdutoDescri;
	@FXML
	public TextField precoVendaProd;
	@FXML
	public Button bntIncluirProd;

	@FXML
	public void cadastrarProduto(ActionEvent event) throws IOException {

		Produto novoProduto = new Produto();
		novoProduto.setNome(cadastroProdutoName.getText());
		novoProduto.setDescricao(cadastroProdutoDescri.getText());
		novoProduto.setPrecoVenda(Double.parseDouble(precoVendaProd.getText()));

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
	private TableColumn<Produto, Integer> column_precoVenda;

	@FXML
	public void carregarProdutoTabela(ActionEvent event) throws IOException {

		column_IdProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		column_NomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
		column_DescriProduto.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		column_precoVenda.setCellValueFactory(new PropertyValueFactory<>("precoVenda"));

		table_Produto.setItems(listaDeProdutos());

	}

	private ObservableList<Produto> listaDeProdutos() {

		ProdutoDAO ProDao = new ProdutoDAO();

		return FXCollections.observableArrayList(ProDao.getAll());
	}

	@FXML
	public void excluirProduto(ActionEvent event) throws IOException {
		if (table_Produto.getSelectionModel().getSelectedItem() != null) {
			int idProdutoExcluir;
			idProdutoExcluir = table_Produto.getSelectionModel().getSelectedItem().getIdProduto().intValue();

			prodDAO = new ProdutoDAO();
			prodDAO.remove(idProdutoExcluir);
			GeraAlerta("Excluido com sucesso!", "Produto excluido com sucesso!");
			panelBuscarProdutoVisible(event);
			panelBuscarProdutoVisible(event);
		} else {
			GeraAlerta("Selecione um Produto!", "Nenhum Produto selecionado!");
		}

	}
	// --------- FIM METODOS PRODUTOS ------------

	// --------- ORDEM DE VENDA ------------------

	@FXML
	public AnchorPane panelNovaOrdemVenda;
	@FXML
	public AnchorPane panelBuscarOrdemVenda;
	@FXML
	public AnchorPane dropDown_Ordens;
	@FXML
	public JFXButton btnMenuOrdens;
	@FXML
	public JFXButton btnNovaOrdemVenda;
	@FXML
	public JFXButton btnBuscarOrdemVenda;
	@FXML
	public Button btnCancelarOrdemVenda;
	@FXML
	public Button btnSalvarOrdemVenda;
	@FXML
	public Button btnExcluirProdutoOrdemVenda;
	@FXML
	public Button btnIncluirProdutoOrdemVenda;

	// ----------- TABLE ORDEM DE VENDA -------------

	@FXML
	private TableView<OrdemVenda> table_OrdemVenda;
	@FXML
	private TableColumn<OrdemVenda, Long> column_IdProdutoOrdemVenda;
	@FXML
	private TableColumn<OrdemVenda, String> column_NomeOrdemVenda;
	@FXML
	private TableColumn<OrdemVenda, Double> column_PrecoOrdemVenda;
        @FXML
	private TableColumn<OrdemVenda, Double> column_quantidadeSelecionada;
        
        //---------------- TABLE BUSCA ORDEM DE VENDA --------------------
        
        @FXML
        private TableView <OrdemVenda> table_BuscarOrdemVenda;
        @FXML
        private TableColumn<OrdemVenda, Long> column_CpfCnpjCliente;
        @FXML
        private TableColumn<OrdemVenda, Long> column_ValorTotalOV;
        @FXML
        private TableColumn<OrdemVenda, String> column_observacao;
        
	// -------------- ATRIBUTOS DA ORDEM DE VENDA --------------------

	@FXML
	public TextField codigoOrdemVenda;
	@FXML
	public TextField dataOrdemVenda;
	@FXML
	public TextField valorTotalOrdemVenda;
        @FXML
	public TextField observacao_ordemVenda;   
        
	@FXML
	public void menuOrdemVendaVisible(MouseEvent event) {
		if (dropDown_Ordens.isVisible() == true) {
			dropDown_Ordens.setVisible(false);
		} else {
			dropDown_Ordens.setVisible(true);
			dropDown_Cliente.setVisible(false);
			dropDown_Produto.setVisible(false);
			dropDown_Estoque.setVisible(false);

		}
	}

	@FXML
	public void menuOrdemVendaVisibleOnClick(ActionEvent event) {
		if (dropDown_Ordens.isVisible() == true) {
			dropDown_Ordens.setVisible(false);
		} else {
			dropDown_Ordens.setVisible(true);
			dropDown_Cliente.setVisible(false);
			dropDown_Produto.setVisible(false);
			dropDown_Estoque.setVisible(false);
		}
	}

	@FXML
	public void panelNovaOrdemVendaVisible(ActionEvent event) {
		if (panelNovaOrdemVenda.isVisible() == true) {
			panelNovaOrdemVenda.setVisible(false);
			dropDown_Ordens.setVisible(false);
		} else {
			fecharTodosPanel(event);
			panelNovaOrdemVenda.setVisible(true);
		}
	}

	@FXML
	public void panelBuscarOrdemVendaVisible(ActionEvent event) {
		if (panelBuscarOrdemVenda.isVisible() == true) {
			panelBuscarOrdemVenda.setVisible(false);
			dropDown_Ordens.setVisible(false);
		} else {
			fecharTodosPanel(event);
			panelBuscarOrdemVenda.setVisible(true);
		}
	}
        
     

        

        // ---------------------------------------------------------------------
        
        
	// ----------------- FIM MÉTODOS ORDEM DE VENDA ----------

	// --------------------- ESTOQUE --------------------------

	@FXML
	public AnchorPane panelLancamentoEstoque;
	@FXML
	public AnchorPane panelConsultaEstoque;
	@FXML
	public AnchorPane dropDown_Estoque;
	@FXML
	public JFXButton btnMenuEstoque;
	@FXML
	public JFXButton btnLancamentoEstoque;
	@FXML
	public JFXButton btnConsultaEstoque;
	@FXML
	public Button btnIncluirProd_estoque;
        

	// ----------- ATRIBUTOS DO ESTOQUE ----------------
	@FXML
	private TextField documento_estoque;
	@FXML
	private TextField idProduto_estoque;
	@FXML
	private TextField precoCusto_estoque;
	@FXML
	private TextField quantidade_estoque;

	// ----------- TABLE CONSULTA ESTOQUE --------------
	@FXML
	private TableView<Estoque> table_ConsultaEstoque;
	@FXML
	private TableColumn<Estoque, String> column_TipoLancamento;
	@FXML
	private TableColumn<Estoque, String> column_documentoEstoque;
	@FXML
	private TableColumn<Estoque, Number> column_idProdutoEstoque;
	@FXML
	private TableColumn<Estoque, String> column_NomeProdEstoque;
	@FXML
	private TableColumn<Estoque, Double> column_custoUniEstoque;
	@FXML
	private TableColumn<Estoque, Date> column_DataLancamentoEstoque;
	@FXML
	private TableColumn<Estoque, Integer> column_quantidadeEstoque;

	@FXML
	private ComboBox<ListaAgregada> comboBoxIdProdutos = new JFXComboBox<ListaAgregada>();

	@FXML
	public void carregarLista(ActionEvent event) {

		EstoqueDAO agregado = new EstoqueDAO();

		ObservableList<ListaAgregada> obsListaAgregada;

		obsListaAgregada = FXCollections.observableArrayList(agregado.getEstoqueAgregado());

		comboBoxIdProdutos.setItems(obsListaAgregada);

	}

	@FXML
	public void cadastrarEstoque(ActionEvent event) throws IOException {
		
		Estoque lancamento = new Estoque();
		lancamento.setTipoLancamento(TipoLancamento.ENTRADA);
		lancamento.setDataLancamento(Date.valueOf(LocalDate.now()));
		lancamento.setIdproduto(comboBoxIdProdutos.getValue().getIdProduto());

		lancamento.setNomeProduto(comboBoxIdProdutos.getValue().getNomeProduto());

		lancamento.setCustoUnitario(Double.parseDouble(precoCusto_estoque.getText()));
		lancamento.setQuantidade(Integer.parseInt(quantidade_estoque.getText()));
		lancamento.setDocumento(documento_estoque.getText());

		EstoqueDAO estoque = new EstoqueDAO(lancamento);
		estoque.add();

		GeraAlerta("Sucesso", "Lançamento Efetuado com sucesso!");

	}

	@FXML
	public void carregarEstoqueTabela(ActionEvent event) throws IOException {

		column_TipoLancamento.setCellValueFactory(new PropertyValueFactory<>("TipoLancamento"));
		column_DataLancamentoEstoque.setCellValueFactory(new PropertyValueFactory<>("DataLancamento"));
		column_documentoEstoque.setCellValueFactory(new PropertyValueFactory<>("Documento"));
		column_idProdutoEstoque.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		column_NomeProdEstoque.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		column_custoUniEstoque.setCellValueFactory(new PropertyValueFactory<>("custoUnitario"));
		column_quantidadeEstoque.setCellValueFactory(new PropertyValueFactory<>("Quantidade"));

		table_ConsultaEstoque.setItems(listaDeEstoque());

	}

	private ObservableList<Estoque> listaDeEstoque() {

		EstoqueDAO lancamentos = new EstoqueDAO();

		ObservableList<Estoque> teste;
		
		
		teste = FXCollections.observableArrayList(lancamentos.getAll());
		
		System.out.println(teste.get(0).getNomeProduto());
		
		return teste;
	}

	@FXML
	public void menuEstoqueVisible(MouseEvent event) {
		if (dropDown_Estoque.isVisible() == true) {
			dropDown_Estoque.setVisible(false);

		} else {
			dropDown_Estoque.setVisible(true);
			dropDown_Cliente.setVisible(false);
			dropDown_Produto.setVisible(false);
			dropDown_Ordens.setVisible(false);
		}
	}

	@FXML
	public void menuEstoqueVisibleOnClick(ActionEvent event) {
		if (dropDown_Estoque.isVisible() == true) {
			dropDown_Estoque.setVisible(false);
		} else {
			dropDown_Estoque.setVisible(true);
			dropDown_Cliente.setVisible(false);
			dropDown_Produto.setVisible(false);
			dropDown_Ordens.setVisible(false);
		}
	}

	@FXML
	public void panelEstoqueVisible(ActionEvent event) {
		if (panelLancamentoEstoque.isVisible() == true) {
			panelLancamentoEstoque.setVisible(false);
			dropDown_Estoque.setVisible(false);

		} else {
			fecharTodosPanel(event);
			panelLancamentoEstoque.setVisible(true);
			carregarLista(event);
		}
	}

	public void panelConsultaEstoqueVisible(ActionEvent event) throws IOException {
		if (panelConsultaEstoque.isVisible() == true) {
			panelConsultaEstoque.setVisible(false);
			dropDown_Estoque.setVisible(false);
		} else {
			fecharTodosPanel(event);
			carregarEstoqueTabela(event);
			panelConsultaEstoque.setVisible(true);
		}
	}
        
        

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

        
        
                //---------------- ATRIBUTOS DA TELA DE INSERÇÃO -----------
        
        @FXML
	private TextField quantidadeDesejada_inserir;
        
        //---- TABLE DA EXIBIÇÃO DOS PRODUTOS A SEREM INSERIDOS ----
        
        @FXML
        private TableView <ListaAgregada> table_selecaoProdutos;
        @FXML
        private TableColumn<ListaAgregada, Long> column_idProdListaAgregada;
        @FXML
        private TableColumn<ListaAgregada, Integer> column_qtdDispListaAgregada;
        @FXML
        private TableColumn<ListaAgregada, String> column_nomeProdListaAgregada;
        @FXML
        private TableColumn<ListaAgregada, Double> column_precoVendaListaAgregada;
        

                //---------------- TELA DE INSERIR PRODUTO ---------------
        
        @FXML
        public AnchorPane panelIncluirProduto;
        @FXML
	public Button btnSelecionarProduto;
        @FXML
	public Button btnVoltar;
        public void btnIncluirProduto(ActionEvent event) throws IOException {
            
            		if (panelIncluirProduto.isVisible() == true) {
			panelIncluirProduto.setVisible(false);
			dropDown_Estoque.setVisible(false);
		} else {
			fecharTodosPanel(event);
			panelIncluirProduto.setVisible(true);

		}
            
        }
        
}
