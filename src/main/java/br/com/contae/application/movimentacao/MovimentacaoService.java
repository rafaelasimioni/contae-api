package br.com.contae.application.movimentacao;

import br.com.contae.api.movimentacao.dto.MovimentacaoRequestDTO;
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


    public Movimentacao criar (MovimentacaoRequestDTO dto){

        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Movimentacao movimentacao = MovimentacaoMapper.toEntity(dto,conta,categoria);

        return movimentacaoRepository.save(movimentacao);
    }

    //atualizar
    public Movimentacao atualizar (Long id, MovimentacaoRequestDTO dto){

        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new  RuntimeException ("Movimentação não encontrada"));

        Conta conta = contaRepository.findById(dto.getContaId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        MovimentacaoMapper.atualizar(movimentacao, dto , conta, categoria);

        return movimentacaoRepository.save(movimentacao);
    }

    //deletar

    public void deletar (Long id){
        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("movimentação não encontrada"));

        movimentacaoRepository.delete(movimentacao);
    }

    //buscar todas
    public List<Movimentacao> buscarTodas(){
        return movimentacaoRepository.findAll();
    }

    //buscar movimentacao por data
    public List<Movimentacao> buscarPorData(LocalDate data) {
        return movimentacaoRepository.findByData(data);
    }

    //buscar movimentacao por categoria
    public List<Movimentacao> buscarPorCategoria(Long categoriaId){
        return movimentacaoRepository.findByCategoriaId(categoriaId);
    }

    //buscar movimentacao por conta

    public List<Movimentacao> buscarPorConta(Long contaId){
        return movimentacaoRepository.findByContaId(contaId);
    }
}
