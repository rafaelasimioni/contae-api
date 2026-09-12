package br.com.contae.api.categoria.Controller;

import br.com.contae.api.categoria.dto.CategoriaRequestDTO;
import br.com.contae.api.categoria.dto.CategoriaResponseDTO;
import br.com.contae.application.categoria.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "Operações relacionadas às categorias financeiras do usuário")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar categorias", description = "Retorna todas as categorias cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    public List<CategoriaResponseDTO> listar() {
        return categoriaService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna os dados de uma categoria específica.")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Criar categoria", description = "Cria uma nova categoria a partir dos dados informados.")
    @ApiResponse(responseCode = "200", description = "Categoria criada com sucesso")
    public CategoriaResponseDTO salvar(@RequestBody CategoriaRequestDTO dto) {
        return categoriaService.salvar(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir categoria", description = "Remove a categoria pelo ID informado.")
    @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public void excluir(@PathVariable Long id) {
        categoriaService.excluir(id);
    }
}
