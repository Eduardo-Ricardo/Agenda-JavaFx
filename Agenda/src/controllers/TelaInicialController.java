package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Endereco;
import model.Pessoa;



public class TelaInicialController implements Initializable{

    //----------------------------------------------------//    
    
    @FXML
    private ListView<Pessoa> listaContatosListView;
    public static List<Pessoa> pessoas = new ArrayList<>();
    private static ObservableList<Pessoa> obsListaContatos;
    
    //----------------------------------------------------//    
    
    @FXML
    private Button mudarPaginaButton;
    
    @FXML
    private Button apagarButton;
    
    @FXML
    private Button editarDetalhesButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarPessoasPreCadastradas();
        App.addOnChangeScreenListener(new App.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen == "telaInicial")
                {
                    obsListaContatos = FXCollections.observableArrayList(pessoas);
                    listaContatosListView.setItems(obsListaContatos);
                    System.out.println("Tela Atualizada\nTela atual: "+newScreen);
                    System.out.println("Lista de contatos atualizada automaticamente");
                }
            }
        });
    }

    @FXML
    void apagarContato(ActionEvent event) {
        Pessoa pessoaExcluir = listaContatosListView.getSelectionModel().getSelectedItem();
        System.out.println("Excluindo contato: "+pessoaExcluir);
        pessoas.remove(pessoaExcluir);
        obsListaContatos.remove(pessoaExcluir);
        listaContatosListView.setItems(obsListaContatos);
    }
    // botao continuar
    @FXML
    void mudarPaginaAction(ActionEvent event) {
        App.changeScreen("cadastrarPessoa", listaContatosListView.getSelectionModel().getSelectedItem());
    }    
    
    
    static Pessoa pessoaDetalhes;
    // Troca de tela para detalhes
    @FXML
    void irParaDetalhes(ActionEvent event) {
        if (listaContatosListView.getSelectionModel().getSelectedItem() != null){
            Pessoa pessoaDetalhes = listaContatosListView.getSelectionModel().getSelectedItem();
            obsListaContatos.get(obsListaContatos.indexOf(pessoaDetalhes));
            mdsMeAjuda(obsListaContatos.get(obsListaContatos.indexOf(pessoaDetalhes)));
            App.changeScreen("detalhes");
        }
        else{
            System.out.println("Selecione um contato antes para ir para parte de detalhes");
        }
    }
    // adiciona uma pessoa a lista de pessoas
    static void updade(Pessoa novaPessoa) {
        pessoas.add(novaPessoa);
    }
    // importante para passagem de parametros do ListView que n é estatico para o poder ser chamda
    void mdsMeAjuda(Pessoa p){
        pessoaDetalhes = p;
        System.out.println("Pessoa armazenada para detalhes" + pessoaDetalhes);
    }
    // criando pessoas para deixar como exemplo
    public void carregarPessoasPreCadastradas(){
        
        // Endereço 1 //

        Endereco endereco1 = new Endereco(
            "Pontal do Sul", 
            "9", 
            "Casa", 
            "Santa Cruz", 
            "98985-213", 
            "Araucaria");

        // Endereço 2 //

        Endereco endereco2 = new Endereco(
            "Antonia Molina Bella", 
            "663", 
            " . . . " , 
            "Vila Verde - Cic", 
            "21546-324", 
            "Curitiba");

        // Enderelo 3 //

        Endereco endereco3 = new Endereco(
            "Bandeira",
            "888",
            "Ficar em Algum lugar",
            "Azul",
            "98765-432", 
            "Curitiba");

        // Pessoa 1 //
        
        Pessoa p1 = new Pessoa(
            "Eduardo", 
            "12345678-9", 
            "18/01/2001", 
            "Primario", 
            "eduric2001@gmail.com", 
            "Secundario", 
            "eduric2001@hotmail.com", 
            "Pessoal", 
            "(41) 9 9874-6543", 
            "Residencial", 
            "(41) 3333-5555", 
            "Comercial", 
            "(41) 3233-4444");
        p1.setEnderecosMap("Residencial", endereco1);
        p1.setEnderecosMap("Comercial", endereco2);

        // Pessoa 2 // 

        Pessoa p2 = new Pessoa(
            "Rciardo",
            "12543333-9",
            "25/03/1980",
            "Primario",
            "email1@Value.com",
            "Secundario",
            "email2@Value.com",
            "Pessoal",
            "(41) 9632-5874",
            "Residencial",
            "(41) 3322-5566",
            "Comercial",
            "(41) 3366-6666");
        p2.setEnderecosMap("Residencial", endereco3);
        p2.setEnderecosMap("Comercial", endereco2);
        
        // pessoa 3 //
        
        Pessoa p3 = new Pessoa("Talita  ", "Pessoal", "(41) 9 4563-3214");
        p3.setDataNascimento("14/8/2019");
        p3.setEnderecosMap("Residencial", endereco1);
        
        pessoas.add(p1);
        pessoas.add(p2);
        pessoas.add(p3);
        obsListaContatos = FXCollections.observableArrayList(pessoas);
        
        listaContatosListView.setItems(obsListaContatos);
    }        
}
