package br.com.alura.forum.controller.form;

import br.com.alura.forum.model.CursoEntity;
import br.com.alura.forum.model.TopicoEntity;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoTopicoForm {

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

    public AtualizacaoTopicoForm(String titulo, String mensagem) {
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }


    public TopicoEntity atualizar(Long id, TopicoRepository topicoRepository) {
        TopicoEntity topicoEntity = topicoRepository.getOne(id);
        topicoEntity.setTitulo(this.titulo);
        topicoEntity.setMensagem(this.mensagem);
        return topicoEntity;
    }
}
