#  Cofrinho Pro - Dashboard Financeiro Multi-Moedas

![Java](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-latest-blue?style=for-the-badge&logo=postgresql) 

O **Cofrinho Pro** é um ecossistema completo de gestão financeira que permite o controle de ativos em diversas moedas internacionais, 
com conversão automática via API externa e visualização de dados em tempo real.

---

<<<<<<< HEAD
##  Diferenciais Técnicos (Arquitetura)

##  Visual do Sistema

### Dashboard Principal
![Dashboard](./capturas%20de%20tela/painel.png)
*Painel com conversão automática e monitoramento de ativos.*

### Persistência de Dados
![Banco de Dados](./capturas%20de%20tela/banco.png)
*Estrutura de tabelas e logs de auditoria armazenados no PostgreSQL.*
---

<<<<<<< HEAD
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
>>>>>>> 0e2ba16 (Final Dashboard integrado com java 25 e PostegreSQL)

---
**Desenvolvido por Magno.**
