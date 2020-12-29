package br.com.ltst.springwebmvcbasicodigitalinnovationone.dto;

public class GetUpdates {
    private Long updateId;
    private Message message;

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
