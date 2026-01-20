package com.example.demo.controller;

import com.example.demo.model.Moeda;
import com.example.demo.model.Transacao;
import com.example.demo.repository.MoedaRepository;
import com.example.demo.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CONTROLLER DE OPERAÇÕES FINANCEIRAS
 * Gerencia os endpoints de manipulação de saldos e consulta de histórico.
 */
@RestController
@RequestMapping("/moedas")
public class MoedaController {

    @Autowired
    private MoedaRepository repository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    /**
     * Retorna a lista completa de moedas para popular o Dashboard.
     */
    @GetMapping("/total")
    public List<Moeda> listarTodas() {
        return repository.findAll();
    }

    /**
     * Realiza o depósito em uma moeda específica.
     * Além de atualizar o saldo, invoca o registro de histórico para auditoria.
     */
    @GetMapping("/adicionar/{id}")
    public void adicionar(@PathVariable Long id, @RequestParam Double valor) {
        Moeda m = repository.findById(id).orElse(null);
        if (m != null) {
            m.setValor(m.getValor() + valor);
            repository.save(m);
            // O uso de m.getNome() demonstra o uso de @JsonProperty definido na classe abstrata
            registrarHistorico(m.getNome(), valor, "DEPÓSITO");
        }
    }

    /**
     * Realiza a retirada de valores, garantindo que o saldo não fique negativo (Regra de Negócio).
     */
    @GetMapping("/remover-valores/{id}")
    public void removerValores(@PathVariable Long id, @RequestParam Double valor) {
        Moeda m = repository.findById(id).orElse(null);
        if (m != null) {
            double novoValor = m.getValor() - valor;
            m.setValor(novoValor < 0 ? 0.0 : novoValor); // Proteção contra saldo negativo
            repository.save(m);
            registrarHistorico(m.getNome(), valor, "RETIRADA");
        }
    }

    /**
     * Endpoint que alimenta a tabela de transações do Frontend.
     */
    @GetMapping("/historico")
    public List<Transacao> buscarHistorico() {
        return transacaoRepository.findAllByOrderByIdDesc();
    }

    /**
     * MÉTODO AUXILIAR (Privado): Centraliza a criação de logs de transação.
     * Segue o princípio de responsabilidade única (SRP).
     */
    private void registrarHistorico(String nome, Double valor, String tipo) {
        Transacao t = new Transacao();
        t.setMoedaNome(nome);
        t.setValorAlterado(valor);
        t.setTipo(tipo);
        t.setDataHora(LocalDateTime.now());
        transacaoRepository.save(t);
    }
}