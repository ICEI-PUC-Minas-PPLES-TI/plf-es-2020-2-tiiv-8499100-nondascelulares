
package br.tis.app;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import br.tis.dao.ClienteDAO;
import br.tis.dao.EstoqueDAO;
import br.tis.dao.OrdemServicoDAO;
import br.tis.dao.OrdemVendaDAO;
import br.tis.dao.ProdutoDAO;
import br.tis.entidades.Cliente;
import br.tis.entidades.Estoque;
import br.tis.entidades.ListaAgregada;
import br.tis.entidades.OrdemServico;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
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
                panelNovaOrdemServico.setVisible(false);
                panelBuscarOrdemServico.setVisible(false);
                panelCustoMedio.setVisible(false);

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
	private TableView<ListaAgregada> table_OrdemVenda;
	@FXML
	private TableColumn<ListaAgregada, Long> column_IdProdutoOrdemVenda;
	@FXML
	private TableColumn<ListaAgregada, String> column_NomeOrdemVenda;
	@FXML
	private TableColumn<ListaAgregada, Double> column_PrecoOrdemVenda;
	@FXML
	private TableColumn<ListaAgregada, Double> column_quantidadeSelecionada;

	// ---------------- TABLE BUSCA ORDEM DE VENDA --------------------

	@FXML
	private TableView<OrdemVenda> table_BuscarOrdemVenda;
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
	public void cadastrarOrdemVenda(ActionEvent event) throws IOException {

		Estoque est = new Estoque();
		List<Estoque> lancamentos = new ArrayList<>();
		OrdemVenda ov = new OrdemVenda();
		ov.setIdOrdemVenda(Long.valueOf(codigoOrdemVenda.getText()));
		ov.setData(Date.valueOf(LocalDate.now()));
		ov.setObservacao(observacao_ordemVenda.getText());
		ov.setValorTotal(Float.valueOf(valorTotalOrdemVenda.getText()));
		
		for (ListaAgregada agre : listaProSelecionado) {
			
			est.setTipoLancamento(TipoLancamento.SAIDA);
			est.setDataLancamento(ov.getData());
			est.setIdproduto(agre.getIdProduto());

			est.setNomeProduto(agre.getNomeProduto());
			est.setQuantidade(agre.getQuantidadeDisp());
			est.setDocumento(String.valueOf(ov.getIdOrdemVenda()));
			lancamentos.add(est);
		}
		
		
		OrdemVendaDAO ordemV = new OrdemVendaDAO(ov);
		ordemV.add();
		
		EstoqueDAO estoque = new EstoqueDAO();
		
		estoque.addAll(lancamentos);

		GeraAlerta("Sucesso", "Lançamento Efetuado com sucesso!");

	}
	
	@FXML
	public void carregarProdutosOV(ActionEvent event) throws IOException {

		column_IdProdutoOrdemVenda.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		column_NomeOrdemVenda.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		column_quantidadeSelecionada.setCellValueFactory(new PropertyValueFactory<>("quantidadeDisp"));
		column_PrecoOrdemVenda.setCellValueFactory(new PropertyValueFactory<>("precoVenda"));

		table_OrdemVenda.setItems(ListProdutosOV());

	}

	private ObservableList<ListaAgregada> ListProdutosOV() {
		return FXCollections.observableArrayList(listaProSelecionado);
	}
	
	
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
	public void panelNovaOrdemVendaVisible(ActionEvent event) throws IOException {
		if (panelNovaOrdemVenda.isVisible() == true) {
			panelNovaOrdemVenda.setVisible(false);
			dropDown_Ordens.setVisible(false);
		} else {
			fecharTodosPanel(event);
			carregarProdutosOV(event);
			OrdemVenda ov = new OrdemVenda();
			long numOV = ov.geraNumOrdemVenda();
			codigoOrdemVenda.setText(String.valueOf(numOV));
			
			
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
        
        
        // ----------------- ORDEM DE SERVIÇO ----------------------------------
        
        @FXML
	public AnchorPane panelNovaOrdemServico;
        @FXML
	public AnchorPane panelBuscarOrdemServico;
        @FXML
	public AnchorPane panelVisualizacaoOrdemServico;
        @FXML
	public JFXButton btnNovaOrdemServico;
	@FXML
	public JFXButton btnIncluirClienteOrdemServico;
	@FXML
	public Button btnSalvarOrdemServico;
	@FXML
	public Button btnCancelarOrdemServico;
        @FXML
	public JFXButton btnBuscarOrdemServico;
        @FXML
	public Button btnVisualizarOrdemServico;
        @FXML
	public Button btnExcluirOrdemServico;
        @FXML
	public Button btnAlterarStatus;
        @FXML
	public Button btnSalvarVisualizacaoOrdem;
        @FXML
	public Button btnFecharOrdemServico;
        
        

       // ---------------- ATRIBUTOS DA ORDEM DE SERVICO ------------------------
        
        @FXML
	public TextField campoMarca;
        @FXML
	public TextField campoModelo;
        @FXML
	public TextField campoNumserie;
        @FXML
	public TextField data_ordemservico;
        @FXML
	public TextField campoOutros;
        @FXML
	public TextField campoDefeitos;
        @FXML
	public TextField valorOrcamento;
        @FXML
	public TextField cliente_ordemServico;
        @FXML
	public TextField codOrdemServico;
        @FXML
	public CheckBox opcoes_semchip;
        @FXML
	public CheckBox opcoes_semcartao;
        @FXML
	public CheckBox opcoes_sembateria;
        @FXML
	public CheckBox opcoes_semtampa;
        @FXML
	public CheckBox opcoes_desbloqueio;
        @FXML
	public CheckBox opcoes_trocabateria;
        @FXML
	public CheckBox opcoes_desoxidacao;
        @FXML
	public CheckBox opcoes_atualizacao;
        @FXML
	public CheckBox opcoes_limpeza;
        @FXML
	public CheckBox opcoes_slotchip;
        @FXML
	public CheckBox opcoes_conectorcarga;
        @FXML
	public CheckBox opcoes_trocatouch;
        @FXML
	public RadioButton status_naLoja;
        @FXML
	public RadioButton status_entregue;
               
        
    	@FXML
    	public void cadastrarOrdemServico(ActionEvent event) throws IOException {

    	
    		
    		OrdemServico os = new OrdemServico();
    		
    		os.setIdOrdemServico(Long.valueOf(codOrdemServico.getText()));
    		os.setData(Date.valueOf(data_ordemservico.getText()));
    		os.setMarca(campoMarca.getText());
    		os.setModelo(campoModelo.getText());
    		os.setNumSerie(campoNumserie.getText());
    		os.setStatus(btnAlterarStatus.getText());
    		
    		os.setSemChip(Boolean.valueOf(opcoes_semchip.isSelected()));
    		os.setSemCartaoMemoria(Boolean.valueOf(opcoes_semcartao.isSelected()));
    		os.setSemBateria(Boolean.valueOf(opcoes_sembateria.isSelected()));
    		os.setSemTampaTraseira(Boolean.valueOf(opcoes_semtampa.isSelected()));
    		os.setDesbloqueio(Boolean.valueOf(opcoes_desbloqueio.isSelected()));
    		os.setTrocaBateria(Boolean.valueOf(opcoes_trocabateria.isSelected()));
    		os.setDesoxidacao(Boolean.valueOf(opcoes_desoxidacao.isSelected()));
    		os.setAtualizacao(Boolean.valueOf(opcoes_atualizacao.isSelected()));
    		os.setLimpeza(Boolean.valueOf(opcoes_limpeza.isSelected()));
    		os.setSlotChip(Boolean.valueOf(opcoes_slotchip.isSelected()));
    		os.setConectorCarga(Boolean.valueOf(opcoes_conectorcarga.isSelected()));
    		os.setTrocaTouch(Boolean.valueOf(opcoes_trocatouch.isSelected()));
    		
    		os.setValorTotal(Double.parseDouble(valorOrcamento.getText()));
    		
    		os.setIdCliente(comboBoxIdClientes.getValue().getNome());
    		os.setDefeitos(campoDefeitos.getText());
    		os.setObservacao(campoOutros.getText());
    		
    		OrdemServicoDAO ordemServico = new OrdemServicoDAO(os);
    		ordemServico.add();		

    		GeraAlerta("Sucesso", "Ordem Servico Efetuado com sucesso!");

    	}
        
        
        
        
        
        
        //---------------ATRIBUTOS DA TELA DE VISUALIZAÇÃO--------
        
         @FXML
	public TextField campoMarca1;
        @FXML
	public TextField campoModelo1;
        @FXML
	public TextField campoNumserie1;
        @FXML
	public TextField data_ordemservico1;
        @FXML
	public TextField campoOutros1;
        @FXML
	public TextField campoDefeitos1;
        @FXML
	public TextField valorOrcamento1;
        @FXML
	public TextField cliente_ordemServico1;
        @FXML
	public TextField codOrdemServico1;
        @FXML
	public CheckBox opcoes_semchip1;
        @FXML
	public CheckBox opcoes_semcartao1;
        @FXML
	public CheckBox opcoes_sembateria1;
        @FXML
	public CheckBox opcoes_semtampa1;
        @FXML
	public CheckBox opcoes_desbloqueio1;
        @FXML
	public CheckBox opcoes_trocabateria1;
        @FXML
	public CheckBox opcoes_desoxidacao1;
        @FXML
	public CheckBox opcoes_atualizacao1;
        @FXML
	public CheckBox opcoes_limpeza1;
        @FXML
	public CheckBox opcoes_slotchip1;
        @FXML
	public CheckBox opcoes_conectorcarga1;
        @FXML
	public CheckBox opcoes_trocatouch1;
        @FXML
	public RadioButton status_naLoja1;
        @FXML
	public RadioButton status_entregue1;
        
        
        // -------------------- TABLE DA CONSULTA -----------------
        @FXML
	private TableView<OrdemServico> table_OrdemServico;
	@FXML
	private TableColumn<OrdemServico, Long> column_idOrdemServico;
	@FXML
	private TableColumn<OrdemServico, String> column_nomeOrdemServico;
	@FXML
	private TableColumn<OrdemServico, String> column_modeloOrdemServico;
	@FXML
	private TableColumn<OrdemServico, Double> column_valortotalOrdemServico;
        
	@FXML
	private ComboBox<Cliente> comboBoxIdClientes = new JFXComboBox<Cliente>();

	@FXML
	public void carregarListaClientes(ActionEvent event) {

		ClienteDAO agregado = new ClienteDAO();

		ObservableList<Cliente> obsListaAgregada;

		obsListaAgregada = FXCollections.observableArrayList(agregado.getAll());

		comboBoxIdClientes.setItems(obsListaAgregada);

	}    
	
	
	
	
        @FXML
	public void menuOrdemServicoVisible(MouseEvent event) {
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
	public void menuOrdemServicoVisibleOnClick(ActionEvent event) {
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
	public void panelNovaOrdemServicoVisible(ActionEvent event) {
		if (panelNovaOrdemServico.isVisible() == true) {
			panelNovaOrdemServico.setVisible(false);
			dropDown_Ordens.setVisible(false);
		} else {
			fecharTodosPanel(event);
			OrdemServico os = new OrdemServico();
			long numOS = os.geraNumOrdemServico();
			codOrdemServico.setText(String.valueOf(numOS));
			data_ordemservico.setText(String.valueOf(LocalDate.now()));
			carregarListaClientes(event);
			panelNovaOrdemServico.setVisible(true);

		}
	}
        @FXML
	public void panelBuscaOrdemServicoVisible(ActionEvent event) throws IOException {
		if (panelBuscarOrdemServico.isVisible() == true) {
			panelBuscarOrdemServico.setVisible(false);
			dropDown_Ordens.setVisible(false);
		} else {
			fecharTodosPanel(event);
			panelBuscarOrdemServico.setVisible(true);
			carregarOrdemServicoTabela(event);

		}
	}
        
        
        
        // --- Table Consutar Ordem de venda ---
        private ObservableList<OrdemServico> listaOrdemServico() {

		OrdemServicoDAO OrdDao = new OrdemServicoDAO();

		return FXCollections.observableArrayList(OrdDao.getAll());
	}
                
        public void carregarOrdemServicoTabela (ActionEvent event) throws IOException {
            column_idOrdemServico.setCellValueFactory(new PropertyValueFactory<>("idOrdemServico"));
            column_nomeOrdemServico.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
            column_modeloOrdemServico.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            column_valortotalOrdemServico.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
            
            table_OrdemServico.setItems(listaOrdemServico());
            
        }
        
        	public void excluirOrdemServico(ActionEvent event) throws IOException {
		if (table_OrdemServico.getSelectionModel().getSelectedItem() != null) {
			int idOrdemServicoExcluir;
			idOrdemServicoExcluir =  (int) table_OrdemServico.getSelectionModel().getSelectedItem().getIdOrdemServico();
			OrdemServicoDAO OrdDao = new OrdemServicoDAO();
			OrdDao.remove(idOrdemServicoExcluir);
			GeraAlerta("Excluido com sucesso!", "Produto excluido com sucesso!");
			panelBuscarProdutoVisible(event);
			panelBuscarProdutoVisible(event);
		} else {
			GeraAlerta("Selecione um Produto!", "Nenhum Produto selecionado!");
		}

	}
      
        
        
	// --------------------- ESTOQUE --------------------------

	@FXML
	public AnchorPane panelLancamentoEstoque;
	@FXML
	public AnchorPane panelConsultaEstoque;
        @FXML
	public AnchorPane panelCustoMedio;
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
        @FXML
	public JFXButton btnCustoMedio;

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
        
        
       // ----------- TABLE CUSTO MÉDIO--------------
        @FXML
	private TableView<Estoque> table_custoMedio;
	@FXML
	private TableColumn<Estoque, Number> column_idCM;
	@FXML
	private TableColumn<Estoque, String> column_produtoCM;
	@FXML
	private TableColumn<Estoque, Double> column_custoMedio;
	@FXML
	private TableColumn<Estoque, Number> column_qtDispCM;
        
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
        
        public void panelCustoMedioVisible(ActionEvent event) throws IOException {
		if (panelCustoMedio.isVisible() == true) {
			panelCustoMedio.setVisible(false);
			dropDown_Estoque.setVisible(false);
		} else {
			fecharTodosPanel(event);
			panelCustoMedio.setVisible(true);
		}
	}
        
	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	// ---------------- ATRIBUTOS DA TELA DE INSERÇÃO -----------

	@FXML
	private TextField quantidadeDesejada_inserir;
	
	

	// ---- TABLE DA EXIBIÇÃO DOS PRODUTOS A SEREM INSERIDOS ----

	@FXML
	private TableView<ListaAgregada> table_selecaoProdutos;
	@FXML
	private TableColumn<ListaAgregada, Long> column_idProdListaAgregada;
	@FXML
	private TableColumn<ListaAgregada, Integer> column_qtdDispListaAgregada;
	@FXML
	private TableColumn<ListaAgregada, String> column_nomeProdListaAgregada;
	@FXML
	private TableColumn<ListaAgregada, Double> column_precoVendaListaAgregada;

	@FXML
	public void carregarProdutosSelecao(ActionEvent event) throws IOException {

		column_idProdListaAgregada.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		column_nomeProdListaAgregada.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		column_qtdDispListaAgregada.setCellValueFactory(new PropertyValueFactory<>("quantidadeDisp"));
		column_precoVendaListaAgregada.setCellValueFactory(new PropertyValueFactory<>("precoVenda"));

		table_selecaoProdutos.setItems(ListaAgregadaOV());

	}

	private List<ListaAgregada> listaProSelecionado = new ArrayList<>();

	private ObservableList<ListaAgregada> ListaAgregadaOV() {

		EstoqueDAO lancamentos = new EstoqueDAO();

		return FXCollections.observableArrayList(lancamentos.getEstoqueAgregado());
	}

	// ---------------- TELA DE INSERIR PRODUTO ---------------

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
			carregarProdutosSelecao(event);

		}

	}


	public void btnSelecionarProduto(ActionEvent event) throws IOException {

		ListaAgregada list = new ListaAgregada();

		if (table_selecaoProdutos.getSelectionModel().getSelectedItem() != null
				&& !quantidadeDesejada_inserir.getText().isEmpty()) {

			int qntSelecionada = Integer.parseInt(quantidadeDesejada_inserir.getText());
			long idProdutoSelecionado = table_selecaoProdutos.getSelectionModel().getSelectedItem().getIdProduto();
			String nomeProdutoSelecionado = table_selecaoProdutos.getSelectionModel().getSelectedItem()
					.getNomeProduto();
			float valorProduto = table_selecaoProdutos.getSelectionModel().getSelectedItem().getPrecoVenda();
			
			list.setIdProduto(idProdutoSelecionado);
			list.setQuantidadeDisp(qntSelecionada);
			list.setNomeProduto(nomeProdutoSelecionado);
			list.setPrecoVenda(valorProduto);

			listaProSelecionado.add(list);

			fecharTodosPanel(event);

			carregarProdutosOV(event);
			
			panelNovaOrdemVenda.setVisible(true);
			
			double total = 0; 
			
			for (ListaAgregada tot : listaProSelecionado ) {
				
				total += tot.getTotal();
			}
			
			valorTotalOrdemVenda.setText(String.valueOf(total));

		} else {

			GeraAlerta("Selecione um produto!", "Nenhum produto ou quantidade selecionado!");
		}

	}

}
