package com.example.demo.controller;

import com.example.demo.model.*; // Importa todas as moedas de uma vez (Real, Dolar, Euro, etc)
import com.example.demo.repository.MoedaRepository;
import com.example.demo.repository.TransacaoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/moedas")
public class MoedaController {

    @Autowired
    private MoedaRepository repository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CotacaoService cotacaoService; // Serviço para pegar valores da internet

    @GetMapping("/total")
    public List<Map<String, Object>> buscarTodas() {
        Usuario logado = getUsuarioLogado();
        List<Moeda> moedas = repository.findByUsuario(logado);

        // Buscando o "Kit Global" de cotações em tempo real
        double usd = cotacaoService.getPreco("USD-BRL");
        double eur = cotacaoService.getPreco("EUR-BRL");
        double jpy = cotacaoService.getPreco("JPY-BRL");
        double cny = cotacaoService.getPreco("CNY-BRL");
        double inr = cotacaoService.getPreco("INR-BRL");
        double krw = cotacaoService.getPreco("KRW-BRL");
        double ils = cotacaoService.getPreco("ILS-BRL");

        return moedas.stream().map(m -> {
            Map<String, Object> dto = new HashMap<>();
            dto.put("id", m.getId());
            dto.put("nome", m.getNome());
            dto.put("valorOriginal", m.getValor());

            // Cálculo de conversão automática para o seu gráfico
            double valorEmReal = 0.0;
            if (m instanceof Dolar) valorEmReal = m.getValor() * usd;
            else if (m instanceof Euro) valorEmReal = m.getValor() * eur;
            else if (m instanceof Iene) valorEmReal = m.getValor() * jpy;
            else if (m instanceof Yuan) valorEmReal = m.getValor() * cny;
            else if (m instanceof Rupia) valorEmReal = m.getValor() * inr;
            else if (m instanceof Won) valorEmReal = m.getValor() * krw;
            else if (m instanceof Shekel) valorEmReal = m.getValor() * ils;
            else valorEmReal = m.getValor(); // Real é 1:1

            dto.put("valorEmReal", valorEmReal);
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarMoeda(@RequestParam String nome, @RequestParam Double valorInicial, @RequestParam String tipo) {
        Usuario usuarioLogado = getUsuarioLogado();

        Moeda novaMoeda;
        // Lógica para decidir qual classe instanciar
        if (tipo.equalsIgnoreCase("DOLAR")) novaMoeda = new Dolar(valorInicial);
        else if (tipo.equalsIgnoreCase("EURO")) novaMoeda = new Euro(valorInicial);
        else novaMoeda = new Real(valorInicial);

        novaMoeda.setNome(nome);
        novaMoeda.setUsuario(usuarioLogado);

        repository.save(novaMoeda);
        registrarHistorico(nome, valorInicial, "CRIAÇÃO", usuarioLogado);

        return ResponseEntity.ok("Moeda '" + nome + "' criada com sucesso! ID: " + novaMoeda.getId());
    }

    @PostMapping("/adicionar/{id}")
    public ResponseEntity<String> adicionar(@PathVariable Long id, @RequestParam Double valor) {
        Usuario usuarioLogado = getUsuarioLogado();
        Moeda m = repository.findById(id).orElse(null);

        if (m == null) return ResponseEntity.status(404).body("Erro: A moeda com ID " + id + " não existe!");

        if (!m.getUsuario().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(403).body("Acesso negado: esta moeda não é sua!");
        }

        m.setValor(m.getValor() + valor);
        repository.save(m);
        registrarHistorico(m.getNome(), valor, "DEPÓSITO", usuarioLogado);

        return ResponseEntity.ok("Sucesso! Valor adicionado.");
    }

    @PostMapping("/remover-valores/{id}")
    public ResponseEntity<String> removerValores(@PathVariable Long id, @RequestParam Double valor) {
        Usuario usuarioLogado = getUsuarioLogado();
        Moeda m = repository.findById(id).orElse(null);

        if (m != null) {
            if (!m.getUsuario().getId().equals(usuarioLogado.getId())) {
                return ResponseEntity.status(403).body("Acesso negado!");
            }
            double novoValor = m.getValor() - valor;
            m.setValor(novoValor < 0 ? 0.0 : novoValor);
            repository.save(m);
            registrarHistorico(m.getNome(), valor, "RETIRADA", usuarioLogado);
            return ResponseEntity.ok("Retirada realizada!");
        }
        return ResponseEntity.status(404).body("Moeda não encontrada.");
    }

    @GetMapping("/historico")
    public List<Transacao> buscarHistorico() {
        Usuario logado = getUsuarioLogado();
        // Agora só retorna o que for SEU!
        return transacaoRepository.findByUsuario(logado);
    }

    // --- MÉTODOS AUXILIARES ---

    private Usuario getUsuarioLogado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não autenticado!"));
    }

    private void registrarHistorico(String nome, Double valor, String tipo, Usuario usuario) {
        Transacao t = new Transacao();
        t.setMoedaNome(nome);
        t.setValorAlterado(valor);
        t.setTipo(tipo);
        t.setDataHora(LocalDateTime.now());
        t.setUsuario(usuario);
        transacaoRepository.save(t);
    }
}