package br.com.contae.infrastructure.categoria;

import br.com.contae.domain.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long>  {
}
