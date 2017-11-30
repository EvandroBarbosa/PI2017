package br.com.encontrehoteis.dll;

import br.com.encontrehoteis.model.Cliente;
import br.com.encontrehoteis.model.Pessoa;
import br.com.encontrehoteis.util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author rodrigo
 */
public class ClienteDAO {

    private final Connection cnn;

    public ClienteDAO() {
        cnn = Conexao.getConexao();
    }

    //Método incluir
    public void incluirCliente(Cliente cliente) throws SQLException {

        try {
            cnn.setAutoCommit(false);

            new PessoaDAO().incluirPessoa(cliente.getPessoa(), cnn);

            String sql2 = "SELECT currval('pessoa_codigo_seq') as codigo";

            Statement st = cnn.createStatement();

            ResultSet rs = st.executeQuery(sql2);

            if (rs.next()) {
                cliente.getPessoa().setCodigo(rs.getInt("codigo"));
            }

            PreparedStatement ps = cnn.prepareStatement("insert into cliente "
                    + " (cpf, data_nascimento, sexo, cod_pessoa) "
                    + " values (?, ?, ?, ? ) ");

            ps.setString(1, cliente.getCpf());
            ps.setDate(2, new Date(cliente.getDataNasc().getTime()));
            ps.setString(3, cliente.getSexo());
            ps.setInt(4, cliente.getPessoa().getCodigo());

            ps.execute();

            cnn.commit();
            System.out.println("Cliente adicionado");
        } catch (SQLException erro) {
            cnn.rollback();
            throw new RuntimeException(erro);
        }
    }

    //metodo para buscar por codigo
    public Cliente buscaClienteCodigo(int codigo) {
        Cliente cliente = new Cliente();

        try {
            String sql = "select * from cliente where codigo=" + codigo;
            Statement st = cnn.createStatement();

            ResultSet res = st.executeQuery(sql);
            while (res.next()) {

                cliente.setCod_cliente(codigo);
                cliente.setCpf(res.getString("cpf"));
                cliente.setDataNasc(res.getDate("data_nascimento"));
                cliente.setSexo(res.getString("sexo"));

                int cod_pessoa = res.getInt("cod_pessoa");
                Pessoa pessoa = new PessoaDAO().buscaPessoaCodigo(cod_pessoa);

                // informacoes da pessoa 
                cliente.setCodigo(pessoa.getCodigo());
                cliente.setNome(pessoa.getNome());
                cliente.setTipo(pessoa.getTipo());
                cliente.setSenha(pessoa.getSenha());
                cliente.setUsuario(pessoa.getUsuario());
                cliente.setEndereco(pessoa.getEndereco());
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return cliente;
    }

    //metodo para buscar um cliente pelo cod_pessoa
    public Cliente consultarCod_pessoa(int cod_pessoa) {
        Cliente cliente = new Cliente();

        try {
            String sql = "select * from cliente where cod_pessoa=" + cod_pessoa;
            Statement st = cnn.createStatement();

            ResultSet res = st.executeQuery(sql);
            while (res.next()) {

                cliente.setCod_cliente(res.getInt("codigo"));
                cliente.setCpf(res.getString("cpf"));
                cliente.setDataNasc(res.getDate("data_nascimento"));
                cliente.setSexo(res.getString("sexo"));

                Pessoa pessoa = new PessoaDAO().buscaPessoaCodigo(cod_pessoa);

                // informacoes da pessoa 
                cliente.setCodigo(pessoa.getCodigo());
                cliente.setNome(pessoa.getNome());
                cliente.setTipo(pessoa.getTipo());
                cliente.setSenha(pessoa.getSenha());
                cliente.setUsuario(pessoa.getUsuario());
                cliente.setEndereco(pessoa.getEndereco());
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return cliente;
    }

    //metodo excluir
    public void excluirCliente(int codigo, int cod_pessoa, int cod_endereco) throws SQLException {

        try {
            cnn.setAutoCommit(false);
            String sql = "delete from cliente where codigo=?";
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
            System.out.println("Cliente excluído");

            new PessoaDAO().excluirPessoa(cod_pessoa, cod_endereco, cnn);

            cnn.commit();
        } catch (SQLException erro) {
            cnn.rollback();
            throw new RuntimeException(erro);
        }
    }

    //metodo alterar
    public void alterarCliente(Cliente cliente) throws SQLException {

        try {
            cnn.setAutoCommit(false);

            new PessoaDAO().alterarPessoa(cliente.getPessoa(), cnn);
            String sql = " update cliente set cpf=?, data_nascimento=?, sexo=?, cod_pessoa=? "
                    + "where codigo=? ";

            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, cliente.getCpf());
            ps.setDate(2, new Date(cliente.getDataNasc().getTime()));
            ps.setString(3, cliente.getSexo());
            ps.setInt(4, cliente.getPessoa().getCodigo());
            ps.setInt(5, cliente.getCod_cliente());

            ps.executeUpdate();

            System.out.println("Cliente alterado");
            cnn.commit();

        } catch (SQLException erro) {
            cnn.rollback();
            throw new RuntimeException(erro);
        }
    }
}
