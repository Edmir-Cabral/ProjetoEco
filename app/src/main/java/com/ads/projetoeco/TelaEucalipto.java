package com.ads.projetoeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TelaEucalipto extends AppCompatActivity {

    private TextView link_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_eucalipto);

        getSupportActionBar().hide();
        iniciarComponentes();

        link_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaEucalipto.this, TelaPlantas.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarComponentes(){
        link_voltar = findViewById(R.id.voltarTelaEucalipto);
    }
}