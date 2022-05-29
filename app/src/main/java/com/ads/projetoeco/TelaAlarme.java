package com.ads.projetoeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaAlarme extends AppCompatActivity {

    private TextView txt_hora;
    private TextView txt_minitos;
    private TextView link_voltar;
    private Button bt_lembrete;
    private Button bt_criar_alarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alarme);

        getSupportActionBar().hide();
        iniciarComponentes();

        link_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaAlarme.this, TelaEcoEspacos.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarComponentes() {
        link_voltar = findViewById(R.id.voltarTelaAlarme);
        txt_hora = findViewById(R.id.textHora);
        txt_minitos = findViewById(R.id.textMinutos);
        bt_lembrete = findViewById(R.id.botaoDefinirAlarme);
        bt_lembrete = findViewById(R.id.botaoCriarAlarme);
    }
}