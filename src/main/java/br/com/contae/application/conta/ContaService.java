package br.com.contae.application.conta;

import br.com.contae.api.conta.dto.ContaRequestDTO;
import br.com.contae.api.conta.dto.ContaResponseDTO;
import br.com.contae.api.conta.mapper.ContaMapper;
import br.com.contae.domain.conta.Conta;
import br.com.contae.domain.usuario.Usuario;
import br.com.contae.infrastructure.conta.ContaRepository;
import br.com.contae.infrastructure.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final UsuarioRepository usuarioRepository;

    public ContaService(ContaRepository contaRepository, UsuarioRepository usuarioRepository) {
        this.contaRepository = contaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ContaResponseDTO> listar() {
        return contaRepository.findAll()
                .stream()
                .map(ContaMapper::toResponseDTO)
                .toList();
    }

    public List<ContaResponseDTO> listarPorUsuario(Long usuarioId) {
        return contaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(ContaMapper::toResponseDTO)
                .toList();
    }

    public ContaResponseDTO buscarPorId(Long id) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta nao encontrada"));
        return ContaMapper.toResponseDTO(conta);
    }

    public ContaResponseDTO salvar(ContaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        Conta conta = ContaMapper.toEntity(dto, usuario);
        Conta contaSalva = contaRepository.save(conta);
        return ContaMapper.toResponseDTO(contaSalva);
    }

    public void excluir(Long id) {
        contaRepository.deleteById(id);
    }
}