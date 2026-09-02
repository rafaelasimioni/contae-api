package br.com.contae.api.categoria.dto;

public class CategoriaRequestDTO {
    // Nome da categoria informado pelo cliente.
    private String nome;

    // ID do usuario dono da categoria.
    private Long usuarioId;

    // Construtor vazio exigido para o Spring converter o JSON recebido.
    public CategoriaRequestDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
