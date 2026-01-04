package com.example.demo.model; // Ajuste do pacote para o novo projeto

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Dolar") // Identifica no banco que esta linha é um Dólar
public class Dolar extends Moeda {

    private static final double TAXA_CONVERSAO = 5.5;

    // Construtor vazio obrigatório para o Hibernate/JPA
    public Dolar() {
        super();
    }

    public Dolar(double valor) {

        super(valor);
    }

    @Override
    public double converter() {

        return valor * TAXA_CONVERSAO;
    }
}