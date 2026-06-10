package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoDTO {

    private Long id;
    private String moeda;
    private String codigo;
    private BigDecimal valorAlterado;
    private String tipo;
    private LocalDateTime dataHora;

    public TransacaoDTO(
            Long id,
            String moeda,
            String codigo,
            BigDecimal valorAlterado,
            String tipo,
            LocalDateTime dataHora) {

        this.id = id;
        this.moeda = moeda;
        this.codigo = codigo;
        this.valorAlterado = valorAlterado;
        this.tipo = tipo;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public String getMoeda() {
        return moeda;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getValorAlterado() {
        return valorAlterado;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}