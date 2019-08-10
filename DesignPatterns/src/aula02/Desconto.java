package aula02;

import common.Orcamento;

public abstract class Desconto {

    private Desconto proximo;

    public abstract double calculaDesconto(Orcamento orcamento);

    public double chamarProximo(Orcamento orcamento) {
        if (this.proximo != null) {
            return this.proximo.calculaDesconto(orcamento);
        } else {
            return 0;
        }
    }

    public void setProximo(Desconto desconto) {
        this.proximo = desconto;
    }

}
