package com.example.demo.model; // Ajuste para o pacote do seu projeto

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Rupia") // Identifica no banco que esta linha é uma Rupia
public class Rupia extends Moeda {

    private static final double TAXA_CONVERSAO = 0.065;

    // Construtor vazio obrigatório para o Hibernate/JPA
    public Rupia() {

        super();
    }

    public Rupia(double valor) {

        super(valor);
    }

    @Override
    public double converter() {

        return valor * TAXA_CONVERSAO;
    }
}