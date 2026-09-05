package br.com.contae.api.usuario.dto;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        LocalDateTime dataCriacao,
        boolean ativo
) {
}