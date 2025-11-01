package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.port.out.repository;

import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Usuario findById(Long id);
    Optional<Usuario> findByEmail(String email);
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    List<Usuario> findAll();
    List<Usuario> findAllById(ArrayList<Long> ids);
    void deleteById(Long id);
}
