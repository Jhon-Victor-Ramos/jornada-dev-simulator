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

        this.todosOsCursos = List.of(
            // Cursos de Habilidades Básicas (Estágio)
            new Curso("Fundamentos de SO", 100.00, 3, "SO", NivelHabilidade.INICIANTE, 20, Map.of()),
            new Curso("Pacote Office Essencial", 80.00, 2, "Pacote Office", NivelHabilidade.INICIANTE, 15, Map.of()),
            new Curso("Lógica de Programação", 150.00, 5, "Lógica de Programação", NivelHabilidade.INICIANTE, 30, Map.of()),
            new Curso("Git e GitHub para Iniciantes", 120.00, 3, "Git e GitHub", NivelHabilidade.INICIANTE, 25, Map.of()),
            new Curso("Introdução ao Terminal", 90.00, 2, "Terminal", NivelHabilidade.INICIANTE, 20, Map.of()),
            new Curso("SQL Básico", 200.00, 5, "SQL", NivelHabilidade.INICIANTE, 40, Map.of()),
            
            // Cursos de Habilidades Intermediárias (Júnior)
            new Curso("HTML5 e CSS3", 200.00, 5, "HTML5 e CSS3", NivelHabilidade.INICIANTE, 40, Map.of()),
            new Curso("JavaScript Básico", 250.00, 7, "JavaScript", NivelHabilidade.INICIANTE, 50, Map.of()),
            new Curso("Node.js Básico", 350.00, 7, "Node.js", NivelHabilidade.INICIANTE, 70, Map.of()),
            new Curso("SQL Intermediário", 400.00, 10, "SQL", NivelHabilidade.INTERMEDIARIO, 80, Map.of("SQL", NivelHabilidade.INICIANTE)),
            new Curso("Fundamentos de Flask", 320.00, 6, "Flask", NivelHabilidade.INICIANTE, 65, Map.of()),
            new Curso("Fundamentos de Criptografia", 450.00, 8, "Criptografia", NivelHabilidade.INICIANTE, 90, Map.of()),
            new Curso("IDS/IPS e Segurança de Redes", 500.00, 10, "IDS/IPS", NivelHabilidade.INICIANTE, 100, Map.of()),
            new Curso("Gestão de Vulnerabilidades", 480.00, 9, "Gestão de Vulnerabilidade", NivelHabilidade.INICIANTE, 95, Map.of()),

            // Cursos de Habilidades Avançadas (Pleno)
            new Curso("React.js", 500.00, 12, "React.js", NivelHabilidade.INICIANTE, 120, Map.of("JavaScript", NivelHabilidade.INICIANTE)),
            new Curso("Node.js Intermediário", 450.00, 10, "Node.js", NivelHabilidade.INTERMEDIARIO, 100, Map.of("Node.js", NivelHabilidade.INICIANTE)),
            new Curso("Apache Kafka", 600.00, 15, "Kafka", NivelHabilidade.INICIANTE, 150, Map.of("Java", NivelHabilidade.INTERMEDIARIO)),
            new Curso("AWS Cloud Practitioner", 700.00, 14, "AWS", NivelHabilidade.INICIANTE, 180, Map.of()),
            new Curso("Docker e Kubernetes", 750.00, 16, "Kubernetes", NivelHabilidade.INICIANTE, 200, Map.of()),
            new Curso("SQL Avançado", 550.00, 12, "SQL", NivelHabilidade.AVANCADO, 140, Map.of("SQL", NivelHabilidade.INTERMEDIARIO)),
            new Curso("Business Intelligence (BI)", 650.00, 13, "BI", NivelHabilidade.INICIANTE, 160, Map.of()),
            new Curso("Node.js Avançado", 550.00, 12, "Node.js", NivelHabilidade.AVANCADO, 140, Map.of("Node.js", NivelHabilidade.INTERMEDIARIO)),
            
            // Cursos de Habilidades Sênior
            new Curso("Dominando a LGPD", 800.00, 10, "LGPD", NivelHabilidade.INICIANTE, 220, Map.of()),
            new Curso("Arquitetura de Microsserviços", 900.00, 20, "Arquitetura de Microsserviços", NivelHabilidade.INICIANTE, 250, Map.of("Java", NivelHabilidade.AVANCADO)),
            new Curso("Mentoria em Code Review (Soft Skill)", 400.00, 5, "Mentoria em Code Review", NivelHabilidade.INICIANTE, 100, Map.of()),
            new Curso("AWS Solutions Architect Associate", 1200.00, 25, "AWS", NivelHabilidade.INTERMEDIARIO, 300, Map.of("AWS", NivelHabilidade.INICIANTE)),
            new Curso("Redis para Caching Avançado", 700.00, 9, "Redis", NivelHabilidade.INICIANTE, 180, Map.of()),
            new Curso("Mandarim Básico", 1500.00, 30, "Mandarim", NivelHabilidade.INICIANTE, 150, Map.of()),
            new Curso("Mandarim Avançado", 2000.00, 40, "Mandarim", NivelHabilidade.AVANCADO, 200, Map.of("Mandarim", NivelHabilidade.INICIANTE)),
            new Curso("Apache Spark e Big Data", 1800.00, 28, "Spark", NivelHabilidade.INICIANTE, 400, Map.of()),
            new Curso("Kubeflow e MLOps", 2200.00, 35, "Kubeflow", NivelHabilidade.INICIANTE, 500, Map.of("Kubernetes", NivelHabilidade.INICIANTE)),

            // Cursos de Soft Skills
            new Curso("Inglês Técnico I", 200.00, 10, "Inglês Técnico", NivelHabilidade.INICIANTE, 40, Map.of()),
            new Curso("Inglês Técnico II", 250.00, 12, "Inglês Técnico", NivelHabilidade.INTERMEDIARIO, 50, Map.of("Inglês Técnico", NivelHabilidade.INICIANTE)),
            new Curso("Comunicação Eficaz", 180.00, 4, "Comunicação", NivelHabilidade.INICIANTE, 35, Map.of()),
            new Curso("Trabalho em Equipe", 220.00, 3, "Trabalho em Equipe", NivelHabilidade.INICIANTE, 45, Map.of()),
            new Curso("Adaptabilidade Profissional", 210.00, 3, "Adaptabilidade", NivelHabilidade.INICIANTE, 40, Map.of()),
            new Curso("Flexibilidade e Gestão de Mudanças", 230.00, 4, "Flexibilidade", NivelHabilidade.INICIANTE, 50, Map.of()),
            new Curso("Colaboração e Feedback", 240.00, 3, "Colaboração", NivelHabilidade.INICIANTE, 55, Map.of()),
            new Curso("Resolução de Problemas Complexos", 300.00, 5, "Resolução de Problemas", NivelHabilidade.INICIANTE, 70, Map.of()),
            new Curso("Pensamento Crítico", 280.00, 4, "Pensamento Crítico", NivelHabilidade.INICIANTE, 65, Map.of()),
            new Curso("Inteligência Emocional no Trabalho", 320.00, 5, "Inteligência Emocional", NivelHabilidade.INICIANTE, 75, Map.of()),
            new Curso("Liderança para Iniciantes", 350.00, 6, "Liderança", NivelHabilidade.INICIANTE, 80, Map.of()),
            new Curso("Organização e Gestão do Tempo", 180.00, 2, "Organização", NivelHabilidade.INICIANTE, 30, Map.of()),
            new Curso("Proatividade e Iniciativa", 200.00, 3, "Proatividade", NivelHabilidade.INICIANTE, 40, Map.of()),
            new Curso("Ética no Trabalho", 150.00, 2, "Ética no Trabalho", NivelHabilidade.INICIANTE, 25, Map.of()),
            new Curso("Técnicas de Negociação", 380.00, 5, "Negociação", NivelHabilidade.INICIANTE, 85, Map.of()),
            new Curso("Visão de Negócio para Devs", 420.00, 6, "Visão de Negócio", NivelHabilidade.INICIANTE, 90, Map.of())
        );

        // MUDANÇA: CATÁLOGO DE VAGAS COMPLETO E COM VALIDADE RECALCULADA
        this.todasAsVagas = List.of(
            // --- Vagas para ESTÁGIO (requer ESTAGIARIO_INICIO) ---
            // Cursos: SO(3d) + Pacote Office(2d) = 5d. Validade: 5 + 5 = 10 dias
            new Vaga("Consultores Consultorias", "Estágio em Suporte", 1200.00, 2000, 50, 10, 0, Map.of("SO", NivelHabilidade.INICIANTE, "Pacote Office", NivelHabilidade.INICIANTE), 10, NivelCargo.ESTAGIARIO_INICIO, NivelCargo.ESTAGIARIO),
            // Cursos: Lógica(5d) + Git(3d) + Inglês I(10d) = 18d. Validade: 18 + 5 = 23 dias
            new Vaga("High Tech Corp", "Estágio em Desenvolvimento", 1800.00, 5000, 150, 0, 10, Map.of("Lógica de Programação", NivelHabilidade.INICIANTE, "Git e GitHub", NivelHabilidade.INICIANTE, "Inglês Técnico", NivelHabilidade.INICIANTE), 23, NivelCargo.ESTAGIARIO_INICIO, NivelCargo.ESTAGIARIO),
            // Cursos: SQL(5d) + Terminal(2d) + Inglês I(10d) -> Inglês II(12d) + Comunicação(4d) = 33d. Validade: 33 + 5 = 38 dias
            new Vaga("IBN", "Estágio em Banco de Dados", 2300.00, 10000, 400, 2, 2, Map.of("SQL", NivelHabilidade.INICIANTE, "Terminal", NivelHabilidade.INICIANTE, "Comunicação", NivelHabilidade.INICIANTE, "Inglês Técnico", NivelHabilidade.INTERMEDIARIO), 38, NivelCargo.ESTAGIARIO_INICIO, NivelCargo.ESTAGIARIO),

            // --- Vagas para JÚNIOR (requer ESTAGIARIO) ---
            // Cursos: HTML/CSS(5d) + JS Básico(7d) = 12d. Validade: 12 + 5 = 17 dias
            new Vaga("CE&T", "Desenvolvedor Front-end Jr.", 3500.00, 2000, 50, 10, 0, Map.of("HTML5 e CSS3", NivelHabilidade.INICIANTE, "JavaScript", NivelHabilidade.INICIANTE), 17, NivelCargo.ESTAGIARIO, NivelCargo.JUNIOR),
            // Cursos: Node.js Básico(7d) + SQL Interm.(10d) + Flask(6d) + Trabalho Equipe(3d) = 26d. Validade: 26 + 5 = 31 dias
            new Vaga("Tivet", "Desenvolvedor Back-end Jr.", 5150.00, 5000, 150, 0, 10, Map.of("Node.js", NivelHabilidade.INICIANTE, "SQL", NivelHabilidade.INTERMEDIARIO, "Flask", NivelHabilidade.INICIANTE, "Trabalho em Equipe", NivelHabilidade.INICIANTE), 31, NivelCargo.ESTAGIARIO, NivelCargo.JUNIOR),
            // Cursos: IDS/IPS(10d) + Cripto(8d) + Gestão Vuln(9d) + Adaptabilidade(3d) = 30d. Validade: 30 + 5 = 35 dias
            new Vaga("Oráculo", "Analista de Segurança Jr.", 6800.00, 10000, 400, 2, 2, Map.of("IDS/IPS", NivelHabilidade.INICIANTE, "Criptografia", NivelHabilidade.INICIANTE, "Gestão de Vulnerabilidade", NivelHabilidade.INICIANTE, "Adaptabilidade", NivelHabilidade.INICIANTE), 35, NivelCargo.ESTAGIARIO, NivelCargo.JUNIOR),

            // --- Vagas para PLENO (requer JUNIOR) ---
            // Cursos: React(12d) + Node Interm(10d) + Kafka(15d) + Flexibilidade(4d) = 41d. Validade: 41 + 5 = 46 dias
            new Vaga("Mundo Informática", "Desenvolvedor Full Stack Pleno", 7500.00, 2000, 50, 10, 0, Map.of("React.js", NivelHabilidade.INICIANTE, "Node.js", NivelHabilidade.INTERMEDIARIO, "Kafka", NivelHabilidade.INICIANTE, "Flexibilidade", NivelHabilidade.INICIANTE), 46, NivelCargo.JUNIOR, NivelCargo.PLENO),
            // Cursos: AWS Básico(14d) + Docker/K8s(16d) + Colaboração(3d) + Resolução Prob(5d) = 38d. Validade: 38 + 5 = 43 dias
            new Vaga("J.U.L.I.O", "Engenheiro DevOps Pleno", 10000.00, 5000, 150, 0, 10, Map.of("AWS", NivelHabilidade.INICIANTE, "Kubernetes", NivelHabilidade.INICIANTE, "Colaboração", NivelHabilidade.INICIANTE, "Resolução de Problemas", NivelHabilidade.INICIANTE), 43, NivelCargo.JUNIOR, NivelCargo.PLENO),
            // Cursos: SQL Avançado(12d) + BI(13d) + Node Avançado(12d) + Pens. Crítico(4d) + Int. Emocional(5d) + Liderança(6d) = 52d. Validade: 52 + 5 = 57 dias
            new Vaga("Itai", "Engenheiro de Dados Pleno", 12500.00, 10000, 400, 2, 2, Map.of("SQL", NivelHabilidade.AVANCADO, "BI", NivelHabilidade.INICIANTE, "Node.js", NivelHabilidade.AVANCADO, "Pensamento Crítico", NivelHabilidade.INICIANTE, "Inteligência Emocional", NivelHabilidade.INICIANTE, "Liderança", NivelHabilidade.INICIANTE), 57, NivelCargo.JUNIOR, NivelCargo.PLENO),

            // --- Vagas para SÊNIOR (requer PLENO) ---
            // Cursos: LGPD(10d) + Arq. Microsserv(20d) + Mentoria(5d) + Organização(2d) = 37d. Validade: 37 + 5 = 42 dias
            new Vaga("Acento", "Arquiteto de Soluções Sênior", 13000.00, 2000, 50, 10, 0, Map.of("LGPD", NivelHabilidade.INICIANTE, "Arquitetura de Microsserviços", NivelHabilidade.INICIANTE, "Mentoria em Code Review", NivelHabilidade.INICIANTE, "Organização", NivelHabilidade.INICIANTE), 42, NivelCargo.PLENO, NivelCargo.SENIOR),
            // Cursos: AWS Interm(25d) + Mandarim Básico(30d) + Redis(9d) + Proatividade(3d) = 67d. Validade: 67 + 5 = 72 dias
            new Vaga("Gestão de Negócios", "Engenheiro SRE Sênior", 16500.00, 5000, 150, 0, 10, Map.of("AWS", NivelHabilidade.INTERMEDIARIO, "Mandarim", NivelHabilidade.INICIANTE, "Redis", NivelHabilidade.INICIANTE, "Proatividade", NivelHabilidade.INICIANTE), 72, NivelCargo.PLENO, NivelCargo.SENIOR),
            // Cursos: Kubeflow(35d) + Spark(28d) + Mandarim Avançado(40d) + Ética(2d) + Negociação(5d) + Visão de Neg.(6d) = 116d. Validade: 116 + 5 = 121 dias
            new Vaga("Gogle", "Cientista de Dados Sênior", 20000.00, 10000, 400, 2, 2, Map.of("Kubeflow", NivelHabilidade.INICIANTE, "Spark", NivelHabilidade.INICIANTE, "Mandarim", NivelHabilidade.AVANCADO, "Ética no Trabalho", NvelHabilidade.INICIANTE, "Negociação", NivelHabilidade.INICIANTE, "Visão de Negócio", NivelHabilidade.INICIANTE), 121, NivelCargo.PLENO, NivelCargo.SENIOR)
        );

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