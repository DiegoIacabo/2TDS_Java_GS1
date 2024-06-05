package br.com.fiap.olhoNoMar.repository;

import br.com.fiap.olhoNoMar.entity.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Long> {
}
