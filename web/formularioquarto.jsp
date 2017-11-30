
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
    <div class="formu">
        <% if (request.getAttribute("fotos") == null) { %>
        <form action="/EncontreHoteis/salvar1" method="post" enctype="multipart/form-data" >           
            <fieldset> 
                <legend class="legend">Fotos do Quarto</legend>
                <% if (request.getAttribute("foto1") == null) { %>
                <div class="input-group"> 
                    <label>Selecione 1º  Foto </label>
                    <input type="file" name="fot1" accept="image/jpeg; image/gif; image/bmp; image/png" value="">
                </div>
                <br>
                <% } %>
                <% if (request.getAttribute("foto2") == null) { %>
                <div class="input-group"> 
                    <label>Selecione 2º  Foto </label>
                    <input type="file" name="fot2" accept="image/jpeg; image/gif; image/bmp; image/png" value="">
                </div>
                <br>
                <% } %>
                <% if (request.getAttribute("foto3") == null) { %>
                <div class="input-group"> 
                    <label>Selecione 3º  Foto </label>
                    <input type="file" name="fot3" accept="image/jpeg; image/gif; image/bmp; image/png" value="">
                    <br>
                </div>
                <% } %>
                <br>
                <input type="submit" value="Salvar"/> 
            </fieldset>

        </form>
        <% }%>
        <br/>
        <form id="quarto" method="POST" action="/EncontreHoteis/quarto" name="addQuarto">
            <fieldset>
                <legend class="legend">Dados do Quarto</legend>

                <input type="hidden" name="salvar" value="ok"/>
                <input type="hidden" name="foto1" value="${foto1}"/>
                <input type="hidden" name="foto2" value="${foto2}"/>
                <input type="hidden" name="foto3" value="${foto3}"/>
                <input type="hidden" name="codigo" value="${quarto.codigo}"/>

                <div class="form-group has-feedback">
                    <label for="tipoq">Tipo</label>
                    <select class="form-control" id="tipoq" name="tipo">
                        <c:choose>
                            <c:when test="${quarto.tipo eq 'Standard'}">
                                <option>Selecione um tipo</option>
                                <option value="Standard" selected="true">Standard</option>
                                <option value="Premium">Premium</option>
                                <option value="Golden">Golden</option>
                            </c:when>
                            <c:when test="${quarto.tipo eq 'Premium'}">
                                <option>Selecione um tipo</option>
                                <option value="Standard">Standard</option>
                                <option value="Premium" selected="true">Premium</option>
                                <option value="Golden">Golden</option>
                            </c:when>
                            <c:otherwise>
                                <option value="Standard">Standard</option>
                                <option value="Premium">Premium</option>
                                <option value="Golden" selected="true">Golden</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
                <div class="form-group has-feedback">
                    <label for="numeroq">Numero</label>
                    <input type="text" id="numeroq" class="form-control" maxlength="3"
                           name="numero" value="${quarto.numero}" required=""/>
                </div>
                <div class="form-group">
                    <label for="capacidadeq">Capacidade por quarto</label>
                    <select class="form-control" id="capacidadeq" name="capacidade">
                        <c:choose>
                            <c:when test="${quarto.capacidade == 1}">
                                <option value="1" selected="true">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                            </c:when>
                            <c:when test="${quarto.capacidade == 2}">
                                <option value="1">1</option>
                                <option value="2" selected="true">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                            </c:when>
                            <c:when test="${quarto.capacidade == 3}">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3" selected="true">3</option>
                                <option value="4">4</option>
                            </c:when>
                            <c:otherwise>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4" selected="true">4</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
                <div class="form-group has-feedback">
                    <label for="status">Status</label><br/>
                    <c:choose>
                        <c:when test="${quarto.status eq true}">
                            <input type="radio"  name="status" value="false"/>  Reservado
                            <input type="radio"  name="status" value="true" checked="true"/>  Disponível
                        </c:when>
                        <c:otherwise>
                            <input type="radio"  name="status" value="false" checked="true"/>  Reservado
                            <input type="radio"  name="status" value="true"/>  Disponível
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="form-group has-feedback">
                    <label for="descricao">Descrição</label><br/>
                    <textarea class="form-control" id="descricao" rows="6" name="descricao"
                              placeholder="De uma descrição para o quarto">${quarto.descricao}</textarea>
                </div>
                <div class="form-group has-feedback">
                    <label for="valorD">Valor da Diária</label><br/>
                    <input type="text" class="form-control" id="valorD" name="valorDiaria" maxlength="10"
                           value="${quarto.valorDiaria}" onkeypress='return SomenteNumero(event)' required=""/>
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
        $('#quarto').bootstrapValidator({
            fields: {

                validators: {
                    callback: {
                        message: 'Preencha Corretamente '
                    }
                }

            }
        });
    });
</script>

