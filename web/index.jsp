<!DOCTYPE html>
<html>
    <head>
        <title>Encontre Hoteis</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/css/bootstrap.min.css" media="all" />
        <link href="resource/estilo.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="resource/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resource/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="resource/js/bootstrap.js" type="text/javascript"></script>
    </head>
    <body>
        <nav class="navbar navbar-right navbar-fixed-top" role="navigation">

    <div class="container-fluid" id="cabecalho">         

        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
            </button>                
        </div>

        <div class="collapse navbar-collapse " id="navbar-collapse">           
            <ul class="nav navbar-nav navbar-right" role="tablist">
                <li class="dropdown">
                    <a class="dropdown-toggle" id="login"data-toggle="dropdown">
                        MINHA CONTA <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">  
                        <li><a href="/EncontreHoteis/login">Efetuar login</a></li>
                        <li><a href="/EncontreHoteis/cliente?action=adicionar">Cadastre-se</a></li>
                        <li><a href="/EncontreHoteis/indique">Indique-me</a></li>
                    </ul>  
                </li>
            </ul>
        </div>
    </div>
</nav>
        <div class="container" id="paginaP">

            <form method="GET" name="CidadeCtr">                       

                <img class="img-responsive " id="image" src="resource/imagens/LogoEncontreHoteis-com.png" alt="Logo do site"/>

                <div class="input-group" id="formP">                            
                    <input class="form-control input-lg" type="search" placeholder="Ex: Senador Canedo" />                            
                    <span class="input-group-btn ">
                        <a class="btn btn-primary btn-lg " href="listadequartoparareserva.jsp">Buscar</a>
                    </span>
                </div>
            </form>
            
        </div>
        
    </body>
</html>