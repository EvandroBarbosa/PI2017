package br.com.encontrehoteis.filtro;

import br.com.encontrehoteis.model.Pessoa;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "Filtro", urlPatterns = {"/reserva", "/pagamento"})
public class Filtro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoa");

        if (pessoa == null) {
            request.setAttribute("url", request.getRequestURI());
            RequestDispatcher view = request.getRequestDispatcher("/login");
            view.forward(request, response);
        } else {
            System.out.println("tipo: " + pessoa.getTipo());
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
