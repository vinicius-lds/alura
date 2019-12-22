package br.com.alura.maven;

public class Product {

    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public double getPriceWithTaxes() {
        return this.price * 1.1;
    }

    public Object get(String field) {
        return switch (field) {
            case "name" -> name;
            case "price" -> price;
            default -> throw new IllegalArgumentException("Field not found!");
        };
    }

    @Override
    public String toString() {
        var json = """
                   {
                       "name": "%s",
                       "price": %s
                   }
                   """;
        return String.format(json, this.name, this.price);
    }

}