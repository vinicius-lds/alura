package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.StatusTopico;
import br.com.alura.forum.model.TopicoEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesDoTopicoDto {

    private Long id;
    private String titulo;
    private String mesnagem;
    private LocalDateTime dataCriacao;
    private String autor;
    private StatusTopico status;
    private List<RespostaDto> respostas;

    public DetalhesDoTopicoDto(TopicoEntity topicoEntity) {
        this.id = topicoEntity.getId();
        this.titulo = topicoEntity.getTitulo();
        this.mesnagem = topicoEntity.getMensagem();
        this.dataCriacao = topicoEntity.getDataCriacao();
        this.autor = topicoEntity.getAutor().getNome();
        this.status = topicoEntity.getStatus();
        this.respostas = topicoEntity.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMesnagem() {
        return mesnagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getAutor() {
        return autor;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }
}
