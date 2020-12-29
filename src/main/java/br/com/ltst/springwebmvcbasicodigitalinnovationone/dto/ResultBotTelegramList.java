package br.com.ltst.springwebmvcbasicodigitalinnovationone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultBotTelegramList {
    @JsonProperty("ok")
    private boolean ok;

    private List<GetUpdates> result;

    private boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<GetUpdates> getResult() {
        return result;
    }

    public void setResult(List<GetUpdates> result) {
        this.result = result;
    }
}
