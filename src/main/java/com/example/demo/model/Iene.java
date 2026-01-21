package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * CLASSE IENE (Especialização de Moeda)
 * Representa a moeda japonesa no sistema.
 * Segue a estratégia de Single Table Inheritance, utilizando o
 * DiscriminatorValue para se diferenciar na tabela 'moedas'.
 */
@Entity
@DiscriminatorValue("Iene")
public class Iene extends Moeda {

    // Taxa de câmbio específica para o Iene Japonês (JPY)
    private static final double TAXA_CONVERSAO = 0.36;

    /**
     * Construtor padrão
     * Exigido pelo JPA para converter as linhas do banco em objetos Java.
     */
    public Iene() {
        super();
    }

    /**
     * Construtor com inicialização de saldo
     * Repassa o valor para a superclasse Moeda, que gerencia o estado.
     */
    public Iene(double valor) {
        super(valor);
    }

    /**
     * IMPLEMENTAÇÃO DO MÉTODO ABSTRATO
     * Aplica a lógica de conversão específica para a moeda japonesa.
     * Este é o ponto chave do Polimorfismo no projeto.
     */
    @Override
    public double converter() {
        return valor * TAXA_CONVERSAO;
    }
}