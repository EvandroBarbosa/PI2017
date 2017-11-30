package br.com.encontrehoteis.bll;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 * @author josejulio
 */
@WebServlet(name = "IndiqueCtr", urlPatterns = {"/indique"})
public class IndiqueCtr extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        RequestDispatcher view = req.getRequestDispatcher("/formularioindique.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
               
        boolean casado = false;
        boolean filho = false;
        boolean esporte = false;
        
        if(req.getParameter("casado").equals("true")){
            casado = true;
        }
        
        if(req.getParameter("filho").equals("true")){
            filho = true;
        }
        
        if(req.getParameter("esporte").equals("true")){
            esporte = true;
        }
        
        GetRequest get = Unirest.get("http://localhost:8080/pref?aventureiro="+esporte+"&romantico="+casado+"&familia="+filho);
        String retorno = "Familia";
        try {
            JSONObject json = get.asJson().getBody().getObject();
            retorno = json.getString("perfil");
         } catch (UnirestException ex) {
            Logger.getLogger(IndiqueCtr.class.getName()).log(Level.SEVERE, null, ex);
            retorno = "falha no servidor.";
        }
       
        req.setAttribute("resultado", retorno);
        
        RequestDispatcher view = req.getRequestDispatcher("/formularioindique.jsp");
        view.forward(req, resp);
    }

}
