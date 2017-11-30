package br.com.encontrehoteis.bll;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "SalvarImagemCtr", urlPatterns = {"/salvar"})
public class SalvarImagemCtr extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String nome = null;

        boolean isMultiPart = FileUpload.isMultipartContent(req);

        if (isMultiPart) {
            FileItemFactory factory = new DiskFileItemFactory();

            ServletFileUpload upload = new ServletFileUpload(factory);

            String formulario = "";

            try {
                List items = upload.parseRequest(req);

                Iterator iter = items.iterator();

                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();

                    if (item.getFieldName().equals("foto")) {
                        formulario = item.getString();
                    }

                    if (!item.isFormField()) {
                        if (item.getName().length() > 0) {
                          nome = this.inserirImagemDiretorio(item);
                        }
                    }
                }
                
            } catch (FileUploadException ex) {
               throw new RuntimeException(ex);
            } catch (Exception ex) {
               throw new RuntimeException(ex);
            }

        }
          
        RequestDispatcher view = req.getRequestDispatcher("/hotel");
        req.setAttribute("foto", nome);
        req.setAttribute("action", "formulariocadastroHotel");

        view.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    private String inserirImagemDiretorio(FileItem item) {
        String nome = null;
        try {
            //Pega o diretório /fotos dentro do diretório atual de onde a aplicação está rodando
            String caminho = getServletContext().getRealPath("/fotos")+ "/";
            
            caminho = caminho.replace("/build", "");

            // Cria o diretório caso ele não exista
            File diretorio = new File(caminho);

            if (!diretorio.exists()){
                diretorio.mkdir();
            }

            // Mandar o arquivo para o diretório informado
            nome = item.getName();

            String arq[] = nome.split("\\\\");

            for (String arq1 : arq) {
                nome = arq1;
            }
            
            File file = new File(diretorio, nome);

            try (FileOutputStream output = new FileOutputStream(file)) {
                InputStream is = item.getInputStream();
                
                byte[] buffer = new byte[2048];
                
                int nLidos;
                
                while ((nLidos = is.read(buffer)) >= 0) {
                    output.write(buffer, 0, nLidos);
                }
                
                output.flush();
            }
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return nome;
    }
}