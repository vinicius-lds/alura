package br.com.alura.model;

import br.com.alura.annotation.XmlTagName;

@XmlTagName("category")
public class Category {

    @XmlTagName("name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
