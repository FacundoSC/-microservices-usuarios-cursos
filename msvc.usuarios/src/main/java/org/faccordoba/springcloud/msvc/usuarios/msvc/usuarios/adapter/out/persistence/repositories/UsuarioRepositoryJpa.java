package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.out.persistence.repositories;

import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryJpa extends CrudRepository<UsuarioEntity, Long> {
   Optional<UsuarioEntity> findByEmail(String email);
   List<UsuarioEntity> findAll();
   boolean existsByEmail(String email);
    List<UsuarioEntity> findAllByIdIn(Collection<Long> ids);

}
