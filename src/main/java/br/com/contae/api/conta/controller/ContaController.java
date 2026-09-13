package br.com.contae.api.conta.controller;

import br.com.contae.api.conta.dto.ContaRequestDTO;
import br.com.contae.api.conta.dto.ContaResponseDTO;
import br.com.contae.application.conta.ContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public List<ContaResponseDTO> listar() {
        return contaService.listar();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ContaResponseDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return contaService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public ContaResponseDTO buscarPorId(@PathVariable Long id) {
        return contaService.buscarPorId(id);
    }

    @PostMapping
    public ContaResponseDTO salvar(@RequestBody ContaRequestDTO dto) {
        return contaService.salvar(dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        contaService.excluir(id);
    }
}