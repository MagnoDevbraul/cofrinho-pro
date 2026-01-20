package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável pela autenticação e gestão de usuários.
 * Utiliza @RestController para retornar respostas diretas (Strings/JSON).
 */
@RestController
@RequestMapping("/auth") // Define a rota base para as requisições de autenticação
public class AuthController {

    @Autowired
    private UsuarioRepository repository; // Injeção do repositório para acesso ao PostgreSQL

    @Autowired
    private BCryptPasswordEncoder encoder; // Utilitário para criptografia das senhas (BCrypt)

    /**
     * Endpoint para cadastrar um novo usuário.
     * Criptografa a senha antes de salvar no banco de dados.
     */
    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody Usuario usuario) {
        // Aplica o hash na senha para que ela não seja salva como texto puro
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        repository.save(usuario); // Persiste o usuário no PostgreSQL
        return "Sucesso";
    }

    /**
     * Endpoint para recuperação de conta via palavra-chave.
     * Verifica se o username e a palavra-chave coincidem antes de alterar a senha.
     */
    @PostMapping("/redefinir")
    public String redefinir(@RequestParam String username, @RequestParam String palavraChave, @RequestParam String novaSenha) {
        return repository.findByUsername(username)
                .filter(u -> u.getPalavraChave().equals(palavraChave)) // Validação de segurança extra
                .map(u -> {
                    u.setPassword(encoder.encode(novaSenha)); // Criptografa a nova senha
                    repository.save(u); // Atualiza os dados no banco
                    return "Senha Alterada!";
                }).orElse("Dados incorretos!");
    }

    /**
     * MODO EDIÇÃO: Permite ao usuário atualizar suas informações de perfil.
     * Localiza o usuário pelo username e atualiza os campos necessários.
     */
    @PutMapping("/editar")
    public String editar(@RequestBody Usuario novosDados) {
        return repository.findByUsername(novosDados.getUsername())
                .map(u -> {
                    // Atualiza a senha garantindo a nova criptografia
                    u.setPassword(encoder.encode(novosDados.getPassword()));

                    // Atualiza a palavra-chave, caso um novo valor tenha sido enviado
                    if(novosDados.getPalavraChave() != null) {
                        u.setPalavraChave(novosDados.getPalavraChave());
                    }

                    repository.save(u); // Salva as alterações no PostgreSQL
                    return "Perfil Atualizado!";
                }).orElse("Usuário não encontrado!");
    }

    /**
     * MODO EXCLUSÃO: Remove permanentemente o usuário do sistema.
     * Importante para conformidade com leis de privacidade (LGPD).
     */
    @DeleteMapping("/excluir")
    public String excluir(@RequestParam String username) {
        return repository.findByUsername(username)
                .map(u -> {
                    repository.delete(u); // Remove o registro da tabela de usuários
                    return "Conta excluída com sucesso!";
                }).orElse("Erro ao excluir: Usuário não encontrado.");
    }
}