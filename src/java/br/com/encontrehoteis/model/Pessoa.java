package br.com.encontrehoteis.model;

/**
 * @author Evandro
 */
public class Pessoa {

    private int codigo;
    private String usuario;
    private String senha;
    private String nome;
    private TipoPessoa tipo;
    private Endereco endereco;

    public Pessoa() {
    }

    public Pessoa(int codigo, String usuario, String senha, String nome, TipoPessoa tipo, Endereco endereco) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
        this.endereco = endereco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa getPessoa() {
        return this;
    }
}
