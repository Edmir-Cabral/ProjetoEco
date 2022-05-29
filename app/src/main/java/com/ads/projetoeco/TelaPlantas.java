package com.ads.projetoeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaPlantas extends AppCompatActivity {

    private Button bt_pauBrasil;
    private Button bt_ipeAmarelo;
    private Button bt_angicoBranco;
    private Button bt_eucalipto;
    private TextView link_voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_plantas);

        getSupportActionBar().hide();
        iniciarComponentes();

        bt_pauBrasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPlantas.this, TelaPauBrasil.class);
                startActivity(intent);
            }
        });

        bt_ipeAmarelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPlantas.this, TelaIpeAmarelo.class);
                startActivity(intent);
            }
        });

        bt_angicoBranco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPlantas.this, TelaAngicoBranco.class);
                startActivity(intent);
            }
        });
        bt_eucalipto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPlantas.this, TelaEucalipto.class);
                startActivity(intent);
            }
        });

        link_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPlantas.this, TelaPrincipal.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarComponentes(){
        bt_pauBrasil = findViewById(R.id.botaoPauBrasil);
        bt_ipeAmarelo = findViewById(R.id.botaoIpeAmarelo);
        bt_angicoBranco = findViewById(R.id.botaoAngicoBranco);
        bt_eucalipto = findViewById(R.id.botaoEucalipto);
        link_voltar = findViewById(R.id.voltarTelaPlantas);
    }
}