package ufrn.br.venda.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ufrn.br.venda.entities.Estoque;
import ufrn.br.venda.model.Venda;
import ufrn.br.venda.entities.Cliente;
import ufrn.br.venda.service.VendaService;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {
    VendaService service;
    ModelMapper mapper;

    public VendaController(VendaService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Venda.DtoResponse> list(){
        return this.service.list().stream().map(
                elementoAtual -> {
                    Venda.DtoResponse response = Venda.DtoResponse.convertToDto(elementoAtual, mapper);
                    return response;
                }
        ).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venda.DtoResponse create(@RequestBody Venda.DtoRequest v) throws Exception {
        return this.service.create(v);
    }

    @PutMapping("{id}")
    public Venda.DtoResponse update(@RequestBody Venda e, @PathVariable String id){
        return this.service.update(e, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        this.service.delete(id);
    }
}
