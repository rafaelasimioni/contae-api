package br.com.contae.api.usuario.mapper;

import br.com.contae.api.usuario.dto.UsuarioRequestDTO;
import br.com.contae.api.usuario.dto.UsuarioResponseDTO;
import br.com.contae.domain.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(dto.senha())
                .build();
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataCriacao(),
                usuario.isAtivo()
        );
    }

    public void atualizarEntity(Usuario usuario, UsuarioRequestDTO dto) {
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
    }
}