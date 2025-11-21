<h1 align="center">DevLife: Jornada Dev Simulator</h1>

## Sobre o Projeto

DevLife é um simulador de carreira de TI em modo texto. O jogador deve tomar decisões estratégicas para balancear trabalho, estudos e vida pessoal, gerenciando atributos como energia, saúde mental, soft skills e experiência para progredir de Estagiário a CEO. Este projeto foi desenvolvido em Java com um forte foco nos princípios da Programação Orientada a Objetos (POO).

---

## Funcionalidades Principais

-   **Progressão de Carreira:** Comece como Estagiário e evolua para Júnior, Pleno, Sênior e CEO.
-   **Gerenciamento de Atributos:** Balanceie Energia, Saúde Mental, Felicidade, Soft Skills, Networking e Experiência.
-   **Sistema de Oportunidades:** Candidate-se a vagas de emprego, cada uma com seus próprios requisitos e dificuldades.
-   **Educação Contínua:** Invista em cursos para adquirir novas hard skills e desbloquear novas oportunidades.
-   **Eventos Aleatórios:** Lide com desafios inesperados como bugs críticos, burnout ou participe de eventos como Hackathons.
-   **Mecânica de Dificuldade Dinâmica:** As vagas se tornam mais desafiadoras conforme o jogador ganha experiência, exigindo investimento em soft skills e networking para facilitar a jornada.

---

## Tecnologias Utilizadas

-   **Linguagem:** Java 17 (LTS)

---

## Como Executar o Projeto

**Pré-requisitos:**
*   Java JDK 17

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/jornada-dev-simulator.git

# Navegue até o diretório do projeto
cd jornada-dev-simulator

# Compile o projeto com Maven
mvn compile

# Execute a aplicação
mvn exec:java -Dexec.mainClass="br.com.devlife.simulator.Main"
```

---

## Fluxo de Trabalho de Desenvolvimento

Para garantir consistência e agilidade no desenvolvimento, é necessário que a equipe deve siga um fluxo de trabalho de duas fases.

### Fase 1: Desenvolvimento Rápido (Dentro do IntelliJ)

1.  **Edite o código** nas suas classes `.java`.
2.  **Salve** os arquivos.
3.  **Clique no botão verde "Play" (Run)** ao lado do método `main` na classe `Main.java` para executar o jogo diretamente no console da IDE.

> **Nota:** A limpeza de tela no console do IntelliJ é **simulada** (imprimindo várias linhas em branco). Use esta fase para validar a lógica dos menus, cálculos de atributos e fluxo do jogo, mas não para avaliar a experiência visual final. Contudo, pode também simplesmente não limpar caso só rode usando a opção `RUN` da IDE.

### Fase 2: Teste da Versão Final (Via Terminal)

1.  **Construa o Pacote (`.jar`):**
    *   No IntelliJ, abra o painel do **Maven** (geralmente na lateral direita).
    *   Vá em **Lifecycle**.
    *   Clique duas vezes em **`package`**. O Maven irá compilar e empacotar o projeto. Ao final, você verá uma mensagem de `BUILD SUCCESS`.

2.  **Execute no Terminal Externo:**
    *   Abra o terminal do seu sistema operacional (não o terminal do IntelliJ que aparece quando clicamos em `RUN`. Dentro do IntelliJ podemos acessar um terminal real pelo antepenúltimo símbolo do lado esquerdo da IDE, ou acessá-lo pelo seguinte atalho: Ctrl + `).
    *   Navegue até a pasta raiz do projeto.
    *   Execute o seguinte comando para rodar o jogo:
    ```bash
    java -jar target/jornada-dev-simulator-1.0-SNAPSHOT.jar
    ```

---

## Diagrama UML

![Diagrama UML demonstrando o funcionamento do projeto](https://github.com/Jhon-Victor-Ramos/jornada-dev-simulator/blob/versao-final-corrigida/UML%20Diagram.png)

---

## Equipe

*   Camilla Torquato - [GitHub](https://github.com/camilatorquato) | [LinkedIn](https://www.linkedin.com/in/camila-torquato-5b5356354?utm_source=share_via&utm_content=profile&utm_medium=member_android)


*   Jhon Victor Ramos Martins - [GitHub](https://github.com/Jhon-Victor-Ramos) | [LinkedIn](https://www.linkedin.com/in/jhon-victor-ramos/) | [Instagram](https://www.instagram.com/jhonvictor_dev?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw==) | [Lattes](http://lattes.cnpq.br/2282663214204464)


*   Maria Clara de Oliveira Barbosa - [GitHub](link) | [LinkedIn](link) | [Instagram](link) | [Lattes](link) // Ainda precisa ajustar os links


*   Maria Luiza Monteiro - [GitHub](link) | [LinkedIn](link) | [Instagram](link) | [Lattes](link) // Ainda precisa ajustar os links


*   Rielly Luiza Duarte da Silva - [GitHub](https://github.com/rluizaduarte) | [LinkedIn](https://www.linkedin.com/in/rielly-luiza-370282332?utm_source=share_via&utm_content=profile&utm_medium=member_ios) | [Instagram](https://www.instagram.com/riellylduarte?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw==) | [Lattes](http://lattes.cnpq.br/0089072611329730)
