package aula05;

import common.Orcamento;

public class Reprovado extends EstadoDeUmOrcamento {
    public void aplicaDescontoExtra(Orcamento orcamento) {
        throw new RuntimeException("Orçamentos reprovados não recebem desconto extra!");
    }

    @Override
    public void aprova(Orcamento orcamento) {
        throw new RuntimeException("Orçamentos reprovado não pode ser aprovado!");
    }

    @Override
    public void reprova(Orcamento orcamento) {
        throw new RuntimeException("Orçamentos já está reprovado!");
    }

    @Override
    public void finaliza(Orcamento orcamento) {
        orcamento.setEstadoAtual(new Finalizado());
    }
}
