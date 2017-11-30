package br.com.encontrehoteis.model;

/**
 * @author Evandro
 */
public class Usuario extends Pessoa {

    private String cargo;
    private Hotel hotel;
    private int cod_usuario;

    public Usuario(int codigo, String usuario, String senha, String nome, TipoPessoa tipo, Endereco endereco) {
        super(codigo, usuario, senha, nome, tipo, endereco);
    }

    public Usuario() {
        super();
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }
}
