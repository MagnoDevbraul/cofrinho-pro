package com.example.demo.config;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * SERVIÇO DE AUTENTICAÇÃO CUSTOMIZADO
 * Esta classe é o coração da segurança do projeto. Ela traduz a nossa entidade
 * 'Usuario' para o formato que o Spring Security entende ('UserDetails').
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    /**
     * MÉTODO DE CARREGAMENTO DE USUÁRIO
     * O Spring Security chama este método automaticamente durante o processo de login.
     * * @param username O nome de usuário vindo do formulário de login.
     * @return UserDetails Um objeto de usuário pronto para ser validado pelo Spring.
     * @throws UsernameNotFoundException Caso o usuário não exista no banco.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco usando o repositório que comentamos anteriormente
        Usuario usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilizador não encontrado: " + username));

        /* Retorna uma instância de 'User' (classe interna do Spring Security).
           1. usuario.getUsername(): O nome do utilizador.
           2. usuario.getPassword(): A senha (que já deve estar criptografada com BCrypt).
           3. new ArrayList<>(): Lista de permissões/perfis (Roles), que aqui está vazia por padrão.
        */
        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }
}