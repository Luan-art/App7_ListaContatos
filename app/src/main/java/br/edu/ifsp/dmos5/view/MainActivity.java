package br.edu.ifsp.dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifsp.dmos5.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputUser;
    private  EditText inputPassaword;
    private Button btnLogin;
    private Button btnNewUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUser = findViewById(R.id.eddittext_user);
        inputPassaword = findViewById(R.id.edittext_passoword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnNewUser = findViewById(R.id.buttonNewUser);

        btnLogin.setOnClickListener(this);
        btnNewUser.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin){
            executeLogin();
        }

        if(view == btnNewUser){
            executeNewUser();
        }
    }

    private void executeNewUser() {
    }

    private void executeLogin() {
    }
}