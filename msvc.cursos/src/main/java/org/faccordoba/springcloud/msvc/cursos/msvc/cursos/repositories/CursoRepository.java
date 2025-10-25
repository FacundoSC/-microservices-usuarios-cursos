package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.repositories;

import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.entity.Curso;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    List<Curso> findAll();
}
