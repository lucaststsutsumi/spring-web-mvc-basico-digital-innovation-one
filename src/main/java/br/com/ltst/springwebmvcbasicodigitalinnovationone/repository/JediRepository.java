package br.com.ltst.springwebmvcbasicodigitalinnovationone.repository;

import br.com.ltst.springwebmvcbasicodigitalinnovationone.model.Jedi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JediRepository extends JpaRepository<Jedi, Long> {

    List<Jedi> findByNameContainingIgnoreCase(final String name);

}
