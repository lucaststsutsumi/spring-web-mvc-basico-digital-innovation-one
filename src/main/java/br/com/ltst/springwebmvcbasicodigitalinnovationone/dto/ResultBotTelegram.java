package br.com.ltst.springwebmvcbasicodigitalinnovationone.dto;

import java.util.List;

public class ResultBotTelegram {
    private boolean ok;
    private ResponseMessage result;

    private boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public ResponseMessage getResult() {
        return result;
    }

    public void setResult(ResponseMessage result) {
        this.result = result;
    }
}
