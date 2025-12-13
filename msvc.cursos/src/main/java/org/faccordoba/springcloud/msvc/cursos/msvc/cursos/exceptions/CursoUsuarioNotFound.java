package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.exceptions;

public class CursoUsuarioNotFound extends RuntimeException {

    public CursoUsuarioNotFound(Long cursoId, Long usuarioId) {
        super("Curso con ID " + cursoId + " no tiene asociado al usuario con ID " + usuarioId);
    }
}
