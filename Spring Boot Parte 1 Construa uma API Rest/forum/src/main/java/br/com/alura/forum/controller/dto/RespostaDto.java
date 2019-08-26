package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.RespostaEntity;

import java.time.LocalDateTime;

public class RespostaDto {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDto(RespostaEntity respostaEntity) {
        this.id = respostaEntity.getId();
        this.mensagem = respostaEntity.getMensagem();
        this.dataCriacao = respostaEntity.getDataCriacao();
        this.nomeAutor = respostaEntity.getAutor().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
