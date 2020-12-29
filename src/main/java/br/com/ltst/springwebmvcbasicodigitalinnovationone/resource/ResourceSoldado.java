package br.com.ltst.springwebmvcbasicodigitalinnovationone.resource;


import br.com.ltst.springwebmvcbasicodigitalinnovationone.model.Soldado;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.SoldadoController;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.responses.soldado.SoldadoResponseList;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.responses.soldado.SoldadoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ResourceSoldado {
    private ObjectMapper objectMapper;

    public ResourceSoldado(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public SoldadoResponseList criarLink(Soldado entity) {
        final SoldadoResponseList soldadoResponseList = objectMapper.convertValue(entity, SoldadoResponseList.class);
        final Link link = linkTo(methodOn(SoldadoController.class).findById(entity.getId())).withSelfRel();
        soldadoResponseList.add(link);
        return soldadoResponseList;
    }

    public SoldadoResponse criarDetalheLink(Soldado entity) {
        final SoldadoResponse soldadoResponse = objectMapper.convertValue(entity, SoldadoResponse.class);

        if (entity.getStatus().equals("dead")) {
            final Link link = linkTo(methodOn(SoldadoController.class).deleteById(entity.getId()))
                    .withRel("remover")
                    .withTitle("Deletar Soldado")
                    .withType("delete");
            soldadoResponse.add(link);
        } else if (entity.getStatus().equals("alive")) {
            final Link link = linkTo(methodOn(SoldadoController.class).frenteBatalha(entity.getId()))
                    .withRel("frenteBatalha")
                    .withTitle("Ir para frente de batalha")
                    .withType("put");
            soldadoResponse.add(link);
        }

        return soldadoResponse;
    }
}
