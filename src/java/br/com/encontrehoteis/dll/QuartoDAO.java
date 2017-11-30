package br.com.encontrehoteis.dll;

import br.com.encontrehoteis.model.Hotel;
import br.com.encontrehoteis.model.Quarto;
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
 * @author josejulio
 */
public class QuartoDAO {

    private final Connection conexao;

    public QuartoDAO() {
        conexao = Conexao.getConexao();
    }

    public void incluir(Quarto quarto) {

        try {

            String sql = ("insert into quarto(foto1, foto2, foto3, numero, tipo, "
                    + "capacidade, valor_diaria, status, cod_hotel, descricao) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, quarto.getFoto1());
            ps.setString(2, quarto.getFoto2());
            ps.setString(3, quarto.getFoto3());
            ps.setString(4, quarto.getNumero());
            ps.setString(5, quarto.getTipo());
            ps.setInt(6, quarto.getCapacidade());
            ps.setDouble(7, quarto.getValorDiaria());
            ps.setBoolean(8, quarto.isStatus());
            ps.setInt(9, quarto.getHotel().getCodigo());
            ps.setString(10, quarto.getDescricao());

            ps.executeUpdate();
            System.out.println("Quarto Adicionado");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void alterar(Quarto quarto) {
        try {

            String sql = ("update quarto set foto1=?, foto2=?, foto3=?, numero=?, tipo=?, "
                    + "capacidade=?, valor_diaria=?, status=?, descricao=? where codigo=? ");

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, quarto.getFoto1());
            ps.setString(2, quarto.getFoto2());
            ps.setString(3, quarto.getFoto3());
            ps.setString(4, quarto.getNumero());
            ps.setString(5, quarto.getTipo());
            ps.setInt(6, quarto.getCapacidade());
            ps.setDouble(7, quarto.getValorDiaria());
            ps.setBoolean(8, quarto.isStatus());
            ps.setString(9, quarto.getDescricao());
            ps.setInt(10, quarto.getCodigo());

            ps.executeUpdate();
            System.out.println("Quarto Alterado");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Quarto consultarCodigo(int codigo) {
        Quarto quarto = new Quarto();

        try {
            Statement statement = conexao.createStatement();

            ResultSet res = statement.executeQuery("select * from quarto where codigo=" + codigo);

            while (res.next()) {
                quarto.setCodigo(codigo);
                quarto.setDescricao(res.getString("descricao"));
                quarto.setFoto1(res.getString("foto1"));
                quarto.setFoto2(res.getString("foto2"));
                quarto.setFoto3(res.getString("foto3"));
                quarto.setNumero(res.getString("numero"));
                quarto.setTipo(res.getString("tipo"));
                quarto.setCapacidade(res.getInt("capacidade"));
                quarto.setStatus(res.getBoolean("status"));
                quarto.setValorDiaria(res.getFloat("valor_diaria"));

                // pega as informacoes do hotel vinculado ao quarto          
                int cod_hotel = res.getInt("cod_hotel");
                Hotel hotel = new HotelDAO().Consultar(cod_hotel);
                quarto.setHotel(hotel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return quarto;
    }

    public Iterator<Quarto> listarPorCodigo(int cod_hotel) {
        List<Quarto> quartos = new ArrayList<>();

        try {
            Statement statement = conexao.createStatement();

            ResultSet res = statement.executeQuery("select * from quarto where cod_hotel=" + cod_hotel);

            while (res.next()) {
                Quarto quarto = new Quarto();

                quarto.setCodigo(res.getInt("codigo"));
                quarto.setDescricao(res.getString("descricao"));
                quarto.setFoto1(res.getString("foto1"));
                quarto.setFoto2(res.getString("foto2"));
                quarto.setFoto3(res.getString("foto3"));
                quarto.setNumero(res.getString("numero"));
                quarto.setTipo(res.getString("tipo"));
                quarto.setCapacidade(res.getInt("capacidade"));
                quarto.setStatus(res.getBoolean("status"));
                quarto.setValorDiaria(res.getFloat("valor_diaria"));

                // pega as informacoes do hotel vinculado ao quarto          
                Hotel hotel = new HotelDAO().Consultar(cod_hotel);
                quarto.setHotel(hotel);

                quartos.add(quarto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return quartos.iterator();
    }

    public void excluir(int cod_quarto) {
        try {
            String sql = ("delete from quarto where codigo=?");
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, cod_quarto);

            ps.executeUpdate();
            System.out.println("Quarto excluido");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
