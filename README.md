#  Cofrinho Pro - Dashboard Financeiro Multi-Moedas

O **Cofrinho Pro** é um sistema de gestão de economias que permite o controle de saldos em diversas moedas internacionais (Real, Dólar, Euro, Iene, etc.), com conversão automática e visualização de dados em tempo real através de gráficos dinâmicos.

##  Funcionalidades Principais

* **Gestão de Moedas**: Depósito e retirada de valores com IDs únicos por moeda.
* **Conversão Automática**: Exibição do valor na moeda original e o equivalente em Real (R$).
* **Dashboard Visual**: Gráficos de barras e rosca (Chart.js) para análise de distribuição de patrimônio.
* **Histórico de Transações**: Registro detalhado de todas as operações de depósito e retirada.
* **Segurança e Perfil**: Cadastro de usuários, edição de senha criptografada e opção de exclusão de conta.
* **UX Avançada**: Atalhos por teclado (tecla Enter), efeitos de hover dinâmicos e suporte a **Modo Escuro/Claro**.

##  Tecnologias Utilizadas

* **Backend**: Java com Spring Boot.
* **Frontend**: HTML5, CSS3 moderno (Variáveis CSS) e JavaScript Vanilla.
* **Banco de Dados**: PostgreSQL (Persistência de dados real).
* **Gráficos**: Chart.js.
* **Segurança**: Spring Security com BCrypt para criptografia de senhas.

## ⚙️ Como Rodar o Projeto

1.  **Configurar o Banco de Dados**:
    * Certifique-se de ter o PostgreSQL instalado.
    * Crie um banco chamado `postgres` (ou o nome definido no seu `application.properties`).
2.  **Configurar o `application.properties`**:
    * Ajuste as credenciais de `username` e `password` do seu banco local.
3.  **Executar a Aplicação**:
    * Rode a classe `CofrinhoApiApplication` através da sua IDE (IntelliJ/Eclipse).
4.  **Acesso**:
    * Abra o navegador em `http://localhost:8080/login`.
5.  **Dados para Teste Rápido**:
    * **Usuário**: `Cofre-pro` _(Você precisa se cadastrar com estes dados, na sua primeira vez)_
    * **Senha**: `1500`
    * **Palavra-Chave**: `pro`


    ##  Detalhes Técnicos (API Endpoints)

A comunicação entre o Frontend e o Backend é feita através de uma API REST:

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/auth/cadastrar` | Cria um novo usuário com senha criptografada. |
| `PUT`  | `/auth/editar` | Atualiza dados do perfil (username/senha). |
| `DELETE`| `/auth/excluir` | Remove permanentemente a conta do usuário. |
| `GET`  | `/moedas/total` | Retorna a lista de moedas e saldos. |

          ## Próximas Melhorias (Roadmap)

    Planejamos as seguintes evoluções para o Cofrinho Pro:

   1. **Integração com API de Câmbio Real**: Para atualizar as taxas de conversão (Dólar, Euro, etc.) automaticamente.
   2. **Dashboard de Metas**: Permitir que o usuário defina uma meta de economia (ex: "Viagem") e veja o progresso.
   3. **Exportação de PDF**: Gerar um relatório mensal de todos os depósitos e retiradas.


---
    Desenvolvido por Magno.

    