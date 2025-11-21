package br.com.devlife.systems;

import br.com.devlife.core.Jogador;
import br.com.devlife.domain.AcaoLazer;
import br.com.devlife.domain.Curso;
import br.com.devlife.domain.Evento;
import br.com.devlife.domain.Projeto;
import br.com.devlife.domain.enums.NivelCargo;
import br.com.devlife.domain.enums.NivelHabilidade;
import br.com.devlife.domain.enums.TipoAtividade;
import br.com.devlife.domain.Vaga;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GerenciadorDeAcoes {
    private final List<Projeto> todosOsProjetos;
    private final List<Curso> todosOsCursos;
    private final List<AcaoLazer> todasAsAcoesLazer;
    private final List<Vaga> todasAsVagas;
    private final List<Evento> todosOsEventos;

    public GerenciadorDeAcoes() {
        // ... (A inicialização da lista todosOsProjetos permanece a mesma)
        this.todosOsProjetos = List.of(
                // --- ESTAGIARIO_INICIO ---
                // Front-end
                new Projeto("Clone de Blog Simples", "Recriar o layout de um blog famoso.", 150, 100.00, 45, 15, 10, 3, NivelCargo.ESTAGIARIO_INICIO, Map.of("HTML", NivelHabilidade.INICIANTE)),
                new Projeto("Página Pessoal de Portfólio", "Desenvolver uma página de portfólio estática.", 200, 150.00, 55, 20, 15, 4, NivelCargo.ESTAGIARIO_INICIO, Map.of("HTML", NivelHabilidade.INICIANTE, "CSS", NivelHabilidade.INICIANTE)),
                new Projeto("Formulário de Contato com JS", "Criar um formulário com validação em JS.", 250, 180.00, 65, 25, 20, 5, NivelCargo.ESTAGIARIO_INICIO, Map.of("JavaScript", NivelHabilidade.INICIANTE)),
                // Back-end
                new Projeto("Script 'Hello World' em Java", "Compilar e rodar um programa Java simples.", 150, 100.00, 45, 15, 10, 3, NivelCargo.ESTAGIARIO_INICIO, Map.of("Java", NivelHabilidade.INICIANTE)),
                new Projeto("Consulta SQL Simples", "Escrever uma query SELECT para buscar usuários.", 200, 120.00, 55, 20, 15, 4, NivelCargo.ESTAGIARIO_INICIO, Map.of("SQL", NivelHabilidade.INICIANTE)),
                new Projeto("Endpoint 'Health Check' de API", "Criar um endpoint que retorna 'status: ok'.", 250, 180.00, 65, 25, 20, 5, NivelCargo.ESTAGIARIO_INICIO, Map.of("API REST", NivelHabilidade.INICIANTE)),

                // --- ESTAGIARIO ---
                // Front-end
                new Projeto("Componente de Galeria de Imagens", "Criar um componente reutilizável com JS.", 500, 350.00, 80, 30, 30, 7, NivelCargo.ESTAGIARIO, Map.of("JavaScript", NivelHabilidade.INTERMEDIARIO)),
                new Projeto("Ajuste de Responsividade", "Corrigir layout em um site para dispositivos móveis.", 400, 300.00, 60, 35, 25, 5, NivelCargo.ESTAGIARIO, Map.of("CSS", NivelHabilidade.INTERMEDIARIO)),
                new Projeto("Consumir API com Fetch", "Exibir dados de uma API externa em uma página.", 600, 400.00, 75, 28, 35, 6, NivelCargo.ESTAGIARIO, Map.of("JavaScript", NivelHabilidade.INTERMEDIARIO, "API REST", NivelHabilidade.INICIANTE)),
                // Back-end
                new Projeto("CRUD Básico de Usuários", "Criar um sistema de Cadastro/Leitura/Update/Delete.", 700, 450.00, 90, 40, 40, 8, NivelCargo.ESTAGIARIO, Map.of("Java", NivelHabilidade.INTERMEDIARIO, "SQL", NivelHabilidade.INICIANTE)),
                new Projeto("Sistema de Login Simples", "Implementar um sistema de autenticação.", 800, 500.00, 95, 45, 45, 10, NivelCargo.ESTAGIARIO, Map.of("Java", NivelHabilidade.INTERMEDIARIO)),
                new Projeto("Otimização de Query", "Refatorar uma query SQL lenta em um sistema.", 750, 480.00, 60, 50, 20, 4, NivelCargo.ESTAGIARIO, Map.of("SQL", NivelHabilidade.INTERMEDIARIO)),

                // --- JUNIOR ---
                // Front-end
                new Projeto("Dashboard com Gráficos", "Criar uma página de admin com gráficos usando uma lib.", 1500, 900.00, 100, 40, 80, 10, NivelCargo.JUNIOR, Map.of("JavaScript", NivelHabilidade.AVANCADO)),
                new Projeto("Configurar Rotas em SPA", "Estruturar a navegação de uma Single Page Application.", 1200, 800.00, 85, 38, 90, 8, NivelCargo.JUNIOR, Map.of("JavaScript", NivelHabilidade.INTERMEDIARIO)),
                new Projeto("Teste de Componentes com Jest", "Escrever testes unitários para componentes JS.", 1800, 1000.00, 110, 50, 100, 12, NivelCargo.JUNIOR, Map.of("JavaScript", NivelHabilidade.AVANCADO)),
                // Back-end
                new Projeto("Integração com Gateway de Pagamento", "Conectar a aplicação a um sistema de pagamentos.", 2500, 1500.00, 130, 60, 120, 15, NivelCargo.JUNIOR, Map.of("API REST", NivelHabilidade.INTERMEDIARIO)),
                new Projeto("Implementar Autenticação JWT", "Adicionar segurança baseada em token na API.", 2200, 1300.00, 120, 55, 110, 14, NivelCargo.JUNIOR, Map.of("Java", NivelHabilidade.AVANCADO, "API REST", NivelHabilidade.INTERMEDIARIO)),
                new Projeto("Refatoração de Módulo Legado", "Modernizar um módulo antigo do sistema.", 2800, 1600.00, 140, 65, 50, 18, NivelCargo.JUNIOR, Map.of("Java", NivelHabilidade.AVANCADO)),

                // --- PLENO ---
                // Front-end
                new Projeto("Otimizar Performance de Carregamento", "Aplicar lazy loading e code splitting.", 3500, 2500.00, 120, 60, 150, 20, NivelCargo.PLENO, Map.of("JavaScript", NivelHabilidade.AVANCADO)),
                new Projeto("Auditoria de Acessibilidade (A11Y)", "Corrigir problemas de acessibilidade em um site.", 3000, 2200.00, 80, 35, 180, 15, NivelCargo.PLENO, Map.of("HTML", NivelHabilidade.AVANCADO)),
                new Projeto("Criar uma Biblioteca de Componentes", "Desenvolver componentes reutilizáveis para a equipe.", 4500, 3500.00, 140, 65, 200, 25, NivelCargo.PLENO, Map.of("JavaScript", NivelHabilidade.AVANCADO)),
                // Back-end
                new Projeto("Desenvolver Microserviço de Notificações", "Criar um serviço para enviar e-mails/SMS.", 4000, 3000.00, 125, 65, 160, 22, NivelCargo.PLENO, Map.of("Java", NivelHabilidade.AVANCADO, "API REST", NivelHabilidade.AVANCADO)),
                new Projeto("Implementar Cache em API Crítica", "Adicionar Redis para otimizar performance.", 5000, 4000.00, 145, 80, 140, 18, NivelCargo.PLENO, Map.of("Java", NivelHabilidade.AVANCADO)),
                new Projeto("Migração de Banco de Dados", "Planejar e executar a migração para um novo SGBD.", 5500, 4500.00, 170, 95, 180, 30, NivelCargo.PLENO, Map.of("SQL", NivelHabilidade.AVANCADO)),

                // --- SENIOR ---
                // Front-end
                new Projeto("Desenhar Arquitetura Front-end", "Definir o blueprint de um novo sistema web.", 7000, 5000.00, 100, 60, 300, 25, NivelCargo.SENIOR, Map.of()),
                new Projeto("Liderar Migração de Framework", "Planejar e guiar a equipe na migração de jQuery para React.", 9000, 6000.00, 130, 85, 400, 35, NivelCargo.SENIOR, Map.of()),
                new Projeto("Prova de Conceito com WebAssembly", "Pesquisar e testar o uso de Wasm no produto.", 6000, 4000.00, 120, 40, 350, 18, NivelCargo.SENIOR, Map.of()),
                // Back-end
                new Projeto("Planejar Migração para Microserviços", "Definir a estratégia de quebra de um monólito.", 10000, 7000.00, 125, 80, 450, 40, NivelCargo.SENIOR, Map.of()),
                new Projeto("Liderar Resposta a Incidente Crítico", "Coordenar a equipe para resolver uma falha em produção.", 8000, 8000.00, 180, 120, 500, 7, NivelCargo.SENIOR, Map.of()),
                new Projeto("Estruturar Processo de Code Review", "Definir e implementar a cultura de revisão de código.", 5000, 0.00, 65, 10, 800, 15, NivelCargo.SENIOR, Map.of())
        );

        // ... (A inicialização da lista todosOsCursos permanece a mesma)
        this.todosOsCursos = List.of(
                // Cursos de Habilidades Básicas (Estágio)
                new Curso("Fundamentos de SO", 100.00, 3, "SO", NivelHabilidade.INICIANTE, 20, 6, 3, Map.of()),
                new Curso("Pacote Office Essencial", 80.00, 2, "Pacote Office", NivelHabilidade.INICIANTE, 15, 4, 2, Map.of()),
                new Curso("Lógica de Programação", 150.00, 5, "Lógica de Programação", NivelHabilidade.INICIANTE, 30, 10, 5, Map.of()),
                new Curso("Git e GitHub para Iniciantes", 120.00, 3, "Git e GitHub", NivelHabilidade.INICIANTE, 25, 6, 3, Map.of()),
                new Curso("Introdução ao Terminal", 90.00, 2, "Terminal", NivelHabilidade.INICIANTE, 20, 4, 2, Map.of()),
                new Curso("SQL Básico", 200.00, 5, "SQL", NivelHabilidade.INICIANTE, 40, 10, 5, Map.of()),

                // Cursos de Habilidades Intermediárias (Júnior)
                new Curso("HTML5 e CSS3", 200.00, 5, "HTML5 e CSS3", NivelHabilidade.INICIANTE, 40, 10, 5, Map.of()),
                new Curso("OLHA AQUI → HTML5 e CSS3", 200.00, 5, "HTML5 e CSS3", NivelHabilidade.AVANCADO, 80, 10, 5, Map.of()),
                new Curso("JavaScript Básico", 250.00, 7, "JavaScript", NivelHabilidade.INICIANTE, 50, 14, 7, Map.of()),
                new Curso("OLHA AQUI → JavaScript Intermediário", 250.00, 14, "JavaScript", NivelHabilidade.INTERMEDIARIO, 80, 28, 14, Map.of()),
                new Curso("Node.js Básico", 350.00, 7, "Node.js", NivelHabilidade.INICIANTE, 70, 14, 7, Map.of()),
                new Curso("SQL Intermediário", 400.00, 10, "SQL", NivelHabilidade.INTERMEDIARIO, 80, 20, 10, Map.of("SQL", NivelHabilidade.INICIANTE)),
                new Curso("Fundamentos de Flask", 320.00, 6, "Flask", NivelHabilidade.INICIANTE, 65, 12, 6, Map.of()),
                new Curso("Fundamentos de Criptografia", 450.00, 8, "Criptografia", NivelHabilidade.INICIANTE, 90, 16, 8, Map.of()),
                new Curso("IDS/IPS e Segurança de Redes", 500.00, 10, "IDS/IPS", NivelHabilidade.INICIANTE, 100, 20, 10, Map.of()),
                new Curso("Gestão de Vulnerabilidades", 480.00, 9, "Gestão de Vulnerabilidade", NivelHabilidade.INICIANTE, 95, 18, 9, Map.of()),

                // Cursos de Habilidades Avançadas (Pleno)
                new Curso("React.js", 500.00, 12, "React.js", NivelHabilidade.INICIANTE, 120, 24, 12, Map.of("JavaScript", NivelHabilidade.INICIANTE)),
                new Curso("Node.js Intermediário", 450.00, 10, "Node.js", NivelHabilidade.INTERMEDIARIO, 100, 20, 10, Map.of("Node.js", NivelHabilidade.INICIANTE)),
                new Curso("Apache Kafka", 600.00, 15, "Kafka", NivelHabilidade.INICIANTE, 150, 30, 15, Map.of("Java", NivelHabilidade.INTERMEDIARIO)),
                new Curso("AWS Cloud Practitioner", 700.00, 14, "AWS", NivelHabilidade.INICIANTE, 180, 28, 14, Map.of()),
                new Curso("Docker e Kubernetes", 750.00, 16, "Kubernetes", NivelHabilidade.INICIANTE, 200, 32, 16, Map.of()),
                new Curso("SQL Avançado", 550.00, 12, "SQL", NivelHabilidade.AVANCADO, 140, 24, 12, Map.of("SQL", NivelHabilidade.INTERMEDIARIO)),
                new Curso("Business Intelligence (BI)", 650.00, 13, "BI", NivelHabilidade.INICIANTE, 160, 26, 13, Map.of()),
                new Curso("Node.js Avançado", 550.00, 12, "Node.js", NivelHabilidade.AVANCADO, 140, 24, 12, Map.of("Node.js", NivelHabilidade.INTERMEDIARIO)),

                // Cursos de Habilidades Sênior
                new Curso("Dominando a LGPD", 800.00, 10, "LGPD", NivelHabilidade.INICIANTE, 220, 20, 10, Map.of()),
                new Curso("Arquitetura de Microsserviços", 900.00, 20, "Arquitetura de Microsserviços", NivelHabilidade.INICIANTE, 250, 40, 20, Map.of("Java", NivelHabilidade.AVANCADO)),
                new Curso("Mentoria em Code Review (Soft Skill)", 400.00, 5, "Mentoria em Code Review", NivelHabilidade.INICIANTE, 100, 10, 5, Map.of()),
                new Curso("AWS Solutions Architect Associate", 1200.00, 25, "AWS", NivelHabilidade.INTERMEDIARIO, 300, 50, 25, Map.of("AWS", NivelHabilidade.INICIANTE)),
                new Curso("Redis para Caching Avançado", 700.00, 9, "Redis", NivelHabilidade.INICIANTE, 180, 18, 9, Map.of()),
                new Curso("Mandarim Básico", 1500.00, 30, "Mandarim", NivelHabilidade.INICIANTE, 150, 60, 30, Map.of()),
                new Curso("Mandarim Avançado", 2000.00, 40, "Mandarim", NivelHabilidade.AVANCADO, 200, 80, 40, Map.of("Mandarim", NivelHabilidade.INICIANTE)),
                new Curso("Apache Spark e Big Data", 1800.00, 28, "Spark", NivelHabilidade.INICIANTE, 400, 56, 28, Map.of()),
                new Curso("Kubeflow e MLOps", 2200.00, 35, "Kubeflow", NivelHabilidade.INICIANTE, 500, 70, 35, Map.of("Kubernetes", NivelHabilidade.INICIANTE)),

                // Cursos de Soft Skills
                new Curso("Inglês Técnico I", 200.00, 10, "Inglês Técnico", NivelHabilidade.INICIANTE, 40, 20, 10, Map.of()),
                new Curso("Inglês Técnico II", 250.00, 12, "Inglês Técnico", NivelHabilidade.INTERMEDIARIO, 50, 24, 12, Map.of("Inglês Técnico", NivelHabilidade.INICIANTE)),
                new Curso("Comunicação Eficaz", 180.00, 4, "Comunicação", NivelHabilidade.INICIANTE, 35, 8, 4, Map.of()),
                new Curso("Trabalho em Equipe", 220.00, 3, "Trabalho em Equipe", NivelHabilidade.INICIANTE, 45, 6, 3, Map.of()),
                new Curso("Adaptabilidade Profissional", 210.00, 3, "Adaptabilidade", NivelHabilidade.INICIANTE, 40, 6, 3, Map.of()),
                new Curso("Flexibilidade e Gestão de Mudanças", 230.00, 4, "Flexibilidade", NivelHabilidade.INICIANTE, 50, 8, 4, Map.of()),
                new Curso("Colaboração e Feedback", 240.00, 3, "Colaboração", NivelHabilidade.INICIANTE, 55, 6, 3, Map.of()),
                new Curso("Resolução de Problemas Complexos", 300.00, 5, "Resolução de Problemas", NivelHabilidade.INICIANTE, 70, 10, 5, Map.of()),
                new Curso("Pensamento Crítico", 280.00, 4, "Pensamento Crítico", NivelHabilidade.INICIANTE, 65, 8, 4, Map.of()),
                new Curso("Inteligência Emocional no Trabalho", 320.00, 5, "Inteligência Emocional", NivelHabilidade.INICIANTE, 75, 10, 5, Map.of()),
                new Curso("Liderança para Iniciantes", 350.00, 6, "Liderança", NivelHabilidade.INICIANTE, 80, 12, 6, Map.of()),
                new Curso("Organização e Gestão do Tempo", 180.00, 2, "Organização", NivelHabilidade.INICIANTE, 30, 4, 2, Map.of()),
                new Curso("Proatividade e Iniciativa", 200.00, 3, "Proatividade", NivelHabilidade.INICIANTE, 40, 6, 3, Map.of()),
                new Curso("Ética no Trabalho", 150.00, 2, "Ética no Trabalho", NivelHabilidade.INICIANTE, 25, 4, 2, Map.of()),
                new Curso("Técnicas de Negociação", 380.00, 5, "Negociação", NivelHabilidade.INICIANTE, 85, 10, 5, Map.of()),
                new Curso("Visão de Negócio para Devs", 420.00, 6, "Visão de Negócio", NivelHabilidade.INICIANTE, 90, 12, 6, Map.of())
        );

        // ... (A inicialização da lista todasAsVagas permanece a mesma)
        this.todasAsVagas = List.of(
                // --- Vagas para ESTÁGIO (requer ESTAGIARIO_INICIO) ---
                // Cursos: SO(3d) + Pacote Office(2d) = 5d. Validade: 5 + 5 = 10 dias
                new Vaga("Consultores Consultorias", "Estágio em Suporte", 1200.00, 2000, 50, 15, 5, Map.of("SO", NivelHabilidade.INICIANTE, "Pacote Office", NivelHabilidade.INICIANTE), 60, NivelCargo.ESTAGIARIO_INICIO, NivelCargo.ESTAGIARIO),
                // Cursos: Lógica(5d) + Git(3d) + Inglês I(10d) = 18d. Validade: 18 + 5 = 23 dias
                new Vaga("High Tech Corp", "Estágio em Desenvolvimento", 1800.00, 5000, 150, 5, 15, Map.of("Lógica de Programação", NivelHabilidade.INICIANTE, "Git e GitHub", NivelHabilidade.INICIANTE, "Inglês Técnico", NivelHabilidade.INICIANTE), 55, NivelCargo.ESTAGIARIO_INICIO, NivelCargo.ESTAGIARIO),
                // Cursos: SQL(5d) + Terminal(2d) + Inglês I(10d) -> Inglês II(12d) + Comunicação(4d) = 33d. Validade: 33 + 5 = 38 dias
                new Vaga("IBN", "Estágio em Banco de Dados", 2300.00, 10000, 400, 8, 8, Map.of("SQL", NivelHabilidade.INICIANTE, "Terminal", NivelHabilidade.INICIANTE, "Comunicação", NivelHabilidade.INICIANTE, "Inglês Técnico", NivelHabilidade.INTERMEDIARIO), 50, NivelCargo.ESTAGIARIO_INICIO, NivelCargo.ESTAGIARIO),

                // --- Vagas para JÚNIOR (requer ESTAGIARIO) ---
                // Cursos: HTML/CSS(5d) + JS Básico(7d) = 12d. Validade: 12 + 5 = 17 dias
                new Vaga("CE&T", "Desenvolvedor Front-end Jr.", 3500.00, 2000, 50, 15, 5, Map.of("HTML5 e CSS3", NivelHabilidade.INICIANTE, "JavaScript", NivelHabilidade.INICIANTE), 60, NivelCargo.ESTAGIARIO, NivelCargo.JUNIOR),
                // Cursos: Node.js Básico(7d) + SQL Interm.(10d) + Flask(6d) + Trabalho Equipe(3d) = 26d. Validade: 26 + 5 = 31 dias
                new Vaga("Tivet", "Desenvolvedor Back-end Jr.", 5150.00, 5000, 150, 8, 18, Map.of("Node.js", NivelHabilidade.INICIANTE, "SQL", NivelHabilidade.INTERMEDIARIO, "Flask", NivelHabilidade.INICIANTE, "Trabalho em Equipe", NivelHabilidade.INICIANTE), 55, NivelCargo.ESTAGIARIO, NivelCargo.JUNIOR),
                // Cursos: IDS/IPS(10d) + Cripto(8d) + Gestão Vuln(9d) + Adaptabilidade(3d) = 30d. Validade: 30 + 5 = 35 dias
                new Vaga("Oráculo", "Analista de Segurança Jr.", 6800.00, 10000, 400, 10, 10, Map.of("IDS/IPS", NivelHabilidade.INICIANTE, "Criptografia", NivelHabilidade.INICIANTE, "Gestão de Vulnerabilidade", NivelHabilidade.INICIANTE, "Adaptabilidade", NivelHabilidade.INICIANTE), 50, NivelCargo.ESTAGIARIO, NivelCargo.JUNIOR),

                // --- Vagas para PLENO (requer JUNIOR) ---
                // Cursos: React(12d) + Node Interm(10d) + Kafka(15d) + Flexibilidade(4d) = 41d. Validade: 41 + 5 = 46 dias
                new Vaga("Mundo Informática", "Desenvolvedor Full Stack Pleno", 7500.00, 2000, 50, 18, 8, Map.of("React.js", NivelHabilidade.INICIANTE, "Node.js", NivelHabilidade.INTERMEDIARIO, "Kafka", NivelHabilidade.INICIANTE, "Flexibilidade", NivelHabilidade.INICIANTE), 70, NivelCargo.JUNIOR, NivelCargo.PLENO),
                // Cursos: AWS Básico(14d) + Docker/K8s(16d) + Colaboração(3d) + Resolução Prob(5d) = 38d. Validade: 38 + 5 = 43 dias
                new Vaga("J.U.L.I.O", "Engenheiro DevOps Pleno", 10000.00, 5000, 150, 10, 20, Map.of("AWS", NivelHabilidade.INICIANTE, "Kubernetes", NivelHabilidade.INICIANTE, "Colaboração", NivelHabilidade.INICIANTE, "Resolução de Problemas", NivelHabilidade.INICIANTE), 65, NivelCargo.JUNIOR, NivelCargo.PLENO),
                // Cursos: SQL Avançado(12d) + BI(13d) + Node Avançado(12d) + Pens. Crítico(4d) + Int. Emocional(5d) + Liderança(6d) = 52d. Validade: 52 + 5 = 57 dias
                new Vaga("Itai", "Engenheiro de Dados Pleno", 12500.00, 10000, 400, 12, 12, Map.of("SQL", NivelHabilidade.AVANCADO, "BI", NivelHabilidade.INICIANTE, "Node.js", NivelHabilidade.AVANCADO, "Pensamento Crítico", NivelHabilidade.INICIANTE, "Inteligência Emocional", NivelHabilidade.INICIANTE, "Liderança", NivelHabilidade.INICIANTE), 60, NivelCargo.JUNIOR, NivelCargo.PLENO),

                // --- Vagas para SÊNIOR (requer PLENO) ---
                // Cursos: LGPD(10d) + Arq. Microsserv(20d) + Mentoria(5d) + Organização(2d) = 37d. Validade: 37 + 5 = 42 dias
                new Vaga("Acento", "Arquiteto de Soluções Sênior", 13000.00, 2000, 50, 20, 10, Map.of("LGPD", NivelHabilidade.INICIANTE, "Arquitetura de Microsserviços", NivelHabilidade.INICIANTE, "Mentoria em Code Review", NivelHabilidade.INICIANTE, "Organização", NivelHabilidade.INICIANTE), 150, NivelCargo.PLENO, NivelCargo.SENIOR),
                // Cursos: AWS Interm(25d) + Mandarim Básico(30d) + Redis(9d) + Proatividade(3d) = 67d. Validade: 67 + 5 = 72 dias
                new Vaga("Gestão de Negócios", "Engenheiro SRE Sênior", 16500.00, 5000, 150, 15, 25, Map.of("AWS", NivelHabilidade.INTERMEDIARIO, "Mandarim", NivelHabilidade.INICIANTE, "Redis", NivelHabilidade.INICIANTE, "Proatividade", NivelHabilidade.INICIANTE), 140, NivelCargo.PLENO, NivelCargo.SENIOR),
                // Cursos: Kubeflow(35d) + Spark(28d) + Mandarim Avançado(40d) + Ética(2d) + Negociação(5d) + Visão de Neg.(6d) = 116d. Validade: 116 + 5 = 121 dias
                new Vaga("Gogle", "Cientista de Dados Sênior", 20000.00, 10000, 400, 15, 15, Map.of("Kubeflow", NivelHabilidade.INICIANTE, "Spark", NivelHabilidade.INICIANTE, "Mandarim", NivelHabilidade.AVANCADO, "Ética no Trabalho", NivelHabilidade.INICIANTE, "Negociação", NivelHabilidade.INICIANTE, "Visão de Negócio", NivelHabilidade.INICIANTE), 130, NivelCargo.PLENO, NivelCargo.SENIOR)
        );

        // ... (A inicialização da lista todasAsAcoesLazer permanece a mesma)
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

        this.todosOsEventos = List.of(
                new Evento("Campus Party", "Um dos maiores eventos de tecnologia, inovação, ciência e cultura digital do mundo.", "12 a 16 de Março de 2026", "São Paulo Expo – São Paulo/SP", "Estudantes, profissionais de TI, gamers e entusiastas.", 120, 80, 300.00, 70, 5, true),
                new Evento("Semana da Computação UNICAP", "Evento acadêmico com palestras, oficinas e debates sobre TI.", "10 a 14 de Junho de 2026", "Universidade Católica de Pernambuco - Recife/PE", "Estudantes e iniciantes.", 60, 40, 0.00, 30, 5, false),
                new Evento("REC'n'Play", "Festival nacional que integra tecnologia, inovação, cultura e economia criativa.", "20 a 23 de Outubro de 2026", "Bairro do Recife - Recife/PE", "Profissionais criativos, TI e startups.", 90, 100, 50.00, 50, 4, false),
                new Evento("Olinda Tech Week", "Semana de workshops e palestras sobre tendências tecnológicas.", "5 e 6 de Setembro de 2026", "Centro Cultural de Olinda – Olinda/PE", "Entusiastas e recém-chegados à área.", 70, 60, 40.00, 45, 2, false),
                new Evento("Python Nordeste", "Encontro regional da comunidade Python focado em palestras, minicursos e networking.", "18 e 19 de Julho de 2026", "Fortaleza/CE", "Programadores e cientistas de dados.", 100, 85, 90.00, 55, 2, false),
                new Evento("ExpoTI Recife", "Feira tecnológica com exposição de empresas, workshops e oportunidades de carreira.", "14 de Agosto de 2026", "Centro de Convenções de Pernambuco – Recife/PE", "Profissionais e estudantes de TI.", 80, 50, 20.00, 35, 1, false),
                new Evento("DevOps Brasil Summit", "Conferência voltada para práticas DevOps, automação e infraestrutura moderna.", "3 a 5 de Novembro de 2026", "São Paulo/SP", "Profissionais de infraestrutura e desenvolvimento.", 110, 95, 150.00, 60, 3, false),
                new Evento("Front-End Experience Day", "Dia inteiro de talks sobre UI/UX, frameworks modernos e carreira front-end.", "27 de Abril de 2026", "Porto Digital – Recife/PE", "Devs front-end e designers.", 65, 75, 30.00, 40, 1, false),
                new Evento("Cyber Security Expo", "Evento focado em segurança cibernética, com oficinas, palestras e simulações.", "21 e 22 de Agosto de 2026", "Curitiba/PR", "Analistas e profissionais de segurança.", 130, 120, 200.00, 85, 2, false),
                new Evento("Encontro de Inovação e Startups", "Conecta empreendedores e investidores, com pitches e painéis sobre inovação.", "9 de Dezembro de 2026", "Belo Horizonte/MG", "Profissionais de startups e inovação.", 95, 110, 60.00, 50, 1, false)
        );
    }

    public List<Vaga> getVagasDisponiveis(Jogador jogador, int diaAtual) {
        return todasAsVagas.stream()
                .filter(vaga -> vaga.getNivelRequerido() == jogador.getCargo())
                .filter(vaga -> diaAtual <= vaga.getDiasValidade())
                .collect(Collectors.toList());
    }

    public boolean jogadorTemRequisitosParaVaga(Jogador jogador, Vaga vaga) {
        for (Map.Entry<String, NivelHabilidade> requisito : vaga.getRequisitos().entrySet()) {
            NivelHabilidade nivelMinimoRequerido = requisito.getValue();
            NivelHabilidade nivelJogador = jogador.getNivelHabilidade(requisito.getKey());

            if (nivelJogador.getValor() < nivelMinimoRequerido.getValor()) {
                return false; // Se uma habilidade não for suficiente, já retorna falso
            }
        }
        return true; // Se passar por todos os requisitos, retorna verdadeiro
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

    public List<Evento> getEventosDisponiveis(Jogador jogador) {
        // No futuro, podemos adicionar um filtro de data aqui para exibir apenas eventos futuros.
        return todosOsEventos.stream()
                .filter(evento -> !jogador.jaConcluiuEvento(evento)) // Filtra os que ele já participou
                .filter(evento -> jogador.getDinheiro() >= evento.getCustoFinanceiro()) // Filtra se pode pagar
                .filter(evento -> jogador.getEnergia() > evento.getEnergiaConsumida()) // Filtra se tem energia suficiente
                .collect(Collectors.toList());
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