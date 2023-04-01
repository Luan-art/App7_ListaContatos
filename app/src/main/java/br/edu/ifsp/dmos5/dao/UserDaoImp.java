package br.edu.ifsp.dmos5.dao;

import br.edu.ifsp.dmos5.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserDaoImp implements UserDao{

    private final List<User> database;

    public UserDaoImp() {
        database = new ArrayList<>();
    }

    @Override
    public void addUser(User usuario) {
        if(usuario != null){
            database.add(usuario);
        }
    }

    @Override
    public User login(String senha) {
        return database.stream()
                .filter(user -> user.getUserName().equals(senha))
                .findAny()
                .orElse(null);
    }

    @Override
    public User chkUser(String name) {
        return database.stream()
                .filter(user -> user.getUserName().equals(name))
                .findAny()
                .orElse(null);
    }
}
