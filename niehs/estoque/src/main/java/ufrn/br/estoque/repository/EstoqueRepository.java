package ufrn.br.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.estoque.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, String> {
}
