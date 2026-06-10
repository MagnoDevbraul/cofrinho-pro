package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Entidade que representa a tabela de usuários no banco de dados.
 */
@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "O username deve ter no mínimo 3 caracteres")
    @Size(min = 3, message = "O username deve ter no mínimo 3 caracteres")
    private String username;

    /**
     * Senha criptografada armazenada no banco.
     * Nunca deve ser enviada para o frontend.
     */
    @JsonIgnore
    @Column(nullable = false)
    @NotBlank(message = "A senha não pode estar vazia")
    private String password;

    /**
     * Palavra-chave utilizada para recuperação de senha.
     * Também não deve ser enviada para o frontend.
     */
    @JsonIgnore
    private String palavraChave;
}