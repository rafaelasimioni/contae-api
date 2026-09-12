package br.com.contae.api.categoria.Controller;

import br.com.contae.api.categoria.dto.CategoriaRequestDTO;
import br.com.contae.api.categoria.dto.CategoriaResponseDTO;
import br.com.contae.application.categoria.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // GET /categorias
    // Lista todas as categorias.
    @GetMapping
    public List<CategoriaResponseDTO> listar(Authentication authentication) {
        return categoriaService.listar(authentication.getName());
    }

    // GET /categorias/1
    // Busca uma categoria pelo ID.
    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id, Authentication authentication) {
        return categoriaService.buscarPorId(id, authentication.getName());
    }

    // POST /categorias
    // Cria uma nova categoria a partir do CategoriaRequestDTO.
    @PostMapping
    public CategoriaResponseDTO salvar(@Valid @RequestBody CategoriaRequestDTO dto,
                                       Authentication authentication) {
        return categoriaService.salvar(dto, authentication.getName());
    }

    // DELETE /categorias/1
    // Exclui a categoria de ID 1.
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id, Authentication authentication) {
        categoriaService.excluir(id, authentication.getName());
    }
}