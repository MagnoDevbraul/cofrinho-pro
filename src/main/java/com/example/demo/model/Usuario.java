package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidade que representa a tabela de usuários no banco de dados.
 * A anotação @Data do Lombok gera automaticamente Getters, Setters,
 * Equals, HashCode e ToString, reduzindo o código boilerplate.
 */
@Data
@Entity
@Table(name = "usuarios") // Define explicitamente o nome da tabela no PostgreSQL
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento gerenciado pelo banco de dados
    private Long id;

    // Constraint: O username deve ser único e não pode ser nulo
    @Column(unique = true, nullable = false)
    @NotBlank(message = "O username deve ter no mínimo 3 caracteres")
    @Size(min = 3, message = "O username deve ter no mínimo 3 caracteres")
    private String username;

    // A senha é obrigatória e será armazenada
    // como um Hash BCrypt (via Controller)
    @Column(nullable = false)
    @NotBlank(message = "A senha não pode estar vazia")
    private String password;

    /**
     * Campo de segurança utilizado para a recuperação de senha.
     * Funciona como uma 'pergunta de segurança' para validar a identidade do usuário.
     */
    private String palavraChave;
}