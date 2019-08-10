package aula03;

import common.Orcamento;

public class IHIT extends TemplateDeImpostoCondicional {
    @Override
    public boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
        return orcamento.getItens().stream().distinct().count() > orcamento.getItens().size();
    }

    @Override
    public double maximaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() * 0.13 + 100;
    }

    @Override
    public double minimaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() * 0.01 * orcamento.getItens().size();
    }
}
