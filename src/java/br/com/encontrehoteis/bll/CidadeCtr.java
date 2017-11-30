package br.com.encontrehoteis.bll;

import br.com.encontrehoteis.dll.CidadeDAO;
import br.com.encontrehoteis.model.Cidade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author josejulio
 */
@WebServlet(name = "CidadeCtr", urlPatterns = {"/cidades"})
public class CidadeCtr extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
         int cod_estado = Integer.parseInt(req.getParameter("cod_estado"));
         
        PrintWriter saida = resp.getWriter();
         
        try {
            
           Iterator<Cidade> cidades = new CidadeDAO().listar(cod_estado);
           StringBuilder dados = new StringBuilder("");
           
            while(cidades.hasNext()) {
                Cidade cidade = cidades.next();
                dados.append(cidade.getCodigo()+"-"+cidade.getNome()+":");
            }
            
            saida.write(dados.toString());
                      
        } catch (SQLException ex) {
            Logger.getLogger(CidadeCtr.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
