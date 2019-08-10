package aula02;

import common.Orcamento;

public class MaisDeCincoItens extends Desconto {

    @Override
    public double calculaDesconto(Orcamento orcamento) {
        if (orcamento.getItens().size() > 5) {
            return orcamento.getValor() * 0.1;
        } else {
            return super.chamarProximo(orcamento);
        }
    }

}
