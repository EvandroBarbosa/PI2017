package br.com.encontrehoteis.model;

import java.util.Date;

/**
 * @author Evandro
 */
public class Cliente extends Pessoa {

    private String cpf;
    private Date dataNasc;
    private String sexo;
    private int cod_cliente;

    public Cliente(String cpf, Date dataNasc, String sexo) {
        super();
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
    }

    public Cliente() {
        super();
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

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }
}
