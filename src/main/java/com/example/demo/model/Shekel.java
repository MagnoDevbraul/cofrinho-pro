package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * CLASSE SHEKEL (Especialização de Moeda)
 * Representa o Novo Shekel Israelense (ILS).
 * Esta classe utiliza a anotação @DiscriminatorValue para garantir que o
 * Hibernate filtre corretamente os dados dentro da estratégia de herança SINGLE_TABLE.
 */
@Entity
@DiscriminatorValue("Shekel")
public class Shekel extends Moeda {

    // Taxa de conversão específica para o Shekel (exemplo: ILS -> BRL)
    private static final double TAXA_CONVERSAO = 1.65;

    /**
     * Construtor padrão (Vazio)
     * Requisito técnico fundamental para que o JPA consiga realizar
     * a reconstrução do objeto ao consultar o PostgreSQL.
     */
    public Shekel() {
        super();
    }

    /**
     * Construtor com inicialização de valor
     * Passa o montante depositado para a classe base Moeda.
     */
    public Shekel(double valor) {
        super(valor);
    }

    /**
     * IMPLEMENTAÇÃO DE POLIMORFISMO
     * Sobrescreve o método de conversão aplicando a taxa de câmbio israelense.
     * Este método é chamado dinamicamente pelo Controller ou Service.
     */
    @Override
    public double converter() {
        return valor * TAXA_CONVERSAO;
    }
}