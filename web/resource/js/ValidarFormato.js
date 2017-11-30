function formatar(mascara, documento) {

var i = documento.value.length;
        var saida = mascara.substring(0, 1);
        var texto = mascara.substring(i)

        if (texto.substring(0, 1) != saida) {
documento.value += texto.substring(0, 1);
}
}

function validacpf(value){

var cpf = value.replace(/[^\d]+/g, '');
        if (cpf == '')
        return false;
        if (cpf.length != 11)
        return false;
        // testa se os 11 digitos são iguais, que não pode.
        var valido = 0;
        for (i = 1; i < 11; i++) {
if (cpf.charAt(0) != cpf.charAt(i))
        valido = 1;
}
if (valido == 0)
        return false;
        var aux = 0;
        for (i = 0; i < 9; i ++)
        aux += parseInt(cpf.charAt(i)) * (10 - i);
        var check = 11 - (aux % 11);
        if (check == 10 || check == 11)
        check = 0;
        if (check != parseInt(cpf.charAt(9)))
        return false;
        aux = 0;
        for (i = 0; i < 10; i ++)
        aux += parseInt(cpf.charAt(i)) * (11 - i);
        check = 11 - (aux % 11);
        if (check == 10 || check == 11)
        check = 0;
        if (check != parseInt(cpf.charAt(10)))
        return false;
        return true;
        }
/*VALIDANDO O CNPJ*/

//adiciona mascara de cnpj

//valida o CNPJ digitado
//function ValidarCNPJ(value) {
//    var cnpj = value.replace(/[^\d]+/g, '');
//    var valida = new Array(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
//    var dig1 = new Number;
//    var dig2 = new Number;
//
//    var digito = new Number(eval(cnpj.charAt(12) + cnpj.charAt(13)));
//
//    for (i = 0; i < valida.length; i++) {
//        dig1 += (i > 0 ? (cnpj.charAt(i - 1) * valida[i]) : 0);
//        dig2 += cnpj.charAt(i) * valida[i];
//    }
//    dig1 = (((dig1 % 11) < 2) ? 0 : (11 - (dig1 % 11)));
//    dig2 = (((dig2 % 11) < 2) ? 0 : (11 - (dig2 % 11)));
//
//    if (((dig1 * 10) + dig2) != digito)
//        alert('CNPJ Invalido!');
//
//}


//outra forma de validar cnpj
function validarCNPJ(value) {

cnpj = value.replace(/[^\d]+/g, '');
        if (cnpj == '') return false;
        if (cnpj.length != 14)
        return false;
        // Elimina CNPJs invalidos conhecidos
        if (cnpj == "00000000000000" ||
                cnpj == "11111111111111" ||
                cnpj == "22222222222222" ||
                cnpj == "33333333333333" ||
                cnpj == "44444444444444" ||
                cnpj == "55555555555555" ||
                cnpj == "66666666666666" ||
                cnpj == "77777777777777" ||
                cnpj == "88888888888888" ||
                cnpj == "99999999999999")
        return false;
        // Valida DVs
        tamanho = cnpj.length - 2
        numeros = cnpj.substring(0, tamanho);
        digitos = cnpj.substring(tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--) {
soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
        pos = 9;
}
resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(0))
        return false;
        tamanho = tamanho + 1;
        numeros = cnpj.substring(0, tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--) {
soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
        pos = 9;
}
resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(1))
        return false;
        return true;
        }

//valida campos que é so numero
function SomenteNumero(e){
var tecla = (window.event)?event.keyCode:e.which;
        if ((tecla > 47 && tecla < 58)) return true;
        else{
        if (tecla == 8 || tecla == 0) return true;
                else  return false;
        }
}

