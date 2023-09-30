package ufrn.br.venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.venda.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, String> {

}