<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="row">
        <div class="formu">
            <form id="cpf_form" method="POST" action="/EncontreHoteis/usuario" name="addUsuario">
                <fieldset>
                    <legend class="legend">Dados pessoais</legend>

                    <input type="hidden" name="codigo" value="${usuario.cod_usuario}"/>
                    <input type="hidden" name="cod_pessoa" value="${pes.codigo}"/>
                    <input type="hidden" name="cod_endereco" value="${endereco.codigo}"/>

                    <div class="form-group has-feedback">
                        <label for="nomeL">Nome</label>
                        <input type="text" id="nomeL" class="form-control " maxlength="50" name="nome"
                               value="<c:out value="${usuario.nome}"/>" required=""/>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="cargo">Cargo</label>
                        <input type="text" id="nomeS" class="form-control" maxlength="11" name="cargo" 
                               value="<c:out value="${usuario.cargo}"/>" required=""/>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="hotel">Hotel</label>
                        <select class="form-control " name="cod_hotel">
                            <option>Selecione</option>
                            <c:forEach var="hotel" items="${hoteis}">
                                <option value="<c:out value="${hotel.codigo}"/>"
                                        ${cod_hotel ==  hotel.codigo ? 'selected' : ''}>
                                    <c:out value="${hotel.nome}"/>                                      
                                </option>
                            </c:forEach>
                        </select> 
                    </div>
                </fieldset>
                <fieldset>
                    <legend class="legend">Endereço</legend>
                    <div class="form-group has-feedback">
                        <label for="numeroc">Numero</label>
                        <input type="text" id="numeroc" class="form-control" maxlength="5" name="numero"
                               value="<c:out value="${endereco.numero}"/>"  onkeypress='return SomenteNumero(event)' required=""/>
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
</div>

<script>

    $(document).ready(function () {
        $('#cpf_form').bootstrapValidator({
            fields: {
                cpf: {
                    validators: {
                        callback: {
                            message: 'Preencha Corretamente '
                        }
                    }
                }
            }
        });

    });
</script>
