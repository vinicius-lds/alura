package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.CursoEntity;
import br.com.alura.forum.model.TopicoEntity;
import br.com.alura.forum.repository.CursoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicoForm {

    public static TopicoEntity converter(TopicoForm topicoForm, CursoRepository cursoRepository) {
        CursoEntity cursoEntity = cursoRepository
                .findByNome(topicoForm.getNomeCurso())
                .orElseThrow(() -> new IllegalArgumentException("Nome do curso é inválido!"));
        return new TopicoEntity(topicoForm.getTitulo(), topicoForm.getMensagem(), cursoEntity);
    }

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String mensagem;
    @NotNull
    @NotEmpty
    private String nomeCurso;

    public TopicoForm(String titulo, String mensagem, String nomeCurso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.nomeCurso = nomeCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }
}
