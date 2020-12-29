package br.com.ltst.springwebmvcbasicodigitalinnovationone.client;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.MessageSend;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.ResultBotTelegram;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.ResultBotTelegramList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RestTemplateClient {
    @Value("${telegram.api}")
    private String BASE_PATH;

    @Value("${telegram.token}")
    private String TOKEN;

    private RestTemplate restTemplate;

    public RestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarMensagem(MessageSend msg) {
        // POST for location retornando a uri, Pega informação do header.
        URI uri = restTemplate.postForLocation(BASE_PATH + TOKEN + "/sendMessage", msg);

        // POST for location retornando o objeto que eu definir de retorno.
        ResultBotTelegram retorno = restTemplate
                .postForObject(BASE_PATH + TOKEN + "/sendMessage", msg, ResultBotTelegram.class);

        // POST for location retornando o objeto que eu definir de retorno com dados da requisição,Headers,http status, etc....
        ResultBotTelegram retorno2 = restTemplate
                .postForObject(BASE_PATH + TOKEN + "/sendMessage", msg, ResultBotTelegram.class);

        // POST for location retornando o objeto que eu definir de retorno comdados da requisição, Headers,http status
        //Enviando Headers
        HttpHeaders headers = headers();
        HttpEntity<MessageSend> request = new HttpEntity(msg, headers);
        ResponseEntity<ResultBotTelegram> retorno3 =
                restTemplate.postForEntity(BASE_PATH + TOKEN + "/sendMessage", request, ResultBotTelegram.class);

        //Exchange
        HttpHeaders headers1 = headers();
        HttpEntity<MessageSend> request1 = new HttpEntity(msg, headers1);
        ResponseEntity<ResultBotTelegram> retorno4 =
                restTemplate.exchange(BASE_PATH + TOKEN + "/sendMessage",
                        HttpMethod.POST,
                        request1,
                        ResultBotTelegram.class);

    }


    public ResultBotTelegramList buscarAtualizacao() {
        return restTemplate.getForObject(BASE_PATH + TOKEN + "/getUpdates", ResultBotTelegramList.class);
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("MEU_HEADER", "MEU_VALOR");
        return headers;
    }
}
