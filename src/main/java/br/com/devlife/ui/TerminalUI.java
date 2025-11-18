package br.com.devlife.ui;

import br.com.devlife.core.Jogador;
import br.com.devlife.domain.AcaoLazer;
import br.com.devlife.domain.Projeto;
import br.com.devlife.domain.enums.NivelHabilidade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TerminalUI {

    // Constantes ANSI
    public static final String ANSI_RESET = "\033[0m";
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\033[32m";
    public static final String ANSI_YELLOW = "\033[33m";
    public static final String ANSI_CYAN = "\033[36m";
    public static final String ANSI_BOLD = "\033[1m";
    public static final String ANSI_ITALIC = "\033[3m";
    public static final String ANSI_UNDERLINE = "\033[4m";
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
            // Se falhar, o jogo continua, mas a tela não será limpa. Make the L!
        }
    }

    /**
     * Exibe o dashboard completo do jogo.
     */
    public void exibirDashboard(Jogador jogador, String log) {
        limparTela();
        StringBuilder sb = new StringBuilder();

        sb.append(ANSI_GREEN).append("=====================[ ").append(ANSI_REVERSED).append("DevLife").append(ANSI_RESET)
                .append(ANSI_GREEN).append(" ]=====================").append(ANSI_RESET).append("\n\n");

        sb.append(String.format("%sNome:%s %-23s | %sDinheiro:%s R$ %.2f\n",
                ANSI_BOLD, ANSI_RESET, jogador.getNome(), ANSI_BOLD, ANSI_RESET, jogador.getDinheiro()));
        sb.append("-----------------------------------------------------\n");

        sb.append(formatarBarraDeStatus("Energia", jogador.getEnergia(), 100));
        sb.append(formatarBarraDeStatus("Sanidade", jogador.getSanidade(), 100));
        sb.append(formatarBarraDeStatus("Saúde", jogador.getSaude(), 100));
        sb.append("\n");

        sb.append(String.format("%sXp:%s %-26d | %sCargo:%s %s\n",
                ANSI_BOLD, ANSI_RESET, jogador.getExperiencia(), ANSI_BOLD, ANSI_RESET, formatarNomeCargo(jogador.getCargo().name())));
        sb.append(String.format("%sNetworking:%s %d\n", ANSI_BOLD, ANSI_RESET, jogador.getNetworking()));
        sb.append("\n");

        sb.append("-----------------------------------------------------\n");


        sb.append(ANSI_BOLD).append("========================[ MENU ]=====================").append(ANSI_RESET).append("\n");
        sb.append(" 1. Trabalhar em Projetos     | 4. Procurar Vagas\n");
        sb.append(" 2. Estudar (Cursos)          | 5. Participar de Eventos\n");
        sb.append(" 3. Cuidar de Si              | 6. Ver Filmes\n");
        sb.append("-----------------------------------------------------\n");
        sb.append("\n");
        sb.append(ANSI_BOLD).append(ANSI_GREEN).append("==================[ Top 5 Habilidades ]===============").append(ANSI_RESET).append("\n");
        sb.append(formatarHabilidades(jogador.getHabilidades()));
        sb.append(ANSI_BOLD).append("LOG:").append(ANSI_RESET).append(" ").append(ANSI_ITALIC).append(log).append(ANSI_RESET).append("\n");

        System.out.println(sb.toString());
    }

    /**
     * Limpa a tela, exibe uma mensagem de transição e aguarda.
     */
    public void exibirMensagemComDelay(String mensagem, int milissegundos) {
        limparTela();
        System.out.println("\n" + ANSI_ITALIC + mensagem + ANSI_RESET);
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Exemplo de um submenu para a ação "Trabalhar em Projetos".
     */
    public int exibirSubMenuProjetos(List<Projeto> projetos) {
        limparTela();
        StringBuilder sb = new StringBuilder();

        sb.append(ANSI_BOLD).append("====================[ Projetos Disponíveis ]=====================").append(ANSI_RESET).append("\n\n");

        if (projetos.isEmpty()) {
            sb.append("  Nenhum projeto disponível no momento. Talvez você não tenha energia ou as habilidades necessárias.\n");
        } else {
            for (int i = 0; i < projetos.size(); i++) {
                Projeto projeto = projetos.get(i);
                // Formata a linha do menu com o número da opção, nome e descrição
                sb.append(String.format(" %d. %s - %s\n", (i + 1), projeto.getNome(), projeto.getDescricao()));
            }
        }

        sb.append("-----------------------------------------------------------\n");
        sb.append(" 0. Voltar ao menu principal\n\n");

        System.out.println(sb.toString());
        return lerOpcao();
    }

    public int exibirSubMenuCuidarDeSi(List<AcaoLazer> acoes) {
        limparTela();
        StringBuilder sb = new StringBuilder();

        sb.append(ANSI_BOLD).append("====================[ Cuidar de Si ]=====================").append(ANSI_RESET).append("\n\n");

        if (acoes.isEmpty()) {
            sb.append("  Nenhuma ação de lazer disponível no momento (verifique seu dinheiro).\n");
        } else {
            for (int i = 0; i < acoes.size(); i++) {
                AcaoLazer acao = acoes.get(i);
                sb.append(String.format(" %d. %s - %s\n", (i + 1), acao.getNome(), acao.getDescricao()));
                sb.append(String.format("    Custo: R$ %.2f | Energia: %+d | Sanidade: %+d | Saúde: %+d\n\n",
                        acao.getCustoDinheiro(), acao.getBonusEnergia(), acao.getBonusSanidade(), acao.getBonusSaude()));
            }
        }

        sb.append("-----------------------------------------------------------\n");
        sb.append(" 0. Voltar ao menu principal\n\n");

        System.out.println(sb.toString());
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
        int percentualInteiro = (int) (percentualDecimal * 100);
        return String.format("%s%-9s%s %s%s%s %3d%%\n",
                ANSI_BOLD, nome + ":", ANSI_RESET, cor, barra, ANSI_RESET, percentualInteiro);
    }

    private String formatarHabilidades(Map<String, NivelHabilidade> habilidades) { // DEPOIS
        if (habilidades.isEmpty()) {
            return "  Nenhuma habilidade adquirida ainda.\n";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, NivelHabilidade> entry : habilidades.entrySet()) { // DEPOIS
            String habilidade = entry.getKey();
            String nivel = entry.getValue().getNomeExibicao();
            sb.append(String.format("  - %-15s: %s\n", habilidade, nivel));
        }
        return sb.toString();
    }

    private String formatarNomeCargo(String nomeEnum) {
        String[] partes = nomeEnum.toLowerCase().split("_");
        for (int i = 0; i < partes.length; i++) {
            partes[i] = Character.toUpperCase(partes[i].charAt(0)) + partes[i].substring(1);
        }
        return String.join(" ", partes);
    }
}