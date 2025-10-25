package org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.domain.port.in.services;


import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.application.dto.response.UsuarioResponse;
import org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.domain.exception.UsuarioNotFound;

import java.util.List;

/**
 * Puerto de entrada para el caso de uso: Obtener Usuario(s)
 * Define las operaciones de consulta de usuarios
 */
public interface ObtenerUsuarioUseCase {

    /**
     * Obtiene un usuario por su identificador
     *
     * @param id identificador del usuario
     * @return datos del usuario encontrado
     * @throws UsuarioNotFound si no existe el usuario
     */
    UsuarioResponse findById(Long id);

    /**
     * Obtiene todos los usuarios del sistema
     *
     * @return lista de usuarios (puede estar vacía)
     */
    List<UsuarioResponse> findAll();

    /**
     * Busca un usuario por su email
     *
     * @param email email del usuario
     * @return datos del usuario encontrado
     * @throws UsuarioNotFound si no existe el usuario
     */
    UsuarioResponse findByEmail(String email);
}
