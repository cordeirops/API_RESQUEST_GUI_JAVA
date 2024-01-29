package com.example.gui_test;

public class Cnpj {
    public int id;
    public String cnpj;
    public String name;
    public String lock_date;
    public String is_blocked;
    public String test_date;
    public String test_system;
    public String active;
    public String installation_date;
    public String phone;
    public String version;
    public String system;
    public String observation;
    public String state;
    public String city;

    //var block_date = LocalDateTime.now();

    private String ativo (){
        if (active == "true"){
            return "Sim";
        }else{
            return "Não";
        }
    };

    private String bloqueado (){
        if (is_blocked == "true"){
            return "Sim";
        }else{
            return "Não";
        }
    };

    @Override
    public String toString() {
        return "RESULTADO ENCONTRADO PARA O CNPJ" + '\n' +
                "- ID: " + id + '\n' +
                "- Cnpj: " + cnpj + '\n' +
                "- Nome: " + name + '\n' +
                "- Data de Bloqueio: " + lock_date + '\n' +
                "- Está bloqueado? " + bloqueado() + '\n' +
                "- Ativo? " + ativo() + '\n' +
                "- Data Instalação: " + installation_date + '\n' +
                "- Tefone: " + phone + '\n' +
                "- Versão Sistema: " + version + '\n' +
                "- Sistema: " + system + '\n' +
                "- Observações: " + observation + '\n' +
                "- Estado: " + state + '\n' +
                "- Cidade: " + city + '\n';
    }

}

