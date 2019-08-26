package br.com.alura.forum.controller.dto;

import br.com.alura.forum.model.TopicoEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class TopicoDto {

    public static Page<TopicoDto> converter(Page<TopicoEntity> topicos) {
        return topicos.map(TopicoDto::new);
    }

    public static TopicoDto converter(TopicoEntity topico) {
        return new TopicoDto(topico);
    }

    private Long id;
    private String titulo;
    private String mesnagem;
    private LocalDateTime dataCriacao;

    public TopicoDto(TopicoEntity topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mesnagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
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
}
