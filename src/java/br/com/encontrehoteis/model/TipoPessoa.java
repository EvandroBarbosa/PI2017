package br.com.encontrehoteis.model;

public enum TipoPessoa {

    CLIENTE(1), USUARIO(2), ADMINISTRADOR(3);

    public int valorPessoa;

    TipoPessoa(int valor) {
        this.valorPessoa = valor;
    }
}
