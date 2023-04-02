package br.edu.ifsp.dmos5.dao;

import br.edu.ifsp.dmos5.model.User;

public interface UserDao {

    void addUser (User usuario);

    User chkUser(String name);

    User chkPassoword(String senha);

}
