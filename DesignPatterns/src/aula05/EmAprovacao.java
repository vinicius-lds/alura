package aula05;

import common.Orcamento;

public class EmAprovacao extends EstadoDeUmOrcamento {
    public void aplicaDescontoExtra(Orcamento orcamento) {
        super.aplicaDescontoExtra(orcamento);
        orcamento.setValor(orcamento.getValor() - orcamento.getValor() * 0.05);
    }

    public void aprova(Orcamento orcamento) {
        // desse estado posso ir para o estado de aprovado
        orcamento.setEstadoAtual(new Aprovado());
    }

    public void reprova(Orcamento orcamento) {
        // desse estado posso ir para o estado de reprovado tambem
        orcamento.setEstadoAtual(new Reprovado());
    }

    public void finaliza(Orcamento orcamento) {
        // daqui não posso ir direto para finalizado
        throw new RuntimeException("Orcamento em aprovação não podem ir para finalizado diretamente");
    }

}
