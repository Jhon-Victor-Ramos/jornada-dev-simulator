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
        // CONTEÚDO EXPANDIDO: 3 projetos de front e 3 projetos de back para cada nível
                this.todosOsProjetos = List.of(
            // --- ESTAGIARIO_INICIO ---
            // Front-end
            new Projeto("Clone de Blog Simples", "Recriar o layout de um blog famoso.", 10, 50.00, 20, 2, 1, 1, NivelCargo.ESTAGIARIO_INICIO, Map.of("HTML", NivelHabilidade.INICIANTE)),
            new Projeto("Página Pessoal de Portfólio", "Desenvolver uma página de portfólio estática.", 20, 100.00, 30, 1, 5, 2, NivelCargo.ESTAGIARIO_INICIO, Map.of("HTML", NivelHabilidade.INICIANTE, "CSS", NivelHabilidade.INICIANTE)),
            new Projeto("Formulário de Contato com JS", "Criar um formulário com validação em JS.", 25, 120.00, 35, 5, 2, 3, NivelCargo.ESTAGIARIO_INICIO, Map.of("JavaScript", NivelHabilidade.INICIANTE)),
            // Back-end
            new Projeto("Script 'Hello World' em Java", "Compilar e rodar um programa Java simples.", 10, 50.00, 20, 2, 1, 1, NivelCargo.ESTAGIARIO_INICIO, Map.of("Java", NivelHabilidade.INICIANTE)),
            new Projeto("Consulta SQL Simples", "Escrever uma query SELECT para buscar usuários.", 15, 75.00, 25, 3, 1, 2, NivelCargo.ESTAGIARIO_INICIO, Map.of("SQL", NivelHabilidade.INICIANTE)),
            new Projeto("Endpoint 'Health Check' de API", "Criar um endpoint que retorna 'status: ok'.", 20, 100.00, 30, 1, 5, 2, NivelCargo.ESTAGIARIO_INICIO, Map.of("API REST", NivelHabilidade.INICIANTE)),

            // --- ESTAGIARIO ---
            // Front-end
            new Projeto("Componente de Galeria de Imagens", "Criar um componente reutilizável com JS.", 45, 220.00, 35, 8, 3, 4, NivelCargo.ESTAGIARIO, Map.of("JavaScript", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Ajuste de Responsividade", "Corrigir layout em um site para dispositivos móveis.", 40, 200.00, 25, 10, 2, 2, NivelCargo.ESTAGIARIO, Map.of("CSS", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Consumir API com Fetch", "Exibir dados de uma API externa em uma página.", 50, 250.00, 30, 5, 4, 3, NivelCargo.ESTAGIARIO, Map.of("JavaScript", NivelHabilidade.INTERMEDIARIO, "API REST", NivelHabilidade.INICIANTE)),
            // Back-end
            new Projeto("CRUD Básico de Usuários", "Criar um sistema de Cadastro/Leitura/Update/Delete.", 60, 300.00, 50, 12, 5, 6, NivelCargo.ESTAGIARIO, Map.of("Java", NivelHabilidade.INTERMEDIARIO, "SQL", NivelHabilidade.INICIANTE)),
            new Projeto("Sistema de Login Simples", "Implementar um sistema de autenticação.", 65, 320.00, 55, 15, 6, 7, NivelCargo.ESTAGIARIO, Map.of("Java", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Otimização de Query", "Refatorar uma query SQL lenta em um sistema.", 70, 350.00, 25, 18, 2, 2, NivelCargo.ESTAGIARIO, Map.of("SQL", NivelHabilidade.INTERMEDIARIO)),

            // --- JUNIOR ---
            // Front-end
            new Projeto("Dashboard com Gráficos", "Criar uma página de admin com gráficos usando uma lib.", 90, 550.00, 40, 10, 8, 6, NivelCargo.JUNIOR, Map.of("JavaScript", NivelHabilidade.AVANCADO)),
            new Projeto("Configurar Rotas em SPA", "Estruturar a navegação de uma Single Page Application.", 80, 500.00, 35, 8, 10, 5, NivelCargo.JUNIOR, Map.of("JavaScript", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Teste de Componentes com Jest", "Escrever testes unitários para componentes JS.", 100, 600.00, 50, 15, 12, 7, NivelCargo.JUNIOR, Map.of("JavaScript", NivelHabilidade.AVANCADO)),
            // Back-end
            new Projeto("Integração com Gateway de Pagamento", "Conectar a aplicação a um sistema de pagamentos.", 120, 800.00, 60, 20, 15, 10, NivelCargo.JUNIOR, Map.of("API REST", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Implementar Autenticação JWT", "Adicionar segurança baseada em token na API.", 110, 750.00, 55, 18, 10, 9, NivelCargo.JUNIOR, Map.of("Java", NivelHabilidade.AVANCADO, "API REST", NivelHabilidade.INTERMEDIARIO)),
            new Projeto("Refatoração de Módulo Legado", "Modernizar um módulo antigo do sistema.", 130, 900.00, 70, 25, 5, 8, NivelCargo.JUNIOR, Map.of("Java", NivelHabilidade.AVANCADO)),

            // --- PLENO ---
            // Front-end
            new Projeto("Otimizar Performance de Carregamento", "Aplicar lazy loading e code splitting.", 200, 1500.00, 50, 20, 25, 15, NivelCargo.PLENO, Map.of("JavaScript", NivelHabilidade.AVANCADO)),
            new Projeto("Auditoria de Acessibilidade (A11Y)", "Corrigir problemas de acessibilidade em um site.", 180, 1300.00, 30, 10, 30, 10, NivelCargo.PLENO, Map.of("HTML", NivelHabilidade.AVANCADO)),
            new Projeto("Criar uma Biblioteca de Componentes", "Desenvolver componentes reutilizáveis para a equipe.", 250, 1800.00, 60, 25, 40, 20, NivelCargo.PLENO, Map.of("JavaScript", NivelHabilidade.AVANCADO)),
            // Back-end
            new Projeto("Desenvolver Microserviço de Notificações", "Criar um serviço para enviar e-mails/SMS.", 220, 1600.00, 55, 22, 28, 18, NivelCargo.PLENO, Map.of("Java", NivelHabilidade.AVANCADO, "API REST", NivelHabilidade.AVANCADO)),
            new Projeto("Implementar Cache em API Crítica", "Adicionar Redis para otimizar performance.", 280, 2000.00, 65, 30, 20, 14, NivelCargo.PLENO, Map.of("Java", NivelHabilidade.AVANCADO)),
            new Projeto("Migração de Banco de Dados", "Planejar e executar a migração para um novo SGBD.", 300, 2500.00, 80, 40, 35, 25, NivelCargo.PLENO, Map.of("SQL", NivelHabilidade.AVANCADO)),

            // --- SENIOR ---
            // Front-end
            new Projeto("Desenhar Arquitetura Front-end", "Definir o blueprint de um novo sistema web.", 400, 3000.00, 40, 25, 60, 20, NivelCargo.SENIOR, Map.of()),
            new Projeto("Liderar Migração de Framework", "Planejar e guiar a equipe na migração de jQuery para React.", 500, 4000.00, 60, 40, 80, 30, NivelCargo.SENIOR, Map.of()),
            new Projeto("Prova de Conceito com WebAssembly", "Pesquisar e testar o uso de Wasm no produto.", 350, 2000.00, 50, 15, 70, 15, NivelCargo.SENIOR, Map.of()),
            // Back-end
            new Projeto("Planejar Migração para Microserviços", "Definir a estratégia de quebra de um monólito.", 550, 4500.00, 55, 35, 85, 35, NivelCargo.SENIOR, Map.of()),
            new Projeto("Liderar Resposta a Incidente Crítico", "Coordenar a equipe para resolver uma falha em produção.", 450, 5000.00, 90, 50, 100, 5, NivelCargo.SENIOR, Map.of()),
            new Projeto("Estruturar Processo de Code Review", "Definir e implementar a cultura de revisão de código.", 300, 0.00, 20, -5, 120, 10, NivelCargo.SENIOR, Map.of())
        );

        this.todosOsCursos = List.of();

        this.todasAsAcoesLazer = List.of(
            // --- Atividades de SAÚDE ---
            new AcaoLazer("Caminhada Leve", "Custo: R$ 0 | Saúde: +3", 0.00, -5, 3, 1, 0, TipoAtividade.SAUDE),
            new AcaoLazer("Correr no Parque", "Custo: R$ 5 | Saúde: +10", 5.00, -15, 10, 0, 0, TipoAtividade.SAUDE),
            new AcaoLazer("Comprar Vegetais", "Custo: R$ 50 | Saúde: +15", 50.00, -5, 15, 0, 0, TipoAtividade.SAUDE),
            new AcaoLazer("Tomar Vitaminas", "Custo: R$ 75 | Saúde: +25", 75.00, 0, 25, 0, 0, TipoAtividade.SAUDE),
            new AcaoLazer("Fazer um Check-up", "Custo: R$ 150 | Saúde: +30 | Duração: 1 dia", 150.00, -10, 30, 0, 1, TipoAtividade.SAUDE),
            new AcaoLazer("Consulta no Hospital", "Custo: R$ 500 | Saúde: +50 | Duração: 1 dia", 500.00, -20, 50, 0, 1, TipoAtividade.SAUDE),

            // --- Atividades de SANIDADE ---
            new AcaoLazer("Jogar Online com Amigos", "Custo: R$ 0 | Sanidade: +3", 0.00, -5, 0, 3, 0, TipoAtividade.SANIDADE),
            new AcaoLazer("Meditar por 15 minutos", "Custo: R$ 5 | Sanidade: +5", 5.00, 5, 0, 5, 0, TipoAtividade.SANIDADE),
            new AcaoLazer("Ir ao Cinema", "Custo: R$ 30 | Sanidade: +10", 30.00, -10, 0, 10, 0, TipoAtividade.SANIDADE),
            new AcaoLazer("Sair com os Amigos", "Custo: R$ 50 | Sanidade: +15", 50.00, -15, 0, 15, 0, TipoAtividade.SANIDADE),
            new AcaoLazer("Ir a um Show", "Custo: R$ 100 | Sanidade: +25", 100.00, -20, 0, 25, 0, TipoAtividade.SANIDADE),
            new AcaoLazer("Sessão de Terapia", "Custo: R$ 500 | Sanidade: +50 | Duração: 1 dia", 500.00, -10, 0, 50, 1, TipoAtividade.SANIDADE)
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