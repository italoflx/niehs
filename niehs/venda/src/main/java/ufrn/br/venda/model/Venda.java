package ufrn.br.venda.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String Id;
    String idCliente;
    String cpfCliente;
    Integer qtdVendida;
    String codProd;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Data
    public static class DtoResponse{
        String cpfCliente;
        Integer qtdVendida;
        String codProd;
        public static Venda.DtoResponse convertToDto(Venda v, ModelMapper mapper){
            return mapper.map(v, Venda.DtoResponse.class);
        }
    }

    @Data
    public static class DtoRequest{
        String idCliente;
        String cpfCliente;
        String codProd;
        Integer qtdVendida;
        public static Venda convertToEntity(Venda.DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Venda.class);
        }
    }
}
