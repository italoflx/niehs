package ufrn.br.estoque.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ufrn.br.estoque.model.Estoque;
import ufrn.br.estoque.repository.EstoqueRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
    EstoqueRepository repository;
    public EstoqueService(EstoqueRepository repository){
        this.repository = repository;
    }

    public Estoque create(Estoque e) {
        return this.repository.save(e);
    }

    public Estoque update(Estoque d, String id) {
        Optional<Estoque> estoqueBanco = repository.findById(id);
        if (estoqueBanco.isPresent()){
            return this.repository.save(d);
        }else{
            throw new EntityNotFoundException();
        }
    }

    public void delete(String id) {
        this.repository.deleteById(id);
    }

    public List<Estoque> list() {
        return (List<Estoque>) this.repository.findAll();
    }

    public Estoque getById(String id) {
        Optional<Estoque> estoqueBanco = repository.findById(id);
        if (estoqueBanco.isPresent()){
            return estoqueBanco.get();
        }else{
            throw new EntityNotFoundException();
        }
    }
}
