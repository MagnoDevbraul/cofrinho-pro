package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * CLASSE RUPIA (Especialização de Moeda)
 * Representa a moeda utilizada em mercados como a Índia.
 * O uso de @DiscriminatorValue("Rupia") é fundamental para a estratégia
 * de Herança SINGLE_TABLE no PostgreSQL, permitindo que o JPA identifique
 * o tipo correto de moeda sem precisar de tabelas separadas.
 */
@Entity
@DiscriminatorValue("Rupia")
public class Rupia extends Moeda {

    // Taxa de conversão específica (Exemplo: Rupia para Real)
    private static final double TAXA_CONVERSAO = 0.065;

    /**
     * Construtor padrão
     * Exigência do framework Hibernate para operações de leitura do banco de dados.
     */
    public Rupia() {
        super();
    }

    /**
     * Construtor com valor inicial
     * Repassa o saldo para a superclasse Moeda, que é quem gerencia o atributo 'valor'.
     */
    public Rupia(double valor) {
        super(valor);
    }

    /**
     * POLIMORFISMO
     * Implementa o cálculo de conversão específico da Rupia.
     * Através deste método, o sistema consegue exibir o saldo convertido no Dashboard.
     */
    @Override
    public double converter() {
        return valor * TAXA_CONVERSAO;
    }
}