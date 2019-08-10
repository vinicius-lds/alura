package aula04;

import aula05.Visitor;

public class RaizQuadrada implements Expressao {

    public Numero numero;

    public RaizQuadrada(Numero numero) {
        this.numero = numero;
    }

    @Override
    public int avalia() {
        return ((Double)Math.sqrt(numero.avalia())).intValue();
    }

    @Override
    public void aceita(Visitor visitor) {
        // TODO
    }

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero numero) {
        this.numero = numero;
    }
}
