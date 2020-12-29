package br.com.ltst.springwebmvcbasicodigitalinnovationone.controller.requests.soldado;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.enums.Race;

public class SoldadoEditRequest {

    private String name;
    private Race race;
    private String weapon;

    public SoldadoEditRequest() {

    }

    public SoldadoEditRequest(String name, Race race, String weapon) {
        this.name = name;
        this.race = race;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
}
