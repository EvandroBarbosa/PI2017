<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="perfilCliente">
        <div class="col-sm-9 sidena">  
            <h1>Dados Pessoais</h1>
            <label>Nome: ${pes.nome}</label><br/>
            <label>Cpf: ${cliente.cpf}</label><br/>
            <label>Data Nascimento: ${cliente.dataNasc}</label><br/>
            <label>Sexo: ${sexo}</label><br/>
            <h1>Endereço</h1>
            <label>Estado: ${estado.nome}</label><br/>
            <label>Cidade: ${cidade.nome}</label><br/>
            <label>Bairro: ${endereco.bairro}</label><br/>
            <label>CEP: ${endereco.cep}</label><br/>
            <label>Número: ${endereco.numero}</label><br/>
            <label>Complemento: ${endereco.cep}</label><br/>
            
             <div class="col-md-4">
                <div class="btn">
                  <a class="btn btn-warning" href="/EncontreHoteis/cliente?action=editar&codigo=<c:out value="${cliente.cod_cliente}"/>">
                    <span class="glyphicon glyphicon-edit">Alterar</span>
                  </a>
                </div>
                <div class="btn">
                  <a class="btn btn-danger" href="/EncontreHoteis/cliente?action=deletar&codigo=<c:out value="${cliente.cod_cliente}"/>">
                    <span class="glyphicon glyphicon-trash">Excluir</span>
                  </a>
                </div>
            </div>
                    
        </div>
    </div>
</div>