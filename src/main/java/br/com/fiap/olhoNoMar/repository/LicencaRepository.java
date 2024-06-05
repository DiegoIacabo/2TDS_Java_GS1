package br.com.fiap.olhoNoMar.repository;

import br.com.fiap.olhoNoMar.entity.Licenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicencaRepository extends JpaRepository<Licenca, Long> {
}
