package aula05;

import aula04.Numero;
import aula04.Soma;
import aula04.Subtracao;

public class ImpressoraPrefixada implements Visitor {
    @Override
    public void visitaSoma(Soma soma) {
        System.out.print("(+ ");
        soma.getDireita().aceita(this);
        System.out.print(" ");
        soma.getEsquerda().aceita(this);
        System.out.print(") ");
    }

    @Override
    public void visitaSubtracao(Subtracao subtracao) {
        System.out.print("(- ");
        subtracao.getDireita().aceita(this);
        System.out.print(" ");
        subtracao.getEsquerda().aceita(this);
        System.out.print(") ");
    }

    @Override
    public void visitaNumero(Numero numero) {
        System.out.print(numero.getNumero());
    }
}
