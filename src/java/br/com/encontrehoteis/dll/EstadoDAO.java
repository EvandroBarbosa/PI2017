package br.com.encontrehoteis.dll;

import br.com.encontrehoteis.model.Estado;
import br.com.encontrehoteis.util.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EstadoDAO {

    private final Connection cnn;

    public EstadoDAO() throws SQLException {
        this.cnn = Conexao.getConexao();
    }

    public Iterator<Estado> listar() {
        List<Estado> listEstado = new ArrayList<>();

        try {
            //traz os dados ordenados pelo codigo
            String sql = "SELECT * FROM estado ORDER BY id";

            Statement stm = cnn.createStatement();
            try (ResultSet rs = stm.executeQuery(sql)) {
                while (rs.next()) {
                    Estado estado = new Estado();
                    estado.setCodigo(rs.getInt("id"));
                    estado.setNome(rs.getString("nome"));
                    estado.setSigla(rs.getString("sigla"));

                    listEstado.add(estado);
                }
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

        return listEstado.iterator();
    }
}
