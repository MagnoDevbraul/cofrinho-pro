#  Cofrinho Pro - Dashboard Financeiro Multi-Moedas

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-latest-blue?style=for-the-badge&logo=postgresql) 

O **Cofrinho Pro** é um ecossistema completo de gestão financeira que permite o controle de ativos em diversas moedas internacionais, 
com conversão automática via API externa e visualização de dados em tempo real.

---


##  Diferenciais Técnicos (Arquitetura)
=======
##  Diferenciais Técnicos e Arquitetura
> 0e2ba16 (Final Dashboard integrado com java 25 e PostegreSQL)

Este projeto demonstra maturidade em Engenharia de Software ao aplicar conceitos fundamentais de forma prática:

- **Herança e Polimorfismo**: Implementação de *Single Table Inheritance* com JPA. A classe base `Moeda` define o comportamento comum, enquanto subclasses especializadas (`Dolar`, `Euro`, `Iene`, etc.) gerenciam lógicas de conversão específicas.
- **Integração com API Externa**: Consumo em tempo real da *AwesomeAPI* para atualização automática das taxas de câmbio (USD, EUR, JPY, etc.) diretamente no Backend.
- **Segurança**: Autenticação robusta utilizando `Spring Security` com criptografia de senhas via `BCrypt`.
- **Persistência**: Banco de dados PostgreSQL com estratégia de atualização de esquema automática (`ddl-auto=update`).

---


##  Funcionalidades
=======
##  Funcionalidades Principais
> 0e2ba16 (Final Dashboard integrado com java 25 e PostegreSQL)

* **Dashboard Inteligente**: Cards dinâmicos que mostram o saldo na moeda original e a conversão instantânea para Real (R$).
* **Conversão em Tempo Real**: O sistema identifica a moeda e busca o valor de mercado atualizado para compor o patrimônio total.
* **Gráficos Dinâmicos**: Visualização por composição (Rosca) e saldo absoluto (Barras) utilizando **Chart.js**.
* **Histórico Auditável**: Registro automático de depósitos e retiradas com data, hora e tipo de transação.
* **Modo Escuro/Claro**: Interface moderna com suporte a temas persistentes.

---


=======
##  Tecnologias Utilizadas

* **Backend**: Java, Spring Boot 3.x, Spring Data JPA, Spring Security.
* **Frontend**: HTML5, CSS3, JavaScript Vanilla, Chart.js.
* **Banco de Dados**: PostgreSQL.
* **Consumo de API**: JSON/REST para cotações financeiras.

---

> 0e2ba16 (Final Dashboard integrado com java 25 e PostegreSQL)
##  Visual do Sistema

### Painel de Controle Principal
![Painel](./screenshots/dashboard.png)

### Persistência de Dados
![Banco de Dados](./screenshots/banco.png)

---


##  Tecnologias Utilizadas

* **Backend**: Java 25, Spring Boot 3.x, Spring Data JPA.
* **Frontend**: HTML5, CSS3 (Variáveis Modernas), JavaScript Vanilla, Thymeleaf.
* **Banco de Dados**: PostgreSQL.
* **Segurança**: Spring Security + BCrypt.

---

##  Como Rodar o Projeto

1.  **Configurar o Banco de Dados**:
    * Certifique-se de ter o PostgreSQL instalado.
    * Crie um banco chamado `postgres` (ou o nome definido no seu `application.properties`).
2.  **Configurar o `application.properties`**:
    * Ajuste as credenciais de `username` e `password` do seu banco local.
3.  **Executar a Aplicação**:
    * Rode a classe `CofrinhoApiApplication` através do IntelliJ ou via terminal: `./mvnw spring-boot:run`
4.  **Acesso**:
    * Abra o navegador em `http://localhost:8080/login`.
    * **usuário**: `Cofre-pro`
    * **Senha**: `1500`

> **Dica para Teste**: Cadastre-se na primeira execução ou use o usuário criado durante o setup para explorar o dashboard.

---

##  Roadmap de Evolução

1.  **Integração com API de Câmbio**: Atualização das taxas (Dólar, Euro, etc.) via API externa em tempo real.
2.  **Dashboard de Metas**: Sistema de progresso para objetivos de economia (ex: "Viagem").
3.  **Exportação de Relatórios**: Geração de extratos mensais em PDF.
=======
##  Como Executar

1. **Clone o repositório**.
2. **Configuração do Banco**: No `application.properties`, ajuste as credenciais do seu PostgreSQL local.
3. **Build e Run**: Execute a classe `CofrinhoApiApplication`.
4. **Acesso**: O dashboard está disponível em `http://localhost:8080/index.html`.
> 0e2ba16 (Final Dashboard integrado com java 25 e PostegreSQL)

---
**Desenvolvido por Magno.**
