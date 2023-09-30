package ufrn.br.cliente.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Cliente{
    @Id
    String id;
    String cpf;
    String nome;

    @Data
    public static class DtoRequest{
        String cpf;
        String nome;
        public static Cliente convertToEntity(Cliente.DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Cliente.class);
        }
    }

    @Data
    public static class DtoResponse{
        String cpf;
        String nome;
        public static Cliente.DtoResponse convertToDto(Cliente e, ModelMapper mapper){
            return mapper.map(e, Cliente.DtoResponse.class);
        }
    }
}