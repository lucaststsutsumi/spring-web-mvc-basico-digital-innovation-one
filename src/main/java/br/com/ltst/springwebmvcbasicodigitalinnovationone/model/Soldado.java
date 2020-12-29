package br.com.ltst.springwebmvcbasicodigitalinnovationone.model;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.enums.Race;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Soldado {
    @Id
    @Column(name = "soldado_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "soldado_cpf")
    @NotBlank(message = "CPF cannot be blank")
    private String cpf;

    @Column(name = "soldado_name")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(name = "soldado_race")
    private Race race;

    @Column(name = "soldado_weapon")
    @NotBlank(message = "Weapon cannot be blank")
    private String weapon;

    @Column(name = "soldado_status")
    @NotBlank(message = "Status cannot be blank")
    private String status;

    public Soldado() {
    }

    public Soldado(Long id, String cpf, String name, Race race, String weapon, String status) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.race = race;
        this.weapon = weapon;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
