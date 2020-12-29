package br.com.ltst.springwebmvcbasicodigitalinnovationone.enums;

import java.util.stream.Stream;

public enum Race {
    HUMANO("humano"), ELFO("elfo"), ORC("orc"), ANAO("anao");

    private String value;

    Race(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Race of(String value) {
        return Stream.of(Race.values())
                .filter(it -> it.getValue().equals(value))
                .findFirst()
                .orElseThrow();
    }
}
