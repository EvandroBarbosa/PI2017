<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
        <script src="resource/js/ValidarFormato.js" type="text/javascript"></script>
        <script src="resource/js/bootstrap.js" type="text/javascript"></script>
        <link href="resource/homeHotel.css" rel="stylesheet" type="text/css"/>
        <script src="resource/js/bootstrapValidator.js" type="text/javascript"></script>
        <script src="resource/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="resource/js/bootstrapValidator.min.js" type="text/javascript"></script>
        <script src="resource/js/validator.min.js" type="text/javascript"></script>
        <script src="resource/js/ajax.js" type="text/javascript"></script>
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
                <form id="cpf_form" method="POST" action="/EncontreHoteis/cliente" name="addCliente">
                    <fieldset>
                        <legend class="legend">Dados pessoais</legend>

                        <input type="hidden" name="codigo" value="${cliente.cod_cliente}"/>
                        <input type="hidden" name="cod_pessoa" value="${pes.codigo}"/>
                        <input type="hidden" name="cod_endereco" value="${endereco.codigo}"/>

                        <div class="form-group has-feedback">
                            <label for="nomeL">Nome</label>
                            <input type="text" id="nomeL" class="form-control " maxlength="50" name="nome"
                                   value="<c:out value="${cliente.nome}"/>" required=""/>
                        </div>
                        <c:if test="${cliente.cpf eq null}">
                            <div class="form-group">
                                <label for="cpf">CPF</label>
                                <input type="text" id="cpf" onKeyPress=" return formatar('###.###.###-##', this);" 
                                       class="form-control" name="cpf" maxlength="14" value="<c:out value="${cliente.cpf}"/>" required=""/>
                            </div>
                        </c:if>

                        <div class="form-group has-feedback">
                            <label>Data Nascimento</label>
                            <input type="date" class="form-control" name="data"
                                   value="<fmt:formatDate pattern="yyyy/MM/dd" value="${cliente.dataNasc}"/>" required=""/>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="sexos">Sexo</label><br/>
                            <c:choose>
                                <c:when test="${cliente.sexo eq 'M'}">
                                    <input type="radio" id="sexos" class="input-sexo" name="sexo"
                                           checked="true" value="M"/>  Masculino
                                    <input type="radio" id="sexos" class="input-sexo" name="sexo"
                                           value="F"/>  Feminino
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" id="sexos" class="input-sexo" name="sexo"
                                           value="M"/>  Masculino
                                    <input type="radio" id="sexos" class="input-sexo" name="sexo"
                                           checked="true" value="F"/>  Feminino
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend class="legend">Endereço</legend>
                        <div class="form-group has-feedback">
                            <label for="numeroc">Numero</label>
                            <input type="text" id="numeroc" class="form-control" maxlength="5" name="numero"
                                   value="<c:out value="${endereco.numero}"/>" onkeypress='return SomenteNumero(event)' required=""/>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="log">Logradouro</label>
                            <input type="text" id="log" class="form-control" maxlength="20" name="logradouro"
                                   value="<c:out value="${endereco.logradouro}"/>" required=""/>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="cep">CEP</label>
                            <input type="text" id="cep" class="form-control" maxlength="8" name="cep"
                                   value="<c:out value="${endereco.cep}"/>" onkeypress='return SomenteNumero(event)' required=""/>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="comp">Complemento</label>
                            <input type="text" id="comp" class="form-control" maxlength="50" name="complemento"
                                   value="<c:out value="${endereco.complemento}"/>" required=""/>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="bairro">Bairro</label>
                            <input type="text" id="bairro" class="form-control" maxlength="20" name="bairro"
                                   value="<c:out value="${endereco.bairro}"/>" required=""/>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="estado">Estado</label>
                            <select class="form-control" name="estado">
                                <option>Selecione</option>
                                <c:forEach items="${estados}" var="estado">
                                    <option value="<c:out value="${estado.codigo}"/>" 
                                            ${cod_estado ==  estado.codigo ? 'selected' : ''}>
                                        <c:out value="${estado.nome}"/>                                      
                                    </option>
                                </c:forEach>
                            </select>                               
                        </div>
                        <div class="form-group has-feedback">
                            <label for="cidade">Cidade</label>
                            <select class="form-control " name="cidade">
                                <option>Selecione</option>
                                <c:forEach var="cidade" items="${cidades}">
                                    <option value="<c:out value="${cidade.codigo}"/>"
                                            ${cod_cidade ==  cidade.codigo ? 'selected' : ''}>
                                        <c:out value="${cidade.nome}"/>                                      
                                    </option>
                                </c:forEach>
                            </select> 
                        </div>
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend class="legend">Usuário Para Acesso</legend>
                        <div class="form-group has-feedback">
                            <label for="login">Login</label>
                            <input type="text" id="login" class="form-control" maxlength="30"
                                   name="usuario" value="${pes.usuario}" required=""/>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="senhas">Senha</label>
                            <input type="password" id="senhas" class="form-control" minlength="6"
                                   maxlength="10" name="senha" value="${pes.senha}" required=""/>
                        </div>
                    </fieldset>
                    <br/>
                    <div class="btn">                                
                        <button type="submit" class="btn btn-success btnSalvar">Salvar</button>
                    </div>

                    <div class="btn">                                
                        <button type="reset" class="btn btn-danger btnCancelar">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>             
        <script>

            $(document).ready(function () {
                $('#cpf_form').bootstrapValidator({
                    fields: {
                        cpf: {
                            validators: {
                                callback: {
                                    message: 'Preencha Corretamente ',
                                    callback: function (value) {
                                        return validacpf(value);
                                    }

                                }
                            }
                        }
                    }
                });

            });
        </script>
        <section id="secrodape">
            <nav class="navbar navbar-nav navbar-fixed-bottom ">
                <div class="rodape">
                    <p id="rodape">Copyright &copy; 2017 | EncontreHotéis.com</p>
                </div>
            </nav> 
        </section>
    </body>
</html>
