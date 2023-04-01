package br.edu.ifsp.dmos5.model;

public class Contact {

    private String apelido;
    private String name;
    private String telefone;

    public Contact(String apelido, String name, String telefone) {
        setApelido(apelido);
        setName(name);
        setTelefone(telefone);
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
