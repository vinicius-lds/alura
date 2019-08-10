package aula02;

import java.util.HashMap;
import java.util.Map;

public class NotasMusicaisFactory {

    private static final Map<String, Nota> notas = new HashMap<>();

    private NotasMusicaisFactory() {}

    private static Nota get(String nota) {
        switch (nota) {
            case "do":
                return new Do();
            case "re":
                return new Re();
            case "mi":
                return new Mi();
            case "fa":
                return new Fa();
            case "sol":
                return new Sol();
            case "la":
                return new La();
            case "si":
                return new Si();
            case "dosustenido":
                return new DoSustenido();
            case "resustenido":
                return new ReSustenido();
            default:
                throw new IllegalArgumentException("Nota Invalida");
        }
    }

    private static Nota getInstance(String nota) {
        if (!notas.containsKey(nota)) {
            notas.put(nota, get(nota));
        }
        return notas.get(nota);
    }

}
