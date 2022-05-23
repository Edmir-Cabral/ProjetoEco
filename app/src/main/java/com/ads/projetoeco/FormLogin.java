package com.ads.projetoeco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FormLogin extends AppCompatActivity {

    private TextView text_tela_cadastro;
    private EditText edit_email, edit_senha;
    private Button bt_entrar;
    private ProgressBar progressBar;
    String[] mensagem = {"Erro ao logar usuário", "Entrando..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        getSupportActionBar().hide();
        IniciarComponentes();

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
            }
        });

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, mensagem[0], Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else {
                    AutenticarUsuario(view);
                }
            }
        });
    }

    private void AutenticarUsuario(View view){

        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();
                        }
                    },2000);
                }else {
                    String erro;
                    try {
                        throw task.getException();

                    }catch (Exception e){
                        erro = "Erro ao efetuar login";
                    }
                    Snackbar snackbar = Snackbar.make(view, mensagem[0], Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }

    protected void onStart(){
        super.onStart();
        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if(usuarioAtual != null){
            TelaPrincipal();
        }
    }

    private void TelaPrincipal(){
        Intent intent = new Intent(FormLogin.this, TelaPrincipal.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes(){
        text_tela_cadastro = findViewById(R.id.linkCadastrar);
        edit_email = findViewById(R.id.email);
        edit_senha = findViewById(R.id.senha);
        bt_entrar = findViewById(R.id.botaoEntrar);
        progressBar = findViewById(R.id.iconeCarregando);
    }
}