package br.com.encontrehoteis.dll;

import br.com.encontrehoteis.model.Cidade;
import br.com.encontrehoteis.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CidadeDAO {

    private final Connection cnn;

    public CidadeDAO() throws SQLException {
        this.cnn = Conexao.getConexao();
    }

    public Iterator<Cidade> listar(int cod_estado) {
        List<Cidade> listCidade = new ArrayList<>();

        //traz os dados ordenados pelo codigo
        String sql = "SELECT * from cidade "
                + " WHERE estado_id=" + cod_estado;

        try {
            PreparedStatement stm = cnn.prepareStatement(sql);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Cidade cidade = new Cidade();
                    cidade.setCodigo(rs.getInt("id"));
                    cidade.setNome(rs.getString("nome"));

                    listCidade.add(cidade);
                }
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return listCidade.iterator();
    }
}
