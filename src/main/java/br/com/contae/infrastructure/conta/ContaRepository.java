package br.com.contae.infrastructure.conta;

import br.com.contae.domain.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta,Long> {
}
