package br.com.ltst.springwebmvcbasicodigitalinnovationone.service;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.exception.SoldadoNotFoundException;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.model.Soldado;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.repository.SoldadoRepository;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.resource.ResourceSoldado;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.requests.soldado.SoldadoEditRequest;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.responses.soldado.SoldadoResponseList;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.responses.soldado.SoldadoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SoldadoService {

    @Autowired
    private SoldadoRepository repository;
    @Autowired
    private ResourceSoldado resourceHateoas;


    public SoldadoResponse findById(Long id) {
        final Soldado soldadoEntity = repository.findById(id).orElseThrow();
        SoldadoResponse soldadoResponse = resourceHateoas.criarDetalheLink(soldadoEntity);
        return soldadoResponse;
    }

    public Soldado create(Soldado soldado) {
        return repository.save(soldado);
    }

    public CollectionModel<SoldadoResponseList> findAll() {
        List<Soldado> all = repository.findAll();
        List<SoldadoResponseList> soldadoStream = all.stream()
                .map(it -> resourceHateoas.criarLink(it))
                .collect(Collectors.toList());

        return CollectionModel.of(soldadoStream);
    }

    public Soldado update(Long id, SoldadoEditRequest soldadoEditRequest) {

        final Optional<Soldado> entity = repository.findById(id);

        if (!entity.isPresent()) {
            throw new SoldadoNotFoundException();
        }

        Soldado soldado = entity.get();

        soldado.setName(soldadoEditRequest.getName());
        soldado.setRace(soldadoEditRequest.getRace());
        soldado.setWeapon(soldadoEditRequest.getWeapon());

        return repository.save(soldado);
    }

    public void delete(Long id) {
        final Optional<Soldado> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new SoldadoNotFoundException();
        }

        Soldado soldado = entity.get();
        repository.delete(soldado);
    }
}
