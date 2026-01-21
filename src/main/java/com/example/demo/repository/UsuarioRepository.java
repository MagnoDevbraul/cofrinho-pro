package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * REPOSITÓRIO DE USUÁRIOS
 * Interface responsável pela persistência e busca de dados de autenticação.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * QUERY METHOD: Busca um usuário pelo nome de usuário.
     * * @param username O nome de usuário digitado no login.
     * @return Retorna um Optional<Usuario>. O uso de 'Optional' é uma boa prática
     * do Java 8+ que ajuda a evitar o erro 'NullPointerException', forçando quem
     * chama o método a tratar o caso onde o usuário não existe.
     */
    Optional<Usuario> findByUsername(String username);
}