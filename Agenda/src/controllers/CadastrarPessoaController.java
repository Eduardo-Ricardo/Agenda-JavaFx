package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Pessoa;

public class CadastrarPessoaController implements Initializable{
    
    public static Pessoa pessoa;
    
    @FXML
    private Button cancelarButton;
    
    @FXML
    private Button continuarButton;
    
    @FXML
    private TextField nomeTextField;
    
    @FXML
    private TextField rgTextField;
    
    @FXML
    private TextField dataDeNascimentoTextField;
    
    @FXML
    private TextField emailPrimarioTextField;
    
    @FXML
    private TextField emailSecundarioTextField;
    
    @FXML
    private TextField telefonePessoalTextField;
    
    @FXML
    private TextField telefoneResidencialTextField;
    
    @FXML
    private TextField telefoneComercialTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        App.addOnChangeScreenListener(new App.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen == "cadastrarPessoa"){
                    System.out.println("Tela Atualizada\nTela atual: "+newScreen);
                    esvaziarTextField();
                }
            }
        });
        
    }
    
    @FXML
    void voltarTelaInicial(ActionEvent event) {
        App.changeScreen("telaInicial");
    }    
    // Depois de validar o cadastro instancia um novo objeto pessoa e faz o up date para a ListView
    @FXML
    void proximaPaginaEndereco(ActionEvent event) {
        if( validarCadastro() ) {
            pessoa = new Pessoa(
                nomeTextField.getText(), 
                rgTextField.getText(), 
                dataDeNascimentoTextField.getText(), 
                "Primario", emailPrimarioTextField.getText(), 
                "Segundario", emailSecundarioTextField.getText(), 
                "Pessoal", telefonePessoalTextField.getText(), 
                "Residencial", telefoneResidencialTextField.getText(), 
                "Comercial", telefoneComercialTextField.getText()
                );
                TelaInicialController.updade(pessoa);
                App.changeScreen("cadastrarEndereco"
            );
        }
    }
    // para que  cadastro seja efetuado com sucesso é nessessário cadastrar o nome e pelomenos um tipo de telefone
    private boolean validarCadastro(){
        if(nomeTextField.getText() != ""){
            if((telefoneComercialTextField.getText() + telefonePessoalTextField.getText() + telefoneResidencialTextField.getText()) != null){
                System.out.println("Cadastro efetuado");
                return true;
            }
        }
        return false;
    }
    // cada vez que a tela é chamada esvazia os Text Fields
    public void esvaziarTextField(){
        nomeTextField.setText("");
        rgTextField.setText("");
        dataDeNascimentoTextField.setText("");
        emailPrimarioTextField.setText("");
        emailSecundarioTextField.setText("");
        telefonePessoalTextField.setText("");
        telefoneResidencialTextField.setText("");
        telefoneComercialTextField.setText("");
    }
}
