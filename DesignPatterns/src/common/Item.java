package common;

public class Item {

    private final String desc;
    private final double valor;

    public Item(String desc, double valor) {
        this.desc = desc;
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }

    public String getDesc() {
        return this.desc;
    }

}