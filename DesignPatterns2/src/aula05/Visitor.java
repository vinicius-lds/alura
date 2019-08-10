package aula05;

import aula04.Numero;
import aula04.Soma;
import aula04.Subtracao;

public interface Visitor {
    void visitaSoma(Soma soma);
    void visitaSubtracao(Subtracao subtracao);
    void visitaNumero(Numero numero);
}
