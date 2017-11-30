<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>EncontreHotéis.com</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="resource/login.css" rel="stylesheet" type="text/css"/>
        <link href="resource/cabecalhoRodape.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>

        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active">
                    <img src="resource/imagens/hotel.jpg" alt="Los Angeles" id="imageCarousel">
                </div>

                <div class="item">
                    <img src="resource/imagens/hotel1_1.jpg" alt="Chicago" id="imageCarousel">
                </div>

                <div class="item">
                    <img src="resource/imagens/cliente4.jpg" alt="New york" id="imageCarousel">
                </div>
            </div>   

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Anterior</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Proximo</span>
            </a>
        </div>
        <div class="container " >   
            <form method="POST" action="/EncontreHoteis/login" id="Formusuario">
                <input type="hidden" name="url" value="${url}"/>
                
                <div class="input-group">
                    <label class="" for="login">Usuário</label>
                    <input class="form-control input-lg" type="text" id="login" name="usuario" placeholder="Digite seu usuário"/>
                </div>
                <div class="input-group">                    
                    <label class="" for="senha">Senha</label>
                    <input class="form-control input-lg" type="password" id="senha" name="senha" placeholder="Digite sua senha"/>
                </div>
                <div class="btn btn-group">
                    <button class="btn btn-lg" id="logar" type="submit" value="">Logar</button>
                </div>
            </form>
        </div>
       
        <section id="secrodape">
           <nav class="navbar navbar-nav navbar-fixed-bottom ">
              <div class="rodape">
                <p id="rodape">Copyright &copy; 2017 | EncontreHotéis.com</p>
              </div>
           </nav> 
        </section>

        <!-- Scripts -->
        <script src="resource/js/jquery-3.2.1.min.js"></script>
        <script src="resource/js/bootstrap.min.js"></script>
        <script src="resource/js/bootstrap.js"></script>
    </body>
</html>
