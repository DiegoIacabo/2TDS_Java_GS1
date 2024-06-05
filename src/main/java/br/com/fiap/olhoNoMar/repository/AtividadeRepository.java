package br.com.fiap.olhoNoMar.repository;

import br.com.fiap.olhoNoMar.entity.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
