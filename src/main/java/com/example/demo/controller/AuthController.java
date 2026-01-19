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

    // MODO EDIÇÃO: Atualiza nome e senha do usuário
    @PutMapping("/editar")
    public String editar(@RequestBody Usuario novosDados) {
        return repository.findByUsername(novosDados.getUsername())
                .map(u -> {
                    // Atualiza a senha com criptografia
                    u.setPassword(encoder.encode(novosDados.getPassword()));
                    // Se você tiver campo de nome ou palavra-chave, pode atualizar aqui também
                    if(novosDados.getPalavraChave() != null) u.setPalavraChave(novosDados.getPalavraChave());

                    repository.save(u);
                    return "Perfil Atualizado!";
                }).orElse("Usuário não encontrado!");
    }

    // MODO EXCLUSÃO: Remove o usuário do banco de dados
    @DeleteMapping("/excluir")
    public String excluir(@RequestParam String username) {
        return repository.findByUsername(username)
                .map(u -> {
                    repository.delete(u);
                    return "Conta excluída com sucesso!";
                }).orElse("Erro ao excluir: Usuário não encontrado.");
    }

}
