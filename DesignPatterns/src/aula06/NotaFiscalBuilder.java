package aula06;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotaFiscalBuilder {

    private String razaoSocial;
    private String cnpj;

    private List<ItemDaNota> todosItens = new ArrayList<>();
    private double valorBruto;
    private double impostos;
    private String observacoes;
    private Calendar data;

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

    public NotaFiscal constroi() {
        return new NotaFiscal(razaoSocial, cnpj, data == null ? Calendar.getInstance() : data , valorBruto, impostos, todosItens, observacoes);
    }
}


