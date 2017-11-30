<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="table-responsive" id="tabela">
        <table class="table table-striped">
            <thead>
                <tr>
                    <td class="text-center">ID</td>
                    <td class="text-center">Nome</td>
                    <td class="text-center">Descrição</td>                            
                    <td class="text-center">E-mail</td>
                    <td class="text-center">Telefone</td>                            
                    <td class="text-center">Razão Social</td>                            
                    <td colspan="2">Ações</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${hoteis}" var="listarHotel">
                    <tr>
                        <td><c:out value="${listarHotel.codigo}"/></td>
                        <td><c:out value="${listarHotel.nome}"/></td>
                        <td><c:out value="${listarHotel.descricao}"/></td>                                
                        <td><c:out value="${listarHotel.email}"/></td>
                        <td><c:out value="${listarHotel.telefone}"/></td>                                
                        <td><c:out value="${listarHotel.razaoSocial}"/></td>                                
                        <td><a class="" href="/EncontreHoteis/hotel?action=editar&codigo=<c:out value="${listarHotel.codigo}"/>"><span class="glyphicon glyphicon-edit">Editar</span></a></td>
                        <td><a class="" href="/EncontreHoteis/hotel?action=deletar&codigo=<c:out value="${listarHotel.codigo}"/>"><span class="glyphicon glyphicon-trash">Deletar</span></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br> <br>
        <p> <a class="btn btn-success" href="/EncontreHoteis/hotel?action=adicionarhotel">
                <span class="glyphicon glyphicon-plus"> </span>  Incluir Hotel</a>
        </p>
    </div>
</div>
