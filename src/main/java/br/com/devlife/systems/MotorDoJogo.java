package br.com.devlife.systems;

import br.com.devlife.core.Jogador;
import br.com.devlife.domain.AcaoLazer;
import br.com.devlife.domain.enums.AreaAtuacao;
import br.com.devlife.domain.enums.NivelCargo;
import br.com.devlife.ui.TerminalUI;
import br.com.devlife.domain.Projeto;
import java.util.List;

public class MotorDoJogo {
    private int diaAtual = 1;
    private Jogador jogador;
    private final TerminalUI terminal;
    private final GerenciadorDeAcoes gerenciador;

    public MotorDoJogo() {
        this.terminal = new TerminalUI();
        this.jogador = new Jogador("Dev Comum", AreaAtuacao.BACKEND, NivelCargo.ESTAGIARIO_INICIO);
        this.gerenciador = new GerenciadorDeAcoes();
    }

    public void iniciar() {
        String log = "Bem-vindo ao DevLife! Sua jornada como dev começa agora.";
        boolean jogoRodando = true;

        while (jogoRodando) {
            if (jogador.getEnergia() <= 0) {
                log = "Você ficou sem energia e desmaiou! O jogo acabou.";
                terminal.exibirDashboard(jogador, log);
                jogoRodando = false;
                continue;
            }

            terminal.exibirDashboard(jogador, log);
            int opcao = terminal.lerOpcao();

            switch (opcao) {
                case 1:
                    terminal.exibirMensagemComDelay("Verificando projetos compatíveis com suas habilidades...", 1500);
                    List<Projeto> projetosDisponiveis = gerenciador.getProjetosDisponiveis(jogador);
                    int escolhaProjeto = terminal.exibirSubMenuProjetos(projetosDisponiveis);

                    if (escolhaProjeto > 0 && escolhaProjeto <= projetosDisponiveis.size()) {
                        Projeto projetoEscolhido = projetosDisponiveis.get(escolhaProjeto - 1);

                        jogador.adicionarExperiencia(projetoEscolhido.getXpGanho());
                        jogador.adicionarDinheiro(projetoEscolhido.getDinheiroGanho());
                        jogador.alterarRecursoVital("energia", -projetoEscolhido.getEnergiaCusto());
                        jogador.alterarRecursoVital("sanidade", -projetoEscolhido.getSanidadeCusto());
                        jogador.setNetworking(jogador.getNetworking() + projetoEscolhido.getNetworkingGanho());

                        log = "Você completou o projeto: '" + projetoEscolhido.getNome() + "'.";
                        // Assumindo que um projeto leva 1 dia para ser concluído

                    } else if (escolhaProjeto == 0) {
                        log = "Você decidiu não pegar nenhum projeto por enquanto.";
                    } else {
                        log = "Opção de projeto inválida.";
                    }
                    break;
                case 2:
                    terminal.exibirMensagemComDelay("Funcionalidade de cursos ainda em desenvolvimento...", 1500);
                    log = "Você decidiu não estudar hoje.";
                    break;
                case 3:
                    terminal.exibirMensagemComDelay("Verificando o que você pode fazer para relaxar...", 1500);
                    List<AcaoLazer> acoesDisponiveis = gerenciador.getAcoesLazerDisponiveis(jogador);
                    int escolhaAcao = terminal.exibirSubMenuCuidarDeSi(acoesDisponiveis);

                    if (escolhaAcao > 0 && escolhaAcao <= acoesDisponiveis.size()) {
                        AcaoLazer acaoEscolhida = acoesDisponiveis.get(escolhaAcao - 1);

                        jogador.gastarDinheiro(acaoEscolhida.getCustoDinheiro());
                        jogador.alterarRecursoVital("energia", acaoEscolhida.getBonusEnergia());
                        jogador.alterarRecursoVital("saude", acaoEscolhida.getBonusSaude());
                        jogador.alterarRecursoVital("sanidade", acaoEscolhida.getBonusSanidade());

                        if (acaoEscolhida.getDuracaoEmDias() > 0) {
                            avancarDias(acaoEscolhida.getDuracaoEmDias());
                        }

                        log = "Você decidiu: " + acaoEscolhida.getNome();
                    } else if (escolhaAcao == 0) {
                        log = "Você decidiu focar no trabalho por enquanto.";
                    } else {
                        log = "Opção de lazer inválida.";
                    }
                    break;
                default:
                    log = "Opção inválida, tente novamente.";
                    break;
            }
        }
        terminal.exibirMensagemComDelay("Fim de jogo. Obrigado por jogar!", 3000);
    }

    private void avancarDias(int dias) {
        for (int i = 0; i < dias; i++) {
            this.diaAtual++;
            jogador.alterarRecursoVital("energia", 40);
        }
    }
}