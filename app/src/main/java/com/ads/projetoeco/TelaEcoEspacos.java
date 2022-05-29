package com.ads.projetoeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TelaEcoEspacos extends AppCompatActivity {

    private TextView link_voltar;
    private TextView link_alarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_espacos);

        getSupportActionBar().hide();
        iniciarComponentes();

        link_alarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaEcoEspacos.this, TelaAlarme.class);
                startActivity(intent);
            }
        });

        link_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaEcoEspacos.this, TelaPrincipal.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarComponentes() {
        link_voltar = findViewById(R.id.voltarTelaEcoEspaco);
        link_alarme = findViewById(R.id.linkDefinirAlarme);
    }
}