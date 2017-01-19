package com.ejt.toaquimobile;

import com.ejt.toaquimobile.Contato;
import com.ejt.toaquimobile.Endereco;
import com.ejt.toaquimobile.Usuario;

/**
 * Created by Eric Gustavo on 31/03/2016.
 */
public class Estabelecimento {

    private int id_estabelecimento;
    private String nome;
    private Endereco endereco;
    private Contato contato;
    private String categoria;
    private String pagamento_visa;
    private String pagamento_master;
    private String pagamento_cielo;
    private String pagamento_hiper;
    private String pagamento_outro;
    private String descricao;
    private String imagem;
    private int id_user;

    private Usuario user;

    public Estabelecimento(int id_estabelecimento, String nome, Endereco endereco, Contato contato, String categoria,
                           String pagamento_visa, String pagamento_master, String pagamento_cielo, String pagamento_hiper,
                           String pagamento_outro, String descricao, String imagem, int id_user) {
        super();
        this.id_estabelecimento = id_estabelecimento;
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;
        this.id_user = id_user;
    }

    public Estabelecimento(int id_estabelecimento, String nome, Endereco endereco, Contato contato, String categoria,
                           String pagamento_visa, String pagamento_master, String pagamento_cielo, String pagamento_hiper,
                           String pagamento_outro, String descricao, String imagem) {
        super();
        this.id_estabelecimento = id_estabelecimento;
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public Estabelecimento(String nome, Endereco endereco, Contato contato, String categoria, String pagamento_visa,
                           String pagamento_master, String pagamento_cielo, String pagamento_hiper, String pagamento_outro,
                           String descricao, String imagem, int id_user) {
        super();
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;
        this.id_user = id_user;
    }

    public Estabelecimento(String nome, Endereco endereco, Contato contato, String categoria, String pagamento_visa,
                           String pagamento_master, String pagamento_cielo, String pagamento_hiper, String pagamento_outro,
                           String descricao, String imagem) {
        super();
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;

    }

    public Estabelecimento(int id_estabelecimento, String nome, String categoria, String pagamento_visa,
                           String pagamento_master, String pagamento_cielo, String pagamento_hiper, String pagamento_outro,
                           String descricao, String imagem, int id_user) {
        super();
        this.id_estabelecimento = id_estabelecimento;
        this.nome = nome;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;
        this.id_user = id_user;
    }

    public Estabelecimento(String nome, String categoria, String pagamento_visa, String pagamento_master,
                           String pagamento_cielo, String pagamento_hiper, String pagamento_outro, String descricao, String imagem,
                           int id_user) {
        super();
        this.nome = nome;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;
        this.id_user = id_user;
    }

    public Estabelecimento(String nome, String categoria, String pagamento_visa, String pagamento_master,
                           String pagamento_cielo, String pagamento_hiper, String pagamento_outro, String descricao, String imagem) {
        super();
        this.nome = nome;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;

    }

    public Estabelecimento(int id_estabelecimento, String nome, String endereco1, String longitude, String latitude,
                           String email, String telefone, String celular, String categoria, String pagamento_visa,
                           String pagamento_master, String pagamento_cielo, String pagamento_hiper, String pagamento_outro,
                           String descricao, String imagem, int id_user) {
        this.id_estabelecimento = id_estabelecimento;
        this.nome = nome;
        endereco1 = endereco1;
        longitude = longitude;
        latitude = latitude;
        email = email;
        telefone = telefone;
        celular = celular;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;
        this.id_user = id_user;
    }

    public Estabelecimento(int id_estabelecimento, String nome, String endereco1, String longitude, String latitude,
                           String email, String telefone, String celular, String categoria, String pagamento_visa,
                           String pagamento_master, String pagamento_cielo, String pagamento_hiper, String pagamento_outro,
                           String descricao, String imagem) {
        this.id_estabelecimento = id_estabelecimento;
        this.nome = nome;
        endereco1 = endereco1;
        longitude = longitude;
        latitude = latitude;
        email = email;
        telefone = telefone;
        celular = celular;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;

    }

    public Estabelecimento(int id_estabelecimento, String nome, String endereco1, String longitude, String latitude,
                           String email, String telefone, String celular, String categoria, String pagamento_visa,
                           String pagamento_master, String pagamento_cielo, String pagamento_hiper, String pagamento_outro,
                           String descricao, String imagem, String user) {
        this.id_estabelecimento = id_estabelecimento;
        this.nome = nome;
        endereco1 = endereco1;
        longitude = longitude;
        latitude = latitude;
        email = email;
        telefone = telefone;
        celular = celular;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;
        user = user;
    }

    public Estabelecimento(String nome, String endereco1, String longitude, String latitude, String email, String telefone,
                           String celular, String categoria, String pagamento_visa, String pagamento_master, String pagamento_cielo,
                           String pagamento_hiper, String pagamento_outro, String descricao, String imagem) {
        this.id_estabelecimento = id_estabelecimento;
        this.nome = nome;
        endereco1 = endereco1;
        longitude = longitude;
        latitude = latitude;
        email = email;
        telefone = telefone;
        celular = celular;
        this.categoria = categoria;
        this.pagamento_visa = pagamento_visa;
        this.pagamento_master = pagamento_master;
        this.pagamento_cielo = pagamento_cielo;
        this.pagamento_hiper = pagamento_hiper;
        this.pagamento_outro = pagamento_outro;
        this.descricao = descricao;
        this.imagem = imagem;
        this.id_user = id_user;
    }

    public Estabelecimento(){

    }

    public int getId_estabelecimento() {
        return id_estabelecimento;
    }

    public void setId_estabelecimento(int id_estabelecimento) {
        this.id_estabelecimento = id_estabelecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPagamento_visa() {
        return pagamento_visa;
    }

    public void setPagamento_visa(String pagamento_visa) {
        this.pagamento_visa = pagamento_visa;
    }

    public String getPagamento_master() {
        return pagamento_master;
    }

    public void setPagamento_master(String pagamento_master) {
        this.pagamento_master = pagamento_master;
    }

    public String getPagamento_cielo() {
        return pagamento_cielo;
    }

    public void setPagamento_cielo(String pagamento_cielo) {
        this.pagamento_cielo = pagamento_cielo;
    }

    public String getPagamento_hiper() {
        return pagamento_hiper;
    }

    public void setPagamento_hiper(String pagamento_hiper) {
        this.pagamento_hiper = pagamento_hiper;
    }

    public String getPagamento_outro() {
        return pagamento_outro;
    }

    public void setPagamento_outro(String pagamento_outro) {
        this.pagamento_outro = pagamento_outro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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
        return "Estabelecimento [id_estabelecimento=" + id_estabelecimento + ", nome=" + nome + ", endereco=" + endereco
                + ", contato=" + contato + ", categoria=" + categoria + ", pagamento_visa=" + pagamento_visa
                + ", pagamento_master=" + pagamento_master + ", pagamento_cielo=" + pagamento_cielo
                + ", pagamento_hiper=" + pagamento_hiper + ", pagamento_outro=" + pagamento_outro + ", descricao="
                + descricao + ", imagem=" + imagem + ", id_user=" + id_user + "]";
    }

}
