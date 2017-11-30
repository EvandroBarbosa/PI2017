package br.com.encontrehoteis.dll;

import br.com.encontrehoteis.model.Cidade;
import br.com.encontrehoteis.model.Endereco;
import br.com.encontrehoteis.model.Estado;
import br.com.encontrehoteis.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author aluno
 */
public class EnderecoDAO {

    private final Connection cnn;

    public EnderecoDAO() {
        cnn = Conexao.getConexao();
    }

    public void incluir(Endereco endereco, Connection co) throws SQLException {
        try {
            PreparedStatement ps = co.prepareStatement("INSERT INTO endereco"
                    + "(bairro, complemento, numero, cep, logadouro, cod_cidade)"
                    + " VALUES(?,?,?,?,?,?)");

            ps.setString(1, endereco.getBairro());
            ps.setString(2, endereco.getComplemento());
            ps.setInt(3, endereco.getNumero());
            ps.setString(4, endereco.getCep());
            ps.setString(5, endereco.getLogradouro());
            ps.setInt(6, endereco.getCidade().getCodigo());

            ps.execute();
            System.out.println("Endereço adicionado");
        } catch (SQLException erro) {
            co.rollback();
            throw new RuntimeException();
        }

    }

    public Endereco buscaEnderecoCodigo(int codigo) {
        Endereco endereco = new Endereco();

        try {
            String sql = "select * from endereco where codigo = " + codigo;
            Statement st = cnn.createStatement();

            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                endereco.setCodigo(res.getInt("codigo"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setComplemento(res.getString("complemento"));
                endereco.setNumero(res.getInt("numero"));
                endereco.setCep(res.getString("cep"));
                endereco.setLogradouro(res.getString("logadouro"));

                int cod_cidade = res.getInt("cod_cidade");
                Cidade cidade = buscarCidadePorCodigo(cod_cidade);

                endereco.setCidade(cidade);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return endereco;
    }

    public void alterarEndereco(Endereco endereco, Connection co) throws SQLException {
        try {
            String sql = "UPDATE endereco set bairro=?, complemento=?, numero=?,"
                    + "cep=?, logadouro=?, cod_cidade=? " + "where codigo=?";

            PreparedStatement ps = co.prepareStatement(sql);
            ps.setString(1, endereco.getBairro());
            ps.setString(2, endereco.getComplemento());
            ps.setInt(3, endereco.getNumero());
            ps.setString(4, endereco.getCep());
            ps.setString(5, endereco.getLogradouro());
            ps.setInt(6, endereco.getCidade().getCodigo());
            ps.setInt(7, endereco.getCodigo());

            ps.executeUpdate();

            System.out.println("Endereço Alterado");

        } catch (SQLException erro) {
            co.rollback();
            throw new RuntimeException(erro);
        }
    }

    public void deletarEndereco(int codigo, Connection co) throws SQLException {
        try {

            String sql = "Delete from endereco where codigo=?";
            PreparedStatement ps = co.prepareStatement(sql);
            ps.setInt(1, codigo);

            ps.executeUpdate();
            System.out.println("Endereço excluido");
        } catch (SQLException erro) {
            co.rollback();
            throw new RuntimeException(erro);
        }
    }

    public Cidade buscarCidadePorCodigo(int id) {
        Cidade cidade = new Cidade();

        try {
            String sql = "select * from cidade where id = " + id;
            Statement st = cnn.createStatement();

            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                cidade.setCodigo(id);
                cidade.setNome(res.getString("nome"));
                int cod_estado = res.getInt("estado_id");

                Estado estado = buscaEstadoPorCodigo(cod_estado);
                cidade.setEstado(estado);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return cidade;
    }

    private Estado buscaEstadoPorCodigo(int codigo) {
        Estado estado = new Estado();

        try {
            String sql = "select * from estado where id = " + codigo;
            Statement st = cnn.createStatement();

            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                estado.setCodigo(codigo);
                estado.setNome(res.getString("nome"));
                estado.setSigla("sigla");
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return estado;
    }
}
