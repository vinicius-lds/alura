package aula03;

import java.util.Calendar;

public class Programa {

    public static void main(String[] args) {


        Contrato contrato = new Contrato(Calendar.getInstance(), "Mauricio", TipoContrato.NOVO);
        System.out.println(contrato.getTipo());

        contrato.avanca();
        System.out.println(contrato.getTipo());

        contrato.avanca();
        System.out.println(contrato.getTipo());

        contrato.avanca();
        System.out.println(contrato.getTipo());

        contrato.restaura(-1);
        System.out.println(contrato.getTipo());
    }
}
