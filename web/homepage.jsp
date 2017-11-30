<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EncontreHotéis.com</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        
        <link href="resource/homeHotel.css" rel="stylesheet" type="text/css"/>
        <link href="resource/homepage.css" rel="stylesheet" type="text/css"/>
        <link href="resource/estilo.css" rel="stylesheet" type="text/css"/>
        <link href="resource/perfilcliente.css" rel="stylesheet" type="text/css"/>
        <link href="resource/cabecalhoRodape.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrapValidator.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css"/>
        <script src="resource/js/ValidarFormato.js" type="text/javascript"></script>
        <script src="resource/js/bootstrap.js" type="text/javascript"></script>
        <script src="resource/js/bootstrapValidator.js" type="text/javascript"></script>
        <script src="resource/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="resource/js/bootstrapValidator.min.js" type="text/javascript"></script>
        <script src="resource/js/validator.min.js" type="text/javascript"></script>
        <script src="resource/js/ajax.js" type="text/javascript"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <nav class="navbar navbar-right navbar-fixed-top" role="navigation">

            <div class="container-fluid" id="cabecalho">         

                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
                        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                    </button> 
                    <a href="index.jsp" class="navbar-brand">Encontrehoteis.com</a>     
                </div>

                <div class="collapse navbar-collapse " id="navbar-collapse">

                    <c:if test="${pessoa.tipo eq 'ADMINISTRADOR'}">
                        <ul class="nav navbar-nav navbar-right" role="tablist">
                            <li class="dropdown">
                                <a class="dropdown-toggle" id="login"data-toggle="dropdown">
                                    Cadastros <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu"> 
                                    <li><a href="/EncontreHoteis/hotel?action=hoteis">Hoteis</a></li>
                                    <li><a href="/EncontreHoteis/usuario?action=usuarios">Usuarios</a></li>
                                    <li><a href="/EncontreHoteis/logout">Sair</a></li>
                                </ul>  
                            </li>
                        </ul>
                    </c:if>

                    <c:if test="${pessoa.tipo eq 'CLIENTE'}">
                        <ul class="nav navbar-nav">
                            <li> <a href="index.jsp" class="navsp">Reservas</a> </li>
                            <li> <a href="index.jsp" class="navs">Pagamentos</a> </li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right" role="tablist">
                            <li class="dropdown">
                                <a class="dropdown-toggle" id="login"data-toggle="dropdown">
                                    MINHA CONTA <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu">  
                                    <li><a href="/EncontreHoteis/cliente?action=meuPerfil">Meu Perfil</a></li>
                                    <li><a href="/EncontreHoteis/logout">Sair</a></li>
                                </ul>  
                            </li>
                        </ul>
                        
                        <c:if test="${pagina != 'perfilCliente'}">
                            <jsp:include page="listadehoteis.jsp"/>
                        </c:if>
                    </c:if>

                    <c:if test="${pessoa.tipo eq 'USUARIO'}">
                        <ul class="nav navbar-nav">
                            <li> <a href="index.jsp" class="navsp">Reservas</a> </li>
                            <li> <a href="/EncontreHoteis/quarto?action=quartos&cod_hotel=<c:out value="${hotel.codigo}"/>"
                                    class="navs">Quartos</a> 
                            </li>
                        </ul>

                        <ul class="nav navbar-nav navbar-right" role="tablist">
                            <li class="dropdown">
                                <a class="dropdown-toggle" id="login"data-toggle="dropdown">
                                    MINHA CONTA <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu" role="menu">  
                                    <li><a href="/EncontreHoteis/usuario?action=perfil">Meu Perfil</a></li>
                                    <li><a href="/EncontreHoteis/logout">Sair</a></li>
                                </ul>  
                            </li>
                        </ul>
                    </c:if>

                </div>
            </div>
        </nav>

        <!-- Listas -->
        <c:if test="${pagina eq 'listaHotel'}">
           <jsp:include page="listaHotel.jsp"/>
        </c:if>
        
        <c:if test="${pagina eq 'listaUsuario'}">
           <jsp:include page="listaUsuario.jsp"/>
        </c:if>
        
        <c:if test="${pagina eq 'listaQuarto'}">
           <jsp:include page="listaQuarto.jsp"/>
        </c:if>
        
        <!-- Perfis -->
        <c:if test="${pagina eq 'perfilCliente'}">
           <jsp:include page="perfilCliente.jsp"/>
        </c:if>
        
        <c:if test="${pagina eq 'perfilUsuario'}">
           <jsp:include page="perfilUsuario.jsp"/>
        </c:if>
        
        <!-- Formularios -->
        <c:if test="${pagina eq 'formularioHotel'}">
           <jsp:include page="formulariohotel.jsp"/>
        </c:if>
        
        <c:if test="${pagina eq 'formularioUsuario'}">
           <jsp:include page="formulariousuario.jsp"/>
        </c:if>
        
        <c:if test="${pagina eq 'formularioQuarto'}">
           <jsp:include page="formularioquarto.jsp"/>
        </c:if>

        <section id="secrodape">
           <nav class="navbar navbar-nav navbar-fixed-bottom ">
              <div class="rodape">
                <p id="rodape">Copyright &copy; 2017 | EncontreHotéis.com</p>
              </div>
           </nav> 
        </section>
    </body>
</html>
