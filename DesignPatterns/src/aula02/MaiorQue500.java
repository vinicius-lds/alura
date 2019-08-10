package aula02;

import common.Orcamento;

public class MaiorQue500 extends Desconto {

    @Override
    public double calculaDesconto(Orcamento orcamento) {
        if (orcamento.getValor() > 500) {
            return orcamento.getValor() * 0.07;
        } else {
            return super.chamarProximo(orcamento);
        }
    }

}