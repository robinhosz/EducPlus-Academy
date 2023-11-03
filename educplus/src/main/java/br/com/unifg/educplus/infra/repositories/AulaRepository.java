package br.com.unifg.educplus.infra.repositories;

import br.com.unifg.educplus.domain.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
}