package br.com.encontrehoteis.dll;

import br.com.encontrehoteis.model.Endereco;
import br.com.encontrehoteis.model.Hotel;
import br.com.encontrehoteis.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author rodrigo
 */
public class HotelDAO {

    private final Connection conexao;

    public HotelDAO() {
        conexao = Conexao.getConexao();
    }

    public void Incluir(Hotel hotel) throws SQLException {
        try {
            conexao.setAutoCommit(false);

            new EnderecoDAO().incluir(hotel.getEndereco(), conexao);

            String sql2 = "SELECT currval('endereco_codigo_seq') as codigo";

            Statement st = conexao.createStatement();

            ResultSet rs = st.executeQuery(sql2);

            if (rs.next()) {
                hotel.getEndereco().setCodigo(rs.getInt("codigo"));
            }

            String sql = ("insert into hotel(nome, descricao, foto,"
                    + "email, telefone, cnpj, razao_social, cod_endereco) values(?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, hotel.getNome());
            ps.setString(2, hotel.getDescricao());
            ps.setString(3, hotel.getFoto());
            ps.setString(4, hotel.getEmail());
            ps.setString(5, hotel.getTelefone());
            ps.setString(6, hotel.getCnpj());
            ps.setString(7, hotel.getRazaoSocial());
            ps.setInt(8, hotel.getEndereco().getCodigo());

            ps.executeUpdate();
            conexao.commit();
            System.out.println("Hotel adicionado");
        } catch (SQLException erro) {
            conexao.rollback();
            throw new RuntimeException(erro);
        }
    }

    public void Alterar(Hotel hotel) throws SQLException {

        try {
            conexao.setAutoCommit(false);

            new EnderecoDAO().alterarEndereco(hotel.getEndereco(), conexao);

            String sql = ("update hotel set nome=?, descricao=?, foto=?, email=?, "
                    + "telefone=? where codigo=? ");
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, hotel.getNome());
            ps.setString(2, hotel.getDescricao());
            ps.setString(3, hotel.getFoto());
            ps.setString(4, hotel.getEmail());
            ps.setString(5, hotel.getTelefone());
            ps.setInt(6, hotel.getCodigo());

            ps.executeUpdate();
            conexao.commit();

            System.out.println("Hotel alterado");
        } catch (SQLException e) {
            conexao.rollback();
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("empty-statement")
    public Iterator<Hotel> Listar() {
        List<Hotel> lista = new ArrayList();

        try {
            Connection cnn = Conexao.getConexao();

            Statement statement = cnn.createStatement();
            ResultSet rs = statement.executeQuery("select * from hotel");
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setCodigo(rs.getInt("codigo"));
                hotel.setNome(rs.getString("nome"));
                hotel.setDescricao(rs.getString("descricao"));
                hotel.setFoto(rs.getString("foto"));
                hotel.setEmail(rs.getString("email"));
                hotel.setTelefone(rs.getString("telefone"));
                hotel.setCnpj(rs.getString("cnpj"));
                hotel.setRazaoSocial(rs.getString("razao_social"));

                int cod_endereco = rs.getInt("cod_endereco");

                Endereco end = new EnderecoDAO().buscaEnderecoCodigo(cod_endereco);

                hotel.setEndereco(end);

                lista.add(hotel);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return lista.iterator();
    }

    public Hotel Consultar(int codigo) {
        Hotel hotel = new Hotel();

        try {
            Statement statement = conexao.createStatement();

            ResultSet rs = statement.executeQuery("select * from hotel Where codigo = " + codigo);

            while (rs.next()) {

                hotel.setCodigo(rs.getInt("codigo"));
                hotel.setNome(rs.getString("nome"));
                hotel.setDescricao(rs.getString("descricao"));
                hotel.setFoto(rs.getString("foto"));
                hotel.setEmail(rs.getString("email"));
                hotel.setTelefone(rs.getString("telefone"));
                hotel.setCnpj(rs.getString("cnpj"));
                hotel.setRazaoSocial(rs.getString("razao_social"));

                int cod_endereco = rs.getInt("cod_endereco");

                Endereco end = new EnderecoDAO().buscaEnderecoCodigo(cod_endereco);
                hotel.setEndereco(end);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return hotel;
    }

    public void Excluir(int cod_hotel, int cod_endereco) {
        try {
            conexao.setAutoCommit(false);

            String sql = ("delete from hotel where codigo=?");
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, cod_hotel);

            ps.executeUpdate();
            System.out.println("Hotel excluido");

            new EnderecoDAO().deletarEndereco(cod_endereco, conexao);

            conexao.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
