package br.com.alura.forum.controller.dto;

public class TokenDto {

    private String tokem;
    private String tipo;

    public TokenDto(String tokem, String tipo) {
        this.tokem = tokem;
        this.tipo = tipo;
    }

    public String getTokem() {
        return tokem;
    }

    public String getTipo() {
        return tipo;
    }

}
