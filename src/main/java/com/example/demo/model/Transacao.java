package com.example.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ENTIDADE DE TRANSAÇÃO
 * Esta classe registra o histórico de movimentações financeiras.
 */
@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "moeda_id", nullable = false)
    private Moeda moeda;

    private BigDecimal valorAlterado;

    private String tipo;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // CONSTRUTOR VAZIO OBRIGATÓRIO
    public Transacao() {
    }

    // CONSTRUTOR AUXILIAR
    public Transacao(
            Moeda moeda,
            BigDecimal valorAlterado,
            String tipo,
            LocalDateTime dataHora,
            Usuario usuario) {

        this.moeda = moeda;
        this.valorAlterado = valorAlterado;
        this.tipo = tipo;
        this.dataHora = dataHora;
        this.usuario = usuario;
    }

    //  Getters E Setters
    public Long getId() { return id; }

    public Moeda getMoeda() {
        return moeda;
    }

    public void setMoeda(Moeda moeda) {
        this.moeda = moeda;
    }

    public java.math.BigDecimal getValorAlterado() {
        return valorAlterado;
    }

    public void setValorAlterado(java.math.BigDecimal valorAlterado) {
        this.valorAlterado = valorAlterado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public java.time.LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(java.time.LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}