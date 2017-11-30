<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>EncontreHotéis.com</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, inicial-scale=1.0"/>       
        <script src='http://code.jquery.com/jquery-2.1.3.min.js'></script>
        <link href="resource/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrapValidator.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css"/>
        <link href="resource/cabecalhoRodape.css" rel="stylesheet" type="text/css"/>
        <script src="resource/js/bootstrap.js" type="text/javascript"></script>
        <link href="resource/homeHotel.css" rel="stylesheet" type="text/css"/>
        <script src="resource/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    </head>
    <body id="clinete">
        <nav class="navbar  navbar-fixed-top" role="navigation">
            <div class="container-fluid" id="cabecalho">         
                <div class="navbar-header ">                     
                    <a href="index.jsp" class="navbar-brand" id="cabeHotel">Encontrehoteis.com</a>
                </div>               
            </div>
        </nav>
        <div class="container">
            <div class="formu">
                <form id="quarto" method="POST" action="/EncontreHoteis/indique">
                    <fieldset>
                        <legend class="legend">Sistema de indicacoes de hoteis</legend>
                        <div class="form-group">
                            <label for="status">E casado: </label><br/>
                            <input type="radio"  name="casado" value="true"/>  Sim
                            <input type="radio"  name="casado" value="false" checked="true"/>  Nao
                        </div>
                        <div class="form-group">
                            <label for="status">Tem Filhos: </label><br/>
                            <input type="radio"  name="filho" value="true"/>  Sim
                            <input type="radio"  name="filho" value="false" checked="true"/>  Nao
                        </div>           
                        <div class="form-group">
                            <label for="status">Gosta de Esportes Radicais: </label><br/>     
                            <input type="radio"  name="esporte" value="true"/>  Sim
                            <input type="radio"  name="esporte" value="false" checked="true"/>  Nao                           
                        </div>
                    </fieldset>
                    <br/> 
                    
                    <c:if test="${resultado != null}">
                        <textarea class="form-control" id="descricao" rows="6"
                              placeholder="De uma descrição para o quarto">O seu perfil: ${resultado}</textarea>
                    </c:if>
                    
                    <div class="btn">                                
                       <button type="submit" class="btn btn-success btnSalvar">Salvar</button>
                    </div>
                    <div class="btn">                                
                       <button type="reset" class="btn btn-danger btnCancelar">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
        <section id="secrodape">
           <nav class="navbar navbar-nav navbar-fixed-bottom ">
              <div class="rodape">
                <p id="rodape">Copyright &copy; 2017 | EncontreHotéis.com</p>
              </div>
           </nav> 
        </section>        
    </body>
</html>
