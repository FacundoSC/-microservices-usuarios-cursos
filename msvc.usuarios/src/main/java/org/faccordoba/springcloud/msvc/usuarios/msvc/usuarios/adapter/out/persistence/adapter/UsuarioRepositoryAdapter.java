package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.out.persistence.adapter;

import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.out.persistence.entity.UsuarioEntity;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.out.persistence.mapper.UsuarioMapper;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.out.persistence.repositories.UsuarioRepositoryJpa;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.exception.UsuarioNotFound;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.model.Usuario;
import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.port.out.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryAdapter implements UsuarioRepository {
    private UsuarioRepositoryJpa repository;
    private UsuarioMapper usuarioMapper;
    public UsuarioRepositoryAdapter(UsuarioRepositoryJpa repository, UsuarioMapper usuarioMapper) {
        this.repository = repository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = usuarioMapper.toEntity(usuario);
        UsuarioEntity save = repository.save(entity);
        return usuarioMapper.toDomain(save);
    }

    @Override
    public Usuario findById(Long id) {
        return repository.findById(id).map(usuarioMapper::toDomain)
                .orElseThrow(()->new UsuarioNotFound(id));
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email).
                map(usuarioMapper::toDomain)
                .map(Optional::of)
                .orElseThrow(()->new UsuarioNotFound(email));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioMapper.toDomainList(repository.findAll());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
