package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Yuan")
public class Yuan extends Moeda {

    private static final double TAXA_CONVERSAO = 0.76;

    // Construtor vazio para o Hibernate
    public Yuan() {
        super();
    }

    public Yuan(double valor) {
        super(valor);
    }

    @Override
    public double converter() {
        return this.valor * TAXA_CONVERSAO;
    }
}