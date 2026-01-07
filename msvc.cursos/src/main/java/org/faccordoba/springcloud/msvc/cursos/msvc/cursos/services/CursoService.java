package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.services;

import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request.CrearUsuarioRequest;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.dto.request.Usuario;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.models.entity.Curso;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> findAll();
    Optional<Curso> findById(Long id, String token);
    Curso save(Curso curso);
    void deleteById(Long id);
    void deleteCursoUsuarioById(Long usuarioId);

    Curso update(Long id, Curso curso);
    Curso asignarUsuario(Long cursoId, Usuario usuario, String token);
    Curso desasignarUsuario(Long cursoId, Usuario usuario, String token);
    Curso crearUsuario(Long cursoId, CrearUsuarioRequest usuario);



}
