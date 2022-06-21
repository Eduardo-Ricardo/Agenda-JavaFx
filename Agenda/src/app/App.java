package app;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    
    private static Stage stage;

    // Scenes
    private static Scene telaInicialScene;
    private static Scene cadastrarPessoaScene;
    private static Scene cadastrarEnderecoScene;
    private static Scene detalhesScene;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        primaryStage.setTitle("Tela Inicial");
        
        // Criando telas em "cache"
        Parent fxmlTelaInicial = FXMLLoader.load(getClass().getResource("../view/telaInicial.fxml"));
        telaInicialScene = new Scene(fxmlTelaInicial, 640, 400);
        
        Parent fxmlCadastrarPessoa = FXMLLoader.load(getClass().getResource("../view/cadastrarPessoa.fxml"));
        cadastrarPessoaScene = new Scene(fxmlCadastrarPessoa, 640, 400);
        
        Parent fxmlCadastrarEndereco = FXMLLoader.load(getClass().getResource("../view/cadastrarEndereco.fxml"));
        cadastrarEnderecoScene = new Scene(fxmlCadastrarEndereco, 640, 400);
        
        Parent fxmlDetalhes = FXMLLoader.load(getClass().getResource("../view/detalhes.fxml"));
        detalhesScene = new Scene(fxmlDetalhes, 640, 400);

        primaryStage.setScene(telaInicialScene);
        primaryStage.show();
    }


    // menu de troca de telas
    public static void changeScreen(String scr, Object userData){
        switch(scr){
            case "telaInicial":
                stage.setScene(telaInicialScene);
                notifyAllListeners(scr, userData);
                break;
            case "cadastrarPessoa":
                stage.setScene(cadastrarPessoaScene);
                notifyAllListeners(scr, userData);
                break;
            case "cadastrarEndereco":
                stage.setScene(cadastrarEnderecoScene);
                notifyAllListeners(scr, userData);
                break;
            case "detalhes":
                stage.setScene(detalhesScene);
                notifyAllListeners(scr, userData);
                break;
        }
    }
    
    public static void changeScreen(String scr){
        changeScreen(scr, null);
    }


    // Um observador que avisa os componentes cada vez que a tela é mudada
//---------------------------------------------------------------------------------------
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen{
        void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Object userData){
        for(OnChangeScreen l : listeners){
            l.onScreenChanged(newScreen, userData);
        }
    }
//----------------------------------------------------------------------------------------
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
    