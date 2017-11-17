package br.com.encontrehoteis.model;

/**
 *
 * @author Evandro
 */
public class Pessoa {
    private int codigo;
    private String usuario;
    private String senha;
    private String nome;
    private String sobreNome;
    private TipoPessoa tipo;

    public Pessoa() {
    }
    
    

    public Pessoa(int codigo, String usuario, String senha, String nome, String sobreNome, TipoPessoa tipo) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.tipo = tipo;
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

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public Enum getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }
    
    
}
