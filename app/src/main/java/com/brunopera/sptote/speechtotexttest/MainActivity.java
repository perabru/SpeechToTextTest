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

    private final int REQ_CODE_SPEECH_OUTPUT = 1;


    //classe cadastro
    private Cadastro cad = new Cadastro();
    //Label que recebe o que foi falado
    private TextView lblShowVoice;
    //Botão para começar a falar
    private Button btnTap;
    //Label que monta uma frase
    private TextView lblResultado;
    //Array que recebe primeiramente o que foi falado (nome, sobrenome e idade)
    private ArrayList<String> voiceInText;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnTap = (Button) findViewById(R.id.btnTap);
        lblShowVoice = (TextView) findViewById(R.id.lblShowVoice);
        lblResultado = (TextView) findViewById(R.id.lblResultado);

       // openMic();


        btnTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMic();
            }
        });
    }


    private void openMic() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Olá, qual seu nome ?");
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 20000000);

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);
        } catch (ActivityNotFoundException tim) {

            Toast.makeText(getApplicationContext(), "Opps! Your device doesn’t support Speech to Text", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_OUTPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    try {

                        voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                        lblShowVoice.setText(voiceInText.get(0));

                        lblResultado.setText("Olá, " + voiceInText.get(0));



                      ConexaoBD cbd = new ConexaoBD();
                      cbd.inserirNomeBD(voiceInText.get(0).toString());


                        Toast.makeText(getApplicationContext(), "Dados cadastrados com sucesso!", Toast.LENGTH_SHORT).show();

                    } catch (Exception ex) {
                        // Log.i("ERRO PK", primaryKey);
                        Toast.makeText(getApplicationContext(), "Não foi possível gravar seu nome e idade, tente de novo" +ex, Toast.LENGTH_LONG).show();
                        openMic();

                        break;
                    }

                }

                Intent myIntent = new Intent(getApplicationContext(), Idade.class);
                startActivityForResult(myIntent, 0);
                break;
                //final do case
            }


        }


    }


}