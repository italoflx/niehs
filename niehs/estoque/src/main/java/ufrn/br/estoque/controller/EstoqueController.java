package ufrn.br.estoque.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ufrn.br.estoque.model.Estoque;
import ufrn.br.estoque.service.EstoqueService;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    EstoqueService service;
    ModelMapper mapper;

    public EstoqueController(EstoqueService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Estoque.DtoResponse> list(){
        return this.service.list().stream().map(
                elementoAtual -> {
                    Estoque.DtoResponse response = Estoque.DtoResponse.convertToDto(elementoAtual, mapper);
                    return response;
                }
        ).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estoque.DtoResponse create(@RequestBody Estoque.DtoRequest e){
        Estoque estoque = this.service.create(Estoque.DtoRequest.convertToEntity(e, mapper));
        Estoque.DtoResponse response = Estoque.DtoResponse.convertToDto(estoque, mapper);
        return response;
    }

    @GetMapping("{id}")
    public Estoque.DtoResponse getById(@PathVariable String id){
        Estoque estoque = this.service.getById(id);
        Estoque.DtoResponse response = Estoque.DtoResponse.convertToDto(estoque, mapper);
        return response;
    }

    @PutMapping("{id}")
    public Estoque update(@RequestBody Estoque e, @PathVariable String id){
        return this.service.update(e, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        this.service.delete(id);
    }
}