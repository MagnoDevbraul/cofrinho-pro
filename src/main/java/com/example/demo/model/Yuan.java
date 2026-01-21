package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * CLASSE YUAN (Especialização de Moeda)
 * Representa o Yuan Chinês (Renminbi - CNY).
 * Esta classe completa o ecossistema de moedas internacionais do projeto,
 * utilizando as anotações do JPA para manter a persistência na tabela única.
 */
@Entity
@DiscriminatorValue("Yuan")
public class Yuan extends Moeda {

    // Taxa de conversão específica para o Yuan Chinês
    private static final double TAXA_CONVERSAO = 0.76;

    /**
     * Construtor padrão (Vazio)
     * Indispensável para o Hibernate realizar a criação dinâmica de objetos
     * a partir dos dados recuperados do PostgreSQL.
     */
    public Yuan() {
        super();
    }

    /**
     * Construtor com inicialização de saldo
     * Utiliza a palavra-chave 'super' para enviar o valor para a classe pai Moeda.
     */
    public Yuan(double valor) {
        super(valor);
    }

    /**
     * IMPLEMENTAÇÃO POLIMÓRFICA
     * Aplica a taxa de câmbio chinesa sobre o valor armazenado.
     * Garante que o Dashboard exiba o valor equivalente em Real corretamente.
     */
    @Override
    public double converter() {
        return this.valor * TAXA_CONVERSAO;
    }
}