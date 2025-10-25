package org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.domain.port.in.services;

import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.domain.exception.UsuarioNotFound;

/**
 * Puerto de entrada para el caso de uso: Eliminar Usuario
 */
public interface EliminarUsuarioUseCase {

    /**
     * Elimina un usuario del sistema
     *
     * @param id identificador del usuario a eliminar
     * @throws UsuarioNotFound si no existe el usuario
     */
     void delete(Long id);
}