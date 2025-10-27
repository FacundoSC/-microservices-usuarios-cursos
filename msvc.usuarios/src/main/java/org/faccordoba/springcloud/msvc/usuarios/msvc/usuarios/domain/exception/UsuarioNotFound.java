package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.exception;

public class UsuarioNotFound extends DomainException {
    public UsuarioNotFound(Long id) {
        super("Usuario con ID " + id + " no encontrado");
    }

    public UsuarioNotFound(String email) {
        super("Usuario con email " + email + " no encontrado");
    }
}