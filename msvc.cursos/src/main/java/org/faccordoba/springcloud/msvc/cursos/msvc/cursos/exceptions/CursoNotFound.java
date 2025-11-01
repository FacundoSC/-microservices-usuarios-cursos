package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.exceptions;

public class CursoNotFound extends RuntimeException {

    public CursoNotFound(Long id) {
        super("Curso con ID " + id + " no encontrado");
    }
}
