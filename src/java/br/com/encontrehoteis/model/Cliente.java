package br.com.encontrehoteis.model;

import java.util.Date;

/**
 *
 * @author Evandro
 */
public class Cliente extends Pessoa{
    private String cpf;
    private Date dataNasc;
    private String sexo;

    public Cliente(int codigo, String usuario, String senha, String nome, String sobreNome, Enum tipo) {
        super(codigo, usuario, senha, nome, sobreNome, tipo);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    
}
