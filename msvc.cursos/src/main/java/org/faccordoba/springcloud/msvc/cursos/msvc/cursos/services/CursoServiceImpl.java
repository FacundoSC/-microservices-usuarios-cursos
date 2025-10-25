package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.services;

import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.entity.Curso;
import org.faccordoba.springcloud.msvc.cursos.msvc.cursos.repositories.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {
    private final CursoRepository repository;
    public CursoServiceImpl(CursoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Curso update(Long id, Curso curso) {
        Optional<Curso> cursoOptional = repository.findById(id);
        if (cursoOptional.isPresent()) {
            Curso cursoDB = cursoOptional.get();
            cursoDB.setNombre(curso.getNombre());
            return repository.save(cursoDB);
        }
        return null;
    }
}
