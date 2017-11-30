package br.com.encontrehoteis.bll;

import br.com.encontrehoteis.dll.CidadeDAO;
import br.com.encontrehoteis.dll.ClienteDAO;
import br.com.encontrehoteis.dll.EstadoDAO;
import br.com.encontrehoteis.model.Cidade;
import br.com.encontrehoteis.model.Cliente;
import br.com.encontrehoteis.model.Endereco;
import br.com.encontrehoteis.model.Estado;
import br.com.encontrehoteis.model.TipoPessoa;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ClienteCtr", urlPatterns = "/cliente")
public class ClienteCtr extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static String INSERIR_OU_EDITAR = "formulariocliente.jsp";
    private final static String MEU_PERFIL = "homepage.jsp";
    private final static String INDEX = "index.jsp";

    private final ClienteDAO dao;

    public ClienteCtr() {
        dao = new ClienteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String forward = INDEX;

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("deletar")) {
            try {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                
                Cliente cliente = dao.buscaClienteCodigo(codigo);
                
                // pega o codigo da pessoa e do endereco para serem excluidos tambem
                int cod_pessoa = cliente.getPessoa().getCodigo();
                int cod_endereco = cliente.getPessoa().getEndereco().getCodigo();
                
                // apos o cliente excluir sua conta sera redirecionado para o index
                dao.excluirCliente(codigo, cod_pessoa, cod_endereco);
                forward = INDEX;
            } catch (SQLException ex) {
                Logger.getLogger(ClienteCtr.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("editar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            Cliente cliente = dao.buscaClienteCodigo(codigo);

            request.setAttribute("cliente", cliente);
            request.setAttribute("endereco", cliente.getEndereco());
            request.setAttribute("pes", cliente.getPessoa());

            // coloca o cod_estado e cod_cidade para os selects da cidade e estado            
            request.setAttribute("cod_estado", cliente.getEndereco().getCidade().getEstado().getCodigo());
            request.setAttribute("cod_cidade", cliente.getEndereco().getCidade().getCodigo());

            // parte para carregar o select do estado   
            try {
                Iterator<Estado> lista = new EstadoDAO().listar();
                request.setAttribute("estados", lista);
            } catch (SQLException ex) {
                Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
            }

            // parte para carregar o select da cidade atraves do codigo do estado  
            try {
                Iterator<Cidade> cidades = new CidadeDAO().listar(cliente.getEndereco().getCidade().getEstado().getCodigo());
                request.setAttribute("cidades", cidades);
            } catch (SQLException ex) {
                Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
            }

            forward = INSERIR_OU_EDITAR;
        } else if(action.equalsIgnoreCase("meuPerfil")){
           
            Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");

            request.setAttribute("cliente", cliente);
            request.setAttribute("endereco", cliente.getEndereco());
            request.setAttribute("pes", cliente.getPessoa());
            request.setAttribute("estado", cliente.getEndereco().getCidade().getEstado());
            request.setAttribute("cidade", cliente.getEndereco().getCidade());
            
            if(cliente.getSexo().equals("M")){
                request.setAttribute("sexo", "Masculino");
            }else {
                request.setAttribute("sexo", "Feminino");
            }

            request.setAttribute("pagina", "perfilCliente");
            forward = MEU_PERFIL;
        }else {
            // parte para carregar o select do estado para o cliente poder se cadastrar  
            try {
                Iterator<Estado> lista = new EstadoDAO().listar();
                request.setAttribute("estados", lista);
            } catch (SQLException ex) {
                Logger.getLogger(HotelCtr.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            forward = INSERIR_OU_EDITAR;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Object url = request.getAttribute("url");

        if (url != null) {
            doGet(request, response);
        }
        
        Cliente cliente = new Cliente();
        cliente.setCpf(request.getParameter("cpf"));
        
        String data = request.getParameter("data");
        
        // pega a data de nascimento no formato yyyy/MM/dd
        Date date = new Date();
        try {
	   date = new SimpleDateFormat("yyyy/MM/dd").parse(data);
	} catch (ParseException e) {
          e.printStackTrace();
        }

        cliente.setDataNasc(date);
        
        cliente.setSexo(request.getParameter("sexo"));
        
        // informacoes da pessoa      
        cliente.setNome(request.getParameter("nome"));
        cliente.setSenha(request.getParameter("senha"));
        cliente.setUsuario(request.getParameter("usuario"));
        cliente.setTipo(TipoPessoa.CLIENTE);
   
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
        cliente.setEndereco(endereco);
        
        String codigo = request.getParameter("codigo");
        if (codigo == null || codigo.isEmpty()) {
            try {
                dao.incluirCliente(cliente);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteCtr.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            HttpSession sessao = request.getSession();
            sessao.setAttribute("pessoa", cliente.getPessoa());
            sessao.setAttribute("cliente", cliente);
            request.setAttribute("pessoa", cliente.getPessoa());
        } else {
            int cod_endereco = Integer.parseInt(request.getParameter("cod_endereco"));
            int cod_pessoa = Integer.parseInt(request.getParameter("cod_pessoa")); 
            
            cliente.getEndereco().setCodigo(cod_endereco);
            cliente.setCodigo(cod_pessoa);
            cliente.setCod_cliente(Integer.parseInt(codigo));
            
            try {
                dao.alterarCliente(cliente);
            } catch (SQLException ex) {
                Logger.getLogger(ClienteCtr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Apos adicionar ou altera um cliente o mesmo sera redirecionado para o seu perfil   
        RequestDispatcher view = request.getRequestDispatcher(MEU_PERFIL);
        request.setAttribute("usuario", cliente);
        request.setAttribute("endereco", cliente.getEndereco());
        request.setAttribute("pes", cliente.getPessoa());
        request.setAttribute("estado", cliente.getEndereco().getCidade().getEstado());
        request.setAttribute("cidade", cliente.getEndereco().getCidade());
            
        view.forward(request, response);
    }
}
