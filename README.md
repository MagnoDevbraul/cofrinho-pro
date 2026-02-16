# Cofrinho Pro - Dashboard Financeiro Multi-Moedas

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-latest-blue?style=for-the-badge&logo=postgresql) 

O **Cofrinho Pro** é um ecossistema completo de gestão financeira que permite o controle de ativos em diversas moedas internacionais, com conversão automática via API externa e visualização de dados em tempo real.

---

##  A Origem e Evolução (De CLI para Web)

 O projeto nasceu originalmente como um desafio acadêmico desenvolvido no **Eclipse**, com interface via **Terminal (CLI)**. Na versão inicial, os dados eram armazenados em memória (`ArrayList`) e o câmbio era inserido manualmente/fixo.

**A jornada de evolução incluiu:**
1.  **Migração de IDE**: Do Eclipse para o **IntelliJ IDEA**, visando melhor suporte ao ecossistema Spring e ferramentas de banco de dados.
2.  **Refatoração de Arquitetura**: A lógica de coleções (`ArrayList`) foi substituída pela persistência robusta em **PostgreSQL**.
3.  **Modernização do Mapeamento**: A hierarquia de classes original foi convertida para **Single Table Inheritance (STI)** com JPA.
4.  **Automação Financeira**: A implementação do consumo da **AwesomeAPI** eliminou a necessidade de cotações manuais, trazendo valores de mercado em tempo real.

---

##  Diferenciais Técnicos e Arquitetura

Desenvolvi este projeto para demonstrar maturidade em Engenharia de Software:

* **Integração com API Externa (Câmbio Real)**: O sistema consome dados em tempo real da **AwesomeAPI**, garantindo que as conversões de moedas como: USD, EUR, JPY e outras, estejam sempre atualizadas com o mercado financeiro.
* **Herança e Polimorfismo (ORM)**: A classe abstrata `Moeda` agora utiliza `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`, otimizando as consultas ao banco em uma única tabela.
* **Segurança**: Autenticação com `Spring Security` e criptografia de senhas via `BCrypt`.
* **Persistência**: Camada de dados gerenciada pelo Spring Data JPA com banco PostgreSQL.

---

##  Funcionalidades Principais

* **Conversão Automática**: Identificação instantânea da moeda e busca de cotação via API para compor o patrimônio em Reais (R$).
* **Dashboard Inteligente**: Cards dinâmicos e visualização de saldos de forma clara e moderna.
* **Gráficos Dinâmicos**: Gráficos de composição de carteira (Rosca) e saldo absoluto (Barras) via **Chart.js**.
* **Histórico Auditável**: Registro automático de depósitos e retiradas com data e hora.
* **Modo Escuro/Claro**: Interface moderna com suporte a temas e variáveis CSS.

---

##  Tecnologias Utilizadas

* **Backend**: Java 25, Spring Boot 3.x, Spring Data JPA, Spring Security.
* **Frontend**: HTML5, CSS3, JavaScript Vanilla, Thymeleaf, Chart.js.
* **Banco de Dados**: PostgreSQL.
* **Consumo de API**: JSON/REST (AwesomeAPI).

---

##  Visual do Sistema

### Painel de Controle Principal
![Painel](./screenshots/dashboard.png)

### Persistência de Dados (PostgreSQL)
![Banco de Dados](./screenshots/banco.png)

---

##  Como Rodar o Projeto

1.  **Configurar o Banco de Dados**:
    * Certifique-se de ter o PostgreSQL instalado.
    * Crie um banco chamado `cofrinho_pro`.
2.  **Configurar o `application.properties`**:
    * Ajuste as credenciais de `username` e `password` do seu banco local.
3.  **Executar a Aplicação**:
    * Rode a classe `CofrinhoApiApplication` através da sua IDE ou via terminal:
    ```bash
    ./mvnw spring-boot:run
    ```
4.  **Acesso**:
    * Abra `http://localhost:8080/login`.
    * **Primeiro Acesso**: Use o botão de cadastro para criar sua conta.

---

##  Roadmap de Evolução

- [ ] **Dashboard de Metas**: Sistema de progresso para objetivos de economia (ex: "Viagem").
- [ ] **Exportação de Relatórios**: Geração de extratos mensais em PDF.
- [ ] **Notificações**: Alertas via e-mail quando uma moeda atingir um valor específico de câmbio.

---

**Desenvolvido por Magno Walério.**
