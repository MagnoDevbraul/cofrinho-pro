package com.example.demo.model; // Ajuste para o pacote do seu novo projeto

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Euro") // Identifica no banco que esta linha é um Euro
public class Euro extends Moeda {

    private static final double TAXA_CONVERSAO = 6.34;

    // Construtor vazio obrigatório para o Hibernate/JPA
    public Euro() {
        super();
    }

    public Euro(double valor) {

        super(valor);
    }

    @Override
    public double converter() {

        return valor * TAXA_CONVERSAO;
    }
}