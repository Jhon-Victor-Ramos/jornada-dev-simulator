<div align="center">
  <h1 align="center">🎮 DevLife: A Text-Based Career Simulation RPG 🎮</h1>
  <p align="center">
    Simule a jornada de um profissional de TI, desde o primeiro estágio até o topo da carreira, gerenciando habilidades, finanças e bem-estar.
  </p>
</div>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue.svg" alt="Java 17">
  <img src="https://img.shields.io/badge/Build-Maven-red.svg" alt="Built with Maven">
  <img src="https://img.shields.io/badge/License-MIT-green.svg" alt="License MIT">
</p>

<p align="center">
 <a href="#-sobre-o-projeto">Sobre</a> •
 <a href="#-gameplay--features">Features</a> •
 <a href="#-tech-stack">Tecnologias</a> •
 <a href="#-começando">Como Executar</a> •
 <a href="#-arquitetura-do-projeto">Arquitetura</a> •
 <a href="#-diagrama-uml">UML</a> •
 <a href="#-equipe">Equipe</a>
</p>

---

## 📖 Sobre o Projeto

**DevLife** é um jogo de simulação e RPG em modo texto onde você assume o papel de um desenvolvedor de software em início de carreira. O seu objetivo é simples, mas desafiador: subir na hierarquia corporativa, saindo de **Estagiário** até se tornar **CEO**.

Para isso, você precisará tomar decisões estratégicas dia após dia, balanceando o desenvolvimento de novas habilidades, a entrega de projetos, a busca por empregos melhores e, o mais importante, a sua própria **saúde, sanidade e energia**.

Este projeto foi desenvolvido em **Java 17**, com um forte foco em boas práticas de **Programação Orientada a Objetos (POO)**, resultando em um código limpo, modular e de fácil manutenção.

---

## ✨ Gameplay & Features

-   🧠 **Gerenciamento Estratégico de Recursos:** Cada ação consome recursos. Fique de olho nos seus níveis de **Energia**, **Saúde** e **Sanidade**. Níveis baixos podem levar a penalidades e esgotamento!

-   🚀 **Progressão de Carreira Realista:** Avance por níveis de cargo bem definidos: `Estagiário Início` -> `Estagiário` -> `Júnior` -> `Pleno` -> `Sênior` -> `CEO`. Cada cargo desbloqueia novas oportunidades e desafios.

-   📚 **Desenvolvimento de Habilidades:** Invista em um catálogo extenso de cursos para aprender **Hard Skills** (como `Java`, `SQL`, `AWS`, `Kubernetes`) e **Soft Skills** (como `Liderança`, `Comunicação`, `Negociação`).

-   💼 **Oportunidades Dinâmicas:** O mercado de trabalho é vivo! Novas vagas, projetos e cursos se tornam disponíveis de acordo com seu nível de cargo e habilidades atuais. Cumpra os pré-requisitos para ter acesso às melhores oportunidades.

-   💰 **Gestão Financeira:** Ganhe dinheiro com projetos e salários mensais. Use seus recursos com sabedoria para investir em cursos, participar de eventos e cuidar do seu bem-estar.

-   🌐 **Networking e Eventos:** Participe de eventos de tecnologia para aumentar seu networking, ganhar experiência e até mesmo desbloquear bônus especiais, como descontos em cursos!

---

## 🛠️ Tech Stack

-   **Linguagem Principal:** **Java 17 (LTS)**
-   **Gerenciador de Dependências e Build:** **Maven**

---

## 🚀 Começando

Para executar o projeto localmente, siga os passos abaixo.

### Pré-requisitos:
*   **Java JDK 17** ou superior
*   **Apache Maven**

### Execução

```bash
# 1. Clone o repositório
git clone https://github.com/Jhon-Victor-Ramos/jornada-dev-simulator.git

# 2. Navegue até o diretório do projeto
cd jornada-dev-simulator

# 3. Compile o projeto
# Este comando baixará as dependências e compilará o código-fonte.
mvn compile

# 4. Execute o jogo!
# O Maven executará a classe principal definida no pom.xml.
mvn exec:java -Dexec.mainClass="br.com.devlife.Main"
```

**Alternativa (Executando o .jar):**

Você também pode empacotar a aplicação em um arquivo `.jar` para uma execução mais simples.

```bash
# 1. Empacote o projeto (após clonar e entrar na pasta)
mvn package

# 2. Execute o arquivo .jar gerado na pasta 'target'
# Nota: O nome do arquivo pode variar ligeiramente.
java -jar target/devlife-1.0-SNAPSHOT.jar
```

---

## 🏗️ Arquitetura do Projeto

O projeto foi estruturado seguindo princípios de **Separação de Responsabilidades**, assemelhando-se a uma arquitetura MVC (Model-View-Controller) para aplicações de terminal.

-   📁 **`src/main/java/br/com/devlife`**
    -   🔵 **`core` (Model):** Contém a classe `Jogador`, que representa o estado central e todos os atributos do personagem.
    -   📦 **`domain` (Model):** Define as entidades imutáveis do jogo, como `Curso`, `Projeto`, `Vaga`, e os `enums` que garantem a consistência dos dados.
    -   🧠 **`systems` (Controller):** Contém a lógica principal do jogo. `MotorDoJogo` gerencia o loop de gameplay e o fluxo de turnos, enquanto `GerenciadorDeAcoes` atua como um "banco de dados" de conteúdo, filtrando as ações disponíveis para o jogador.
    -   🖥️ **`ui` (View):** A classe `TerminalUI` é a única responsável por toda a interação com o usuário, desde exibir o dashboard e menus até capturar as entradas, garantindo que a lógica do jogo e a apresentação sejam desacopladas.

---

## 🗺️ Diagrama UML

O diagrama abaixo ilustra a relação entre as principais classes do sistema, demonstrando a estrutura e o fluxo de interações.

![Diagrama UML demonstrando o funcionamento do projeto](https://raw.githubusercontent.com/Jhon-Victor-Ramos/jornada-dev-simulator/versao-final-corrigida/UML%20Diagram.png)

---

## 👥 Equipe

*   **Camilla Torquato** - [GitHub](https://github.com/camilatorquato) | [LinkedIn](https://www.linkedin.com/in/camila-torquato-5b5356354?utm_source=share_via&utm_content=profile&utm_medium=member_android)
*   **Jhon Victor Ramos Martins** - [GitHub](https://github.com/Jhon-Victor-Ramos) | [LinkedIn](https://www.linkedin.com/in/jhon-victor-ramos/) | [Instagram](https://www.instagram.com/jhonvictor_dev?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw==) | [Lattes](http://lattes.cnpq.br/2282663214204464)
*   **Maria Clara de Oliveira Barbosa** - [GitHub](link) | [LinkedIn](link) | [Instagram](link) | [Lattes](link)
*   **Maria Luiza Monteiro** - [GitHub](link) | [LinkedIn](link) | [Instagram](link) | [Lattes](link)
*   **Rielly Luiza Duarte da Silva** - [GitHub](https://github.com/rluizaduarte) | [LinkedIn](https://www.linkedin.com/in/rielly-luiza-370282332?utm_source=share_via&utm_content=profile&utm_medium=member_ios) | [Instagram](https://www.instagram.com/riellylduarte?utm_source=ig_web_button_share_sheet&igsh=ZDNlZDc0MzIxNw==) | [Lattes](http://lattes.cnpq.br/0089072611329730)
