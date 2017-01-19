package com.ejt.toaquimobile;

import com.ejt.toaquimobile.Usuario;

/**
 * Created by Eric Gustavo on 20/05/2016.
 */
public class Avaliacao {

        private int id_user;
        private int id_estabelecimento;
        private int nota;
        private String descricao;
        private Usuario usuario;


        public Avaliacao(){

        }

        public Avaliacao(int id_user, int id_estabelecimento, int nota, String descricao) {
            super();
            this.id_user = id_user;
            this.id_estabelecimento = id_estabelecimento;
            this.nota = nota;
            this.descricao = descricao;
        }

        public Avaliacao(int nota, String descricao) {
            super();
            this.nota = nota;
            this.descricao = descricao;
        }

        public Avaliacao(String nome_user, String nome_estabelecimento, int nota, String descricao) {
            super();
            nome_user = nome_user;
            nome_estabelecimento = nome_estabelecimento;
            this.nota = nota;
            this.descricao = descricao;
        }

        public Avaliacao(String nome_user, int nota, String descricao) {
            super();
            nome_user = nome_user;
            this.nota = nota;
            this.descricao = descricao;
        }




        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }

        public int getId_user() {
            return id_user;
        }

        public void setId_user(int id_user) {
            this.id_user = id_user;
        }

        public int getId_estabelecimento() {
            return id_estabelecimento;
        }

        public void setId_estabelecimento(int id_estabelecimento) {
            this.id_estabelecimento = id_estabelecimento;
        }

        public int getNota() {
            return nota;
        }

        public void setNota(int nota) {
            this.nota = nota;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        @Override
        public String toString() {
            return "Avaliacao [id_user=" + id_user + ", id_estabelecimento=" + id_estabelecimento + ", nota=" + nota
                    + ", descricao=" + descricao + "]";
        }

    }
