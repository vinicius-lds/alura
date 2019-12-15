package br.com.alura.controller;

public class ProductController {

    public void list() {
        System.out.println("Listing items");
    }

    public String listAndReturn() {
        System.out.println("Listing items");
        return "Returned items: {1}; {2};";
    }

}
