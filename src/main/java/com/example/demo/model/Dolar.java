package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * CLASSE DOLAR (Especialização de Moeda)
 * A anotação @Entity indica que esta classe faz parte do mapeamento ORM.
 * @DiscriminatorValue("Dolar") define o valor que será gravado na coluna
 * 'tipo_moeda' na tabela única do PostgreSQL.
 */
@Entity
@DiscriminatorValue("Dolar")
public class Dolar extends Moeda {

    // Constante que define a regra de negócio específica para esta moeda
    private static final double TAXA_CONVERSAO = 5.5;

    /**
     * Construtor padrão (Vazio)
     * Essencial para o Hibernate conseguir instanciar o objeto ao buscar dados do banco.
     */
    public Dolar() {
        super();
    }

    /**
     * Construtor com valor inicial
     * Chama o construtor da superclasse (Moeda) para inicializar o saldo.
     */
    public Dolar(double valor) {
        super(valor);
    }

    /**
     * IMPLEMENTAÇÃO DO POLIMORFISMO
     * Sobrescreve o método abstrato da classe pai para aplicar a
     * taxa de conversão específica do Dólar para Real.
     */
    @Override
    public double converter() {
        return valor * TAXA_CONVERSAO;
    }
}