package com.example.demo.model; // Ajuste para o seu pacote novo

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Real") // Identifica que este registro no banco é um "Real"
public class Real extends Moeda {

    // Construtor vazio para o Hibernate
    public Real() {};

    public Real(double valor) {
        super(valor);
    }

    @Override
    public double converter() {
        return this.valor; // Já está em Real
    }
}