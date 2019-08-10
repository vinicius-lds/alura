package aula02;

import common.Item;
import common.Orcamento;

public class Main {
    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(600);
        orcamento.addItem(new Item("Caneta", 250));
        orcamento.addItem(new Item("Lapis", 250));
        CalculadoraDeDescontos calculadoraDeDescontos = new CalculadoraDeDescontos();
        calculadoraDeDescontos.calcula(orcamento);
    }
}

