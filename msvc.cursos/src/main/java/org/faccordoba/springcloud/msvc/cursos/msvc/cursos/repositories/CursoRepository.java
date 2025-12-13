package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.repositories;

import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    List<Curso> findAll();

    @Query("delete from CursoUsuario cu where cu.usuarioId=?1")
    @Modifying
    void deleteCursoUsuarioByUserId(Long usuarioId);
}
