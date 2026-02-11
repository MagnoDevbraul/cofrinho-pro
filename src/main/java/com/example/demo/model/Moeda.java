package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;

@Entity
@Table(name = "moedas")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_moeda")
public abstract class Moeda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Adicionado para resolver o erro 'cannot find symbol'

    protected double valor;

    @Column(name = "data_deposito")
    private LocalDateTime dataDeposito = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Moeda() {}

    public Moeda(double valor) {
        this.valor = valor;
    }

    // Getters e Setters Essenciais
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDataDeposito() { return dataDeposito; }

    @JsonProperty("tipo") // Alterado para não conflitar com o campo 'nome'
    public String getTipoClasse() {
        return this.getClass().getSimpleName();
    }

    public abstract double converter();
}