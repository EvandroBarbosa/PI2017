package br.com.encontrehoteis.bll;

import br.com.encontrehoteis.dll.QuartoDAO;
import br.com.encontrehoteis.model.Hotel;
import br.com.encontrehoteis.model.Quarto;
import br.com.encontrehoteis.model.Usuario;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author josejulio
 */
@WebServlet(name = "QuartoCtr", urlPatterns = "/quarto")
public class QuartoCtr extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static String HOME_PAGE = "homepage.jsp";

    QuartoDAO dao;

    public QuartoCtr() {
        dao = new QuartoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String forward = null;

        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");

            if (action.equalsIgnoreCase("deletar")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                Quarto quarto = dao.consultarCodigo(codigo);

                dao.excluir(codigo);

                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("usuario");

                Iterator<Quarto> quartos = dao.listarPorCodigo(usuario.getHotel().getCodigo());

                request.setAttribute("quartos", quartos);
                request.setAttribute("pagina", "listaQuarto");
                forward = HOME_PAGE;
            } else if (action.equalsIgnoreCase("editar")) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));

                Quarto quarto = dao.consultarCodigo(codigo);

                request.setAttribute("quarto", quarto);
                request.setAttribute("hotel", quarto.getHotel());
                request.setAttribute("fotos", "todas");
                
                request.setAttribute("pagina", "formularioQuarto");
                forward = HOME_PAGE;
            } else if (action.equalsIgnoreCase("quartos")) {
                int cod_hotel = Integer.parseInt(request.getParameter("cod_hotel"));

                Iterator<Quarto> quartos = dao.listarPorCodigo(cod_hotel);

                request.setAttribute("quartos", quartos);
                request.setAttribute("pagina", "listaQuarto");

                forward = HOME_PAGE;
            } else {
                request.setAttribute("pagina", "formularioQuarto");
                forward = HOME_PAGE;
            }

            if (request.getAttribute("foto1") != null
                    && request.getAttribute("foto2") != null
                    && request.getAttribute("foto3") != null) {
                // coloca o atributo fotos na request assim os campos das fotos ficaram invisiveis         
                request.setAttribute("fotos", "todas marcadas");
            } else {
                if (action.equals("cadastrar")) {
                    /* caso todas as fotos tenhm sido preenchidas nao estara mais disponivel 
               a selecao de novas fotos */
                    if (request.getAttribute("foto1") != null
                            && request.getAttribute("foto2") != null
                            && request.getAttribute("foto3") != null) {
                        request.setAttribute("fotos", "todas prenchidas");
                        request.setAttribute("pagina", "formularioQuarto");
                        forward = HOME_PAGE;
                    }

                    /* enquanto uma das fotos forem nulas manda de volta para o formulario  
               para o usuario poder selecionar */
                    if (request.getAttribute("foto1") == null
                            || request.getAttribute("foto2") == null
                            || request.getAttribute("foto3") == null) {
                        request.setAttribute("pagina", "formularioQuarto");
                        forward = HOME_PAGE;
                    }
                }
            }
        } else {

            if (request.getAttribute("actionCadastrar") != null) {

                /* caso todas as fotos tenhm sido preenchidas nao estara mais disponivel 
               a selecao de novas fotos */
                if (request.getAttribute("foto1") != null
                        && request.getAttribute("foto2") != null
                        && request.getAttribute("foto3") != null) {
                    request.setAttribute("fotos", "todas prenchidas");
                    request.setAttribute("pagina", "formularioQuarto");
                    forward = HOME_PAGE;
                }

                /* enquanto uma das fotos forem nulas manda de volta para o formulario  
               para o usuario poder selecionar */
                if (request.getAttribute("foto1") == null
                        || request.getAttribute("foto2") == null
                        || request.getAttribute("foto3") == null) {
                    request.setAttribute("pagina", "formularioQuarto");
                    forward = HOME_PAGE;
                }
            }else {
               request.setAttribute("pagina", "listaQuarto");
            }
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
       
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String foto1 = request.getParameter("foto1");
        String foto2 = request.getParameter("foto2");
        String foto3 = request.getParameter("foto3");

        if (request.getParameter("salvar") == null) {
            // enquantos as fotos nao forem selecionadas manda para o formulario de novo   
            if (request.getAttribute("fotos") == null) {
                request.setAttribute("actionCadastrar", "cadastrar");
                doGet(request, response);
                return;
            }
        }

        Quarto quarto = new Quarto();

        quarto.setNumero(request.getParameter("numero"));
        quarto.setCapacidade(Integer.parseInt(request.getParameter("capacidade")));
        quarto.setTipo(request.getParameter("tipo"));

        String status = request.getParameter("status");

        if (status.equals("true")) {
            quarto.setStatus(true);
        } else {
            quarto.setStatus(false);
        }

        quarto.setDescricao(request.getParameter("descricao"));

        quarto.setFoto1("/home/josejulio/NetBeansProjects/EncontreHoteis/web/fotos/" + foto1);
        quarto.setFoto2("/home/josejulio/NetBeansProjects/EncontreHoteis/web/fotos/" + foto2);
        quarto.setFoto3("/home/josejulio/NetBeansProjects/EncontreHoteis/web/fotos/" + foto3);

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        Hotel hotel = new Hotel();
        hotel.setCodigo(usuario.getHotel().getCodigo());

        quarto.setHotel(hotel);

        float valorDiaria = Float.parseFloat(request.getParameter("valorDiaria"));
        quarto.setValorDiaria(valorDiaria);

        quarto.setDescricao(request.getParameter("descricao"));

        String codigo = request.getParameter("codigo");
        if (codigo == null || codigo.isEmpty()) {
            dao.incluir(quarto);
        } else {
            quarto.setCodigo(Integer.parseInt(codigo));
            dao.alterar(quarto);
        }

        RequestDispatcher view = request.getRequestDispatcher(HOME_PAGE);
        request.setAttribute("quartos", dao.listarPorCodigo(hotel.getCodigo()));
        request.setAttribute("pagina", "listaQuarto");
        view.forward(request, response);
    }
}