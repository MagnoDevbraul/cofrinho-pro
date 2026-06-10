package com.example.demo.service;

import com.example.demo.model.Meta;
import com.example.demo.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class MetaService {

    @Autowired
    private MetaRepository repository;

    public List<Meta> listarTodas() {
        return repository.findAll();
    }

    // NOVA FUNÇÃO: Calcula a porcentagem concluída
    public BigDecimal calcularPorcentagem(Meta meta) {
        if (meta.getValorObjetivo().compareTo(BigDecimal.ZERO) > 0) {
            return meta.getValorAtual()
                    .multiply(new BigDecimal("100"))
                    .divide(meta.getValorObjetivo(), 2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}