package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * CLASSE WON (Especialização de Moeda)
 * Representa o Won Sul-Coreano (KRW).
 * Esta classe demonstra a versatilidade do sistema ao lidar com moedas
 * que possuem taxas de conversão muito baixas em relação ao Real.
 */
@Entity
@DiscriminatorValue("Won") // Identifica a especialização na tabela única do PostgreSQL
public class Won extends Moeda {

    // Taxa de conversão para o Won Sul-Coreano
    private static final double TAXA_CONVERSAO = 0.0042;

    /**
     * Construtor padrão
     * Requisito do JPA para a criação de instâncias via Reflexão durante a busca no banco.
     */
    public Won() {
        super();
    }

    /**
     * Construtor com parâmetro de valor
     * Garante que o saldo inicial seja repassado corretamente para a lógica da superclasse.
     */
    public Won(double valor) {
        super(valor);
    }

    /**
     * POLIMORFISMO EM AÇÃO
     * Implementa a lógica de conversão específica para o Won.
     * Mesmo com uma taxa de quatro casas decimais, o uso do tipo 'double'
     * garante a precisão necessária para o cálculo.
     */
    @Override
    public double converter() {
        return valor * TAXA_CONVERSAO;
    }
}