package com.ads.projetoeco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormCadastro extends AppCompatActivity {

    private EditText edit_nome, edit_email, edit_senha, edit_CEP, edit_logradouro, edit_bairro,
                     edit_numero, edit_cidade, edit_UF;
    private TextView voltar_login;
    private Button bt_cadastrar;

    String[] mensagens = {"Preencha todos os campos.", "Cadastrado com sucesso!"};
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();


        voltar_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormCadastro.this, FormLogin.class);
                startActivity(intent);
            }
        });

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();
                String CEP = edit_CEP.getText().toString();
                String logradouro = edit_logradouro.getText().toString();
                String bairro = edit_bairro.getText().toString();
                String numero = edit_numero.getText().toString();
                String cidade = edit_cidade.getText().toString();
                String UF = edit_UF.getText().toString();

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || CEP.isEmpty() || logradouro.isEmpty()
                    || bairro.isEmpty() || numero.isEmpty() || cidade.isEmpty() || UF.isEmpty()){

                    Snackbar snackbar = Snackbar.make(view, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }else{
                    CadastrarUsuario(view);
                    CadastrarDadosUsuario();
                }

            }
        });
    }

    private void CadastrarUsuario(View view){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Snackbar snackbar = Snackbar.make(view, mensagens[1], Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else{
                    String erro;
                    try {
                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e){
                        erro = "A senha deve ter no mínimo 6 digitos";
                    }catch (FirebaseAuthUserCollisionException e){
                        erro = "Conta já possui cadastro";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mail inválido!";
                    }catch (Exception e){
                        erro = "Erro ao cadastrar";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }

    private void CadastrarDadosUsuario(){
        String nome = edit_nome.getText().toString();
        String CEP = edit_CEP.getText().toString();
        String logradouro = edit_logradouro.getText().toString();
        String bairro = edit_bairro.getText().toString();
        String numero = edit_numero.getText().toString();
        String cidade = edit_cidade.getText().toString();
        String UF = edit_UF.getText().toString();

        FirebaseFirestore dados = FirebaseFirestore.getInstance();

        Map<String, Object> dadosUsuarios = new HashMap<>();

        dadosUsuarios.put("nome", nome);
        dadosUsuarios.put("CEP", CEP);
        dadosUsuarios.put("logradouro", logradouro);
        dadosUsuarios.put("bairro", bairro);
        dadosUsuarios.put("numero", numero);
        dadosUsuarios.put("cidade", cidade);
        dadosUsuarios.put("UF", UF);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = dados.collection("Usuarios").document(usuarioID);
        documentReference.set(dadosUsuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("dados", "Dados salvo");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("dados erro", "Erro ao salvar dados" + e.toString());
                    }
                });
    }

    private void IniciarComponentes(){
        edit_nome = findViewById(R.id.editNome);
        edit_email = findViewById(R.id.editEmail);
        edit_senha = findViewById(R.id.editSenha);
        edit_CEP = findViewById(R.id.editCEP);
        edit_logradouro = findViewById(R.id.editLogradouro);
        edit_bairro = findViewById(R.id.editBairro);
        edit_numero = findViewById(R.id.editNumero);
        edit_cidade = findViewById(R.id.editCidade);
        edit_UF = findViewById(R.id.editUF);
        bt_cadastrar = findViewById(R.id.botaoCriarCadastro);
        voltar_login = findViewById(R.id.linkVoltar);
    }
}