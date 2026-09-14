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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaRequestDTO {
    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(max = 100, message = "O nome da categoria deve ter no máximo 100 caracteres")
    private String nome;

    // Construtor vazio exigido para o Spring converter o JSON recebido.
    public CategoriaRequestDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
