package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Won")
public class Won extends Moeda {

    private static final double TAXA_CONVERSAO = 0.0042;

    public Won() {

        super();
    }

    public Won(double valor) {

        super(valor);
    }

    @Override
    public double converter() {
        return valor * TAXA_CONVERSAO;
    }
}