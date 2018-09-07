package com.brunopera.sptote.speechtotexttest;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Cadastro {

    private String nome;
    private String sobrenome;
    private int idade;
    private Calendar data = Calendar.getInstance();
    private String dataAtual = DateFormat.getDateInstance().format(data.getTime());


    public Cadastro() {


    }

    public String getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(String dataAtual) {
        this.dataAtual = dataAtual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
