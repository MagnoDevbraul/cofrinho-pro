package com.example.demo.service;

import com.example.demo.dto.MoedaResumoDTO;
import com.example.demo.model.Moeda;
import com.example.demo.model.Transacao;
import com.example.demo.model.Usuario;
import com.example.demo.repository.MoedaRepository;
import com.example.demo.repository.TransacaoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;

@Service
public class MoedaService {

    @Autowired
    private MoedaRepository moedaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransacaoRepository transacaoRepository; // ADICIONADO

    public List<MoedaResumoDTO> obterResumoDasMoedas(Long usuarioId) {
        return moedaRepository.buscarResumoPorUsuario(usuarioId);
    }

    public List<MoedaResumoDTO> obterResumoPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .map(usuario -> moedaRepository.buscarResumoPorUsuario(usuario.getId()))
                .orElse(Collections.emptyList());
    }

    // ===== NOVO MÉTODO: DEPÓSITO =====
    @Transactional
    public void realizarDeposito(Long moedaId, BigDecimal valor, String username) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        Moeda moeda = moedaRepository.findById(moedaId)
                .orElseThrow(() -> new IllegalArgumentException("Moeda não encontrada."));

        // Garante que a moeda pertence ao usuário logado
        if (!moeda.getUsuario().getId().equals(usuario.getId())) {
            throw new SecurityException("Operação não permitida.");
        }

        // Atualiza o saldo somando com segurança
        moeda.setSaldo(moeda.getSaldo().add(valor));
        moedaRepository.save(moeda);

        // Salva a transação vinculando Moeda e Usuário
        Transacao transacao = new Transacao(moeda, valor, "DEPOSITO", LocalDateTime.now(), usuario);
        transacaoRepository.save(transacao);
    }

    // ===== NOVO MÉTODO: RETIRADA =====
    @Transactional
    public void realizarRetirada(Long moedaId, BigDecimal valor, String username) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da retirada deve ser maior que zero.");
        }

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        Moeda moeda = moedaRepository.findById(moedaId)
                .orElseThrow(() -> new IllegalArgumentException("Moeda não encontrada."));

        if (!moeda.getUsuario().getId().equals(usuario.getId())) {
            throw new SecurityException("Operação não permitida.");
        }

        // Validação de saldo
        if (moeda.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a retirada.");
        }

        // Subtrai o saldo
        moeda.setSaldo(moeda.getSaldo().subtract(valor));
        moedaRepository.save(moeda);

        // Salva transação
        Transacao transacao = new Transacao(moeda, valor, "RETIRADA", LocalDateTime.now(), usuario);
        transacaoRepository.save(transacao);
    }
}