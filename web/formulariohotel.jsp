<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
    <div class="formu">
        <% if (request.getAttribute("foto") == null) { %>
        <form action="/EncontreHoteis/salvar" method="post" enctype="multipart/form-data" > 
            <fieldset> 
                <legend class="legend">Imagem do Hotel</legend>
                <div class="input-group"> 
                    <label>Selecione uma Foto </label>
                    <input type="file" name="foto" accept="image/jpeg; image/gif; image/bmp; image/png" value="$">
                    <br>
                    <input type="submit" value="Salvar imagem"/> 
                </div>
            </fieldset>
        </form>
        <% } %>
        <br/>
        <form id="cpf_form" method="POST" action="/EncontreHoteis/hotel" name="addHotel">
            <fieldset>
                <legend class="legend">Dados do Hotel</legend>

                <input type="hidden" name="foto" value="${foto}"/>
                <input type="hidden" name="codigo" value="${hotel.codigo}"/>
                <input type="hidden" name="cod_endereco" value="${endereco.codigo}"/>

                <div class="form-group has-feedback">
                    <label>Nome do Hotel</label>
                    <input type="text" class="form-control " maxlength="50" name="nome" 
                           value="<c:out value="${hotel.nome}"/>" required=""/>
                </div>
                <% if (request.getAttribute("razao") == null) { %>
                <div class="form-group has-feedback">
                    <label>Razão Social</label>
                    <input type="text" class="form-control " maxlength="250" name="razaoSocial"
                           value="<c:out value="${hotel.razaoSocial}"/>" required=""/>
                </div>
                <% }%>
                <div class="form-group has-feedback">
                    <label>Descrição do Hotel</label>
                    <input type="text" class="form-control " maxlength="50" name="descricao"
                           value="<c:out value="${hotel.descricao}"/>" required=""/>
                </div>
                <c:if test="${hotel.cnpj == null}">
                  <div class="form-group has-feedback">
                    <label>CNPJ</label>
                    <input type="text" class="form-control" onKeyPress=" return formatar('##.###.###/####-##', this);" maxlength="18" name="cnpj" 
                           value="<c:out value="${hotel.cnpj}"/>" required=""/>
                  </div>
                </c:if>
                <div class="form-group has-feedback">
                    <label for="email">Email</label>
                    <input type="email" class="form-control " id="email" maxlength="50" name="email"
                           value="<c:out value="${hotel.email}"/>" required=""/>
                </div>
                <div class="form-group has-feedback">
                    <label for="tele">Telefone</label>
                    <input type="tel" class="form-control " id="tele" maxlength="50" name="telefone"
                           value="<c:out value="${hotel.telefone}"/>" onkeypress='return SomenteNumero(event)' required=""/>
                </div>
            </fieldset>
            <br />
            <fieldset>
                <legend class="legend">Endereço</legend>
                <div class="form-group has-feedback">
                    <label>Numero</label>
                    <input type="text" class="form-control " maxlength="5" name="numero"
                           value="<c:out value="${endereco.numero}"/>" onkeypress='return SomenteNumero(event)' required=""/>
                </div>
                <div class="form-group has-feedback">
                    <label>Logradouro</label>
                    <input type="text" class="form-control " maxlength="20" name="logradouro"
                           value="<c:out value="${endereco.logradouro}"/>" required=""/>
                </div>
                <div class="form-group has-feedback">
                    <label>CEP</label>
                    <input type="text" class="form-control " maxlength="8" name="cep"
                           value="<c:out value="${endereco.cep}"/>" onkeypress='return SomenteNumero(event)' required=""/>
                </div>
                <div class="form-group has-feedback">
                    <label>Complemento</label>
                    <input type="text" class="form-control " maxlength="50" name="complemento"
                           value="<c:out value="${endereco.complemento}"/>" required=""/>
                </div>
                <div class="form-group has-feedback">
                    <label>Bairro</label>
                    <input type="text" class="form-control " maxlength="20" name="bairro"
                           value="<c:out value="${endereco.bairro}"/>" required=""/>
                </div>

                <div class="form-group has-feedback">
                    <label>Estado</label>
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
                    <label>Cidade</label>
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
            <div class="btn">                                
                <button type="submit" class="btn btn-success btnSalvar" >Salvar</button>
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
                cnpj: {
                    validators: {
                        callback: {
                            message: 'Preencha Corretamente ',
                            callback: function (value) {
                                return validarCNPJ(value);
                            }
                        }
                    }
                }
            }
        });

    });
</script>
