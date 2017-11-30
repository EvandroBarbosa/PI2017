
package br.com.encontrehoteis.dll;


public class ReservaDAO {
//    private final Connection cnn;
//    
//    public ReservaDAO() {
//        cnn = Conexao.getConexao();
//    }
//    
//    //Metodo Incluir
//    public void incluirReserva(Reserva reserva) {
//        try {
//                PreparedStatement ps = cnn.prepareStatement("insert into reserva"
//                + "(data_entrada, data_saida, valor_total, pagamento, confirmar, cod_cliente)" 
//                        + "values(?, ?, ?, ?, ?, ?)");
//                
//                ps.setDate(1, (Date) reserva.getDataEntrada());
//                ps.setDate(2, (Date) reserva.getDataSaida());
//                ps.setFloat(3, reserva.getValorTotal());
//                ps.setBoolean(4, reserva.isPagamento());
//                ps.setBoolean(5, reserva.isConfirmar());
//                ps.setInt(6, reserva.getCliente().getCodigo());
//                
//                ps.execute();
//        } catch (SQLException erro) {
//            System.out.println("Conexão desfeita");
//        }
//    }
//    
//    //Metodo para busca por codigo
//    public Reserva buscaReservaCodigo(int codigo) {
//        Reserva reserva = new Reserva();
//        
//        try {
//                
//                String sql = "select * from reserva where codigo=?" + codigo;
//                Statement st = cnn.createStatement();
//                        
//                ResultSet res = st.executeQuery(sql);
//                while(res.next()) {
//                    
//                    reserva.setCodigo(res.getInt("codigo"));
//                    
//                }
//        } catch (Exception e) {
//        }
//    }
//}
//
//                    
//                    cliente.setCodigo(res.getInt("codigo"));
//                    cliente.setCpf(res.getString("cpf"));
//                    cliente.setDataNasc(res.getDate("data_nascimento"));
//                    cliente.setSexo(res.getString("sexo"));
//                    cliente.getPessoa().setCodigo(res.getInt("codigo"));
//                }
//        } catch (SQLException erro) {
//        } 
//        return cliente;
//    } 
//    
//    //metodo excluir
//    public void excluirCliente(int codigo){
//        try {
//            
//                String sql = "delete from cliente where codigo=?";
//                PreparedStatement ps = cnn.prepareStatement(sql);
//                ps.setInt(1, codigo);
//                ps.executeUpdate();
//                System.out.println("Cliente excluído");
//        } catch (SQLException erro) {
//            erro.printStackTrace();
//        }
//    }
//    
//    //metodo alterar
//    public void alterarCliente(Cliente cliente) {
//        try {
//            
//                String sql = "update cliente set cpf=?, data_nascimento=?, sexo=?, cod_pessoa=? "
//                + "where codigo=?";
//                
//                PreparedStatement ps = cnn.prepareStatement(sql);
//                ps.setString(1, cliente.getCpf());
//                ps.setDate(2, (Date) cliente.getDataNasc());
//                ps.setString(3, cliente.getSexo());
//                ps.setInt(4, cliente.getPessoa().getCodigo());
//                
//                ps.executeUpdate();
//                
//                System.out.println("Cliente alterado");
//                
//        } catch (SQLException erro) {
//            erro.printStackTrace();
//        }
//    }
//}
}