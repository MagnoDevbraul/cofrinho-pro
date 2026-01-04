package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;

@Entity
@Table(name = "moedas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_moeda")
public abstract class Moeda {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected double valor;

    @Column(name = "data_deposito")
    private LocalDateTime dataDeposito = LocalDateTime.now();

    public Moeda() {}

    public Moeda(double valor) {
        this.valor = valor;
    }

    public void setId(Long id) { this.id = id; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDataDeposito() { return dataDeposito; }

    @JsonProperty("nome")
    public String getNome() {
        return this.getClass().getSimpleName();
    }

    public abstract double converter();
}