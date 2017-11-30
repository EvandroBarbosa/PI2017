<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="table-responsive" id="tabela">
        <table class="table table-striped">
            <thead>
                <tr>
                    <td class="text-center">ID</td>
                    <td class="text-center">Nº do Quarto</td>
                    <td class="text-center">Capacidade</td> 
                    <td class="text-center">Tipo</td> 
                    <td class="text-center">Status</td>
                    <td class="text-center">Valor da Diária</td> 
                    <td colspan="2">Ações</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${quartos}" var="quarto">
                    <tr>
                        <td><c:out value="${quarto.codigo}"/></td>
                        <td><c:out value="${quarto.numero}"/></td>
                        <td><c:out value="${quarto.capacidade}"/></td> 
                        <td><c:out value="${quarto.tipo}"/></td> 
                        <td>${quarto.status eq true ? 'Disponível' : 'Reservado'}</td>
                        <td><c:out value="${quarto.valorDiaria}"/></td> 
                        <td><a class="" href="/EncontreHoteis/quarto?action=editar&codigo=<c:out value="${quarto.codigo}"/>"><span class="glyphicon glyphicon-edit">Editar</span></a></td>
                        <td><a class="" href="/EncontreHoteis/quarto?action=deletar&codigo=<c:out value="${quarto.codigo}"/>"><span class="glyphicon glyphicon-trash">Deletar</span></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br> <br>
        <p> <a class="btn btn-success" href="/EncontreHoteis/quarto?action=adicionar">
                <span class="glyphicon glyphicon-plus"> </span>  Incluir Quarto</a>
        </p>
    </div>
</div>