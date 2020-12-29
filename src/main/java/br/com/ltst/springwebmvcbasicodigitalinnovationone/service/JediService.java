package br.com.ltst.springwebmvcbasicodigitalinnovationone.service;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.exception.JediNotFoundException;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.model.Jedi;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JediService {
    @Autowired
    private JediRepository repository;

    public List<Jedi> findAll() {
        return repository.findAll();
    }

    public Jedi findById(Long id) {

        final var jedi = repository.findById(id);

        if (jedi.isPresent()) {
            return jedi.get();
        } else {
            throw new JediNotFoundException();
        }
    }

    public Jedi save(Jedi jedi) {
        return repository.save(jedi);
    }

    public Jedi update(Long id, Jedi dto) {
        final Optional<Jedi> jediEntity = repository.findById(id);

        if (!jediEntity.isPresent()) {
            throw new JediNotFoundException();
        }

        Jedi jedi = jediEntity.get();

        jedi.setName(dto.getName());
        jedi.setLastName(dto.getLastName());

        return repository.save(jedi);
    }

    public void delete(Long id) {
        final Jedi jediEntity = findById(id);
        repository.delete(jediEntity);
    }
}
