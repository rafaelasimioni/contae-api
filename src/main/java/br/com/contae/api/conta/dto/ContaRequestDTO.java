package br.com.contae.api.conta.dto;

import br.com.contae.domain.conta.TipoConta;

import java.math.BigDecimal;

public class ContaRequestDTO {

    private String nome;
    private TipoConta tipo;
    private BigDecimal saldo;
    private Long usuarioId;

    public ContaRequestDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}