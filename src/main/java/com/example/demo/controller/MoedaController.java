package com.example.demo.controller;

import com.example.demo.model.Moeda;
import com.example.demo.model.Transacao;
import com.example.demo.repository.MoedaRepository;
import com.example.demo.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/moedas")
public class MoedaController {

    @Autowired
    private MoedaRepository repository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @GetMapping("/total")
    public List<Moeda> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/adicionar/{id}")
    public void adicionar(@PathVariable Long id, @RequestParam Double valor) {
        Moeda m = repository.findById(id).orElse(null);
        if (m != null) {
            m.setValor(m.getValor() + valor);
            repository.save(m);
            // Usando m.getNome() que já existe na sua classe Moeda original
            registrarHistorico(m.getNome(), valor, "DEPÓSITO");
        }
    }

    @GetMapping("/remover-valores/{id}")
    public void removerValores(@PathVariable Long id, @RequestParam Double valor) {
        Moeda m = repository.findById(id).orElse(null);
        if (m != null) {
            double novoValor = m.getValor() - valor;
            m.setValor(novoValor < 0 ? 0.0 : novoValor);
            repository.save(m);
            registrarHistorico(m.getNome(), valor, "RETIRADA");
        }
    }

    @GetMapping("/historico")
    public List<Transacao> buscarHistorico() {
        return transacaoRepository.findAllByOrderByIdDesc();
    }

    private void registrarHistorico(String nome, Double valor, String tipo) {
        Transacao t = new Transacao();
        t.setMoedaNome(nome);
        t.setValorAlterado(valor);
        t.setTipo(tipo);
        t.setDataHora(LocalDateTime.now());
        transacaoRepository.save(t);
    }
}