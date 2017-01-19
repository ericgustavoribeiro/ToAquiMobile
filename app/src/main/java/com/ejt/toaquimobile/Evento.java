package com.ejt.toaquimobile;

import com.ejt.toaquimobile.Contato;
import com.ejt.toaquimobile.Endereco;
import com.ejt.toaquimobile.Usuario;

/**
 * Created by Eric Gustavo on 28/04/2016.
 */
public class Evento {

    private int id_evento;
    private String nome;
    private String local;
    protected Endereco endereco;
    private Contato contato;
    private String data_evento;
    private float valor_ingresso;
    private String imagem;
    private String descricao;
    private int id_user;
    private Usuario user;

    public Evento(){

    }

    public Evento(int id_evento, String nome, String local, Endereco endereco, Contato contato, String data_evento,
                  float valor_ingresso, String imagem, String descricao, int id_user) {
        super();
        this.id_evento = id_evento;
        this.nome = nome;
        this.local = local;
        this.endereco = endereco;
        this.contato = contato;
        this.data_evento = data_evento;
        this.valor_ingresso = valor_ingresso;
        this.imagem = imagem;
        this.descricao = descricao;
        this.id_user = id_user;
    }

    public Evento(String nome, String local, Endereco endereco, Contato contato, String data_evento,
                  float valor_ingresso, String imagem, String descricao, int id_user) {
        super();

        this.nome = nome;
        this.local = local;
        this.endereco = endereco;
        this.contato = contato;
        this.data_evento = data_evento;
        this.valor_ingresso = valor_ingresso;
        this.imagem = imagem;
        this.descricao = descricao;
        this.id_user = id_user;
    }

    public Evento(String nome, String local, Endereco endereco, Contato contato, String data_evento,
                  float valor_ingresso, String imagem, String descricao) {
        super();

        this.nome = nome;
        this.local = local;
        this.endereco = endereco;
        this.contato = contato;
        this.data_evento = data_evento;
        this.valor_ingresso = valor_ingresso;
        this.imagem = imagem;
        this.descricao = descricao;

    }

    public Evento(String nome, String local, String data_evento,
                  float valor_ingresso, String imagem, String descricao) {
        super();
        this.id_evento = id_evento;
        this.nome = nome;
        this.local = local;
        this.endereco = endereco;
        this.contato = contato;
        this.data_evento = data_evento;
        this.valor_ingresso = valor_ingresso;
        this.imagem = imagem;
        this.descricao = descricao;
        this.id_user = id_user;
    }

    public Evento(int id_evento, String nome, String local, String endereco1, String longitude, String latitude,
                  String email, String telefone, String celular, String data_evento,
                  float valor_ingresso, String imagem, String descricao, int id_user) {

        this.id_evento = id_evento;
        this.nome = nome;
        this.local = local;
        endereco1 = endereco1;
        longitude = longitude;
        latitude = latitude;
        email = email;
        telefone = telefone;
        celular = celular;
        this.endereco = endereco;
        this.contato = contato;
        this.data_evento = data_evento;
        this.valor_ingresso = valor_ingresso;
        this.imagem = imagem;
        this.descricao = descricao;
        this.id_user = id_user;
    }

    public Evento(int id_evento, String nome, String local, String endereco1, String longitude, String latitude,
                  String email, String telefone, String celular, String data_evento,
                  float valor_ingresso, String imagem, String descricao, String user) {

        this.id_evento = id_evento;
        this.nome = nome;
        this.local = local;
        endereco1 = endereco1;
        longitude = longitude;
        latitude = latitude;
        email = email;
        telefone = telefone;
        celular = celular;
        this.endereco = endereco;
        this.contato = contato;
        this.data_evento = data_evento;
        this.valor_ingresso = valor_ingresso;
        this.imagem = imagem;
        this.descricao = descricao;
        this.id_user = id_user;
        user = user;
    }



    public Evento(String nome, String local, String endereco1, String longitude, String latitude,
                  String email, String telefone, String celular, String data_evento,
                  float valor_ingresso, String imagem, String descricao) {


        this.nome = nome;
        this.local = local;
        endereco1 = endereco1;
        longitude = longitude;
        latitude = latitude;email = email;
        telefone = telefone;
        celular = celular;
        this.endereco = endereco;
        this.contato = contato;
        this.data_evento = data_evento;
        this.valor_ingresso = valor_ingresso;
        this.imagem = imagem;
        this.descricao = descricao;

    }

    public int getId_evento() {
        return id_evento;
    }



    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }



    public String getNome() {
        return nome;
    }



    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getLocal() {
        return local;
    }



    public void setLocal(String local) {
        this.local = local;
    }



    public Endereco getEndereco() {
        return endereco;
    }



    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }



    public Contato getContato() {
        return contato;
    }



    public void setContato(Contato contato) {
        this.contato = contato;
    }



    public String getData_evento() {
        return data_evento;
    }



    public void setData_evento(String data_evento) {
        this.data_evento = data_evento;
    }



    public float getValor_ingresso() {
        return valor_ingresso;
    }



    public void setValor_ingresso(float valor_ingresso) {
        this.valor_ingresso = valor_ingresso;
    }



    public String getImagem() {
        return imagem;
    }



    public void setImagem(String imagem) {
        this.imagem = imagem;
    }



    public String getDescricao() {
        return descricao;
    }



    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    public int getId_user() {
        return id_user;
    }



    public void setId_user(int id_user) {
        this.id_user = id_user;
    }


    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Evento [id_evento=" + id_evento + ", nome=" + nome + ", local=" + local + ", endereco=" + endereco
                + ", contato=" + contato + ", data_evento=" + data_evento + ", valor_ingresso=" + valor_ingresso
                + ", imagem=" + imagem + ", descricao=" + descricao + ", id_user=" + id_user + "]";
    }




}
