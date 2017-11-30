<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EncontreHotéis.com</title>
        <link href="resource/perfilcliente.css" rel="stylesheet" type="text/css"/>        
        <link href="resource/cabecalhoRodape.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="resource/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="resource/js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row content">
                <nav class="navbar  navbar-fixed-top" role="navigation">

                    <div class="container-fluid" id="cabecalho">         
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
                                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                            </button>                
                        </div>
                        <a href="index.jsp" class="navbar-brand" id="navbar-collapse">Encontrehoteis.com</a>                    
                        <div class="navbar-header collapse navbar-collapse perfil navbar-right"> 
                            <ul class="nav navbar-nav ">
                                <li>      
                                    <article>
                                        <img class="img-circle" src="resource/imagens/clie.jpg" width="50px" height="50px"/>
                                    </article>
                                </li>                                    
                                <li>                                    
                                    <a  href="#"><label>Nome do cliente</label></a>                    
                                </li>

                            </ul>
                        </div>             
                    </div>
                </nav>

                <div class="col-sm-2 sidenav">                    
                    <ul class="nav nav-pills nav-stacked">
                        <li class="active"><a href="dadosdocliente.jsp">Dadas Pessoais</a></li>
                        <li><a href="listadereservas.jsp">Reservas</a></li>
                        <li><a href="formulariopagamento.jsp">Pagamentos</a></li>                        
                    </ul><br>

                </div>

                <div class="col-sm-9 sidenav">                     
                    <form id="formpaga" method="POST" action="" name="addPagamento">
                        <fieldset>
                            <legend>Informações do pagamento</legend>
                            <div class="form-group">
                                <label>Codigo da Reserva</label>
                                <input type="text" class="form-control" name="" value="" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Data do pagamento</label>
                                <input type="date" class="form-control" name="" value="" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Valor Total</label>
                                <input type="text" class="form-control" name="" value="" required=""/>
                            </div>
                        </fieldset>
                        <br/>
                        <fieldset>
                            <legend>Dados do Cartão</legend>
                            <div class="form-group">
                                <label>Nome Titular</label>
                                <input type="text" class="form-control" name="" value="" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Numero do Cartão</label>
                                <input type="text" class="form-control" name="" value="" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Data de validade do Cartão</label>
                                <input type="date" class="form-control" name="" value="" required=""/>
                            </div>
                            <div class="form-group">
                                <label>Codigo de segurança do Cartão</label>
                                <input type="text" class="form-control" name="" value="" required=""/>
                            </div>
                        </fieldset>

                        <div class="form-group">

                            <button type="submit" class="btn btn-success" name="" value="">Confirmar</button>
                        </div>

                    </form>
                </div>
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
