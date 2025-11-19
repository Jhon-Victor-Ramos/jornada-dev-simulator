package br.com.devlife.systems;

import br.com.devlife.core.Jogador;
import br.com.devlife.domain.AcaoLazer;
import br.com.devlife.domain.Curso;
import br.com.devlife.domain.Projeto;
import br.com.devlife.domain.enums.NivelCargo;
import br.com.devlife.domain.enums.NivelHabilidade;
import br.com.devlife.domain.enums.TipoAtividade;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GerenciadorDeAcoes {
    private final List<Projeto> todosOsProjetos;
    private final List<Curso> todosOsCursos;
    private final List<AcaoLazer> todasAsAcoesLazer;

    public GerenciadorDeAcoes() {
        // CONTEÚDO EXPANDIDO: 5 projetos para cada NivelCargo
        this.todosOsProjetos = List.of(
            // --- ESTAGIARIO_INICIO ---
            new Projeto("Clone de Blog Simples", "Recriar o layout de um blog famoso.", 10, 50.00, 20, 2, 1, 1, NivelCargo.ESTAGIARIO_INICIO, Map.of("HTML", NivelHabilidade.INICIANTE)),
            new Projeto("Calculadora com JavaScript", "Criar uma calculadora funcional.", 15, 75.00, 25, 3, 1, 2, NivelCargo.ESTAGIARIO_INICIO, Map.of("JavaScript", NivelHabilidade.INICIANTE)),
            new Projeto("Página Pessoal de Portfólio", "Desenvolver uma página de portfólio estática.", 20, 100.00, 30, 1, 5, 2, NivelCargo.ESTAGIARIO_INICIO, Map.of("HTML", NivelHabilidade.INICIANTE, "CSS", NivelHabilidade.INICIANTE)),
            new Projeto("Formulário de Contato", "Criar um formulário com validação em JS.", 25, 120.00, 35, 5, 2, 3, NivelCargo.ESTAGIARIO_INICIO, Map.of("JavaScript", NivelHabilidade.INICIANTE)),
            new Projeto("Ajustes de CSS em Site", "Corrigir bugs de responsividade em um site.", 30, 150.00, 15, 8, 2, 1, NivelCargo.ESTAGIARIO_INICIO, Map.of("CSS", NivelHabilidade.INICIANTE)),

            // --- ESTAGIARIO ---
            new Projeto("CRUD Básico de Usuários", "Criar um sistema de Cadastro/Leitura/Update/Delete.", 50, 250.00, 40, 10, 5, 5, NivelCargo.ESTAGIARIO, Map.of("Java", NivelHabilidade.INICIANTE, "SQL", NivelHabilidade.INICIANTE)),
            new Projeto("Consumir API de Cotação", "Exibir cotações de moedas em tempo real.", 40, 200.00, 30, 5, 3, 3, NivelCargo.ESTAGIARIO, Map.of("API REST", NivelHabilidade.INICIANTE)),
            new Projeto("Sistema de Login Simples", "Implementar um sistema de autenticação.", 60, 300.00, 50, 12, 5, 6, NivelCargo.ESTAGIARIO, Map.of("Java", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Componente de Galeria de Imagens", "Criar um componente reutilizável com JS.", 45, 220.00, 35, 8, 3, 4, NivelCargo.ESTAGIARIO, Map.of("JavaScript", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Otimização de Query", "Refatorar uma query SQL lenta em um sistema.", 65, 350.00, 25, 15, 2, 2, NivelCargo.ESTAGIARIO, Map.of("SQL", NivelHabilidade.INTERMEDIARIO)),

            // --- JUNIOR ---
            new Projeto("Módulo de Carrinho de Compras", "Implementar a funcionalidade de carrinho.", 80, 500.00, 45, 15, 8, 7, NivelCargo.JUNIOR, Map.of("Java", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Integração com Gateway de Pagamento", "Conectar a aplicação a um sistema de pagamentos.", 120, 800.00, 60, 20, 15, 10, NivelCargo.JUNIOR, Map.of("API REST", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Refatoração de Módulo Legado", "Modernizar um módulo antigo do sistema.", 100, 600.00, 70, 25, 5, 8, NivelCargo.JUNIOR, Map.of("Java", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Implementar Autenticação JWT", "Adicionar segurança baseada em token na API.", 110, 750.00, 55, 18, 10, 9, NivelCargo.JUNIOR, Map.of("Java", NivelHabilidade.AVANCADO)),
            new Projeto("Dashboard com Gráficos", "Criar uma página de admin com gráficos.", 90, 550.00, 40, 10, 8, 6, NivelCargo.JUNIOR, Map.of("JavaScript", NivelHabilidade.AVANCADO)),

            // --- PLENO ---
            new Projeto("Desenvolver Microserviço de Notificações", "Criar um serviço independente para enviar e-mails/SMS.", 200, 1500.00, 50, 20, 25, 15, NivelCargo.PLENO, Map.of("Java", NivelHabilidade.AVANCADO, "API REST", NivelHabilidade.AVANCADO)),
            new Projeto("Implementar Cache em API Crítica", "Adicionar Redis para otimizar performance.", 250, 1800.00, 60, 30, 20, 12, NivelCargo.PLENO, Map.of("Java", NivelHabilidade.AVANCADO)),
            new Projeto("Configurar Pipeline de CI/CD", "Automatizar o deploy da aplicação.", 180, 1300.00, 40, 15, 30, 10, NivelCargo.PLENO, Map.of("API REST", NivelHabilidade.AVANCADO)), // Simplificado
            new Projeto("Mentoria de Estagiário", "Acompanhar e guiar um novo estagiário.", 150, 0.00, 20, -10, 50, 20, NivelCargo.PLENO, Map.of()),
            new Projeto("Migração de Banco de Dados", "Planejar e executar a migração para um novo SGBD.", 300, 2500.00, 80, 40, 35, 25, NivelCargo.PLENO, Map.of("SQL", NivelHabilidade.AVANCADO)),

            // --- SENIOR ---
            new Projeto("Desenhar Arquitetura de Novo Sistema", "Criar o blueprint para um novo produto.", 400, 3000.00, 40, 25, 60, 20, NivelCargo.SENIOR, Map.of()),
            new Projeto("Planejar Migração de Monólito para Microserviços", "Definir a estratégia de quebra do sistema.", 500, 4000.00, 50, 35, 80, 30, NivelCargo.SENIOR, Map.of()),
            new Projeto("Prova de Conceito com Nova Tecnologia", "Pesquisar e testar uma nova tecnologia para a empresa.", 350, 2000.00, 60, 15, 70, 15, NivelCargo.SENIOR, Map.of()),
            new Projeto("Liderar Resposta a Incidente Crítico", "Coordenar a equipe para resolver uma falha em produção.", 450, 5000.00, 90, 50, 100, 5, NivelCargo.SENIOR, Map.of()),
            new Projeto("Estruturar Processo de Code Review", "Definir e implementar a cultura de revisão de código.", 300, 0.00, 20, -5, 120, 10, NivelCargo.SENIOR, Map.of()),

            // --- CEO ---
            new Projeto("Definir Roadmap de Produto Anual", "Planejar as features para o próximo ano.", 1000, 0.00, 50, 30, 200, 15, NivelCargo.CEO, Map.of()),
            new Projeto("Apresentação para Rodada de Investimento", "Conquistar novos investidores para a empresa.", 1500, 100000.00, 70, 50, 500, 10, NivelCargo.CEO, Map.of()),
            new Projeto("Planejar Aquisição de Startup Concorrente", "Analisar e negociar a compra de outra empresa.", 2000, -50000.00, 80, 60, 400, 30, NivelCargo.CEO, Map.of()),
            new Projeto("Palestra de Abertura em Conferência Tech", "Apresentar a visão da empresa para o mercado.", 800, 10000.00, 30, -10, 1000, 3, NivelCargo.CEO, Map.of()),
            new Projeto("Lançar Programa de Cultura e Valores", "Definir e disseminar a cultura da empresa.", 600, 0.00, 20, -20, 300, 20, NivelCargo.CEO, Map.of())
        );

        this.todosOsCursos = List.of();

        // ATIVIDADES DE LAZER CATEGORIZADAS
        this.todasAsAcoesLazer = List.of(
            // Saúde
            new AcaoLazer("Correr no Parque", "Custo: R$0, +10 Saúde", 0.00, -10, 10, 0, 0, TipoAtividade.SAUDE),
            new AcaoLazer("Comer uma Salada", "Custo: R$15, +15 Saúde", 15.00, -5, 15, 0, 0, TipoAtividade.SAUDE),
            new AcaoLazer("Ir à Academia", "Custo: R$80, +25 Saúde", 80.00, -20, 25, 0, 0, TipoAtividade.SAUDE),

            // Sanidade
            new AcaoLazer("Jogar Online com Amigos", "Custo: R$0, +10 Sanidade", 0.00, -5, 0, 10, 0, TipoAtividade.SANIDADE),
            new AcaoLazer("Assistir a um Filme", "Custo: R$30, +15 Sanidade", 30.00, -5, 0, 15, 0, TipoAtividade.SANIDADE),
            new AcaoLazer("Sair para Jantar", "Custo: R$100, +25 Sanidade", 100.00, -10, 0, 25, 0, TipoAtividade.SANIDADE)
        );
    }

    // LÓGICA DE FILTRAGEM ATUALIZADA
    public List<Projeto> getProjetosDisponiveis(Jogador jogador) {
        return todosOsProjetos.stream()
                .filter(projeto -> projeto.getNivelRequerido() == jogador.getCargo()) // 1. Filtra por cargo
                .filter(projeto -> !jogador.jaConcluiuProjeto(projeto)) // 2. Filtra os já concluídos
                .filter(projeto -> jogadorTemRequisitosParaProjeto(jogador, projeto)) // 3. Filtra por skills/energia
                .limit(5) // 4. Limita a 5 opções
                .collect(Collectors.toList());
    }

    public List<Curso> getCursosDisponiveis(Jogador jogador) {
        return List.copyOf(todosOsCursos);
    }

    // MÉTODO ATUALIZADO PARA FILTRAR POR TIPO
    public List<AcaoLazer> getAcoesLazerDisponiveis(Jogador jogador, TipoAtividade tipo) {
        return todasAsAcoesLazer.stream()
                .filter(acao -> acao.getTipo() == tipo) // Filtra pelo tipo (SAUDE ou SANIDADE)
                .filter(acao -> jogador.getDinheiro() >= acao.getCustoDinheiro()) // Filtra se pode pagar
                .collect(Collectors.toList());
    }

    private boolean jogadorTemRequisitosParaProjeto(Jogador jogador, Projeto projeto) {
        if (jogador.getEnergia() < projeto.getEnergiaCusto()) {
            return false;
        }

        for (Map.Entry<String, NivelHabilidade> requisito : projeto.getRequisitos().entrySet()) {
            NivelHabilidade nivelMinimoRequerido = requisito.getValue();
            NivelHabilidade nivelJogador = jogador.getNivelHabilidade(requisito.getKey());

            if (nivelJogador.getValor() < nivelMinimoRequerido.getValor()) {
                return false;
            }
        }
        return true;
    }
}