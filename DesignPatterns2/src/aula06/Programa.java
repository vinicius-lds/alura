package aula06;

import java.util.Calendar;

public class Programa {
    public static void main(String[] args) {
        // Bridge - porque se comunica com outros sistemas
        Mapa mapa = new GoogleMaps();
        String conteudo = mapa.devolveMapa("Rua Vergueiro, 3185");

        // Adapter - porque adapta um código do meu sistema com uma interface nova
        Relogio relogio = new RelogioDoSistema();
        Calendar hoje = relogio.hoje();

        // Difereça é somente semantica
    }
}
