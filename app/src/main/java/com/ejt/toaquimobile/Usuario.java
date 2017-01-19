package com.ejt.toaquimobile;

import java.io.Serializable;

/**
 * Created by Eric Gustavo on 31/03/2016.
 */
public class Usuario implements Serializable{

    private int id_user;
    private String nome;
    private String email;
    private String senha;
    public static final long serialVersionUID = 100L;


    public Usuario() {
        super();
    }

    public Usuario(int id_user, String nome, String email, String senha) {
        super();
        this.id_user = id_user;
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }

    public Usuario(String nome, String email, String senha) {
        super();
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }

    public Usuario(String nome, String email) {
        super();
        this.nome = nome;
        this.email = email;

    }

    public Usuario(int id_user, String nome, String email) {
        super();
        this.id_user = id_user;
        this.nome = nome;
        this.email = email;

    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario [id_user=" + id_user + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
    }

}
