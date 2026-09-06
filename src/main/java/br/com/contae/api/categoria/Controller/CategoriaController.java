package br.com.contae.api.categoria.Controller;

import br.com.contae.api.categoria.dto.CategoriaRequestDTO;
import br.com.contae.api.categoria.dto.CategoriaResponseDTO;
import br.com.contae.application.categoria.CategoriaService;
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
    public List<CategoriaResponseDTO> listar() {
        return categoriaService.listar();
    }

    // GET /categorias/1
    // Busca uma categoria pelo ID.
    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id);
    }

    // POST /categorias
    // Cria uma nova categoria a partir do CategoriaRequestDTO.
    @PostMapping
    public CategoriaResponseDTO salvar(@RequestBody CategoriaRequestDTO dto) {
        return categoriaService.salvar(dto);
    }

    // DELETE /categorias/1
    // Exclui a categoria de ID 1.
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        categoriaService.excluir(id);
    }
}