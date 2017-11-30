package br.com.encontrehoteis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection conexao;

    private Conexao() {
    }

    public static Connection getConexao() {
        if (conexao != null) {
            return conexao;
        } else {
            try {
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/encontrehoteis", "postgres", "123456");
            } catch (ClassNotFoundException erro) {
                System.out.println("Aplicação não contém o driver do banco");
                return null;
            } catch (SQLException ex) {
                System.out.println("Erro na conexão como banco: verifique a url, o usuário e a senha");
                return null;
            }
        }
        return conexao;
    }
}
