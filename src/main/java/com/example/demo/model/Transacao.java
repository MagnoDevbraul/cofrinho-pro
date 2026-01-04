package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String moedaNome;
    private Double valorAlterado;
    private String tipo;
    private LocalDateTime dataHora;

    public Transacao() {}

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