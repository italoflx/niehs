package ufrn.br.venda.service;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ufrn.br.venda.entities.RequestResponse;
import ufrn.br.venda.model.Venda;
import ufrn.br.venda.repository.VendaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    VendaRepository repository;
    ModelMapper mapper;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public VendaService(VendaRepository repository, WebClient.Builder webClientBuilder, ModelMapper mapper){
        this.repository = repository;
        this.webClientBuilder = webClientBuilder;
        this.mapper = mapper;
    }

    public Optional<Venda> findById(String id) {
        return this.repository.findById(id);
    }

    public Venda.DtoResponse update(Venda v, String id) {
        Optional<Venda> vendaBanco = repository.findById(id);
        if (vendaBanco.isPresent()){
            this.repository.save(v);
            return Venda.DtoResponse.convertToDto(v, mapper);
        }else{
            throw new EntityNotFoundException();
        }
    }

    public void delete(String id) {
        this.repository.deleteById(id);
    }

    public List<Venda> list() {
        return this.repository.findAll();
    }

    public Venda.DtoResponse create(Venda.DtoRequest v) throws Exception {
        RequestResponse clienteExiste = new RequestResponse(false);
        RequestResponse estoqueExiste = new RequestResponse(false);

            webClientBuilder.build()
                .get()
                .uri("http://CLIENTE/cliente/{clienteId}", v.getIdCliente())
                .retrieve()
                .onStatus(
                        HttpStatusCode::is2xxSuccessful,
                        status -> {
                            clienteExiste.setExiste(true);
                            return Mono.empty();
                        }
                )
                .bodyToMono(String.class)
                .block();

            webClientBuilder.build()
                .get()
                .uri("http://ESTOQUE/estoque/{codProd}", v.getCodProd())
                .retrieve()
                .onStatus(
                        HttpStatusCode::is2xxSuccessful,
                        status -> {
                            estoqueExiste.setExiste(true);
                            return Mono.empty();
                        }
                )
                .bodyToMono(String.class)
                .block();

        if(clienteExiste.getExiste() && estoqueExiste.getExiste()){
            Venda venda = Venda.DtoRequest.convertToEntity(v, mapper);
            return Venda.DtoResponse.convertToDto(this.repository.save(venda), mapper);
        }else{
            throw new Exception("Um dos parametros passados esta incorreto!!!");
        }
    }
}