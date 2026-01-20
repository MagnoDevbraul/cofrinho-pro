package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * CLASSE REAL (Moeda Base do Sistema)
 * Representa a moeda nacional brasileira. No contexto deste sistema,
 * o Real serve como a unidade de medida comum para todos os cálculos.
 */
@Entity
@DiscriminatorValue("Real") // Define a identidade da classe na tabela única de moedas
public class Real extends Moeda {

    /**
     * Construtor padrão
     * Necessário para o framework JPA/Hibernate realizar a persistência de dados.
     */
    public Real() {}

    /**
     * Construtor com inicialização de saldo
     * Invoca o construtor da superclasse Moeda para definir o valor inicial.
     */
    public Real(double valor) {
        super(valor);
    }

    /**
     * IMPLEMENTAÇÃO DO MÉTODO DE CONVERSÃO
     * Como o sistema utiliza o Real como base para o Dashboard,
     * o fator de conversão aqui é 1:1, retornando o próprio valor.
     */
    @Override
    public double converter() {
        return this.valor;
    }
}