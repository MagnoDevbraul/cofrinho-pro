package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "moedas")
public class Moeda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Ex: "Dólar Carteira"

    private String codigo; // Ex: USD, BRL, EUR

    // Saldo atual nominal da moeda no cofrinho
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal saldo = BigDecimal.ZERO;

    // NOVO CAMPO: Meta financeira convertida em Real (Ex: R$ 10.000,00)
    @Column(name = "meta_em_real", nullable = false, precision = 19, scale = 4)
    private BigDecimal metaEmReal = BigDecimal.ZERO;

    // Relacionamento com usuário dono da moeda
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Construtor Padrão (Obrigatório pelo JPA)
    public Moeda() {
        this.saldo = BigDecimal.ZERO;
        this.metaEmReal = BigDecimal.ZERO; // Segurança extra contra valores nulos
    }

    // Construtor Completo atualizado
    public Moeda(String nome, String codigo, BigDecimal saldo, BigDecimal metaEmReal, Usuario usuario) {
        this.nome = nome;
        this.codigo = codigo;
        this.saldo = saldo != null ? saldo : BigDecimal.ZERO;
        this.metaEmReal = metaEmReal != null ? metaEmReal : BigDecimal.ZERO;
        this.usuario = usuario;
    }

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getSaldo() {
        return saldo != null ? saldo : BigDecimal.ZERO;
    }

    public BigDecimal getMetaEmReal() {
        return metaEmReal != null ? metaEmReal : BigDecimal.ZERO;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    // ===== SETTERS =====

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo != null ? saldo : BigDecimal.ZERO;
    }

    public void setMetaEmReal(BigDecimal metaEmReal) {
        this.metaEmReal = metaEmReal != null ? metaEmReal : BigDecimal.ZERO;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}