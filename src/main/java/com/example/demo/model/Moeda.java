package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;

/**
 * CLASSE ABSTRATA MOEDA (Base para o Polimorfismo)
 * * @Inheritance(strategy = InheritanceType.SINGLE_TABLE):
 * Estratégia de "Tabela Única". Todas as moedas (Real, Dolar, etc) ficam na mesma tabela 'moedas',
 * o que melhora a performance em consultas globais.
 * * @DiscriminatorColumn: Cria uma coluna especial 'tipo_moeda' no PostgreSQL
 * para identificar se aquela linha representa um Real, Dolar, Euro, etc.
 */
@Entity
@Table(name = "moedas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_moeda")
public abstract class Moeda {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 'protected' permite que as classes filhas acessem o valor diretamente
    protected double valor;

    @Column(name = "data_deposito")
    private LocalDateTime dataDeposito = LocalDateTime.now();

    // Construtor padrão exigido pelo JPA
    public Moeda() {}

    public Moeda(double valor) {
        this.valor = valor;
    }

    public void setId(Long id) { this.id = id; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    /**
     * Formata a data para um padrão legível no JSON retornado ao Frontend.
     */
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDataDeposito() { return dataDeposito; }

    /**
     * @JsonProperty("nome"): Expõe o nome da classe (ex: "Real", "Dolar")
     * como um atributo 'nome' no JSON, facilitando a identificação no Dashboard.
     */
    @JsonProperty("nome")
    public String getNome() {
        return this.getClass().getSimpleName();
    }

    /**
     * MÉTODO ABSTRATO: Obriga cada moeda filha a implementar sua própria
     * lógica de conversão, garantindo o comportamento polimórfico.
     */
    public abstract double converter();
}