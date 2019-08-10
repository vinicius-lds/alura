package aula03;

import java.util.Calendar;

public class Contrato {

    private Calendar data;
    private String cliente;
    private TipoContrato tipo;
    private Historico historico;

    public Contrato(Calendar data, String cliente, TipoContrato tipo) {
        this.data = data;
        this.cliente = cliente;
        this.tipo = tipo;
        this.historico = new Historico();
        this.salvaEstado();
    }

    public Contrato(Calendar data, String cliente, TipoContrato tipo, Historico historico) {
        this.data = data;
        this.cliente = cliente;
        this.tipo = tipo;
        this.historico = historico;
    }

    public void avanca() {
        if(tipo == TipoContrato.NOVO) {
            tipo = TipoContrato.EM_ANDAMENTO;
        } else if(tipo == TipoContrato.EM_ANDAMENTO) {
            tipo = TipoContrato.ACERTADO;
        } else if(tipo == TipoContrato.ACERTADO) {
            tipo = TipoContrato.CONCLUIDO;
        }
        this.salvaEstado();
    }

    public void salvaEstado() {
        this.historico.adiciona(new Estado(new Contrato(data, cliente, tipo, null)));
    }

    public void restaura(int index) {
        Estado estado = this.historico.pega(this.pegaIndexCorreto(index));
        this.data = estado.getContrato().getData();
        this.cliente = estado.getContrato().getCliente();
        this.tipo = estado.getContrato().getTipo();
    }

    private int pegaIndexCorreto(int index) {
        if (index < 0) {
            return this.historico.tamanho() - Math.abs(index) - 1;
        } else {
            return index;
        }
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public TipoContrato getTipo() {
        return tipo;
    }

    public void setTipo(TipoContrato tipo) {
        this.tipo = tipo;
    }
}
