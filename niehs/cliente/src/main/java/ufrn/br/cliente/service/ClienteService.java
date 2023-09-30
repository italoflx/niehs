package ufrn.br.cliente.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ufrn.br.cliente.model.Cliente;
import java.util.List;
import java.util.Optional;
import ufrn.br.cliente.repository.ClienteRepository;

@Service
public class ClienteService{
    ClienteRepository repository;

    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }

    public Cliente create(Cliente c) {
        return this.repository.save(c);
    }

    public Cliente update(Cliente d, String id) {
        Optional<Cliente> clienteBanco = repository.findById(id);
        if (clienteBanco.isPresent()){
            return this.repository.save(d);
        }
        return null;
    }

    public void delete(String id) {
        this.repository.deleteById(id);
    }

    public List<Cliente> list() {
        return this.repository.findAll();
    }

    public Cliente getById(String id) {
        Optional<Cliente> clienteBanco = repository.findById(id);
        if (clienteBanco.isPresent()){
            return clienteBanco.get();
        }else{
            throw new EntityNotFoundException();
        }
    }
}