package br.com.encontrehoteis.bll;

import br.com.encontrehoteis.dll.CidadeDAO;
import br.com.encontrehoteis.dll.EstadoDAO;
import br.com.encontrehoteis.dll.HotelDAO;
import br.com.encontrehoteis.model.Cidade;
import br.com.encontrehoteis.model.Endereco;
import br.com.encontrehoteis.model.Estado;
import br.com.encontrehoteis.model.Hotel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HotelCtrl", urlPatterns = "/hotel")
public class HotelCtr extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String HOMEPAGE = "/homepage.jsp";

    private final HotelDAO dao;

    public HotelCtr() {
        dao = new HotelDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = HOMEPAGE;

        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("deletar")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                
                Hotel hotel = dao.Consultar(codigo);
                
                int cod = hotel.getEndereco().getCodigo();

                dao.Excluir(codigo, cod);
                
                request.setAttribute("pagina", "listaHotel");
                request.setAttribute("hoteis", dao.Listar());
                
                forward = HOMEPAGE;
            } else if (action.equalsIgnoreCase("editar")) {
                int cod_hotel = Integer.parseInt(request.getParameter("codigo"));
                Hotel hotel = dao.Consultar(cod_hotel);
                request.setAttribute("hotel", hotel);
                request.setAttribute("endereco", hotel.getEndereco());
                request.setAttribute("foto", hotel.getFoto());
                request.setAttribute("razao", hotel.getRazaoSocial());
                
                // coloca o cod_estado e cod_cidade para os selects da cidade e estado            
                request.setAttribute("cod_estado", hotel.getEndereco().getCidade().getEstado().getCodigo());
                request.setAttribute("cod_cidade", hotel.getEndereco().getCidade().getCodigo());
                
                // parte para carregar o select do estado   
                try {
                   Iterator<Estado> lista = new EstadoDAO().listar();
                   request.setAttribute("estados", lista);
                } catch (SQLException ex) {
                   Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // parte para carregar o select da cidade atraves do codigo do estado  
                try {
                    Iterator<Cidade> cidades = new CidadeDAO().listar(hotel.getEndereco().getCidade().getEstado().getCodigo());
                    request.setAttribute("cidades", cidades);
                } catch (SQLException ex) {
                    Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                request.setAttribute("pagina", "formularioHotel");
                forward = HOMEPAGE;
            } else if (action.equalsIgnoreCase("hoteis")) {
                Iterator<Hotel> listaHoteis = dao.Listar();
                request.setAttribute("hoteis", listaHoteis);
                request.setAttribute("pagina", "listaHotel");
                
                forward = "homepage.jsp";
            } else {
                request.setAttribute("hoteis", dao.Listar());
                
                 // parte para carregar o select do estado   
                try {
                   Iterator<Estado> lista = new EstadoDAO().listar();
                   request.setAttribute("estados", lista);
                } catch (SQLException ex) {
                   Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                request.setAttribute("pagina", "formularioHotel");
                forward = HOMEPAGE;
            }
        }else {
        
           if(request.getAttribute("foto") != null){
              try {
                   Iterator<Estado> lista = new EstadoDAO().listar();
                   request.setAttribute("estados", lista);
              } catch (SQLException ex) {
                   Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
              }
            
              request.setAttribute("pagina", "formularioHotel");
              forward = HOMEPAGE;
           }else {
              request.setAttribute("pagina", "listaHotel");
              request.setAttribute("hoteis", dao.Listar()); 
           }
           
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object url = request.getAttribute("url");
        Object foto = request.getAttribute("foto");

        // caso o adm autenticar redireciona para a lista de hotel    
        if (url != null) {
            doGet(request, response);
            return;
        }
        
        // verifica se contem a foto caso tenha manda o doGet
        if(foto != null){
            doGet(request, response);
            return;
        }

        Hotel hotel = new Hotel();
        hotel.setNome(request.getParameter("nome"));
        hotel.setDescricao(request.getParameter("descricao"));
        
        hotel.setEmail(request.getParameter("email"));
        hotel.setTelefone(request.getParameter("telefone"));
        hotel.setCnpj(request.getParameter("cnpj"));
        hotel.setRazaoSocial(request.getParameter("razaoSocial"));

        Endereco endereco = new Endereco();
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setComplemento(request.getParameter("complemento"));
        endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
        endereco.setCep(request.getParameter("cep"));
        endereco.setLogradouro(request.getParameter("logradouro"));
        
        int cod_estado = Integer.parseInt(request.getParameter("estado"));
        int cod_cidade = Integer.parseInt(request.getParameter("cidade"));
        
        // adiciona o codigo do estado no objeto cidade        
        Cidade cidade = new Cidade();
        cidade.setCodigo(cod_cidade);
        cidade.setEstado(new Estado());
        cidade.getEstado().setCodigo(cod_estado);
        
        // adiciona a cidade ao endereco e adiciona o endereco ao hotel       
        endereco.setCidade(cidade);
        hotel.setEndereco(endereco);
        
        String fot = request.getParameter("foto");
        fot = "/home/josejulio/NetBeansProjects/EncontreHoteis/web/fotos/"+fot;
        
        hotel.setFoto(fot);

        String codigo = request.getParameter("codigo");
        if (codigo == null || codigo.isEmpty()) {
            try {
                dao.Incluir(hotel);
            } catch (SQLException ex) {
                Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            int cod_endereco = Integer.parseInt(request.getParameter("cod_endereco"));
            hotel.getEndereco().setCodigo(cod_endereco);
            hotel.setCodigo(Integer.parseInt(codigo));
            try {
                dao.Alterar(hotel);
            } catch (SQLException ex) {
                Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        RequestDispatcher view = request.getRequestDispatcher(HOMEPAGE);
        request.setAttribute("hoteis", dao.Listar());
        request.setAttribute("pagina", "listaHotel");
        view.forward(request, response);
    }
}