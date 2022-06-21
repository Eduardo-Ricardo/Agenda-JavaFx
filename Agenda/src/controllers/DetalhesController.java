package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Pessoa;

public class DetalhesController implements Initializable{

    @FXML
    private TextField bairroComercialTextField;

    @FXML
    private TextField bairroResidencialTextField;

    @FXML
    private TextField cepComercialTextField;

    @FXML
    private TextField cepResidencialTextField;

    @FXML
    private TextField cidadeComercialTextField;

    @FXML
    private TextField cidadeResidencialTextField;

    @FXML
    private TextField complementoComercialTextField;

    @FXML
    private TextField complementoResidencialTextField;

    @FXML
    private TextField dataNascimentoTextField;
    
    @FXML
    private TextField email1TextField;
    
    @FXML
    private TextField email2TextField;
    
    @FXML
    private TextField nomeTextField;
    
    @FXML
    private TextField numeroComercialTextField;
    
    @FXML
    private TextField numeroResidencialTextField;
    
    @FXML
    private TextField rgTextField;
    
    @FXML
    private TextField ruaComercialTextField;
    
    @FXML
    private TextField ruaResidencialTextField;
    
    @FXML
    private TextField telefoneComercialTextField;
    
    @FXML
    private TextField telefonePessoalTextField;
    
    @FXML
    private TextField telefoneResidencialTextField;
    
    @FXML
    private Button voltarButton;
    
    @FXML
    private Button editarButton;
    
    @FXML
    private Button salvarButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adicionar();
        App.addOnChangeScreenListener(new App.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen == "detalhes"){
                    System.out.println("Tela Atualizada\nTela atual: "+newScreen);
                    limparTextField();
                    socorroSenhorMeuDeus();
                    bloquear_liberarTextFields(false);
                }
            }
        });       
    }

    @FXML
    void voltarTelaInicial(ActionEvent event){
        limparTextField();
        App.changeScreen("telaInicial");
    }

    @FXML
    void editarContatos(ActionEvent event) {
        bloquear_liberarTextFields(true);
    }

    @FXML
    void salvarContatos(ActionEvent event) {
        Pessoa p = TelaInicialController.pessoaDetalhes;
        p.setNome(nomeTextField.getText());
        p.setRg(rgTextField.getText());
        p.setDataNascimento(dataNascimentoTextField.getText());
        p.setTelefonesMap("Pessoal", telefonePessoalTextField.getText());
        p.setTelefonesMap("Residencial", telefoneResidencialTextField.getText());
        p.setTelefonesMap("Comercial", telefoneComercialTextField.getText());
        p.setEmailsMap("Primario", email1TextField.getText());
        p.setEmailsMap("Secundario", email2TextField.getText());
        if(p.getEnderecosMapObj().containsKey("Residencial")){
            p.getEnderecosMap("Residencial").setRua(ruaResidencialTextField.getText());
            p.getEnderecosMap("Residencial").setNumero(numeroResidencialTextField.getText());
            p.getEnderecosMap("Residencial").setBairro(bairroResidencialTextField.getText());
            p.getEnderecosMap("Residencial").setCidade(cidadeResidencialTextField.getText());
            p.getEnderecosMap("Residencial").setComplemento(complementoResidencialTextField.getText());
            p.getEnderecosMap("Residencial").setCep(cepResidencialTextField.getText());
        }
        if(p.getEnderecosMapObj().containsKey("Comercial")){
            p.getEnderecosMap("Comercial").setRua(ruaComercialTextField.getText());
            p.getEnderecosMap("Comercial").setNumero(numeroComercialTextField.getText());
            p.getEnderecosMap("Comercial").setBairro(bairroComercialTextField.getText());
            p.getEnderecosMap("Comercial").setCidade(cidadeComercialTextField.getText());
            p.getEnderecosMap("Comercial").setComplemento(complementoComercialTextField.getText());
            p.getEnderecosMap("Comercial").setCep(cepComercialTextField.getText());
        }
        TelaInicialController.pessoas.set(TelaInicialController.pessoas.indexOf(TelaInicialController.pessoaDetalhes), p); 
        for (int c = 0 ; c < textFieldList.size() ; c++){
            textFieldList.get(c).setEditable(false);
        } 
    }

    void socorroSenhorMeuDeus(){
        // Essa função utiliza do recurso "clamar pelo divino" para Resgatar o 
        // Objeto pessoa selecinada na ListView da tela inicial. 
        Pessoa p = TelaInicialController.pessoaDetalhes;
        System.out.println("Carregado em detalhes \n"+p);

        nomeTextField.setText(p.getNome());
        rgTextField.setText(p.getRg());
        dataNascimentoTextField.setText(""+p.getDataNascimento());
        email1TextField.setText(p.getEmailsMap("Primario"));
        email2TextField.setText(p.getEmailsMap("Secundario"));
        telefonePessoalTextField.setText(p.getTelefonesMap("Pessoal"));
        telefoneResidencialTextField.setText(p.getTelefonesMap("Residencial"));
        telefoneComercialTextField.setText(p.getTelefonesMap("Comercial"));
        
        if(p.getEnderecosMapObj().containsKey("Residencial")){
            ruaResidencialTextField.setText(p.getEnderecosMap("Residencial").getRua());
            numeroResidencialTextField.setText(p.getEnderecosMap("Residencial").getNumero());
            complementoResidencialTextField.setText(p.getEnderecosMap("Residencial").getComplemento());
            cepResidencialTextField.setText(p.getEnderecosMap("Residencial").getCep());
            bairroResidencialTextField.setText(p.getEnderecosMap("Residencial").getBairro());
            cidadeResidencialTextField.setText(p.getEnderecosMap("Residencial").getCidade());
        }

        if(p.getEnderecosMapObj().containsKey("Comercial")){
            ruaComercialTextField.setText(p.getEnderecosMap("Comercial").getRua());
            numeroComercialTextField.setText(p.getEnderecosMap("Comercial").getNumero());
            complementoComercialTextField.setText(p.getEnderecosMap("Comercial").getComplemento());
            cepComercialTextField.setText(p.getEnderecosMap("Comercial").getCep());
            bairroComercialTextField.setText(p.getEnderecosMap("Comercial").getBairro());
            cidadeComercialTextField.setText(p.getEnderecosMap("Comercial").getCidade());
        }
    }

    List<TextField> textFieldList = new ArrayList<>();

    void adicionar(){
        textFieldList.add(nomeTextField);
        textFieldList.add(rgTextField);
        textFieldList.add(dataNascimentoTextField);
        textFieldList.add(email1TextField);
        textFieldList.add(email2TextField);
        textFieldList.add(telefonePessoalTextField);
        textFieldList.add(telefoneResidencialTextField);
        textFieldList.add(telefoneResidencialTextField);
        textFieldList.add(ruaResidencialTextField);
        textFieldList.add(numeroResidencialTextField);
        textFieldList.add(complementoResidencialTextField);
        textFieldList.add(cepResidencialTextField);
        textFieldList.add(bairroResidencialTextField);
        textFieldList.add(cidadeResidencialTextField);
        textFieldList.add(ruaComercialTextField);
        textFieldList.add(numeroComercialTextField);
        textFieldList.add(complementoComercialTextField);
        textFieldList.add(cepComercialTextField);
        textFieldList.add(bairroComercialTextField);
        textFieldList.add(cidadeComercialTextField);
        bloquear_liberarTextFields(false);
    }

    private void bloquear_liberarTextFields(boolean b){
        for (int c = 0 ; c < textFieldList.size() ; c++){
            textFieldList.get(c).setEditable(b);
        }
    }

    private void limparTextField(){
        for (int c = 0 ; c < textFieldList.size() ; c++){
            textFieldList.get(c).setText("");
        }
    }
}
