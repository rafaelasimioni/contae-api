package br.com.contae.domain.categoria;

import br.com.contae.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categorias")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Categoria {

    // Identificador unico da categoria.
    // O banco de dados gera o ID automaticamente.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // cria a coluna usuario_id na tabela categorias.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Nome da categoria.
    @Setter
    @Column(nullable = false, length = 100)
    private String nome;

    // Construtor utilizado para criar uma categoria.
    public Categoria(Usuario usuario, String nome) {
        this.usuario = usuario;
        this.nome = nome;
    }
}