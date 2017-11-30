package br.com.encontrehoteis.dll;

import br.com.encontrehoteis.model.Pessoa;
import br.com.encontrehoteis.model.TipoPessoa;
import br.com.encontrehoteis.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    private final Connection conexao;

    public LoginDAO() {
        conexao = Conexao.getConexao();
    }

    public Pessoa buscarPessoa(Pessoa pes) {
        String sql = "select * from pessoa where usuario = ? "
                + " and senha = ? ";

        Pessoa pessoa = new Pessoa();

        try {

            PreparedStatement ppt = conexao.prepareStatement(sql);
            ppt.setString(1, pes.getUsuario());
            ppt.setString(2, pes.getSenha());

            ResultSet res = ppt.executeQuery();

            if (res.next()) {
                pessoa.setCodigo(res.getInt("codigo"));
                pessoa.setNome(res.getString("nome"));
                pessoa.setUsuario(res.getString("usuario"));

                // parte responsavel por pegar o tipo da pessoa             
                TipoPessoa tipo;

                int valorTipo = res.getInt("tipo");

                switch (valorTipo) {
                    case 1: {
                        tipo = TipoPessoa.CLIENTE;
                        break;
                    }
                    case 2: {
                        tipo = TipoPessoa.USUARIO;
                        break;
                    }
                    default: {
                        tipo = TipoPessoa.ADMINISTRADOR;
                        break;
                    }
                }
                pessoa.setTipo(tipo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoa;
    }
}
