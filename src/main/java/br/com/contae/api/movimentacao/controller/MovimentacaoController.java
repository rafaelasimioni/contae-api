package br.com.contae.api.movimentacao.controller;

import br.com.contae.api.movimentacao.dto.MovimentacaoRequestDTO;
import br.com.contae.domain.movimentacao.Movimentacao;
import br.com.contae.application.movimentacao.MovimentacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    // Criar
    @PostMapping
    public ResponseEntity<Movimentacao> criar(
            @RequestBody MovimentacaoRequestDTO dto) {

        Movimentacao movimentacao = movimentacaoService.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movimentacao);
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Movimentacao> atualizar(
            @PathVariable Long id,
            @RequestBody MovimentacaoRequestDTO dto) {

        Movimentacao movimentacao = movimentacaoService.atualizar(id, dto);

        return ResponseEntity.ok(movimentacao);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        movimentacaoService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    // Buscar todas
    @GetMapping
    public ResponseEntity<List<Movimentacao>> buscarTodas() {

        return ResponseEntity.ok(movimentacaoService.buscarTodas());
    }

    // Buscar por data
    @GetMapping("/data/{data}")
    public ResponseEntity<List<Movimentacao>> buscarPorData(
            @PathVariable LocalDate data) {

        return ResponseEntity.ok(movimentacaoService.buscarPorData(data));
    }

    // Buscar por categoria
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Movimentacao>> buscarPorCategoria(
            @PathVariable Long categoriaId) {

        return ResponseEntity.ok(
                movimentacaoService.buscarPorCategoria(categoriaId)
        );
    }

    // Buscar por conta
    @GetMapping("/conta/{contaId}")
    public ResponseEntity<List<Movimentacao>> buscarPorConta(
            @PathVariable Long contaId) {

        return ResponseEntity.ok(
                movimentacaoService.buscarPorConta(contaId)
        );
    }
}