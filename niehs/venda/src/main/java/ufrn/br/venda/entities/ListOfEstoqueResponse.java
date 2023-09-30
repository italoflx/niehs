package ufrn.br.venda.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class ListOfEstoqueResponse {
    List<Estoque> estoque;
}
