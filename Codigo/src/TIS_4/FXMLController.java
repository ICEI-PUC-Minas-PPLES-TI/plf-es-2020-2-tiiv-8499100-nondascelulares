/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TIS_4;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Entidades.Cliente;
import Entidades.ExcecaoValorInvalido;
import Entidades.Produto;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import DAO.CadastroClienteDAO;
import DAO.ExcecaoValorDuplicado;
import DAO.ProdutoDAO;


/**
 * FXML Controller class
 *
 * @author Augusto
 */
public class FXMLController implements Initializable { 
    
    private static CadastroClienteDAO cliDAO;
    private static ProdutoDAO prodDAO;
    //Array cliDAO.getAll();
    // --------- Menu ------------
    @FXML public JFXButton btnMenuCliente;
    @FXML public AnchorPane dropDown_Cliente;
    @FXML public JFXButton btnMenuProduto;
    @FXML public AnchorPane dropDown_Produto;
    @FXML public AnchorPane panelCadastrarProduto;
    @FXML public JFXButton btnNovoProduto;
    @FXML public JFXButton btnNovoCliente;
    @FXML public AnchorPane panelCadastrarCliente;
    
    @FXML public void menuClienteVisible(ActionEvent event){
        if (dropDown_Cliente.isVisible() == true){
            dropDown_Cliente.setVisible(false);
        }else{
            dropDown_Cliente.setVisible(true);
            dropDown_Produto.setVisible(false);
        }
    }
    
        @FXML public void menuProdutoVisible(ActionEvent event){
        if (dropDown_Produto.isVisible() == true){
            dropDown_Produto.setVisible(false);
        }else{
            dropDown_Produto.setVisible(true);
            dropDown_Cliente.setVisible(false);
        }
    }
        
        @FXML public void panelNovoProdutoVisible(ActionEvent event){
            if (panelCadastrarProduto.isVisible() == true){
            panelCadastrarProduto.setVisible(false);
            dropDown_Produto.setVisible(false);
        }else{
            dropDown_Produto.setVisible(false);
            panelCadastrarProduto.setVisible(true);
            panelCadastrarCliente.setVisible(false);
            }  
        }
    
        
          @FXML public void panelNovoClienteVisible(ActionEvent event){
            if (panelCadastrarCliente.isVisible() == true){
            panelCadastrarCliente.setVisible(false);
            dropDown_Cliente.setVisible(false);
        }else{
            dropDown_Cliente.setVisible(false);
            panelCadastrarCliente.setVisible(true);
            panelCadastrarProduto.setVisible(false);
            }  
        }
          
          
          //-------- Cadastro Cliente ------------
          
          @FXML public TextField cadastroCliente_nome;
          @FXML public TextField cadastroCliente_Cpf;
          @FXML public TextField cadastroCliente_Telefone;
          @FXML public TextField cadastroCliente_Email;
          @FXML public Button btnCadastrarCliente;
          
          @FXML public void cadastrarCliente(ActionEvent event) throws IOException, ExcecaoValorDuplicado{
              cliDAO = new CadastroClienteDAO("Clientes");
              Cliente novoCliente = new Cliente();
              novoCliente.setNome(cadastroCliente_nome.getText());
              novoCliente.setCpfCnpj(Long.parseLong(cadastroCliente_Cpf.getText()));
              novoCliente.setEmail(cadastroCliente_Email.getText());
              novoCliente.setTelefone(cadastroCliente_Telefone.getText()); 
              
              cliDAO.add(novoCliente);
              JOptionPane.showMessageDialog(null, novoCliente.getNome() + " inserido com sucesso!");
          }
         
          
          //-------- Cadastro Produto ------------
          
          @FXML public TextField cadastroProdutoName;
          @FXML public TextField cadastroProdutoDescri;
          @FXML public TextField quantidadeProd;
          @FXML public TextField precoVendaProd;
          @FXML public TextField precoCusto;
          @FXML public Button bntIncluirProd;
          
          @FXML public void cadastrarProduto(ActionEvent event) throws IOException, NumberFormatException, ExcecaoValorInvalido{
        	  prodDAO = new ProdutoDAO("Produtos");
              Produto novoProduto = new Produto();
              novoProduto.setIdProduto(456L);
                
              novoProduto.setPrecoCompra(Float.parseFloat(precoCusto.getText()));
              novoProduto.setPrecoVenda(Float.parseFloat(precoVendaProd.getText()));
              novoProduto.setNome(cadastroProdutoName.getText());
              
              prodDAO.add(novoProduto);
              JOptionPane.showMessageDialog(null, novoProduto.getNome() + " inserido com sucesso!");
          }
      
    
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
