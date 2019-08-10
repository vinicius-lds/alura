package aula04;

import aula05.Visitor;

public class Subtracao implements Expressao {

    private Expressao esquerda;
    private Expressao direita;

    public Subtracao(Expressao esquerda, Expressao direita) {
        this.esquerda = esquerda;
        this.direita = direita;

    }

    @Override
    public int avalia() {
        return esquerda.avalia() - direita.avalia();
    }

    @Override
    public void aceita(Visitor visitor) {
        visitor.visitaSubtracao(this);
    }

    public Expressao getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Expressao esquerda) {
        this.esquerda = esquerda;
    }

    public Expressao getDireita() {
        return direita;
    }

    public void setDireita(Expressao direita) {
        this.direita = direita;
    }
}
