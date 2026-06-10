package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class CotacaoService {

    private final RestTemplate restTemplate = new RestTemplate();

    // Cache simples em memória
    private Map<String, BigDecimal> cache = new HashMap<>();
    private LocalDateTime ultimaAtualizacao;

    private static final long CACHE_SEGUNDOS = 60;

    public Map<String, BigDecimal> buscarTaxasEmLote() {

        if (cacheValido()) {
            return cache;
        }

        try {

            String url = "https://economia.awesomeapi.com.br/json/last/USD-BRL,EUR-BRL,JPY-BRL,CNY-BRL,INR-BRL,KRW-BRL,ILS-BRL,BTC-BRL";

            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            Map<String, BigDecimal> novasTaxas = new HashMap<>();

            for (String chave : response.keySet()) {

                Object obj = response.get(chave);

                if (!(obj instanceof Map<?, ?> dados)) {
                    continue;
                }

                Object bid = dados.get("bid");

                if (bid != null) {

                    String codigo = chave.replace("BRL", "");
                    BigDecimal valor = new BigDecimal(bid.toString());

                    novasTaxas.put(codigo, valor);
                }
            }

            cache = novasTaxas;
            ultimaAtualizacao = LocalDateTime.now();

            return cache;

        } catch (Exception e) {

            System.err.println("Erro ao buscar cotação: " + e.getMessage());
            return cache; // retorna última válida
        }
    }

    private boolean cacheValido() {

        if (ultimaAtualizacao == null) {
            return false;
        }

        return ultimaAtualizacao.plusSeconds(CACHE_SEGUNDOS).isAfter(LocalDateTime.now());
    }
}