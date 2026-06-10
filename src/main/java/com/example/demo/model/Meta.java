package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "metas", schema = "public")
@Getter @Setter
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "valor_objetivo", nullable = false)
    private BigDecimal valorObjetivo;

    @Column(name = "valor_atual", nullable = false)
    private BigDecimal valorAtual;

    @Column(name = "data_limite")
    private LocalDate dataLimite;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @Transient // Isso indica que o campo não precisa existir na tabela do banco
    private BigDecimal porcentagemConcluida;
}