package br.com.encontrehoteis.model;

/**
 *
 * @author Evandro
 */
public class Usuario extends Pessoa{
    private String cargo;  

    public Usuario(int codigo, String usuario, String senha, String nome, String sobreNome, TipoPessoa tipo) {
        super(codigo, usuario, senha, nome, sobreNome, tipo);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
}
