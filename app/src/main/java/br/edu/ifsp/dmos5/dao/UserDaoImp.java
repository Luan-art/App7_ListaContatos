package br.edu.ifsp.dmos5.dao;

import br.edu.ifsp.dmos5.model.User;
import br.edu.ifsp.dmos5.view.md5.Criptografia;

import java.util.ArrayList;
import java.util.List;


public class UserDaoImp implements UserDao{

    private static UserDaoImp instance;

    private final List<User> database;

    private UserDaoImp() {
        database = new ArrayList<>();
    }

    public static UserDaoImp getInstance() {
        if(instance == null){
            instance = new UserDaoImp();
        }
        return instance;
    }

    @Override
    public void addUser(User usuario) {
        if(usuario != null){
            database.add(usuario);
        }
    }

     @Override
    public User chkUser(String name) {

        return database.stream()
                .filter(user -> user.getUserName().equals(name))
                .findAny()
                .orElse(null);
    }

    public User chkPassoword(String senha) {

        String senhaCrip =  Criptografia.criptografar(senha);

        return database.stream()
                .filter(user -> user.getSenha().equals(senhaCrip))
                .findAny()
                .orElse(null);
    }

    public List<User> getDatabase() {
        return database;
    }
}
