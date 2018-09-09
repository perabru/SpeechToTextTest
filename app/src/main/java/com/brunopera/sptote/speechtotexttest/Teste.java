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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;


//CLASSE ANIVERSARIO
public class Teste extends AppCompatActivity {

    //Array que recebe primeiramente o que foi falado (nome, sobrenome e idade)
    ArrayList<String> voiceInText;

    private TextView lblAniversario;


    private Button btnAniversario;
    private final int REQ_CODE_SPEECH_OUTPUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);
       //openMic();
        btnAniversario = (Button) findViewById(R.id.btnAniversario);
        lblAniversario = (TextView) findViewById(R.id.lblAniversario);

        btnAniversario.setOnClickListener(new View.OnClickListener() {
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

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Olá, me fale seu aniversário");
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 20000000);

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
                    Cadastro cad  = new Cadastro();


                    try {
                        voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                        lblAniversario.setText(voiceInText.get(0));

                        Log.i("AQUI", voiceInText.toString());

                        ConexaoBD cbd = new ConexaoBD();
                        cbd.inserirDataNasc(voiceInText.get(0));

                        Toast.makeText(getApplicationContext(), "Dados cadastrados com sucesso!", Toast.LENGTH_SHORT).show();

                    }catch(Exception ex){
                        Toast.makeText(getApplicationContext(), "Não foi possível gravar a data, tente de novo", Toast.LENGTH_SHORT).show();
                        openMic();
                        break;
                    }
                    }

                Intent myIntent = new Intent(getApplicationContext(), EstadoCivil.class);
                startActivityForResult(myIntent, 0);
                    break;

                //final do case
            }
        }
    }
}
