package com.example.demo.service;

import com.example.demo.dto.TransacaoDTO;
import com.example.demo.model.Transacao;
import com.example.demo.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) {
        this.repository = repository;
    }

    public List<TransacaoDTO> listarPorUsuario(String username) {

        List<Transacao> transacoes =
                repository.findByUsuarioUsername(username);

        return transacoes.stream()
                .map(t -> new TransacaoDTO(
                        t.getId(),
                        t.getMoeda().getNome(),
                        t.getMoeda().getCodigo(),
                        t.getValorAlterado(),
                        t.getTipo(),
                        t.getDataHora()
                ))
                .toList();
    }
}