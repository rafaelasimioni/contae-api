package br.com.contae.api.categoria.dto;

public class CategoriaResponseDTO {


    private Long id;
    private String nome;
    private Long usuarioId;

    // Construtor vazio exigido para o Spring converter para JSON.
    public CategoriaResponseDTO() {
    }

    // Construtor usado pelo CategoriaMapper para montar a resposta.
    public CategoriaResponseDTO(Long id, String nome, Long usuarioId) {
        this.id = id;
        this.nome = nome;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
//