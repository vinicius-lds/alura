package aula04;

import common.Orcamento;

public class ISS extends Imposto {

    public ISS(Imposto outroImposto) {
        super(outroImposto);
    }

    public ISS() {
        super();
    }

    public double calcula(Orcamento orcamento) {
        return orcamento.getValor() * 0.06 + calculaOutroImposto(orcamento);
    }

}


