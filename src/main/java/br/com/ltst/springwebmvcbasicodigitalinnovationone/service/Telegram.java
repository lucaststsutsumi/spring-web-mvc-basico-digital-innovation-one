package br.com.ltst.springwebmvcbasicodigitalinnovationone.service;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.client.FeingClient;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.client.JavaHttpClient;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.client.RestTemplateClient;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.MessageSend;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.ResultBotTelegram;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.ResultBotTelegramList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Telegram {

    private RestTemplateClient restTemplateClient;
    private FeingClient feingClient;
    private JavaHttpClient javaHttpClient;

    public Telegram(
            JavaHttpClient javaHttpClient,
            RestTemplateClient restTemplateClient,
            FeingClient feingClient) {
        this.javaHttpClient = javaHttpClient;
        this.restTemplateClient = restTemplateClient;
        this.feingClient = feingClient;
    }

    public void enviarMensagem(MessageSend mensagem) {
        // HttpClient
        // ResultBotTelegram resultBotTelegramResponseEntity = javaHttpClient.enviarMensagem(mensagem);

        // FeingClient
        ResultBotTelegram resultBotTelegram = feingClient.enviarMensagem1(mensagem);

        // RestTemplate
        // restTemplateClient.enviarMensagem(mensagem);

    }

    public ResultBotTelegramList buscarAtualizacao() {
        ResultBotTelegramList resultBotTelegramList = null;

        // HttpClient
        // resultBotTelegramList = javaHttpClient.buscarAtualizacao();

        // RestTemplate
        // resultBotTelegramList = restTemplateClient.buscarAtualizacao();

        // FeingClient
        ResponseEntity<ResultBotTelegramList> resultBotTelegramListResponseEntity = feingClient.buscarAtualizacao();
        resultBotTelegramList = resultBotTelegramListResponseEntity.getBody();


        return resultBotTelegramList;
    }
}
