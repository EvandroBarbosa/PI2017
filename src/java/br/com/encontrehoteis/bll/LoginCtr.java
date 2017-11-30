package br.com.encontrehoteis.bll;

import br.com.encontrehoteis.dll.ClienteDAO;
import br.com.encontrehoteis.dll.LoginDAO;
import br.com.encontrehoteis.dll.UsuarioDAO;
import br.com.encontrehoteis.model.Cliente;
import br.com.encontrehoteis.model.Pessoa;
import br.com.encontrehoteis.model.TipoPessoa;
import br.com.encontrehoteis.model.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginCtr", urlPatterns = "/login")
public class LoginCtr extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/formulariologin.jsp");
        
        System.out.println("acessando o controller do login");
        
        if(req.getAttribute("url") != null) {
            String url = req.getAttribute("url").toString();
            req.setAttribute("url", url);
        }
        
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String usuario = req.getParameter("usuario");
        String senha = req.getParameter("senha");
        String url = req.getParameter("url");

        Pessoa pessoa = new Pessoa();
        pessoa.setUsuario(usuario);
        pessoa.setSenha(senha);

        pessoa = new LoginDAO().buscarPessoa(pessoa);

        // se encontrou o registro da pessoa redireciona para home com suas funcionalidades ativas.
        if (pessoa.getCodigo() != 0) {
            
            HttpSession sessao = req.getSession();
            sessao.setAttribute("pessoa", pessoa);
            
            if(pessoa.getTipo() == TipoPessoa.USUARIO) {
               Usuario user = new UsuarioDAO().consultarCod_pessoa(pessoa.getCodigo());
               req.setAttribute("hotel", user.getHotel());
               sessao.setAttribute("usuario", user);
            }
            
            if(pessoa.getTipo() == TipoPessoa.CLIENTE) {
               Usuario user = new UsuarioDAO().consultarCod_pessoa(pessoa.getCodigo());
               Cliente cli = new ClienteDAO().consultarCod_pessoa(pessoa.getCodigo());
               sessao.setAttribute("cliente", cli);
            }

            url = url.replace("/EncontreHoteis", "");
            RequestDispatcher view = req.getRequestDispatcher("homepage.jsp");
            req.setAttribute("url", url);
            req.setAttribute("pessoa", pessoa);
            view.forward(req, resp);
        }else {
            // se nao encontrou redireciona para o login novamente
            resp.sendRedirect("/EncontreHoteis/login");
        }
    }
}