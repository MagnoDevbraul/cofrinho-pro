package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class CadastroController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public CadastroController(UsuarioRepository usuarioRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Usuario usuario) {

        String senhaCriptografada =
                passwordEncoder.encode(usuario.getPassword());

        usuario.setPassword(senhaCriptografada);

        usuarioRepository.save(usuario);

        return "redirect:/login.html";
    }

    @GetMapping("/debug")
    @ResponseBody
    public String debug() {

        String senhaDigitada = "123456";

        String hashDoBanco = "$2a$10$GiDOBtYp/SR8kOHD7Zded.kqRJMHVTcwV82R9cRyjEy57fxu0xhwy";

        return String.valueOf(passwordEncoder.matches(senhaDigitada, hashDoBanco));
    }
}