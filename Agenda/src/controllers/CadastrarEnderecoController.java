package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Endereco;

public class CadastrarEnderecoController implements Initializable{

    @FXML
    private TextField bairroTextField;

    @FXML
    private Button cancelarButton;

    @FXML
    private TextField cepTextField;

    @FXML
    private TextField cidadeTextField;

    @FXML
    private TextField complementoTextField;

    @FXML
    private Button continuarButton;

    @FXML
    private Label menssagemEnderecoLabel;

    @FXML
    private TextField numeroTextField;

    @FXML
    private Label parteEnderecoLabel;

    @FXML
    private TextField ruaTextField;

    @FXML
    private Label tipoEnderecoLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        App.addOnChangeScreenListener(new App.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen == "cadastrarEndereco")
                {
                    System.out.println("Tela Atualizada\nTela atual: "+newScreen);   
                }
            }
        });
    }

    String tela = "Residencial";
    
    @FXML
    void voltarAction(ActionEvent event) {
        if (tela == "Residencial"){
            App.changeScreen("cadastrarPessoa");
            tela = "Comercial";
        }
        else if(tela == "Comercial") {
            mudarParaEnderecoResidencial();
            esvaziarTextField();
            preencherResidencial();
            tela = "Residencial";
        }
        System.out.println(tela);
    }

    
    @FXML
    void continuarAction(ActionEvent event) {
        if(tela == "Residencial"){
            pegarDados("Residencial");
            System.out.println("Valor residencial "+residencial);
            if(residencial.getBairro() != "" && residencial.getCidade() != ""){
                mudarParaEnderecoComercial();
                esvaziarTextField();
                tela = "Comercial";
            }
        }
        else if(tela == "Comercial"){
            pegarDados("Comercial");
            if(comercial.getBairro() != "" && comercial.getCidade() != ""){
                App.changeScreen("telaInicial");   
            }
        }
    }
    Endereco residencial, comercial;
    
    private void pegarDados(String str){
        if (str == "Residencial"){
            residencial = new Endereco(
                ruaTextField.getText(),
                numeroTextField.getText(), 
                complementoTextField.getText(), 
                bairroTextField.getText(), 
                cepTextField.getText(), 
                cidadeTextField.getText());
                CadastrarPessoaController.pessoa.setEnderecosMap("Residencial", residencial
            );
        }
        else if (str == "Comercial"){
        comercial = new Endereco(
            ruaTextField.getText(),
            numeroTextField.getText(), 
            complementoTextField.getText(), 
            bairroTextField.getText(), 
            cepTextField.getText(), 
            cidadeTextField.getText());
            CadastrarPessoaController.pessoa.setEnderecosMap("Comercial", comercial);
        }
    }
        
    private void mudarParaEnderecoComercial() {
        parteEnderecoLabel.setText("Parte 3 de 3");
        menssagemEnderecoLabel.setText("Agora é so correr pro abraço");
        tipoEnderecoLabel.setText("Endereço Comercial");
        
    }
    
    private void mudarParaEnderecoResidencial() {
        parteEnderecoLabel.setText("Parte 2 de 3");
        menssagemEnderecoLabel.setText("Quase la aguenta ai...");
    tipoEnderecoLabel.setText("Endereço Comercial");
    }
    
    private void preencherResidencial() {
        bairroTextField.setText(residencial.getBairro());
        cepTextField.setText(residencial.getCep());
        cidadeTextField.setText(residencial.getCidade());
        complementoTextField.setText(residencial.getComplemento());
        numeroTextField.setText(residencial.getNumero());
        ruaTextField.setText(residencial.getRua());
    }

    private void esvaziarTextField() {
        bairroTextField.setText("");
        cepTextField.setText("");
        cidadeTextField.setText("");
        complementoTextField.setText("");
        numeroTextField.setText("");
        ruaTextField.setText("");
    }
}
