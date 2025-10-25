package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.services;

import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    Curso save(Curso curso);
    void deleteById(Long id);
    Curso update(Long id, Curso curso);
}
