package br.com.alura;

import br.com.alura.alurator.Alurator;

import java.util.Scanner;

public class Main {

    /**
     * Simula o navegador
     * <p>
     * Casos possiveis:
     * controlador/metodo
     * controlador/metodo?param1=value&param2=value
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

        try (Scanner s = new Scanner(System.in)) {
            String url = s.nextLine();

            var alurator = new Alurator("br.com.alura.controller");
            while (!url.equals("exit")) {
                try {
                    Object response = alurator.execute(url);
                    System.out.println("Response: " + response);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                url = s.nextLine();
            }
        }

    }

}
