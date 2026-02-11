package com.example.demo.repository;

import com.example.demo.model.Transacao;
import com.example.demo.model.Usuario; // ESTA LINHA ESTAVA FALTANDO!
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * REPOSITÓRIO DE TRANSAÇÕES
 * Responsável pelas operações de leitura e escrita do histórico financeiro.
 */
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    /**
     * Busca as transações de um usuário específico.
     */
    List<Transacao> findByUsuario(Usuario usuario);

    /**
     * Busca todas as transações ordenadas pelo ID de forma decrescente.
     */
    List<Transacao> findAllByOrderByIdDesc();
}