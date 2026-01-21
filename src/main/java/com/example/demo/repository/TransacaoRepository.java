package com.example.demo.repository;

import com.example.demo.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * REPOSITÓRIO DE TRANSAÇÕES
 * Responsável pelas operações de leitura e escrita do histórico financeiro.
 */
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    /**
     * QUERY METHOD: Busca todas as transações ordenadas pelo ID de forma decrescente.
     * O Spring Data JPA interpreta o nome 'findAllByOrderByIdDesc' e gera o SQL:
     * SELECT * FROM transacoes ORDER BY id DESC;
     * * Isso garante que as movimentações mais recentes apareçam primeiro no Dashboard.
     */
    List<Transacao> findAllByOrderByIdDesc();
}