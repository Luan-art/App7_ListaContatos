package br.edu.ifsp.dmos5.view;

import static br.edu.ifsp.dmos5.R.id.buttonNewContact;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UserDao;
import br.edu.ifsp.dmos5.dao.UserDaoImp;
import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.model.User;
import br.edu.ifsp.dmos5.view.md5.Criptografia;

public class NewContactActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textNewApelido;
    private EditText textNewNome;
    private EditText textNewTelefone;
    private Button btnCadContact;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        textNewApelido = findViewById(R.id.eddittext_newContact);
        textNewNome = findViewById(R.id.edittext_name);
        textNewTelefone = findViewById(R.id.edittext_Tel);
        btnCadContact= findViewById(R.id.buttonNewContact);

        btnCadContact.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public void onClick(View view) {
        executeButton();
    }

    private void executeButton() {
        String recNome = textNewNome.getText().toString();
        String recNewApelido = textNewApelido.getText().toString();
        String recCadContact = textNewTelefone.getText().toString();

        if(chkApelido(recNewApelido, recNome)) {
            Toast.makeText(this, R.string.userExists, Toast.LENGTH_SHORT).show();
            return;

        }

        if(addCad(recNome, recNewApelido,recCadContact)){
            Toast.makeText(this, R.string.newUserCreated, Toast.LENGTH_LONG).show();

        }


    }

    private boolean addCad(String nome, String apelido, String telefone) {

        Bundle parametros = getIntent().getExtras();
        String usuario = parametros.getString(MainActivity.USUARIO);
        String senha = parametros.getString(MainActivity.SENHA);

        UserDao userDao = UserDaoImp.getInstance();
        User user = userDao.chkUser(usuario);
        Contact contact = new Contact(nome, apelido, telefone);

        if(user == null) {
            Toast.makeText(this, R.string.errorAddContact, Toast.LENGTH_SHORT).show();
            return false;
        }

        try{
            user.addContact(contact);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, R.string.errorAddContact, Toast.LENGTH_SHORT).show();
            return false;

        }
    }


    private boolean chkApelido(String recNewApelido, String nome) {
        UserDao userDao =  UserDaoImp.getInstance();
        User user = userDao.chkUser(nome);
        return (user != null && user.findByApelido(recNewApelido) != null);
    }

}
