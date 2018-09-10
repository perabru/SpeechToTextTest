package com.brunopera.sptote.speechtotexttest;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConexaoBD {

    //FiREBASE - vai no nó raiz do firebase
    static DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    //adiciona a child cadastro em um objeto
    static DatabaseReference cadastro = referencia.child("cadastro");

    public static String uniquePK = cadastro.push().getKey();

    Cadastro cad = new Cadastro();
    String[] parts;

    //String newPK;

       /* public void quebrarString (String teste){
            String [] partes = teste.split("-");
             newPK = partes[2];
        }*/

    public void inserirNomeBD(String nome) {

        cad.setNome(nome);
        //cad.setDataAtual(cad.getDataAtual());
        cadastro.child(uniquePK).child("Dados Iniciais").setValue(cad);
    }

    public void inserirIdadeBD(ArrayList<String> idade) {

        //corta o array para gravar apenas o numero da idade no banco
        String[] str = new String[idade.size()];
        idade.toArray(str);
        parts = str[0].split("\\s+");


        cadastro.child(uniquePK).child("Idade").setValue(Integer.parseInt(parts[0]));

    }

    public void inserirDataNasc(String dataNascimento) {

        cad.setDataAniversario(dataNascimento);
        cadastro.child(uniquePK).child("Data Nascimento").setValue(cad);

    }

    public void inserirEstadoCivil(String estadoCivil) {

        cad.setEstadoCivil(estadoCivil);
        cadastro.child(uniquePK).child("Estado Civil").setValue(cad);

    }

    public void inserirEndereco(String endereco) {

        cad.setEndereco(endereco);
        cadastro.child(uniquePK).child("Endereço").setValue(cad);

    }

    public void inserirBairro(String bairro) {

        cad.setEndereco(bairro);
        cadastro.child(uniquePK).child("Bairro").setValue(cad);

    }


    public void inserirCidade(String cidade) {

        cad.setEndereco(cidade);
        cadastro.child(uniquePK).child("Cidade").setValue(cad);

    }

    public void inserirCEP(String CEP) {

        cad.setEndereco(CEP);
        cadastro.child(uniquePK).child("CEP").setValue(cad);

    }

    public void inserirTelefone(String telefone) {

        cad.setTelefone(telefone);
        cadastro.child(uniquePK).child("Telefone").setValue(cad);

    }


    public void inserirCelular(String celular) {

        cad.setCelular(celular);
        cadastro.child(uniquePK).child("Celular").setValue(cad);

    }

    public void inserirProfissao(String profissao) {

        cad.setProfissao(profissao);
        cadastro.child(uniquePK).child("Profissao").setValue(cad);

    }

    public void inserirEmail(String email) {

        cad.setEmail(email);
        cadastro.child(uniquePK).child("Email").setValue(cad);

    }

    public void inserirDiagnostico(String diagnostico) {

        cad.setEmail(diagnostico);
        cadastro.child(uniquePK).child("Email").setValue(cad);

    }


}






