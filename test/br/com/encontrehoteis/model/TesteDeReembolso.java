package br.com.encontrehoteis.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sun.util.calendar.Gregorian;

public class TesteDeReembolso {

    public TesteDeReembolso() {
    }

//    @Test
//    public void testarCancelamentoDaResrvaComReembolso(){
//        System.out.println("Testando se o Cliente tem direito ao reembolso com dois dias antes da confirmação da reserva");
//        Reserva instance = new Reserva();
//        
//        int[] dataCancelamento = new int[3];
//        int[] dataReserva = new int[3];
//        
//        String dtCancelamento = "12/02/2017";
//        String dtReserva = "14/02/2017";
//        
//        String[] d = dtCancelamento.split("/");
//        String[] d2 = dtReserva.split("/");
//                
//        for(int i=0; i<3; i++) {
//            dataCancelamento[i] = Integer.parseInt(d[i]);
//            dataReserva[i] = Integer.parseInt(d2[i]);
//        }
//        
//        boolean expResult = true;
//        boolean result = false;
//
//       if(dataCancelamento[2] <= dataReserva[2]) {
//           if(dataCancelamento[0] <= dataReserva[0]) {
//               if(dataCancelamento[1] <= dataReserva[1]) {
//                 result = true;
//              }
//           }
//       }
//        
//        assertEquals(expResult, result);
//    }
//    
    @Test
    public void testarCancelamentoDaResrvaComReembolso1(){
        System.out.println("Testando se o Cliente tem direito ao reembolso "
                + "com cancelamento 2 dias depois da confirmação da reserva sendo"
                + "que o cliente não confirmou a reserva");
        Reserva instance = new Reserva();
        
        int[] dataCancelamento = new int[3];
        int[] dataReserva = new int[3];
        
        String dtCancelamento = "16/02/2017";
        String dtReserva = "14/02/2017";
        
        String[] d = dtCancelamento.split("/");
        String[] d2 = dtReserva.split("/");
                
        for(int i=0; i<3; i++) {
            dataCancelamento[i] = Integer.parseInt(d[i]);
            dataReserva[i] = Integer.parseInt(d2[i]);
        }
        
        boolean expResult = true;
        boolean result = false;

       if(dataCancelamento[2] <= dataReserva[2]) {
           if(dataCancelamento[0] <= dataReserva[0]) {
               if(dataCancelamento[1] <= dataReserva[1]) {
                 result = true;
              }
           }
       }
        
        assertEquals(expResult, result);
    }
    
//    @Test
//    public void descontoComPagamentoAvista() {
//        System.out.println("O cliente terá um desconto de 30% se fizer o "
//                + "pagamento na hora da reserva no site");
//        Pagamento instance = new Pagamento();
//        float desconto = instance.getValorTotalPagamento() - ((30 / 100) * 100);
//        Float expResult = new Float(desconto);
//
//        Float result = instance.getValorTotalPagamento();
//
//        assertEquals(expResult, result);
//
//    }
//
//    @Test
//    public void naoEfetuarReserva() {
//        
//    }
}
