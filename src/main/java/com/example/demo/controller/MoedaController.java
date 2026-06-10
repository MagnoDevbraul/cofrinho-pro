package com.example.demo.controller;

import com.example.demo.dto.MoedaResumoDTO;
import com.example.demo.service.MoedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/moedas")
public class MoedaController {

    @Autowired
    private MoedaService moedaService;

    /**
     * Resumo utilizado pelos gráficos do dashboard.
     */
    @GetMapping("/resumo")
    public ResponseEntity<List<MoedaResumoDTO>> obterResumo(
            @AuthenticationPrincipal UserDetails principal) {

        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        String username = principal.getUsername();

        List<MoedaResumoDTO> resumo =
                moedaService.obterResumoPorUsername(username);

        return ResponseEntity.ok(resumo);
    }

    /**
     * Total geral da carteira.
     */
    @GetMapping("/total-geral")
    public ResponseEntity<Map<String, Object>> obterTotalGeral(
            @AuthenticationPrincipal UserDetails principal) {

        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        String username = principal.getUsername();

        List<MoedaResumoDTO> resumo =
                moedaService.obterResumoPorUsername(username);

        double totalEmReal = resumo.stream()
                .mapToDouble(m ->
                        m.getValorEmReal() != null
                                ? m.getValorEmReal().doubleValue()
                                : 0.0)
                .sum();

        Map<String, Object> response = new HashMap<>();
        response.put("totalEmReal", totalEmReal);

        return ResponseEntity.ok(response);
    }

    /**
     * Realiza depósito.
     */
    @PostMapping("/{id}/depositar")
    public ResponseEntity<Map<String, String>> depositar(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload,
            @AuthenticationPrincipal UserDetails principal) {

        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        BigDecimal valor =
                new BigDecimal(payload.get("valor"));

        moedaService.realizarDeposito(
                id,
                valor,
                principal.getUsername()
        );

        return ResponseEntity.ok(
                Map.of(
                        "mensagem",
                        "Depósito realizado com sucesso!"
                )
        );
    }

    /**
     * Realiza retirada.
     */
    @PostMapping("/{id}/retirar")
    public ResponseEntity<Map<String, String>> retirar(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload,
            @AuthenticationPrincipal UserDetails principal) {

        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        BigDecimal valor =
                new BigDecimal(payload.get("valor"));

        moedaService.realizarRetirada(
                id,
                valor,
                principal.getUsername()
        );

        return ResponseEntity.ok(
                Map.of(
                        "mensagem",
                        "Retirada realizada com sucesso!"
                )
        );
    }
}