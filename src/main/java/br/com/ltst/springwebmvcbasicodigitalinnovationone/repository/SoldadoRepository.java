package br.com.ltst.springwebmvcbasicodigitalinnovationone.repository;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.model.Soldado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldadoRepository extends JpaRepository<Soldado, Long> {
}
