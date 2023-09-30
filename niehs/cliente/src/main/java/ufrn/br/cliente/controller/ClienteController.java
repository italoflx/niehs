package ufrn.br.cliente.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ufrn.br.cliente.model.Cliente;
import ufrn.br.cliente.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/cliente")

public class ClienteController{

    ModelMapper mapper;
    ClienteService service;

    public ClienteController(ClienteService service, ModelMapper mapper){
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public List<Cliente.DtoResponse> list(){
        return this.service.list().stream().map(
                elementoAtual -> {
                    Cliente.DtoResponse response = Cliente.DtoResponse.convertToDto(elementoAtual, mapper);
                    return response;
                }
        ).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente.DtoResponse create(@RequestBody Cliente.DtoRequest e){
        Cliente cliente = this.service.create(Cliente.DtoRequest.convertToEntity(e, mapper));
        Cliente.DtoResponse response = Cliente.DtoResponse.convertToDto(cliente, mapper);
        return response;
    }

    @GetMapping("{id}")
    public Cliente.DtoResponse getById(@PathVariable String id){
        Cliente cliente = this.service.getById(id);
        Cliente.DtoResponse response = Cliente.DtoResponse.convertToDto(cliente, mapper);
        return response;
    }

    @PutMapping("{id}")
    public Cliente update(@RequestBody Cliente e, @PathVariable String id){
        return this.service.update(e, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        this.service.delete(id);
    }
}


