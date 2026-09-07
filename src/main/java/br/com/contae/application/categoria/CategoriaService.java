package br.com.contae.application.categoria;

import br.com.contae.api.categoria.dto.CategoriaResponseDTO;
import br.com.contae.api.categoria.dto.CategoriaRequestDTO;
import br.com.contae.api.categoria.mapper.CategoriaMapper;
import br.com.contae.domain.categoria.Categoria;
import br.com.contae.domain.usuario.Usuario;
import br.com.contae.infrastructure.categoria.CategoriaRepository;
import br.com.contae.infrastructure.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    // Lista todas as categorias, ja convertidas para DTO de saida.
    public List<CategoriaResponseDTO> listar() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaMapper::toResponseDTO)
                .toList();
    }

    // Cria uma nova categoria a partir do DTO de entrada.
    public CategoriaResponseDTO salvar(CategoriaRequestDTO dto) {
        // Busca o usuario dono da categoria pelo ID enviado no DTO.
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() ->
                        new RuntimeException("Usuario nao encontrado"));

        // Converte DTO + Usuario em entidade Categoria.
        Categoria categoria = CategoriaMapper.toEntity(dto, usuario);

        // Salva a entidade no banco.
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        // Converte a entidade salva de volta para DTO de saida.
        return CategoriaMapper.toResponseDTO(categoriaSalva);
    }

    // Busca uma categoria pelo ID e devolve ja como DTO de saida.
    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoria nao encontrada"));
        return CategoriaMapper.toResponseDTO(categoria);
    }

    // Exclui uma categoria pelo ID.
    public void excluir(Long id) {
        categoriaRepository.deleteById(id);
    }
}