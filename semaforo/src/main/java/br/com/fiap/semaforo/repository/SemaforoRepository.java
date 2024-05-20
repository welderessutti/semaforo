package br.com.fiap.semaforo.repository;

import br.com.fiap.semaforo.model.Semaforo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemaforoRepository extends JpaRepository<Semaforo, Long> {
}
