package br.com.alura.forum.repository;

import br.com.alura.forum.model.TopicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<TopicoEntity, Long> {

    Page<TopicoEntity> findByCursoNome(String nomeCurso, Pageable paginacao);

}
