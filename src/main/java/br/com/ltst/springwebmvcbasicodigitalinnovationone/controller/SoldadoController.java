package br.com.ltst.springwebmvcbasicodigitalinnovationone.controller;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.model.Soldado;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.requests.soldado.SoldadoEditRequest;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.responses.soldado.SoldadoResponseList;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.responses.soldado.SoldadoResponse;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.service.SoldadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/soldado",produces = "application/json;charset=UTF-8")// default:produces = "application/hal+json"
public class SoldadoController {

    @Autowired
    private SoldadoService service;

    @Value("${meuValor}")
    private String meuValor;

    @GetMapping
    public ResponseEntity<CollectionModel<SoldadoResponseList>> findAll() {
        System.out.println("==========================");
        System.out.println("chamout findAll");
        System.out.println("==========================");
        final CollectionModel<SoldadoResponseList> soldados = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(soldados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoldadoResponse> findById(@PathVariable Long id) {
        System.out.println("==========================");
        System.out.println("chamout findId");
        System.out.println("==========================");
        SoldadoResponse soldado = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(soldado);
    }

    @PostMapping
    public ResponseEntity<Soldado> create(@RequestBody Soldado body) {
        System.out.println("Chamou metodo create");
        Soldado soldado = service.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(soldado);
    }

    @PutMapping("{id}")
    public ResponseEntity<Soldado> update(@PathVariable Long id, @RequestBody SoldadoEditRequest soldadoEditRequest) {
        Soldado soldado = service.update(id, soldadoEditRequest);
        return ResponseEntity.status(HttpStatus.OK).body(soldado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Soldado> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<SoldadoResponse> frenteBatalha(Long id) {
        final var soldadoResponse = service.findById(id);
        return ResponseEntity.ok().body(soldadoResponse);
    }
}
