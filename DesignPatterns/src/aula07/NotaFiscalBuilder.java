package aula07;

import aula07.ItemDaNota;
import aula07.NotaFiscal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class NotaFiscalBuilder {

    private String razaoSocial;
    private String cnpj;
    private List<ItemDaNota> todosItens = new ArrayList<>();
    private double valorBruto;
    private double impostos;
    private String observacoes;
    private Calendar data;
    private List<AcaoAposGerarNota> todasAcoesASeremExecutadas;

    public NotaFiscalBuilder() {
        this.todasAcoesASeremExecutadas = new ArrayList<>();
    }

    public NotaFiscalBuilder(AcaoAposGerarNota ...acoes) {
        this.todasAcoesASeremExecutadas = new ArrayList<>(acoes.length);
        Collections.addAll(this.todasAcoesASeremExecutadas, acoes);
    }

    public NotaFiscalBuilder paraEmpresa(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public NotaFiscalBuilder comCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public NotaFiscalBuilder com(ItemDaNota item) {
        todosItens.add(item);
        valorBruto += item.getValor();
        impostos += item.getValor() * 0.05;
        return this;
    }

    public NotaFiscalBuilder comObservacoes(String observacoes) {
        this.observacoes = observacoes;
        return this;
    }

    public NotaFiscalBuilder naData(Calendar data) {
        this.data = data;
        return this;
    }

    public NotaFiscalBuilder adicionaAcao(AcaoAposGerarNota novaAcao) {
        this.todasAcoesASeremExecutadas.add(novaAcao);
        return this;
    }

    public NotaFiscal constroi() {
        NotaFiscal nf = new NotaFiscal(razaoSocial, cnpj, data == null ? Calendar.getInstance() : data , valorBruto, impostos, todosItens, observacoes);
        todasAcoesASeremExecutadas.forEach(acaoAposGerarNota -> acaoAposGerarNota.executa(nf));
        return nf;
    }

    public NotaFiscalBuilder naDataAtual() {
        this.data = Calendar.getInstance();
        return this;
    }
}


