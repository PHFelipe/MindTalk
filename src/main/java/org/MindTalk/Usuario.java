package org.MindTalk;

public abstract class Usuario {
    protected String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
