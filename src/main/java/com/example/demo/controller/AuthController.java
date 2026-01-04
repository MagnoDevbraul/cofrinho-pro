package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        repository.save(usuario);
        return "Sucesso";
    }


    @PostMapping("/redefinir")
    public String redefinir(@RequestParam String username, @RequestParam String palavraChave, @RequestParam String novaSenha) {
        return repository.findByUsername(username)
                .filter(u -> u.getPalavraChave().equals(palavraChave))
                .map(u -> {
                    u.setPassword(encoder.encode(novaSenha));
                    repository.save(u);
                    return "Senha Alterada!";
                }).orElse("Dados incorretos!");
    }

}
