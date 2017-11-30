package br.com.encontrehoteis.bll;

import br.com.encontrehoteis.dll.CidadeDAO;
import br.com.encontrehoteis.dll.EstadoDAO;
import br.com.encontrehoteis.dll.HotelDAO;
import br.com.encontrehoteis.dll.UsuarioDAO;
import br.com.encontrehoteis.model.Cidade;
import br.com.encontrehoteis.model.Endereco;
import br.com.encontrehoteis.model.Estado;
import br.com.encontrehoteis.model.Hotel;
import br.com.encontrehoteis.model.TipoPessoa;
import br.com.encontrehoteis.model.Usuario;
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

@WebServlet(name="UsuarioCtr", urlPatterns="/usuario")
public class UsuarioCtr extends HttpServlet{
    
    private static final long serialVersionUID = 1L;

    private static final String HOME_PAGE = "/homepage.jsp";
    
    private final UsuarioDAO dao;

    public UsuarioCtr() {
        dao = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String forward = HOME_PAGE;

        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("deletar")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                
                Usuario usuario = dao.consultarUsuarioCodigo(codigo);
                
                // pega o codigo da pessoa e do endereco para serem excluidos tambem              
                int cod_pessoa = usuario.getPessoa().getCodigo();
                int cod_endereco = usuario.getPessoa().getEndereco().getCodigo();

                try {
                    dao.exlcuirUsuario(codigo, cod_pessoa, cod_endereco);
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioCtr.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                request.setAttribute("pagina", "listaUsuario");
                Iterator<Usuario> usuarios = dao.listarUsuario();
                request.setAttribute("usuarios", usuarios);
            } else if (action.equalsIgnoreCase("editar")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                
                Usuario usuario = dao.consultarUsuarioCodigo(codigo);
                request.setAttribute("usuario", usuario);
                request.setAttribute("endereco", usuario.getEndereco());
                request.setAttribute("pes", usuario.getPessoa());
                request.setAttribute("hotel", usuario.getHotel());
                
                // coloca o cod_estado e cod_cidade e cod_hotel para os selects da cidade e estado  e hotel          
                request.setAttribute("cod_estado", usuario.getEndereco().getCidade().getEstado().getCodigo());
                request.setAttribute("cod_cidade", usuario.getEndereco().getCidade().getCodigo());
                request.setAttribute("cod_hotel", usuario.getHotel().getCodigo());
                
                // parte para carregar o select do estado   
                try {
                   Iterator<Estado> lista = new EstadoDAO().listar();
                   request.setAttribute("estados", lista);
                } catch (SQLException ex) {
                    Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // parte para carregar o select da cidade atraves do codigo do estado  
                try {
                    Iterator<Cidade> cidades = new CidadeDAO().listar(usuario.getEndereco().getCidade().getEstado().getCodigo());
                    request.setAttribute("cidades", cidades);
                } catch (SQLException ex) {
                    Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // parte para carregar o select do hotel
                Iterator<Hotel> hoteis = new HotelDAO().Listar();
                request.setAttribute("hoteis", hoteis);
                
                request.setAttribute("pagina", "formularioUsuario");
            } else if (action.equalsIgnoreCase("usuarios")) {
                Iterator<Usuario> usuarios = dao.listarUsuario();
                request.setAttribute("usuarios", usuarios);
                request.setAttribute("pagina", "listaUsuario");
                
                forward = "homepage.jsp";
            } else if (action.equalsIgnoreCase("adicionar")) {
                 // parte para carregar o select do estado   
                try {
                   Iterator<Estado> lista = new EstadoDAO().listar();
                   request.setAttribute("estados", lista);
                } catch (SQLException ex) {
                    Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // parte para carregar o select do hotel
                Iterator<Hotel> hoteis = new HotelDAO().Listar();
                request.setAttribute("hoteis", hoteis);
                
                request.setAttribute("pagina", "formularioUsuario");
                
            } else if (action.equalsIgnoreCase("perfil")) {
                
                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                
                request.setAttribute("usuario", usuario);
                request.setAttribute("endereco", usuario.getEndereco());
                request.setAttribute("pes", usuario.getPessoa());
                request.setAttribute("hotel", usuario.getHotel());
                request.setAttribute("cidade", usuario.getEndereco().getCidade());
                request.setAttribute("estado", usuario.getEndereco().getCidade().getEstado());
                
                request.setAttribute("pagina", "perfilUsuario");
                
            } else {
                request.setAttribute("usuarios", dao.listarUsuario());
                
                // parte para carregar o select do estado   
                try {
                   Iterator<Estado> lista = new EstadoDAO().listar();
                   request.setAttribute("estados", lista);
                } catch (SQLException ex) {
                   Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // parte para carregar o select do hotel 
                Iterator<Hotel> hoteis = new HotelDAO().Listar();
                request.setAttribute("hoteis", hoteis);
                request.setAttribute("pagina", "listaUsuario");
            }
        }else {
            request.setAttribute("usuarios", dao.listarUsuario());
            request.setAttribute("pagina", "listaUsuario");
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Object url = request.getAttribute("url");

        // caso o adm autenticar redireciona para a lista de usuario   
        if (url != null) {
            doGet(request, response);
            return;
        }
        
        Usuario usuario = new Usuario();
        usuario.setCargo(request.getParameter("cargo"));
        
        // informacoes da pessoa      
        usuario.setNome(request.getParameter("nome"));
        usuario.setSenha(request.getParameter("senha"));
        usuario.setUsuario(request.getParameter("usuario"));
        usuario.setTipo(TipoPessoa.USUARIO);
   
        // endereco da pessoa       
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
        
        // adiciona a cidade ao endereco e adiciona o endereco ao usuario       
        endereco.setCidade(cidade);
        usuario.setEndereco(endereco);
        
        // coloca o codigo do hotel no objeto hotel e vincula o usuario ao hotel    
        Hotel hotel = new Hotel();
        int cod_hotel = Integer.parseInt(request.getParameter("cod_hotel"));
        hotel.setCodigo(cod_hotel);
        usuario.setHotel(hotel);
        
        String codigo = request.getParameter("codigo");
        if (codigo == null || codigo.isEmpty()) {
            try {
                dao.AdicionarUsuario(usuario);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioCtr.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            int cod_endereco = Integer.parseInt(request.getParameter("cod_endereco"));
            int cod_pessoa = Integer.parseInt(request.getParameter("cod_pessoa")); 
            
            usuario.getEndereco().setCodigo(cod_endereco);
            usuario.setCodigo(cod_pessoa);
            usuario.setCod_usuario(Integer.parseInt(codigo));
            
            dao.alterarUsuario(usuario);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(HOME_PAGE);
        request.setAttribute("usuarios", dao.listarUsuario());
        request.setAttribute("pagina", "listaUsuario");
        view.forward(request, response);
    }
}