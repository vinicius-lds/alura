package br.com.alura.forum.repository;

import br.com.alura.forum.model.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

    Optional<CursoEntity> findByNome(String nome);

}
