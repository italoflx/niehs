package ufrn.br.estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Estoque {
    @Id
    String codigo;
    Integer quantidade;

    @Data
    public static class DtoRequest{
        String codigo;
        Integer quantidade;
        public static Estoque convertToEntity(Estoque.DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Estoque.class);
        }
    }

    @Data
    public static class DtoResponse{
        String codigo;
        Integer quantidade;
        public static Estoque.DtoResponse convertToDto(Estoque e, ModelMapper mapper){
            return mapper.map(e, Estoque.DtoResponse.class);
        }
    }
}