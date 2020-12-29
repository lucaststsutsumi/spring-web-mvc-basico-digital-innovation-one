package br.com.ltst.springwebmvcbasicodigitalinnovationone.controller;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.MessageSend;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.dto.ResultBotTelegramList;
import br.com.ltst.springwebmvcbasicodigitalinnovationone.service.Telegram;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/telegram")
public class TelegramController {

    private ObjectMapper objectMapper;
    private Telegram telegramService;

    public TelegramController(ObjectMapper objectMapper,Telegram telegram){
        this.objectMapper = objectMapper;
        this.telegramService = telegram;
    }
    @PostMapping
    public ResponseEntity criarSoldado(@RequestBody MessageSend messageSend) {
        telegramService.enviarMensagem(messageSend);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<ResultBotTelegramList> buscarSoldados() {
        ResultBotTelegramList resultBotTelegramList = telegramService.buscarAtualizacao();
        return ResponseEntity.status(HttpStatus.OK).body(resultBotTelegramList);
    }
}
