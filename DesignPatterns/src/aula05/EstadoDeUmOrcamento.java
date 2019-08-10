package aula05;

import common.Orcamento;

public abstract class EstadoDeUmOrcamento {
    private boolean descontoAplicado = false;
    public void aplicaDescontoExtra(Orcamento orcamento) {
        if (descontoAplicado) {
            throw new RuntimeException("Desconto já aplicado!");
        } else {
            descontoAplicado = true;
        }
    }
    public abstract void aprova(Orcamento orcamento);
    public abstract void reprova(Orcamento orcamento);
    public abstract void finaliza(Orcamento orcamento);
}

