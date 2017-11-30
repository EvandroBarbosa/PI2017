<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="table-responsive" id="tabela">
        <table class="table table-striped">
            <thead>
                <tr>
                    <td class="text-center">ID</td>
                    <td class="text-center">Nome</td>
                    <td class="text-center">Cargo</td> 
                    <td colspan="2">Ações</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${usuarios}" var="usuario">
                <tr>
                    <td><c:out value="${usuario.cod_usuario}"/></td>
                <td><c:out value="${usuario.nome}"/></td>
                <td><c:out value="${usuario.cargo}"/></td> 
                <td><a class="" href="/EncontreHoteis/usuario?action=editar&codigo=<c:out value="${usuario.cod_usuario}"/>"><span class="glyphicon glyphicon-edit">Editar</span></a></td>
                <td><a class="" href="/EncontreHoteis/usuario?action=deletar&codigo=<c:out value="${usuario.cod_usuario}"/>"><span class="glyphicon glyphicon-trash">Deletar</span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <br> <br>
        <p> <a class="btn btn-success" href="/EncontreHoteis/usuario?action=adicionar">
                <span class="glyphicon glyphicon-plus"> </span>  Incluir Usuario</a>
        </p>
    </div>
</div>