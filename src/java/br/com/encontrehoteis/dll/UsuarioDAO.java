package br.com.encontrehoteis.dll;

import br.com.encontrehoteis.model.Hotel;
import br.com.encontrehoteis.model.Pessoa;
import br.com.encontrehoteis.model.Usuario;
import br.com.encontrehoteis.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsuarioDAO {

    private final Connection conexao;

    public UsuarioDAO() {
        conexao = Conexao.getConexao();
    }

    public void AdicionarUsuario(Usuario usuario) throws SQLException {

        try {
            conexao.setAutoCommit(false);

            new PessoaDAO().incluirPessoa(usuario.getPessoa(), conexao);
            String sql2 = "SELECT currval('pessoa_codigo_seq') as codigo";

            Statement st = conexao.createStatement();

            ResultSet rs = st.executeQuery(sql2);

            if (rs.next()) {
                usuario.getPessoa().setCodigo(rs.getInt("codigo"));
            }

            String sql = " INSERT INTO usuario(cargo, cod_pessoa, cod_hotel) "
                    + " VALUES(?,?,?) ";

            PreparedStatement ppt = conexao.prepareStatement(sql);
            ppt.setString(1, usuario.getCargo());
            ppt.setInt(2, usuario.getPessoa().getCodigo());
            ppt.setInt(3, usuario.getHotel().getCodigo());

            ppt.executeUpdate();
            conexao.commit();
            System.out.println("Usuario adicionado");
        } catch (SQLException erro) {
            conexao.rollback();
            throw new RuntimeException(erro);
        }
    }

    public Iterator<Usuario> listarUsuario() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = " SELECT  *  from  usuario ";

        try {
            Statement ct = conexao.createStatement();
            ResultSet res = ct.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();

                usuario.setCod_usuario(res.getInt("codigo"));
                usuario.setCargo(res.getString("cargo"));

                int cod_pessoa = res.getInt("cod_pessoa");
                Pessoa pessoa = new PessoaDAO().buscaPessoaCodigo(cod_pessoa);

                // informacoes da pessoa 
                usuario.setCodigo(pessoa.getCodigo());
                usuario.setNome(pessoa.getNome());
                usuario.setTipo(pessoa.getTipo());
                usuario.setSenha(pessoa.getSenha());
                usuario.setUsuario(pessoa.getUsuario());
                usuario.setEndereco(pessoa.getEndereco());

                int cod_hotel = res.getInt("cod_hotel");
                Hotel hotel = new HotelDAO().Consultar(cod_hotel);
                usuario.setHotel(hotel);

                usuarios.add(usuario);
            }

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return usuarios.iterator();
    }

    public Usuario consultarUsuarioCodigo(int codigo) {

        String sql = "SELECT * from usuario where codigo=" + codigo;

        Usuario usuario = new Usuario();

        try {
            Statement ct = conexao.createStatement();
            ResultSet res = ct.executeQuery(sql);

            while (res.next()) {

                usuario.setCod_usuario(res.getInt("codigo"));
                usuario.setCargo(res.getString("cargo"));

                int cod_pessoa = res.getInt("cod_pessoa");
                Pessoa pessoa = new PessoaDAO().buscaPessoaCodigo(cod_pessoa);

                // informacoes da pessoa             
                usuario.setCodigo(pessoa.getCodigo());
                usuario.setNome(pessoa.getNome());
                usuario.setTipo(pessoa.getTipo());
                usuario.setSenha(pessoa.getSenha());
                usuario.setUsuario(pessoa.getUsuario());
                usuario.setEndereco(pessoa.getEndereco());

                int cod_hotel = res.getInt("cod_hotel");
                Hotel hotel = new HotelDAO().Consultar(cod_hotel);
                usuario.setHotel(hotel);
            }

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return usuario;
    }

    public Usuario consultarCod_pessoa(int cod_pessoa) {
        String sql = "SELECT * from usuario where cod_pessoa=" + cod_pessoa;

        Usuario usuario = new Usuario();

        try {
            Statement ct = conexao.createStatement();
            ResultSet res = ct.executeQuery(sql);

            while (res.next()) {

                usuario.setCod_usuario(res.getInt("codigo"));
                usuario.setCargo(res.getString("cargo"));

                Pessoa pessoa = new PessoaDAO().buscaPessoaCodigo(cod_pessoa);

                // informacoes da pessoa             
                usuario.setCodigo(pessoa.getCodigo());
                usuario.setNome(pessoa.getNome());
                usuario.setTipo(pessoa.getTipo());
                usuario.setSenha(pessoa.getSenha());
                usuario.setUsuario(pessoa.getUsuario());
                usuario.setEndereco(pessoa.getEndereco());

                int cod_hotel = res.getInt("cod_hotel");
                Hotel hotel = new HotelDAO().Consultar(cod_hotel);
                usuario.setHotel(hotel);
            }

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return usuario;
    }

    public void alterarUsuario(Usuario usuario) {

        try {
            conexao.setAutoCommit(false);

            new PessoaDAO().alterarPessoa(usuario.getPessoa(), conexao);

            String sql = " UPDATE usuario SET cargo=?, cod_hotel=?, cod_pessoa=? "
                    + " where codigo=? ";

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getCargo());
            ps.setInt(2, usuario.getHotel().getCodigo());
            ps.setInt(3, usuario.getPessoa().getCodigo());
            ps.setInt(4, usuario.getCod_usuario());

            ps.executeUpdate();

            conexao.commit();

            System.out.println("Usuario alterado");
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public void exlcuirUsuario(int codigo, int cod_pessoa, int cod_endereco) throws SQLException {
        try {
            conexao.setAutoCommit(false);

            String sql = "delete from usuario where codigo=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();

            new PessoaDAO().excluirPessoa(cod_pessoa, cod_endereco, conexao);

            conexao.commit();
            System.out.println("Usuario Excluido");
        } catch (SQLException erro) {
            conexao.rollback();
            throw new RuntimeException(erro);
        }
    }
}
