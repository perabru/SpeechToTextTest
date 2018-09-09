package com.brunopera.sptote.speechtotexttest;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConexaoBD {

        //FiREBASE - vai no nó raiz do firebase
         DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        //adiciona a child cadastro em um objeto
        DatabaseReference cadastro = referencia.child("cadastro");
        String uniquePK = referencia.push().getKey();

        Cadastro cad = new Cadastro();
        String[] parts;


    public void inserirNomeBD(String nome ){

            cad.setNome(nome);
           cadastro.child(uniquePK).child("Dados Iniciais").setValue(cad);


        }

        public void inserirIdadeBD(ArrayList<String> idade){

            //corta o array para gravar apenas o numero da idade no banco
            String[] str = new String[idade.size()];
            idade.toArray(str);
            parts = str[0].split("\\s+");

           cadastro.child(uniquePK).child("Idade").setValue(Integer.parseInt(parts[0]));

        }

        public void inserirDataNasc(String dataNascimento){

        cad.setDataAniversario(dataNascimento);
        cadastro.child(uniquePK).child("Data Nascimento").setValue(cad);

        }

        public void inserirEstadoCivil(String estadoCivil){

        cad.setDataAniversario(estadoCivil);
        cadastro.child(uniquePK).child("Estado Civil").setValue(cad);

        }







}
