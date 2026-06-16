# Manual do Usuário — Jogo da Forca

**Disciplina:** 36811 – Modelagem e Prog. De Sistemas Computacionais  
**Curso:** Engenharia da Informação — UNOESC Chapecó  

---

## 1. Descrição do Sistema

O Jogo da Forca é um jogo de adivinhação de palavras executado via linha de comando (terminal).
O sistema escolhe aleatoriamente uma palavra de um arquivo de texto e o jogador deve adivinhar
as letras antes de esgotar as tentativas permitidas.

---

## 2. Requisitos para Execução

- Java JDK 17 ou superior instalado
- Arquivo `palavras_jogo_forca.txt` presente na mesma pasta de onde o jogo é executado

---

## 3. Como Compilar

Abra o terminal na pasta raiz do projeto e execute:

```bash
# Criar pasta de saída
mkdir -p out

# Compilar todos os arquivos Java
javac -d out src/model/Jogador.java src/model/Jogo.java src/repository/LeitorPalavras.java src/view/InterfaceJogo.java src/Main.java
```

---

## 4. Como Executar

Após compilar, copie o arquivo de palavras para a pasta `out` e execute:

```bash
cp palavras_jogo_forca.txt out/
cd out
java Main
```

---

## 5. Como Jogar

### Passo 1 — Início
Ao iniciar, o jogo exibirá a tela de boas-vindas e pedirá seu nome.

### Passo 2 — Tela do Jogo
A tela exibe:
- Desenho da forca (atualizado a cada erro)
- Nome do jogador
- Tamanho da palavra (número de letras)
- A palavra com `_` no lugar das letras não descobertas
- Letras erradas já tentadas
- Número de tentativas usadas e restantes (máximo: **6**)

Exemplo de tela:
```
  +---+
  |   |
  O   |
 /|   |
      |
      |
=========

Jogador: João
Tamanho da palavra: 10 letras

Palavra: C _ _ _ _ _ _ _ D _ D E

Letras erradas: B X

Tentativas usadas : 2 / 6
Tentativas restantes: 4
─────────────────────────────────────
Digite uma letra:
```

### Passo 3 — Adivinhar
- Digite **uma letra** por vez e pressione Enter
- O sistema informa se a letra está ou não na palavra
- Se a letra já foi tentada anteriormente, o sistema ignora e pede outra
- Letras são aceitas em maiúsculo ou minúsculo

### Passo 4 — Fim de Jogo
- **Vitória:** todas as letras foram descobertas antes de esgotar as tentativas
- **Derrota:** 6 erros atingidos sem adivinhar a palavra completa

Ao final, o sistema pergunta se deseja jogar novamente (S/N).

---

## 6. Regras do Jogo

| Regra | Comportamento |
|---|---|
| Máximo de tentativas | 6 erros |
| Letra repetida | Ignorada, solicita nova entrada |
| Entrada inválida | Mensagem de erro, solicita nova entrada |
| Letras maiúsculas/minúsculas | Ambas aceitas |

---

## 7. Estrutura de Arquivos

```
jogo-forca/
├── src/
│   ├── Main.java                        # Ponto de entrada
│   ├── model/
│   │   ├── Jogador.java                 # Dados do jogador
│   │   └── Jogo.java                    # Lógica do jogo
│   ├── repository/
│   │   └── LeitorPalavras.java          # Leitura do arquivo
│   └── view/
│       └── InterfaceJogo.java           # Interface CLI
├── palavras_jogo_forca.txt              # Lista de palavras
└── MANUAL.md                            # Este manual
```

---

## 8. Arquivo de Palavras

O arquivo `palavras_jogo_forca.txt` deve conter **uma palavra por linha**.  
O sistema lê automaticamente todas as palavras e sorteia uma aleatoriamente a cada jogo.

Exemplo:
```
Aventuras
Coragem
Tecnologia
```

---

## 9. Possíveis Erros

| Erro | Solução |
|---|---|
| Arquivo de palavras não encontrado | Verifique se `palavras_jogo_forca.txt` está na pasta de execução |
| Comando `java` não reconhecido | Instale o Java JDK e configure o PATH |
| Erro de compilação | Verifique se está usando Java 17 ou superior (`java -version`) |
