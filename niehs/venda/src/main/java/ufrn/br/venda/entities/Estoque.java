package ufrn.br.venda.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class Estoque {
    String codigo;
    Integer qtd;


    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Data
    public static class DtoResponse{
        String codigo;
        Integer quantidade;
        public static Estoque.DtoResponse convertToDto(Estoque e, ModelMapper mapper){
            return mapper.map(e, Estoque.DtoResponse.class);
        }
    }
}