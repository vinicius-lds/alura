package aula02;

import java.util.Arrays;
import java.util.List;

public class Programa {

    public static void main(String[] args) {
        NotasMusicais notasMusicais = new NotasMusicais();

        List<Nota> musica = Arrays.asList(
                notasMusicais.pega("do"),
                notasMusicais.pega("re"),
                notasMusicais.pega("mi"),
                notasMusicais.pega("fa"),
                notasMusicais.pega("fa"),
                notasMusicais.pega("fa"),
                notasMusicais.pega("sol"),
                notasMusicais.pega("la")
        );
    }

}
