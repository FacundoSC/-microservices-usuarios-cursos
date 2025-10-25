package org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.domain.port.in.services;

import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.application.dto.request.ActualizarUsuarioRequest;
import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.application.dto.response.UsuarioResponse;
import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.domain.exception.UsuarioNotFound;
import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.domain.exception.UsuarioYaExisteException;

public interface ActualizarUsuarioUseCase {

    /**
     * Actualiza los datos de un usuario existente
     *
     * @param id identificador del usuario
     * @param request nuevos datos del usuario
     * @return información del usuario actualizado
     * @throws UsuarioNotFound si no existe el usuario
     * @throws UsuarioYaExisteException si el nuevo email ya está registrado
     * @throws ValidationException si los datos no son válidos
     */
     UsuarioResponse upload(Long id, ActualizarUsuarioRequest request);
}
