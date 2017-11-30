
$(document).ready(function() {
    $('select[name=estado]').on('change', function() {
         $.ajax({
             type: 'GET',
             url: '/EncontreHoteis/cidades',
             data: 'cod_estado='+$('select[name=estado]').val(),
             statusCode: {
                404: function () { alert('Pagina nao encontrada') },
                500: function () { alert('Erro no servidor')} 
             },
             success: function(dados) {
                 var pegadados = dados.split(":");
                 
                 for(var i=0; i < pegadados.length; i++) {
                    var cod_cidade = pegadados[i].split("-")[0];
                    var nome_cidade = pegadados[i].split("-")[1];
                    
                    $('select[name=cidade]').append('<option value="'+cod_cidade+'">'+nome_cidade+'</option>')
                 }
             }
         }) 
    });
});

