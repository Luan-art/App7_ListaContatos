package br.edu.ifsp.dmos5.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UserDao;
import br.edu.ifsp.dmos5.dao.UserDaoImp;
import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.model.User;
import br.edu.ifsp.dmos5.view.adapter.ContactSpinnerAdapter;

public class ContactsActivity  extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TextView txtName;
    private TextView txtTelefone;
    private Button btnNovoContact;
    private Spinner mSpinner;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        txtName = findViewById(R.id.textview_nameContac);
        txtTelefone = findViewById(R.id.textview_Telefone);
        btnNovoContact = findViewById(R.id.buttonContact);
        mSpinner = findViewById(R.id.spinner_Contacts);

        mUser = (User) getIntent().getSerializableExtra("user");

        mSpinner.setOnItemSelectedListener(this);

        btnNovoContact.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle parametros = getIntent().getExtras();
        String usuario = parametros.getString(MainActivity.USUARIO);
        String senha = parametros.getString(MainActivity.SENHA);

        UserDao userDao = UserDaoImp.getInstance();
        User user = userDao.chkUser(usuario);

        if(!login(senha, usuario)){
            Toast.makeText(this, R.string.userInvalid, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        pupulateSpinner(user);



    }

    private void pupulateSpinner(User user) {
        List<Contact> dataset;
        if (user.getListaContatos() == null) {
            dataset = new ArrayList<>();
        } else {
            dataset = user.getListaContatos();
        }
        if (dataset.size() == 0 || dataset.get(0) != null) {
            dataset.add(0, null);
        }
        ContactSpinnerAdapter adapter = new ContactSpinnerAdapter(this, android.R.layout.simple_spinner_item, dataset);
        mSpinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        executNewContact();

    }

    private void executNewContact() {
        Intent intent = new Intent(this, NewContactActivity.class);
        Bundle parametros = getIntent().getExtras();
        intent.putExtras(parametros);
        startActivity(intent);
        finish();
    }

    public boolean login(String senha, String usuario){

        UserDao userDao = UserDaoImp.getInstance();
        User user = userDao.chkUser(usuario);
        User userP = userDao.chkPassoword(senha);
        if (user == null || userP == null) {
            return false;
        }

        return user.equals(userP);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Contact contact = ((ContactSpinnerAdapter) adapterView.getAdapter()).getItem(i);
        if(contact != null){
            openDetailsActivity(contact);
        }
    }

    private void openDetailsActivity(Contact contact) {
        if(contact != null){
            txtName.setText(contact.getName());
            txtTelefone.setText(contact.getTelefone());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSpinner.setSelection(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}