package br.com.alura.model;

import br.com.alura.annotation.XmlTagName;

import java.util.List;

@XmlTagName("product")
public class Product {

    @XmlTagName("name")
    private String name;

    @XmlTagName("price")
    private double price;

    @XmlTagName("description")
    private String description;

    @XmlTagName("categories")
    private List<Category> categories;

    public Product() {
    }

    public Product(String name, double price, String description, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
