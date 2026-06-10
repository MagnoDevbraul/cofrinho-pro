package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * CONTROLLER DE ROTEAMENTO (Páginas)
 * Diferente do @RestController, esta classe gerencia o direcionamento do usuário
 * entre as diferentes telas da aplicação.
 */
@Controller
public class PageController {

    /**
     * Rota para a tela de autenticação.
     * O 'forward:' realiza um redirecionamento interno no servidor, mantendo a
     * URL limpa no navegador do usuário.
     */

    @GetMapping("/login")
    public String login() {
        return "forward:/login.html";
    }

    /**
     * Rota para a tela de registro de novos usuários.
     */
    @GetMapping("/cadastro")
    public String cadastro() {
        return "forward:/cadastro.html";
    }

    /**
     * Rota principal do Dashboard (Cofrinho).
     * Só deve ser acessada após a autenticação bem-sucedida.
     */
    @GetMapping("/index")
    public String index() {
        return "forward:/dashboard.html";
    }
}