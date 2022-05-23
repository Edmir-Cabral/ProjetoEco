package com.ads.projetoeco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TelaArvores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_arvores);

        getSupportActionBar().hide();
    }
}