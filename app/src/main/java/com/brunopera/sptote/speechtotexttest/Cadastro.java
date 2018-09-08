package com.brunopera.sptote.speechtotexttest;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Cadastro {

    private String nome;
    private String snome;
    private String unome;
    private String qnome;


    private int idade;
    private Calendar data = Calendar.getInstance();
    //private String dataAtual = DateFormat.getDateInstance().format(data.getTime());


    private String dataAniversario;

    public Cadastro() {


    }



   /* public String getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(String dataAtual) {
        this.dataAtual = dataAtual;
    }*/

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSnome() {
        return snome;
    }

    public void setSnome(String snome) {
        this.snome = snome;
    }

    public String getUnome() {
        return unome;
    }

    public void setUnome(String unome) {
        this.unome = unome;
    }

    public String getQnome() {
        return qnome;
    }

    public void setQnome(String qnome) {
        this.qnome = qnome;
    }

    public String getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(String dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }


}
