package br.com.alura.controller;

public class ProductController {

    public void list() {
        System.out.println("Listing items");
    }

    public String listAndReturn() {
        System.out.println("Listing items");
        return "Returned items: {1}; {2};";
    }

    public String listAndReturn(String nome, String valor) throws Exception {
        if (true) {
            throw new Exception("Unexpected excpetion");
        }
        return "Returned items: {1}; {2};";
    }

}
