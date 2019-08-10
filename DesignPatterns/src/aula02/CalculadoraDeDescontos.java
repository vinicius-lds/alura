package aula02;

import common.Orcamento;

public class CalculadoraDeDescontos {

    public void calcula(Orcamento orcamento) {
        var d1 = new MaisDeCincoItens();
        var d2 = new MaiorQue500();

        d1.setProximo(d2);
        
        System.out.println(d1.calculaDesconto(orcamento));
    }

}
