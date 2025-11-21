package br.com.devlife.ui;

import br.com.devlife.core.Jogador;
import br.com.devlife.domain.AcaoLazer;
import br.com.devlife.domain.Curso;
import br.com.devlife.domain.Evento;
import br.com.devlife.domain.Projeto;
import br.com.devlife.domain.Vaga;
import br.com.devlife.domain.enums.AreaAtuacao;
import br.com.devlife.domain.enums.NivelHabilidade;
import br.com.devlife.domain.enums.TipoAtividade;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TerminalUI {

    // Constantes ANSI para cores e estilos no terminal
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_YELLOW = "\033[33m";
    public static final String ANSI_CYAN = "\033[36m";
    public static final String ANSI_BOLD = "\033[1m";
    public static final String ANSI_ITALIC = "\033[3m";
    public static final String ANSI_REVERSED = "\033[7m";

    private final Scanner scanner;

    public TerminalUI() {
        this.scanner = new Scanner(System.in);
    }

    public void limparTela() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Se falhar, o jogo continua, mas a tela não será limpa.
        }
    }

    public void exibirDashboard(Jogador jogador, String log, int diaAtual) {
        limparTela();
        StringBuilder sb = new StringBuilder();

        sb.append(ANSI_GREEN).append("=====================[ ").append(ANSI_REVERSED).append("DevLife").append(ANSI_RESET)
                .append(ANSI_GREEN).append(" ]=====================").append(ANSI_RESET).append("\n\n");

        sb.append(String.format("%sNome:%s %-18s | %sDinheiro:%s R$ %-8.2f | %sDia:%s %d\n",
                ANSI_BOLD, ANSI_RESET, jogador.getNome(), ANSI_BOLD, ANSI_RESET, jogador.getDinheiro(), ANSI_BOLD, ANSI_RESET, diaAtual));
        sb.append("-----------------------------------------------------------\n");

        sb.append(formatarBarraDeStatus("Energia", jogador.getEnergia(), 100));
        sb.append(formatarBarraDeStatus("Sanidade", jogador.getSanidade(), 100));
        sb.append(formatarBarraDeStatus("Saúde", jogador.getSaude(), 100));
        sb.append("\n");

        sb.append(String.format("%sXp:%s %-26d | %sCargo:%s %s\n",
                ANSI_BOLD, ANSI_RESET, jogador.getExperiencia(), ANSI_BOLD, ANSI_RESET, formatarNomeCargo(jogador.getCargo().name())));

        sb.append(String.format("%sNetworking:%s %-7d |       %sSalário:%s R$ %-15.2f\n",
                ANSI_BOLD, ANSI_RESET, jogador.getNetworking(), ANSI_BOLD, ANSI_RESET, jogador.getSalario()));

        sb.append("-----------------------------------------------------------\n");

        sb.append(ANSI_BOLD).append("========================[ MENU ]=====================").append(ANSI_RESET).append("\n");
        sb.append(" 1. Minhas Habilidades\n");
        sb.append(" 2. Estudos\n");
        sb.append(" 3. Vagas de Trabalho\n");
        sb.append(" 4. Eventos\n");
        sb.append(" 5. Projetos\n");
        sb.append(" 6. Lista de Atividades\n");
        sb.append(" 7. Finalizar o dia\n");
        sb.append("-----------------------------------------------------------\n\n");

        sb.append(ANSI_BOLD).append("LOG:").append(ANSI_RESET).append(" ").append(ANSI_ITALIC).append(log).append(ANSI_RESET).append("\n");

        System.out.println(sb.toString());
    }

    public void exibirMensagemComDelay(String mensagem, int milissegundos) {
        limparTela();
        System.out.println("\n" + ANSI_ITALIC + mensagem + ANSI_RESET);
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int exibirSubMenuProjetos(List<Projeto> projetos) {
        limparTela();
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_BOLD).append("====================[ Projetos Disponíveis ]=====================").append(ANSI_RESET).append("\n\n");
        if (projetos.isEmpty()) {
            sb.append("  Nenhum projeto disponível para seu nível ou habilidades atuais.\n");
        } else {
            for (int i = 0; i < projetos.size(); i++) {
                Projeto p = projetos.get(i);
                sb.append(String.format(" %d. %s\n", (i + 1), p.getNome()));
                sb.append(String.format("    └─ %s\n", p.getDescricao()));
                sb.append(ANSI_CYAN);
                sb.append(String.format("       Duração: %d dias | XP: +%d | NET: +%d | Energia: %d | Sanidade: %d\n\n",
                        p.getDuracaoEmDias(), p.getXpGanho(), p.getNetworkingGanho(), -p.getEnergiaCusto(), -p.getSanidadeCusto()));
                sb.append(ANSI_RESET);
            }
        }
        sb.append("-----------------------------------------------------------\n");
        sb.append(" 0. Voltar ao menu principal\n\n");
        System.out.println(sb.toString());
        return lerOpcao();
    }

    public int exibirSubMenuCursos(List<Curso> cursos) {
        limparTela();
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_BOLD).append("======================[ Cursos Disponíveis ]======================").append(ANSI_RESET).append("\n\n");
        if (cursos.isEmpty()) {
            sb.append("  Nenhum novo curso disponível ou você não tem os pré-requisitos/dinheiro.\n");
        } else {
            for (int i = 0; i < cursos.size(); i++) {
                Curso c = cursos.get(i);
                sb.append(String.format(" %d. %s\n", (i + 1), c.getNome()));
                sb.append(ANSI_CYAN);
                sb.append(String.format("       Custo: R$ %.2f | Duração: %d dias | Habilidade: %s (%s) | XP: +%d\n\n",
                        c.getCustoDinheiro(), c.getDuracaoEmDias(), c.getHabilidadeEnsinada(), c.getNivelResultante().getNomeExibicao(), c.getXpGanho()));
                sb.append(ANSI_RESET);
            }
        }
        sb.append("-----------------------------------------------------------\n");
        sb.append(" 0. Voltar ao menu principal\n\n");
        System.out.println(sb.toString());
        return lerOpcao();
    }

    public int exibirSubMenuVagas(List<Vaga> vagas) {
        limparTela();
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_BOLD).append("======================[ Vagas de Emprego ]======================").append(ANSI_RESET).append("\n\n");
        if (vagas.isEmpty()) {
            sb.append("  Nenhuma vaga disponível para o seu nível de cargo atual.\n");
        } else {
            for (int i = 0; i < vagas.size(); i++) {
                Vaga v = vagas.get(i);
                sb.append(String.format(" %d. %s na %s\n", (i + 1), v.getTituloVaga(), v.getNomeEmpresa()));
                sb.append(ANSI_CYAN);
                sb.append(String.format("       Salário: R$ %.2f | Validade: %d dias | XP Bônus: +%d | Net Bônus: +%d\n",
                        v.getSalario(), v.getDiasValidade(), v.getXpBonus(), v.getNetBonus()));
                sb.append("       Requisitos: ").append(formatarRequisitos(v.getRequisitos())).append("\n\n");
                sb.append(ANSI_RESET);
            }
        }
        sb.append("-----------------------------------------------------------\n");
        sb.append(" 0. Voltar ao menu principal\n\n");
        System.out.println(sb.toString());
        return lerOpcao();
    }

    public int exibirSubMenuAtividades(List<AcaoLazer> acoes) {
        limparTela();
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_BOLD).append("====================[ Atividades Disponíveis ]=====================").append(ANSI_RESET).append("\n\n");
        if (acoes.isEmpty()) {
            sb.append("  Nenhuma atividade disponível no momento (verifique seu dinheiro).\n");
        } else {
            for (int i = 0; i < acoes.size(); i++) {
                AcaoLazer acao = acoes.get(i);
                sb.append(String.format(" %d. %s - %s\n", (i + 1), acao.getNome(), acao.getDescricao()));
                sb.append(ANSI_CYAN);
                sb.append(String.format("    Custo: R$ %.2f | Energia: %+d | Sanidade: %+d | Saúde: %+d\n\n",
                        acao.getCustoDinheiro(), acao.getBonusEnergia(), acao.getBonusSanidade(), acao.getBonusSaude()));
                sb.append(ANSI_RESET);
            }
        }
        sb.append("-----------------------------------------------------------\n");
        sb.append(" 0. Voltar ao menu principal\n\n");
        System.out.println(sb.toString());
        return lerOpcao();
    }

    public int exibirMenuEventos() {
        limparTela();
        System.out.println(ANSI_BOLD + "=====================[ Sistema de Eventos ]=====================" + ANSI_RESET);
        System.out.println("\nSelecione uma opção:\n");
        System.out.println("  1. Exibir informações dos eventos");
        System.out.println("  2. Realizar inscrição em um evento");
        System.out.println("----------------------------------------------------------");
        System.out.println("  0. Voltar ao menu principal\n");
        return lerOpcao();
    }

    public void exibirInformacoesEventos(List<Evento> eventos) {
        limparTela();
        System.out.println(ANSI_BOLD + "===============[ Eventos Disponíveis e Informações ]===============" + ANSI_RESET + "\n");
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento disponível para você no momento (verifique seus recursos ou se já participou de todos).");
        } else {
            for (Evento evento : eventos) {
                System.out.println(ANSI_BOLD + evento.getNome() + ANSI_RESET);
                System.out.println("  Descrição: " + evento.getDescricao());
                System.out.println("  Data: " + evento.getData() + " | Duração: " + evento.getDuracaoEmDias() + " dias");
                System.out.println("  Local: " + evento.getLocal());
                System.out.println("  Público: " + evento.getPublicoAlvo());
                System.out.println(ANSI_CYAN + "  Ganhos: XP: +" + evento.getXpGanho() + " | Networking: +" + evento.getNetworkingGanho() + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "  Custos: Dinheiro: R$ " + String.format("%.2f", evento.getCustoFinanceiro()) + " | Energia: -" + evento.getEnergiaConsumida() + ANSI_RESET);
                System.out.println("----------------------------------------------------------");
            }
        }
        esperarEnterParaContinuar();
    }

    public int exibirMenuInscricao(List<Evento> eventos) {
        limparTela();
        System.out.println(ANSI_BOLD + "======================[ Inscrição em Evento ]======================" + ANSI_RESET + "\n");
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento disponível para inscrição (verifique seus recursos ou se já participou de todos).");
        } else {
            System.out.println("Eventos disponíveis para inscrição:\n");
            for (int i = 0; i < eventos.size(); i++) {
                Evento e = eventos.get(i);
                System.out.printf("  %d. %s (Custo: R$ %.2f)\n", i + 1, e.getNome(), e.getCustoFinanceiro());
            }
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("  0. Voltar\n");
        return lerOpcao();
    }

    public int lerOpcao() {
        System.out.print("> Escolha uma opção: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Por favor, digite um número: ");
            scanner.next();
        }
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    private String formatarBarraDeStatus(String nome, int valorAtual, int valorMaximo) {
        double percentualDecimal = (double) valorAtual / valorMaximo;
        String cor;
        if (percentualDecimal > 0.6) cor = ANSI_GREEN;
        else if (percentualDecimal > 0.3) cor = ANSI_YELLOW;
        else cor = ANSI_RED;
        int tamanhoBarra = 10;
        int blocosPreenchidos = (int) (percentualDecimal * tamanhoBarra);
        String barra = "[" + "=".repeat(blocosPreenchidos) + " ".repeat(tamanhoBarra - blocosPreenchidos) + "]";
        return String.format("%s%-9s%s %s%s%s %3d / 100\n",
                ANSI_BOLD, nome + ":", ANSI_RESET, cor, barra, ANSI_RESET, valorAtual);
    }

    private String formatarHabilidades(Map<String, NivelHabilidade> habilidades) {
        if (habilidades.isEmpty()) {
            return "  Nenhuma habilidade adquirida ainda.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, NivelHabilidade> entry : habilidades.entrySet()) {
            String habilidade = entry.getKey();
            String nivel = entry.getValue().getNomeExibicao();
            sb.append(String.format("  - %-20s: %s\n", habilidade, nivel));
        }
        return sb.toString();
    }

    private String formatarRequisitos(Map<String, NivelHabilidade> requisitos) {
        if (requisitos.isEmpty()) return "Nenhum";
        return requisitos.entrySet().stream()
                .map(entry -> String.format("%s (%s)", entry.getKey(), entry.getValue().getNomeExibicao()))
                .collect(Collectors.joining(", "));
    }

    private String formatarNomeCargo(String nomeEnum) {
        String[] partes = nomeEnum.toLowerCase().split("_");
        for (int i = 0; i < partes.length; i++) {
            partes[i] = Character.toUpperCase(partes[i].charAt(0)) + partes[i].substring(1);
        }
        return String.join(" ", partes);
    }

    public void exibirTelaBoasVindas() {
        limparTela();
        System.out.println(ANSI_BOLD + ANSI_GREEN);
        System.out.println("=====================================================");
        System.out.println("                Bem-vindo ao DevLife!                ");
        System.out.println("=====================================================");
        System.out.println(ANSI_RESET);
        System.out.println("Prepare-se para iniciar sua jornada na carreira de TI,");
        System.out.println("desde os primeiros passos como estagiário até o topo!");
        System.out.println("\nVamos começar criando seu personagem.");
        esperarEnterParaContinuar();
    }

    public String pedirNomeJogador() {
        limparTela();
        System.out.println(ANSI_BOLD + "--- Criação de Personagem ---" + ANSI_RESET);
        System.out.print("Digite o nome do seu personagem: ");
        String nome = scanner.nextLine();
        while (nome.trim().isEmpty()) {
            System.out.print("O nome não pode ser vazio. Digite novamente: ");
            nome = scanner.nextLine();
        }
        return nome;
    }

    public AreaAtuacao pedirAreaAtuacao() {
        limparTela();
        System.out.println(ANSI_BOLD + "--- Criação de Personagem ---" + ANSI_RESET);
        System.out.println("Escolha sua área de atuação inicial:");
        System.out.println("  1. Front-end");
        System.out.println("  2. Back-end");

        while (true) {
            int escolha = lerOpcao();
            switch (escolha) {
                case 1:
                    return AreaAtuacao.FRONTEND;
                case 2:
                    return AreaAtuacao.BACKEND;
                default:
                    System.out.print("Opção inválida. Por favor, escolha 1 ou 2: ");
            }
        }
    }

    public void exibirMenuHabilidades(Jogador jogador) {
        limparTela();
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_BOLD).append("====================[ Minhas Habilidades ]=====================").append(ANSI_RESET).append("\n\n");
        sb.append(formatarHabilidades(jogador.getHabilidades()));
        sb.append("\n-----------------------------------------------------------\n");
        System.out.println(sb.toString());
    }

    public TipoAtividade exibirSubMenuEscolhaAtividade() {
        limparTela();
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_BOLD).append("===================[ Lista de Atividades ]===================").append(ANSI_RESET).append("\n\n");
        sb.append("Que área da sua vida você quer focar agora?\n\n");
        sb.append("  1. Melhorar Saúde\n");
        sb.append("  2. Melhorar Sanidade Mental\n");
        sb.append("-----------------------------------------------------------\n");
        sb.append("  0. Voltar\n\n");
        System.out.println(sb.toString());

        while (true) {
            int escolha = lerOpcao();
            switch (escolha) {
                case 1: return TipoAtividade.SAUDE;
                case 2: return TipoAtividade.SANIDADE;
                case 0: return null;
                default:
                    System.out.print("Opção inválida. Por favor, escolha 0, 1 ou 2: ");
            }
        }
    }

    public void esperarEnterParaContinuar() {
        System.out.println("\n" + ANSI_ITALIC + "Pressione Enter para continuar..." + ANSI_RESET);
        scanner.nextLine();
    }
}