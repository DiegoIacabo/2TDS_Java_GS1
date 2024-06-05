package br.com.fiap.olhoNoMar.repository;

import br.com.fiap.olhoNoMar.entity.Pescador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PescadorRepository extends JpaRepository<Pescador, Long> {
}
