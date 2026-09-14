package br.com.contae.application.movimentacao;

import br.com.contae.api.movimentacao.dto.MovimentacaoRequestDTO;
import br.com.contae.api.movimentacao.dto.MovimentacaoResponseDTO;
import br.com.contae.api.movimentacao.mapper.MovimentacaoMapper;
import br.com.contae.domain.categoria.Categoria;
import br.com.contae.domain.conta.Conta;
import br.com.contae.domain.movimentacao.Movimentacao;
import br.com.contae.infrastructure.categoria.CategoriaRepository;
import br.com.contae.infrastructure.conta.ContaRepository;
import br.com.contae.infrastructure.movimentacao.MovimentacaoRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ContaRepository contaRepository;
    private final CategoriaRepository categoriaRepository;

    // Criar
    public MovimentacaoResponseDTO criar(MovimentacaoRequestDTO dto) {

        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Movimentacao movimentacao = MovimentacaoMapper.toEntity(
                dto,
                conta,
                categoria
        );

        Movimentacao salva = movimentacaoRepository.save(movimentacao);

        return MovimentacaoMapper.toResponseDTO(salva);
    }

    // Atualizar
    public MovimentacaoResponseDTO atualizar(
            Long id,
            MovimentacaoRequestDTO dto) {

        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Movimentação não encontrada"));

        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() ->
                        new RuntimeException("Conta não encontrada"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() ->
                        new RuntimeException("Categoria não encontrada"));

        MovimentacaoMapper.atualizar(
                movimentacao,
                dto,
                conta,
                categoria
        );

        Movimentacao atualizada = movimentacaoRepository.save(movimentacao);

        return MovimentacaoMapper.toResponseDTO(atualizada);
    }

    // Deletar
    public void deletar(Long id) {

        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Movimentação não encontrada"));

        movimentacaoRepository.delete(movimentacao);
    }

    // Buscar todas
    public List<MovimentacaoResponseDTO> buscarTodas() {

        return movimentacaoRepository.findAll()
                .stream()
                .map(MovimentacaoMapper::toResponseDTO)
                .toList();
    }

    // Buscar movimentações por data
    public List<MovimentacaoResponseDTO> buscarPorData(LocalDate data) {

        return movimentacaoRepository.findByData(data)
                .stream()
                .map(MovimentacaoMapper::toResponseDTO)
                .toList();
    }

    // Buscar movimentações por categoria
    public List<MovimentacaoResponseDTO> buscarPorCategoria(Long categoriaId) {

        return movimentacaoRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(MovimentacaoMapper::toResponseDTO)
                .toList();
    }

    // Buscar movimentações por conta
    public List<MovimentacaoResponseDTO> buscarPorConta(Long contaId) {

        return movimentacaoRepository.findByContaId(contaId)
                .stream()
                .map(MovimentacaoMapper::toResponseDTO)
                .toList();
    }
}