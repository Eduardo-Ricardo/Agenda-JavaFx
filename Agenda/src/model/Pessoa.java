package model;

import java.util.HashMap;
import java.util.Map;

public class Pessoa {
    
    // Atributos
    private String nome;
    private String rg;
    private String dataNascimento;
    
    private Map<String, String> emailsMap =  new HashMap<>();
    private Map<String, String> telefonesMap = new HashMap<>();
    private Map<String, Endereco> enderecosMap = new HashMap<>();    

    // Construtor Completo
    public Pessoa(
        String nome, 
        String rg, 
        String dataNascimento,
        String email1Key,
        String email1Value,
        String email2Key,
        String email2Value,
        String telPessoalkey,
        String telPessoalValue,
        String telResidencialkey,
        String telResidencialValue,
        String telComercialkey,
        String telComercialValue
        ) {

        this.nome = nome;
        this.rg = rg;
        this.dataNascimento = dataNascimento;

        this.emailsMap.put(email1Key, email1Value);
        this.emailsMap.put(email2Key, email2Value);

        this.telefonesMap.put(telPessoalkey, telPessoalValue);
        this.telefonesMap.put(telResidencialkey, telResidencialValue);
        this.telefonesMap.put(telComercialkey, telComercialValue);
    }

    // Construtor basico

    public Pessoa(String nome, String telefoneKey, String telefoneValue) {
        this.nome = nome;
        this.telefonesMap.put(telefoneKey, telefoneValue);
    }
        
    // gettes e settes  
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
    // HashMaps gettes e settes 
    
    public Map<String, Endereco> getEnderecosMapObj(){
        return this.enderecosMap;
    }
    
    public String getEmailsMap(String key) {
        return emailsMap.get(key);
    }
    public void setEmailsMap(String key, String value) {
        this.emailsMap.put(key, value);
    }

    public String getTelefonesMap(String key) {
        return telefonesMap.get(key);
    }
    public void setTelefonesMap(String key, String value) {
        this.telefonesMap.put(key, value);
    }

    public Endereco getEnderecosMap(String key) {
        return enderecosMap.get(key);
    }
    public void setEnderecosMap(String key, Endereco value) {
        this.enderecosMap.put(key, value);
    }



    @Override
    public String toString() {
        String telefone;
        if (getTelefonesMap("Pessoal") != null){
            telefone = getTelefonesMap("Pessoal");
        }
        else if (getTelefonesMap("Residencial") != null){
            telefone = getTelefonesMap("Resodencial");
        }
        else if (getTelefonesMap("Comercial") != null){
            telefone = getTelefonesMap("Comercial");
        }
        else {
            telefone = "Sem numero registrado";
        }
        return "Nome: "+nome+"\nNumero: "+telefone;
    }
}