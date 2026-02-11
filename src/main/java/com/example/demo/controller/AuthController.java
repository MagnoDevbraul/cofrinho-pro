package com.example.demo.controller;

import com.example.demo.model.*; // Importa todas as moedas (Real, Dolar, etc)
import com.example.demo.repository.MoedaRepository; // Injeção necessária
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private MoedaRepository moedaRepository; // Repositório para salvar o kit inicial

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@Valid @RequestBody Usuario usuario) {
        // 1. Salva o usuário primeiro
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        Usuario usuarioSalvo = repository.save(usuario);

        // 2. Cria as 8 moedas da sua imagem
        List<Moeda> kit = List.of(
                new Real(0.0), new Dolar(0.0), new Euro(0.0),
                new Iene(0.0), new Yuan(0.0), new Rupia(0.0),
                new Won(0.0), new Shekel(0.0)
        );

        // 3. Vincula cada moeda ao usuário e salva no banco
        for (Moeda m : kit) {
            m.setUsuario(usuarioSalvo);
            m.setNome(m.getClass().getSimpleName());
            moedaRepository.save(m);
        }

        return ResponseEntity.ok("Usuário e Carteira criados com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<java.util.List<Usuario>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
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

    @PutMapping("/editar")
    public String editar(@RequestBody Usuario novosDados) {
        return repository.findByUsername(novosDados.getUsername())
                .map(u -> {
                    u.setPassword(encoder.encode(novosDados.getPassword()));
                    if(novosDados.getPalavraChave() != null) {
                        u.setPalavraChave(novosDados.getPalavraChave());
                    }
                    repository.save(u);
                    return "Perfil Atualizado!";
                }).orElse("Usuário não encontrado!");
    }

    @DeleteMapping("/excluir")
    public String excluir(@RequestParam String username) {
        return repository.findByUsername(username)
                .map(u -> {
                    repository.delete(u);
                    return "Conta excluída com sucesso!";
                }).orElse("Erro ao excluir: Usuário não encontrado.");
    }
}