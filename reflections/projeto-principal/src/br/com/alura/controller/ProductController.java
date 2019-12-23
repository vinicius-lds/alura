package br.com.alura.controller;

import br.com.alura.model.Category;
import br.com.alura.model.Product;

import java.util.Arrays;

public class ProductController {

    public void list() {
        System.out.println("Listing items");
    }

    public Product candy() {
        return new Product("Candy", 0.15, "Just some candy", Arrays.asList(new Category("Sweet")));
    }

    public String listAndReturn() {
        System.out.println("Listing items");
        return "Returned items: {1}; {2};";
    }

    public String listAndReturn(String nome, String valor) throws Exception {
        return "Returned items: {1}; {2};";
    }

}
