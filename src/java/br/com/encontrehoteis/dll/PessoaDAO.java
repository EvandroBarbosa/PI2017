package br.com.encontrehoteis.dll;

import br.com.encontrehoteis.model.Endereco;
import br.com.encontrehoteis.model.Pessoa;
import br.com.encontrehoteis.model.TipoPessoa;
import br.com.encontrehoteis.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PessoaDAO {

    private final Connection cnn;

    public PessoaDAO() {
        cnn = Conexao.getConexao();
    }

    public void incluirPessoa(Pessoa pessoa, Connection co) throws SQLException {

        try {
            new EnderecoDAO().incluir(pessoa.getEndereco(), co);

            String sql = "SELECT currval('endereco_codigo_seq') as codigo";

            Statement st = cnn.createStatement();

            ResultSet res = st.executeQuery(sql);

            if (res.next()) {
                pessoa.getEndereco().setCodigo(res.getInt("codigo"));
            }

            PreparedStatement ps = cnn.prepareStatement("INSERT INTO pessoa"
                    + "(nome,tipo,usuario,senha,cod_endereco)"
                    + " VALUES (?,?,?,?,?)");

            ps.setString(1, pessoa.getNome());
            ps.setInt(2, pessoa.getTipo().valorPessoa);
            ps.setString(3, pessoa.getUsuario());
            ps.setString(4, pessoa.getSenha());
            ps.setInt(5, pessoa.getEndereco().getCodigo());

            ps.execute();
            System.out.println("Pessoa " + pessoa.getNome() + " adicionada");
        } catch (SQLException e) {
            co.rollback();
            throw new RuntimeException(e);
        }
    }

    public Pessoa buscaPessoaCodigo(int codigo) {
        Pessoa pessoa = new Pessoa();

        try {
            String sql = "select * from pessoa where codigo = " + codigo;
            Statement st = cnn.createStatement();

            ResultSet res = st.executeQuery(sql);
            while (res.next()) {

                pessoa.setCodigo(codigo);
                pessoa.setNome(res.getString("nome"));

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

                pessoa.setSenha(res.getString("senha"));
                pessoa.setUsuario(res.getString("usuario"));

                int cod_endereco = res.getInt("cod_endereco");
                Endereco endereco = new EnderecoDAO().buscaEnderecoCodigo(cod_endereco);
                pessoa.setEndereco(endereco);
            }

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return pessoa;
    }

    public void excluirPessoa(int codigo, int cod_endereco, Connection co) throws SQLException {
        try {
            String sql = "delete from pessoa where codigo=?";
            PreparedStatement ps = co.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
            System.out.println("Pessoa Excluido");
        } catch (SQLException erro) {
            co.rollback();
            throw new RuntimeException(erro);
        }

        // deleta o endereco da pessoa ja que essa pessoa foi excluida     
        new EnderecoDAO().deletarEndereco(cod_endereco, cnn);

    }

    public void alterarPessoa(Pessoa pessoa, Connection co) throws SQLException {
        try {

            new EnderecoDAO().alterarEndereco(pessoa.getEndereco(), co);

            String sql = "update pessoa set nome=?, tipo=?, usuario=?, senha=?, cod_endereco=? "
                    + "where codigo=?";

            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setInt(2, pessoa.getTipo().valorPessoa);
            ps.setString(3, pessoa.getUsuario());
            ps.setString(4, pessoa.getSenha());
            ps.setInt(5, pessoa.getEndereco().getCodigo());
            ps.setInt(6, pessoa.getCodigo());

            ps.executeUpdate();

            System.out.println("Pessoa alterado");
        } catch (SQLException erro) {
            co.rollback();
            throw new RuntimeException(erro);
        }
    }
}
