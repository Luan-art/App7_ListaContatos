package br.edu.ifsp.dmos5.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UserDao;
import br.edu.ifsp.dmos5.dao.UserDaoImp;
import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.model.User;
import br.edu.ifsp.dmos5.view.md5.Criptografia;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textNewUser;
    private EditText textNewSenha;
    private EditText textConfNewSenha;
    private Button btnCadUser;


     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        textNewUser = findViewById(R.id.eddittext_newUser);
        textNewSenha = findViewById(R.id.edittext_newSenha);
        textConfNewSenha = findViewById(R.id.edittext_ConfnewSenha);
        btnCadUser = findViewById(R.id.buttonCadUser);

        btnCadUser.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View view) {

        executeCriacNewUser();

    }

    private void executeCriacNewUser() {
        String usuario = textNewUser.getText().toString();
        String senha = textNewSenha.getText().toString();
        String confSenha = textConfNewSenha.getText().toString();

        if (usuario.isEmpty() || senha.isEmpty() || confSenha.isEmpty()) {
            Toast.makeText(this, R.string.errorAdd, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!senha.equals(confSenha)) {
            Toast.makeText(this, R.string.difPasswords, Toast.LENGTH_SHORT).show();
            return;
        }

        if (chkName(usuario)) {
            Toast.makeText(this, R.string.userExists, Toast.LENGTH_SHORT).show();
            return;
        }

        if (addUser()) {
            Toast.makeText(this, R.string.newUserCreated, Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public boolean chkName(String name) {
        UserDao userDao = UserDaoImp.getInstance();
        User user = userDao.chkUser(name);
        return (user != null);
    }

    public boolean addUser(){

        List<Contact> listaContatos = new ArrayList<>();;
        String senhaCriptografada = Criptografia.criptografar(textNewSenha.getText().toString());
        UserDao userDao = UserDaoImp.getInstance();
        User usuario = new User(textNewUser.getText().toString(), senhaCriptografada, listaContatos );

        try{
            userDao.addUser(usuario);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, R.string.errorAdd, Toast.LENGTH_SHORT).show();
            return false;

        }

    }
}