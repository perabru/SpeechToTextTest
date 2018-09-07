package com.brunopera.sptote.speechtotexttest;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
//https://www.youtube.com/watch?v=ND6a4V-xdjI
    //https://pt.stackoverflow.com/questions/50773/como-usar-onactivityresult-quando-h%C3%A1-mais-de-um-startactivityforresult

    private final int REQ_CODE_SPEECH_OUTPUT = 143;


    //Label que recebe o que foi falado
    TextView lblShowVoice;
    //Botão para começar a falar
    Button btnTap;
    //Label que monta uma frase
    TextView lblResultado;
    //Array que recebe primeiramente o que foi falado
    ArrayList<String>  voiceInText;

        //FiREBASE - vai no nó raiz do firebase
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTap = (Button) findViewById(R.id.btnTap);
        lblShowVoice = (TextView) findViewById(R.id.lblShowVoice);
        lblResultado = (TextView) findViewById(R.id.lblResultado);



        btnTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openMic();
            }
        });
    }


    private void openMic(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Olá, fale agora");

        try{
            startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);
        }catch(ActivityNotFoundException tim){

            Toast.makeText(getApplicationContext(),"Opps! Your device doesn’t support Speech to Text",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQ_CODE_SPEECH_OUTPUT: {
                if(resultCode == RESULT_OK && null != data){


                     voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    lblShowVoice.setText(voiceInText.get(0));

                    lblResultado.setText("Olá, "+voiceInText.get(0));


                    //adiciona a child cadastro em um objeto
                    DatabaseReference cadastro = referencia.child("cadastro");

                    //classe cadastro
                    Cadastro cad  = new Cadastro();

                    //quebrando array por espaços em branco
                    String[] str = new String[voiceInText.size()];
                    voiceInText.toArray(str);
                    String[] parts = str[0].split("\\s+");

                    //DADOS A SEREM INSERIDOS
                    cad.setNome(parts[0]);
                    cad.setSobrenome(parts[1]);
                    cad.setIdade(Integer.parseInt(parts[2]));
                    cad.setDataAtual(cad.getDataAtual());

                   // cad.setIdade(voiceInText.get(1).toString());

                    for(int i = 0; i < voiceInText.size(); i++){

                        Log.d("AQUI>>>>>>>>>>>>" +i,voiceInText.get(i));
                    }
                    cadastro.child("005").setValue(cad);

                    Toast.makeText(getApplicationContext(),"Dados cadastrados com sucesso!",Toast.LENGTH_SHORT).show();




                    //se o qe foi falado for difrente de nulo, adiciona no banco de dados
                    /*
                    if(voiceInText.get(0) != null){
                        referencia.child("cadastroTeste").child("001").child("nome").setValue(voiceInText.get(0).toString());
                    }*/


                }
                break;
            }
        }
    }


}
