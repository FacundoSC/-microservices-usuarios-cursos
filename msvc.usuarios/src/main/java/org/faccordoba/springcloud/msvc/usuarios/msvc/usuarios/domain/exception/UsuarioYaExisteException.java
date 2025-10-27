package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.exception;

public class UsuarioYaExisteException extends DomainException{
    public UsuarioYaExisteException(String email) {
        super("Ya existe un usuario con el email: " + email);
    }
}
