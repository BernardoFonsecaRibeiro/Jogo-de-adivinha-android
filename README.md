# 🎯 Jogo de Adivinhação - Android (Kotlin)

**Disciplina de Desenvolvimento Mobile — Aula 15: Navegação entre Activities e Transferência de Dados**

Este projeto é um aplicativo mobile Android desenvolvido em **Kotlin** para exercitar os conceitos de navegação entre telas (`Activities`) e transferência de dados utilizando `Intent` e `extras`. Feito por Bernardo Ribeiro

---

## 📱 Sobre o Projeto

O **Jogo de Adivinhação** é composto por duas telas interativas:
1. **Configuração do Jogo:** O usuário digita seu nome e escolhe a dificuldade (limite de números: até 10, 50 ou 100).
2. **Tela do Jogo:** O app sorteia um número secreto e o jogador realiza palpites recebendo dicas térmicas (*Quente/Frio*) e direcionais (*MAIOR/MENOR*) até acertar.

---

## ✨ Funcionalidades

- **Tela 1 (`MainActivity`):**
  - Campo para entrada do **nome do jogador**.
  - Seleção do limite máximo via `RadioGroup` (10, 50 ou 100).
  - Validação de campos (exibe `Toast` informando se o nome estiver vazio ou a opção não for selecionada).
  - Envio dos dados via `Intent.putExtra()` e abertura da segunda tela com `startActivity()`.

- **Tela 2 (`JogoActivity`):**
  - Recepção e leitura dos dados com `intent.getStringExtra()` e `intent.getIntExtra()`.
  - Exibição de mensagem personalizada de boas-vindas.
  - Sorteio randômico do número secreto (`Random.nextInt(1, limite + 1)`).
  - Contagem do número de tentativas a cada palpite.
  - Sistema de dicas:
    - 🔥 **Quente:** diferença entre o palpite e o número secreto é $\le 10\%$ do limite.
    - ❄️ **Frio:** diferença é maior que $10\%$ do limite.
    - ⬆️ / ⬇️ **Direção:** indica se o número secreto é **MAIOR** ou **MENOR** que o palpite.
  - Finalização do jogo ao acertar: bloqueia novas entradas, exibe o total de tentativas e ativa o botão **JOGAR DE NOVO**.
  - Retorno limpo para a tela inicial usando `finish()`.

---

## 🛠️ Tecnologias e Conceitos Aplicados

- **Linguagem:** Kotlin
- **Interface:** Android XML Layouts (`LinearLayout`, `EditText`, `Button`, `TextView`, `RadioGroup`, `RadioButton`)
- **Conceitos Android:**
  - **Intent Explícita:** `Intent(this, JogoActivity::class.java)`
  - **Passagem de Parâmetros:** `intent.putExtra("nome", nome)` e `intent.putExtra("limite", limite)`
  - **Recuperação de Parâmetros:** `intent.getStringExtra("nome")` / `intent.getIntExtra("limite", 50)`
  - **Gerenciamento da Pilha de Activities:** uso correto de `finish()` para fechar a tela atual sem empilhar Activities desnecessárias.
  - **Declaração de Activities:** Registro das duas telas no `AndroidManifest.xml`.

---

## 📂 Estrutura do Código

```
app/src/main/
├── java/br/ulbra/myapplication/
│   ├── MainActivity.kt        # Captura nome e limite, cria o Intent
│   └── JogoActivity.kt        # Lógica do jogo, sorteio e validação de chutes
├── res/layout/
│   ├── activity_main.xml      # Layout da tela de entrada
│   └── activity_jogo.xml      # Layout da tela do jogo
└── AndroidManifest.xml        # Configuração e registro das Activities
```

---

## 🚀 Como Executar

1. Abra o **Android Studio**.
2. Abra a pasta do projeto no Android Studio.
3. Aguarde o **Gradle Sync** finalizar.
4. Execute o aplicativo em um emulador ou dispositivo Android conectado via USB clicando no botão **Run** (`Shift + F10`).
