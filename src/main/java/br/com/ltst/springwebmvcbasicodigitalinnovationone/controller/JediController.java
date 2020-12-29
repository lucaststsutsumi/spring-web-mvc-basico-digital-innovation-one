package br.com.ltst.springwebmvcbasicodigitalinnovationone.controller;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.model.Jedi;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.repository.JediRepository;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class JediController {

    @Autowired
    private JediService service;

    @GetMapping("/api/jedi")
    public List<Jedi> findAll() {
        return service.findAll();
    }

    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> findbyId(@PathVariable("id") Long id) {
        final Jedi jedi = service.findById(id);
        return ResponseEntity.ok(jedi);
    }

    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi createJedi(@Valid @RequestBody Jedi jedi) {
        return service.save(jedi);
    }

    @PutMapping("/api/jedi/{id}")
    public ResponseEntity<Jedi> update(@PathVariable("id") Long id, @Valid @RequestBody Jedi dto) {
        final var jedi = service.update(id, dto);
        return ResponseEntity.ok(jedi);
    }

    @DeleteMapping("/api/jedi/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
