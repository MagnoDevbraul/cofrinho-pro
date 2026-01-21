package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * ENTIDADE DE TRANSAÇÃO
 * Esta classe é responsável por registrar o log de movimentações financeiras.
 * Diferente da classe Moeda, que guarda o saldo atual, esta guarda o rastro de operações.
 */
@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome da moeda que sofreu a alteração (ex: "Dolar", "Real")
    private String moedaNome;

    // O valor exato que foi adicionado ou removido no momento da operação
    private Double valorAlterado;

    // Define a natureza da operação: "DEPÓSITO" ou "RETIRADA"
    private String tipo;

    // Registro temporal preciso (Data e Hora) da ocorrência
    private LocalDateTime dataHora;

    /**
     * Construtor padrão exigido pelo JPA para persistência.
     */
    public Transacao() {}

    /* * Getters e Setters:
     * Permitem que o Spring Data e o Jackson (JSON) acessem os dados
     * para salvar no banco ou enviar para a tabela de histórico no Frontend.
     */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMoedaNome() { return moedaNome; }
    public void setMoedaNome(String moedaNome) { this.moedaNome = moedaNome; }

    public Double getValorAlterado() { return valorAlterado; }
    public void setValorAlterado(Double valorAlterado) { this.valorAlterado = valorAlterado; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}