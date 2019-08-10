package aula04;

import aula05.Visitor;

public class Numero implements Expressao {

    private int numero;
    public Numero(int numero) {
        this.numero = numero;
    }

    @Override
    public int avalia() {
        return numero;
    }

    @Override
    public void aceita(Visitor visitor) {
        visitor.visitaNumero(this);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
