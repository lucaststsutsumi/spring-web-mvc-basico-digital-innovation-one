package br.com.ltst.springwebmvcbasicodigitalinnovationone.client;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.MessageSend;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.ResultBotTelegram;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.ResultBotTelegramList;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${telegram.api}${telegram.token}", name = "telegram")
public interface FeingClient {

    @GetMapping("/getUpdates")
    ResponseEntity<ResultBotTelegramList> buscarAtualizacao();

    @Headers("MEU_HEADER: {meu_header}")
    @GetMapping("/getUpdates")
    ResponseEntity<ResultBotTelegramList> buscarAtualizacao1(@Param("meu_header") String header);

    // retorno com responseEntity
    @PostMapping("/sendMessage")
    ResponseEntity<ResultBotTelegram> enviarMensagem(@RequestBody MessageSend msg);

    // retorno response Object
    @PostMapping("/sendMessage")
    ResultBotTelegram enviarMensagem1(@RequestBody MessageSend msg);

    // set header RequestHeader
    @PostMapping("/sendMessage")
    ResultBotTelegram enviarMensagem2(@RequestBody MessageSend msg, @RequestHeader("MEU_HEADER") String header);

    // RequestMapping informando o tipo de mapping
    @RequestMapping(method = RequestMethod.POST,value = "/sendMessage")
    ResultBotTelegram enviarMensagem3(@RequestBody MessageSend msg, @RequestHeader("MEU_HEADER") String header);

    // Exemplo de path variable
    @GetMapping("/getUpdates/{meuTeste}")
    ResultBotTelegram examplePathVariable(@PathVariable("meuTeste") String param);

    // Exemplo de request Param
    // www.minhaUrl.com.br/getUpdates?nome={nomeInformado}
    @GetMapping("/getUpdates")
    ResultBotTelegram exampleRequestParam(@RequestParam("nome") String nomeInformado);
}
