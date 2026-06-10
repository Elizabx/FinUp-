# 💰 FinUp

Aplicativo Android para gerenciamento financeiro pessoal, desenvolvido com Kotlin e Jetpack Compose.

O FinUp permite que usuários acompanhem receitas, despesas, metas financeiras e relatórios de desempenho, oferecendo uma visão clara da sua situação financeira através de uma interface moderna e intuitiva.

---

## 📱 Funcionalidades

### 👤 Usuários

* Cadastro de usuário
* Login e autenticação local
* Gerenciamento de perfil
* Persistência de sessão

### 💸 Transações

* Cadastro de receitas
* Cadastro de despesas
* Edição de transações
* Exclusão de transações
* Histórico financeiro (Extrato)

### 🎯 Metas Financeiras

* Criação de metas
* Atualização de metas
* Exclusão de metas
* Acompanhamento do progresso

### 📊 Dashboard

* Visualização de saldo atual
* Total de entradas
* Total de saídas
* Resumo financeiro do usuário

### 📈 Relatórios

* Resumo geral das movimentações
* Balanço financeiro
* Indicadores de desempenho financeiro

---

## 🛠️ Tecnologias Utilizadas

### Linguagem

* Kotlin

### Interface

* Jetpack Compose
* Material Design 3

### Persistência de Dados

* Room Database
* SQLite

### Arquitetura

* MVVM (Model-View-ViewModel)
* Repository Pattern

### Navegação

* Navigation Compose

### Injeção de Dependências

* AppContainer (Dependency Injection manual)

---

## 📂 Estrutura do Projeto

```text
app/
├── database/
│   ├── dao/
│   ├── entity/
│   └── FinUpDatabase.kt
│
├── repository/
│   ├── TransactionRepository
│   ├── MetaRepository
│   └── UsuarioRepository
│
├── viewmodel/
│   ├── DashboardViewModel
│   ├── TransactionViewModel
│   ├── MetaViewModel
│   └── UsuarioViewModel
│
├── screens/
│   ├── LoginScreen
│   ├── CadastroScreen
│   ├── DashboardScreen
│   ├── ExtratoScreen
│   ├── MetaScreen
│   ├── RelatorioScreen
│   └── PerfilScreen
│
├── navigation/
├── session/
├── ui/
└── MainActivity.kt
```

---

## 🗄️ Banco de Dados

O aplicativo utiliza o Room Database para armazenamento local.

### Entidades

#### UsuarioEntity

Armazena os dados dos usuários cadastrados.

#### TransactionEntity

Responsável pelas movimentações financeiras.

#### MetaEntity

Armazena metas financeiras criadas pelos usuários.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

* Android Studio Hedgehog ou superior
* JDK 11+
* Android SDK 34
* Gradle 8+

### Clonando o Projeto

```bash
git clone https://github.com/Elizabx/FinUp.git
```

### Abrindo o Projeto

1. Abra o Android Studio.
2. Selecione **Open Project**.
3. Escolha a pasta do projeto.
4. Aguarde a sincronização do Gradle.

### Executando

1. Inicie um emulador Android ou conecte um dispositivo físico.
2. Clique em **Run ▶**.
3. O aplicativo será compilado e instalado automaticamente.

---

## 📸 Telas do Sistema

O projeto possui as seguintes telas:

* Splash Screen
* Login
* Cadastro
* Dashboard
* Extrato
* Metas
* Relatórios
* Perfil

---

## 📐 Arquitetura

O projeto segue o padrão MVVM:

```text
UI (Compose)
      ↓
ViewModel
      ↓
Repository
      ↓
DAO
      ↓
Room Database
```

Essa estrutura promove:

* Separação de responsabilidades
* Facilidade de manutenção
* Reutilização de código
* Melhor testabilidade

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos e educacionais.

