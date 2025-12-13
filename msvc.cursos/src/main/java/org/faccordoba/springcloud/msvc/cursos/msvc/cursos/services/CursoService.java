package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.services;

import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request.CrearUsuarioRequest;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request.Usuario;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    Curso save(Curso curso);
    void deleteById(Long id);
    void deleteCursoUsuarioById(Long usuarioId);

    Curso update(Long id, Curso curso);
    Curso asignarUsuario(Long cursoId, Usuario usuario);
    Curso desasignarUsuario(Long cursoId, Usuario usuario);
    Curso crearUsuario(Long cursoId, CrearUsuarioRequest usuario);



}
