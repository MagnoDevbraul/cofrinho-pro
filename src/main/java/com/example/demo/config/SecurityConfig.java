package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * CONFIGURAÇÃO DE SEGURANÇA (Spring Security)
 * Centraliza as permissões de acesso e a estratégia de criptografia do sistema.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * BEAN DE CRIPTOGRAFIA
     * Define o BCrypt como o algoritmo de hash para senhas.
     * O BCrypt é o padrão da indústria, pois gera um 'salt' aleatório,
     * tornando o sistema imune a ataques de dicionário ou Rainbow Tables.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * CONFIGURAÇÃO DE FILTROS (SecurityFilterChain)
     * Define como as requisições HTTP são tratadas pelo servidor.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita CSRF para facilitar a integração com APIs REST e chamadas Fetch/AJAX
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        /* PÁGINAS PÚBLICAS:
                           Permite acesso sem login para telas de entrada, arquivos estáticos
                           (CSS/JS) e os endpoints da API de autenticação.
                        */
                        .requestMatchers("/login", "/cadastro", "/login.html", "/cadastro.html", "/css/**", "/js/**", "/auth/**").permitAll()

                        /* RESTRITO:
                           Qualquer outra rota (como o Dashboard /index.html) exige que o usuário esteja logado.
                        */
                        .anyRequest().authenticated()
                )

                /* CONFIGURAÇÃO DE LOGIN FORMULÁRIO
                   Define o comportamento da tela de login customizada.
                */
                .formLogin(form -> form
                        .loginPage("/login")             // Rota da nossa tela bonita de login
                        .loginProcessingUrl("/login")    // URL que o Spring intercepta para validar
                        .defaultSuccessUrl("/index.html", true) // Onde o usuário cai após logar
                        .permitAll()
                )

                /* CONFIGURAÇÃO DE LOGOUT
                   Garante que a sessão seja encerrada e o usuário redirecionado com segurança.
                */
                .logout(logout -> logout
                        .logoutSuccessUrl("/login.html")
                        .permitAll()
                );

        return http.build();
    }
}