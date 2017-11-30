<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Quarto para reservas</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="resource/homeHotel.css" rel="stylesheet" type="text/css"/>
        <link href="resource/homepage.css" rel="stylesheet" type="text/css"/>
        <link href="resource/estilo.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="resource/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resource/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="resource/js/bootstrap.js" type="text/javascript"></script>
    </head>
    <body>
        <nav class="navbar  navbar-fixed-top" role="navigation">

            <div class="container-fluid " id="cabe">         

                <div class="navbar-header ">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
                        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
                    </button> 
                    <a href="index.jsp" class="navbar-brand" id="cabeHotel">Encontrehoteis.com</a>
                </div>             
                <div class="collapse navbar-collapse menu_lista" id="navbar-collapse">           
                    <ul class="nav navbar-nav navbar-right" role="tablist">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown">
                                MINHA CONTA <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">  
                                <li><a href="/EncontreHoteis/login">Efetuar login</a></li>
                                <li><a href="/EncontreHoteis/cliente?action=adicionar">Cadastre-se</a></li>
                            </ul>  
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <section id="home">    

            <form method="GET">
                <div class="input-group "  id="pesquisa">                            
                    <input class="form-control input-lg" type="search" placeholder="Ex: Senador Canedo" />                            
                    <span class="input-group-btn ">
                        <button type="button" class="btn btn-primary btn-lg ">Buscar</button>
                    </span>
                </div>
                <div class="input-group data">
                    <span class="glyphicon glyphicon-calendar input-group-addon"></span>
                    <input type="date" class="form-control input-lg" name="data" placeholder="Entrada"/>                            

                    <span class="glyphicon glyphicon-calendar input-group-addon"></span>
                    <input type="date" class="form-control input-lg" name="data" placeholder="Saida"/>                            

                </div>
                <div class="input-group data" id="selecione">
                    <select class="form-control input-lg">
                        <option>Quarto Indvidual</option>
                        <option>Quarto Duplo</option>
                        <option>Quarto Familia</option>
                    </select>

                </div>
                <br>
            </form>

        </section>

        <section class="hoteis" id=""hoteis>
            <div class="container">
                <!--  <div class="hoteis__menu col-md-3">
                      <ul>
                          <li>first</li>
                          <li>second</li>
                          <li>third</li>
                      </ul>
                  </div> -->
                <div class="hoteis__conteudo col-md-12">
                    <ul class="lista-hoteis">

                        <li class="hotel hotel-item">

                            <div class="hotel hotel-item clearfix">

                                <div class="media hotel-imagem">

                                    <span class="ratio"></span>
                                    <img class="content -photo" style="background-image: url(resource/imagens/home.jpg)" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=" itemprop="image">
                                </div>
                                <div class="conteudo container">
                                    <div class="col-md-8">
                                        <p><span>&lrm; Palacio Zone </span></p>
                                    </div>
                                    <div class="col-md-2">
                                        <span>&lrm; R$: 660.00</span>
                                    </div>
                                    <div class="col-md-2" id="conteudo_botao">
                                        <a class="btn btn-success btn-block" href="siteemconstrucao.jsp">Reservar</a>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="hotel hotel-item">

                            <div class="hotel hotel-item clearfix">

                                <div class="media hotel-imagem">

                                    <span class="ratio"></span>
                                    <img class="content -photo" style="background-image: url(resource/imagens/hotelpraia.jpg)" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=" itemprop="image">
                                </div>
                                <div class="conteudo container">
                                    <div class="col-md-8">
                                        <p>  Quisque eget sem sed dui gravida euismod sed vitae massa.</p>
                                    </div>
                                    <div class="col-md-2">
                                        sadfasf <br/>
                                        Quisque eget 
                                    </div>
                                    <div class="col-md-2 botao">
                                        <button class="btn btn-success btn-block">Reservar</button>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="hotel hotel-item">

                            <div class="hotel hotel-item clearfix">

                                <div class="media hotel-imagem">

                                    <span class="ratio"></span>
                                    <img class="content -photo" style="background-image: url(resource/imagens/hotel2.jpg)" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=" itemprop="image">
                                </div>
                                <div class="conteudo container">
                                    <div class="col-md-8">
                                        <p>  Quisque eget sem sed dui gravida euismod sed vitae massa.</p>
                                    </div>
                                    <div class="col-md-2">
                                        sadfasf <br/>
                                        Quisque eget 
                                    </div>
                                    <div class="col-md-2 botao">
                                        <button class="btn btn-success btn-block">Reservar</button>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="hotel hotel-item">

                            <div class="hotel hotel-item clearfix">

                                <div class="media hotel-imagem">

                                    <span class="ratio"></span>
                                    <img class="content -photo" style="background-image: url(resource/imagens/hotel3.jpg)" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=" itemprop="image">
                                </div>
                                <div class="conteudo container">
                                    <div class="col-md-8">
                                        <p>  Quisque eget sem sed dui gravida euismod sed vitae massa.</p>
                                    </div>
                                    <div class="col-md-2">
                                        sadfasf <br/>
                                        Quisque eget 
                                    </div>
                                    <div class="col-md-2 botao">
                                        <button class="btn btn-success btn-block">Reservar</button>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="hotel hotel-item">

                            <div class="hotel hotel-item clearfix">

                                <div class="media hotel-imagem">

                                    <span class="ratio"></span>
                                    <img class="content -photo" style="background-image: url(resource/imagens/hotel4.jpg)" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=" itemprop="image">
                                </div>
                                <div class="conteudo container">
                                    <div class="col-md-8">
                                        <p>  Quisque eget sem sed dui gravida euismod sed vitae massa.</p>
                                    </div>
                                    <div class="col-md-2">
                                        sadfasf <br/>
                                        Quisque eget 
                                    </div>
                                    <div class="col-md-2 botao">
                                        <button class="btn btn-success btn-block">Reservar</button>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </section>



    </body>
</html>