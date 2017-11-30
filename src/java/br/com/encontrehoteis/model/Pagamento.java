package br.com.encontrehoteis.model;

import java.util.Date;

/**
 * @author Evandro
 */
public class Pagamento {

    private int codigo;
    private int numeroCartao;
    private String nomeTitular;
    private Date dataValidade;
    private int codigoSeguranca;
    private int cod_reserva;
    private Date dataPagamento;
    private float valorTotalPagamento;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public int getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(int codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public int getCod_reserva() {
        return cod_reserva;
    }

    public void setCod_reserva(int cod_reserva) {
        this.cod_reserva = cod_reserva;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public float getValorTotalPagamento() {
        return valorTotalPagamento;
    }

    public void setValorTotalPagamento(float valorTotalPagamento) {
        this.valorTotalPagamento = valorTotalPagamento;
    }
}
