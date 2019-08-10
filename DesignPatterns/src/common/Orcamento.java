package common;

import aula05.EmAprovacao;
import aula05.EstadoDeUmOrcamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Orcamento {

    private double valor;
    private final List<Item> itens;
    private EstadoDeUmOrcamento estadoAtual;

    public Orcamento(double valor) {
        this.valor = valor;
        this.itens = new ArrayList();
        this.estadoAtual = new EmAprovacao();
    }

    public void addItem(Item item) {
        this.itens.add(item);
    }

    public double getValor() {
        return this.valor;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(this.itens);
    }

    public void aplicaDescontoExtra() {
        estadoAtual.aplicaDescontoExtra(this);
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setEstadoAtual(EstadoDeUmOrcamento estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public void aprova() {
        estadoAtual.aprova(this);
    }

    public void reprova() {
        estadoAtual.reprova(this);
    }

    public void finaliza() {
        estadoAtual.finaliza(this);
    }
}
