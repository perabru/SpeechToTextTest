package com.brunopera.sptote.speechtotexttest;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Email extends AppCompatActivity {



    //Array que recebe primeiramente o que foi falado (nome, sobrenome e idade)
    ArrayList<String> voiceInText;

    private TextView lblTeste;



    //FiREBASE - vai no nó raiz do firebase
    // private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();



    private Button btnTap2;
    private final int REQ_CODE_SPEECH_OUTPUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
       // openMic();
        btnTap2 = (Button) findViewById(R.id.btnTap2);
        lblTeste = (TextView) findViewById(R.id.lblTeste);

        btnTap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openMic();
            }
        });
    }
    private void openMic(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Olá, me fala a sua profissão");
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 30000000);

        try{
            startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);
        }catch(ActivityNotFoundException tim){

            Toast.makeText(getApplicationContext(),"Opps! Your device doesn’t support Speech to Text",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_OUTPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    //classe cadastro
                    //  Cadastro cad  = new Cadastro();


                    try {
                        voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                        lblTeste.setText(voiceInText.get(0));

                        Log.i("AQUI", voiceInText.toString());


                        ConexaoBD cbd = new ConexaoBD();
                        cbd.inserirEmail(voiceInText.get(0));


                        Toast.makeText(getApplicationContext(), "Dados cadastrados com sucesso!", Toast.LENGTH_SHORT).show();

                    }catch(Exception ex){
                        Toast.makeText(getApplicationContext(), "Não foi possível gravar a Profissão, tente de novo", Toast.LENGTH_SHORT).show();
                        openMic();
                        break;
                    }
                }

                //Intent myIntent = new Intent(getApplicationContext(), Email.class);
                //startActivityForResult(myIntent, 0);
                break;

                //final do case
            }
        }
    }
}
