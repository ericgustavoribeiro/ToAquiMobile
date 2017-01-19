package com.ejt.toaquimobile;

/**
 * Created by Eric Gustavo on 31/03/2016.
 */
public class Contato {
    private String email;
    private String telefone;
    private String celular;

    public Contato(String email, String telefone, String celular) {
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
    }

    public Contato() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Contato [email= " + email + ", telefone= " + telefone + ", celular= " + celular + "]";
    }


}
