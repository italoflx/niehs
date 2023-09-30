package ufrn.br.venda.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ufrn.br.venda.entities.Estoque;
import ufrn.br.venda.entities.ListOfEstoqueResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class ClienteClient {

    private final WebClient webClient;

    public ClienteClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://localhost:8080/CLIENTE-SERVICE").build();
    }

    public Mono<Estoque.DtoResponse> findClientById(String id) {
        log.info("Buscando cliente com o id [{}]", id);
        return webClient
                .get()
                .uri("/" + id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Estoque.DtoResponse.class);
    }

    public Flux<ListOfEstoqueResponse> ListAllItensOfEstoque() {
        log.info("Listando todos os clientes cadastrados");
        return webClient
                .get()
                .uri("/")
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ListOfEstoqueResponse.class);
    }

}