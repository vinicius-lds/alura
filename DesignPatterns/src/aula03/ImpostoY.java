package aula03;

import common.Orcamento;

public class ImpostoY extends TemplateDeImpostoCondicional {

    @Override
    public boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() > 500 && temItemMaiorQue100ReaisNo(orcamento);
    }

    @Override
    public double maximaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() * 0.10;
    }

    @Override
    public double minimaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() * 0.06;
    }

    private boolean temItemMaiorQue100ReaisNo(Orcamento orcamento) {
        // retorna verdadeiro caso algum item seja maior que 100 reais
        return orcamento.getItens().stream().anyMatch(item -> item.getValor() > 100);
    }
}

