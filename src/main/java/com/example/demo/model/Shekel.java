package com.example.demo.model; // Ajuste para o seu pacote

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Shekel")
public class Shekel extends Moeda {

    private static final double TAXA_CONVERSAO = 1.65;

    // Construtor vazio para o Hibernate
    public Shekel() {
        super();
    }

    public Shekel(double valor) {
        super(valor);
    }

    @Override
    public double converter() {

        return valor * TAXA_CONVERSAO;
    }
}