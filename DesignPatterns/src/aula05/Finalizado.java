package aula05;

import common.Orcamento;

public class Finalizado extends EstadoDeUmOrcamento {
    public void aplicaDescontoExtra(Orcamento orcamento) {
        super.aplicaDescontoExtra(orcamento);
        throw new RuntimeException("Orçamentos finalizados não recebem desconto extra!");
    }

    @Override
    public void aprova(Orcamento orcamento) {
        throw new RuntimeException("Orçamentos já finalizado!");
    }

    @Override
    public void reprova(Orcamento orcamento) {
        throw new RuntimeException("Orçamentos já finalizado!");
    }

    @Override
    public void finaliza(Orcamento orcamento) {
        throw new RuntimeException("Orçamentos já finalizado!");
    }
}
