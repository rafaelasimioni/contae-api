package br.com.contae.api.categoria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados enviados pelo cliente para criar ou atualizar uma categoria")
public class CategoriaRequestDTO {

    @Schema(description = "Nome da categoria", example = "Alimentação")
    private String nome;

    @Schema(description = "ID do usuário dono da categoria", example = "1")
    private Long usuarioId;
}