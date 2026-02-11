package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class CotacaoService {

    public double getPreco(String parMoeda) {
        try {
            // Consulta a API AwesomeAPI (Ex: USD-BRL)
            String url = "https://economia.awesomeapi.com.br/json/last/" + parMoeda;
            RestTemplate restTemplate = new RestTemplate();

            // Pega o resultado e extrai o campo "bid" (preço de compra)
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            Map<String, String> dados = (Map<String, String>) response.get(parMoeda.replace("-", ""));

            return Double.parseDouble(dados.get("bid"));
        } catch (Exception e) {
            System.err.println("Erro ao buscar cotação: " + e.getMessage());
            return 1.0; // Valor padrão caso a internet falhe
        }
    }
}