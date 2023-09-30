package ufrn.br.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ufrn.br.cliente.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
}
