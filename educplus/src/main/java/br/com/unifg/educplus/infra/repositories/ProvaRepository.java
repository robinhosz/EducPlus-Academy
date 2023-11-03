package br.com.unifg.educplus.infra.repositories;

import br.com.unifg.educplus.domain.entity.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {
}