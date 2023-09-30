package ufrn.br.venda.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

@Data
public class Cliente {
    @Id
    String cpf;
    String nome;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Data
    public static class DtoResponse{
        String cpf;
        Integer nome;
        public static Cliente.DtoResponse convertToDto(Cliente c, ModelMapper mapper){
            return mapper.map(c, Cliente.DtoResponse.class);
        }
    }

    @Data
    public static class DtoRequest{
        String cpf;
        Integer nome;
        public static Cliente convertToEntity(Cliente.DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Cliente.class);
        }
    }
}