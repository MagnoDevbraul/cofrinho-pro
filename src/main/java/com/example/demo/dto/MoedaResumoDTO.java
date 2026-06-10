package com.example.demo.dto;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class MoedaResumoDTO {

    private Long id;
    private String nome;
    private String codigo;
    private BigDecimal saldo;
    private BigDecimal valorEmReal;
    private BigDecimal metaEmReal;

    public MoedaResumoDTO(Long id, String nome, String codigo, BigDecimal saldo, BigDecimal valorEmReal, BigDecimal metaEmReal) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.saldo = saldo;
        this.valorEmReal = valorEmReal;
        this.metaEmReal = metaEmReal;
    }
}