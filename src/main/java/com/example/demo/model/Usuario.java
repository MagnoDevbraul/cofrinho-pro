package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidade que representa a tabela de usuários no banco de dados.
 * A anotação @Data do Lombok gera automaticamente Getters, Setters,
 * Equals, HashCode e ToString, reduzindo o código boilerplate.
 */
@Entity
@Table(name = "usuarios") // Define explicitamente o nome da tabela no PostgreSQL
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento gerenciado pelo banco de dados
    private Long id;

    // Constraint: O username deve ser único e não pode ser nulo
    @Column(unique = true, nullable = false)
    private String username;

    // A senha é obrigatória e será armazenada como um Hash BCrypt (via Controller)
    @Column(nullable = false)
    private String password;

    /**
     * Campo de segurança utilizado para a recuperação de senha.
     * Funciona como uma 'pergunta de segurança' para validar a identidade do usuário.
     */
    private String palavraChave;
}