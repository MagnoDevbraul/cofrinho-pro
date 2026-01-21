package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * CLASSE EURO (Especialização de Moeda)
 * Demonstra a aplicação prática de Herança para moedas internacionais.
 * O @DiscriminatorValue assegura que o JPA filtre corretamente os registros
 * na tabela única de moedas do PostgreSQL.
 */
@Entity
@DiscriminatorValue("Euro")
public class Euro extends Moeda {

    // Taxa de câmbio específica para conversão Euro -> Real
    private static final double TAXA_CONVERSAO = 6.34;

    /**
     * Construtor padrão
     * Necessário para que o framework possa reconstruir o objeto
     * a partir de uma linha da tabela SQL.
     */
    public Euro() {
        super();
    }

    /**
     * Construtor de inicialização
     * Repassa o saldo inicial para a lógica de persistência da superclasse.
     */
    public Euro(double valor) {
        super(valor);
    }

    /**
     * SOBRESCRITA (Override)
     * Implementa o cálculo de conversão específico utilizando
     * a taxa de câmbio do mercado europeu definida nesta classe.
     */
    @Override
    public double converter() {
        return valor * TAXA_CONVERSAO;
    }
}