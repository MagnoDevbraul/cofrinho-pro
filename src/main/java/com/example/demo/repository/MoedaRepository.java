package com.example.demo.repository;

import com.example.demo.model.Moeda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * INTERFACE REPOSITORY - Camada de Persistência
 * * Ao estender JpaRepository, o Spring gera automaticamente a implementação
 * com todos os métodos CRUD (Create, Read, Update, Delete).
 * * <Moeda, Long> indica que este repositório gerencia a entidade 'Moeda'
 * e que o tipo do ID da entidade é 'Long'.
 */
@Repository
public interface MoedaRepository extends JpaRepository<Moeda, Long> {
    /* Graças à herança do JpaRepository, este repositório já possui métodos como:
       - save(Moeda): Salva ou atualiza uma moeda.
       - findAll(): Retorna todas as moedas da tabela.
       - findById(id): Busca uma moeda específica.
       - count(): Conta quantos registros existem (usado no CofrinhoApiApplication).
    */
}