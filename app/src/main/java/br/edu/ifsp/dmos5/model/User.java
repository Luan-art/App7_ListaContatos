package br.edu.ifsp.dmos5.model;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.dao.UserDao;
import br.edu.ifsp.dmos5.dao.UserDaoImp;
import br.edu.ifsp.dmos5.view.md5.Criptografia;

public class User {

    private String userName;
    private String senha;
    private List<Contact> listaContatos;


    public User (String userName, String senha, List<Contact> listaContatos){
        setUserName(userName);
        setSenha(senha);
        setListaContatos(listaContatos);
    }

    public void addContact(Contact contato){

        listaContatos.add(contato);
    }

    public Contact findByApelido(String apelido){
        return listaContatos.stream()
                .filter(user -> user.getApelido().equals(apelido))
                .findAny()
                .orElse(null);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public List<Contact> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<Contact> listaContatos) {
        this.listaContatos = listaContatos;
    }
}
