package br.com.ltst.springwebmvcbasicodigitalinnovationone.controller;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.requests.soldado.SoldadoEditRequest;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.responses.soldado.SoldadoResponse;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.responses.soldado.SoldadoResponseList;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.enums.Race;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.model.Soldado;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.resource.ResourceSoldado;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.service.SoldadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// @ExtendWith(SpringExtension.class)
// @WebMvcTest(controllers = SoldadoController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SoldadoControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private ResourceSoldado resourceHateoas;

    @MockBean
    protected SoldadoService service;

    @LocalServerPort
    static int randomServerPort;

    private static String BASE_PATH;
    private static final String CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";
    private static final String AUTHORIZATION_VALUE = "BATATINHA";
    public static final int DEFAULT_PARAM_VALUE = 1;

    private Soldado criarNovoSoldado() {
        Soldado soldado = new Soldado();

        soldado.setWeapon("sword");
        soldado.setCpf("123456789");
        soldado.setName("Aragorn");
        soldado.setRace(Race.HUMANO);
        soldado.setStatus("alive");
        soldado.setId(1L);
        return soldado;

    }

    @BeforeAll
    static void init() {
        BASE_PATH = "http://localhost:" + randomServerPort + "/v1/soldado";
    }

    @BeforeEach
    void antesDeCadaTeste() {
        final Soldado soldado = criarNovoSoldado();

        // findAll
        List<Soldado> all = List.of(soldado);
        List<SoldadoResponseList> soldadoStream = all.stream()
                .map(it -> resourceHateoas.criarLink(it))
                .collect(Collectors.toList());

        final var colecao = CollectionModel.of(soldadoStream);
        when(service.findAll()).thenReturn(colecao);

        // findById
        SoldadoResponse soldadoResponse = new SoldadoResponse();
        soldadoResponse.setCpf(soldado.getCpf());
        soldadoResponse.setId(soldado.getId());
        soldadoResponse.setName(soldado.getName());
        soldadoResponse.setRace(soldado.getRace().getValue());
        soldadoResponse.setStatus(soldado.getStatus());
        soldadoResponse.setWeapon(soldado.getWeapon());

        final Link link = linkTo(methodOn(SoldadoController.class).frenteBatalha(soldado.getId()))
                .withRel("frenteBatalha")
                .withTitle("Ir para frente de batalha")
                .withType("put");
        soldadoResponse.add(link);
        when(service.findById(any())).thenReturn(soldadoResponse);


        // create
        when(service.create(any())).thenReturn(soldado);
        // update
        // when(service.update(any(), any())).thenReturn(soldado);
        // delete

    }

    @AfterEach
    void depoisDeCadaTeste() {
        service.findAll().forEach(soldadoResponseList -> service.delete(soldadoResponseList.getId()));
    }

    @Test
    void shouldFindAll() throws Exception {
        mockMvc
                .perform(get(BASE_PATH)
                        .header("authorization", AUTHORIZATION_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", CONTENT_TYPE_VALUE));

    }

    @Test
    void shouldFindById() throws Exception {
        Soldado soldado = criarNovoSoldado();

        SoldadoResponse soldadoResponse = objectMapper.convertValue(soldado, SoldadoResponse.class);
        final Link link = linkTo(methodOn(SoldadoController.class).frenteBatalha(soldado.getId()))
                .withRel("frenteBatalha")
                .withTitle("Ir para frente de batalha")
                .withType("put");
        soldadoResponse.add(link);

        String jsonAsString = objectMapper.writeValueAsString(soldadoResponse);

        mockMvc
                .perform(get(BASE_PATH + "/{id}", DEFAULT_PARAM_VALUE)
                        .header("authorization", AUTHORIZATION_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", CONTENT_TYPE_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        //.andExpect(content().json(jsonAsString));
    }

    @Test
    void shouldCreate() throws Exception {
        Soldado soldado = criarNovoSoldado();
        String jsonAsString = objectMapper.writerWithView(Soldado.class).writeValueAsString(soldado);
        mockMvc
                .perform(post(BASE_PATH)
                        .header("authorization", AUTHORIZATION_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAsString))
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", CONTENT_TYPE_VALUE));
    }

    @Test
    void shouldUpdate() throws Exception {
        Soldado soldado = criarNovoSoldado();
        String jsonAsString = objectMapper.writerWithView(SoldadoEditRequest.class).writeValueAsString(soldado);

        mockMvc
                .perform(put(BASE_PATH + "/{id}", DEFAULT_PARAM_VALUE)
                        .header("authorization", AUTHORIZATION_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAsString))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(
                delete(BASE_PATH + "/{id}", DEFAULT_PARAM_VALUE)
                        .header("authorization", AUTHORIZATION_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void frenteBatalha() {
    }
}