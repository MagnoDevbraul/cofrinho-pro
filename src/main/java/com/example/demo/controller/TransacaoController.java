package com.example.demo.controller;

import com.example.demo.dto.TransacaoDTO;
import com.example.demo.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listarTransacoesDoUsuario(
            @AuthenticationPrincipal User usuarioLogado) {

        if (usuarioLogado == null) {
            return ResponseEntity.status(401)
                    .body("Usuário não autenticado.");
        }

        List<TransacaoDTO> lista =
                service.listarPorUsuario(
                        usuarioLogado.getUsername()
                );

        return ResponseEntity.ok(lista);
    }
}