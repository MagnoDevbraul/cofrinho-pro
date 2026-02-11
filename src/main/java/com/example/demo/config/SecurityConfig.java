package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

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
                .csrf(csrf -> csrf.disable()) // ESSENCIAL: Permite que o POST funcione no Swagger
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated() // Exige login para o MoedaController
                )
                .httpBasic(Customizer.withDefaults()); // Ativa o Basic Auth (Cadeado)

        return http.build();
    }
}