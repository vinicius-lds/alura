package aula05;

import aula04.Expressao;
import aula04.Numero;
import aula04.Soma;
import aula04.Subtracao;

public class Programa {

    public static void main(String[] args) {
        Expressao esquerda = new Subtracao(new Numero(10), new Numero(5));
        Expressao direita = new Soma(new Numero(2), new Numero(10));

        Expressao conta = new Soma(esquerda, direita);

        int resultado = conta.avalia();
        System.out.println(resultado);

        Visitor visitor = new ImpressoraPrefixada();
        conta.aceita(visitor);
    }
}
