package br.com.devlife.systems;

import br.com.devlife.core.Jogador;
import br.com.devlife.domain.AcaoLazer;
import br.com.devlife.domain.Projeto;
import br.com.devlife.domain.enums.AreaAtuacao;
import br.com.devlife.domain.enums.NivelCargo;
import br.com.devlife.domain.enums.TipoAtividade;
import br.com.devlife.ui.TerminalUI;
import java.util.List;

public class MotorDoJogo {
    private int diaAtual = 0; // MUDANÇA: O jogo começa no dia 0
    private Jogador jogador;
    private final TerminalUI terminal;
    private final GerenciadorDeAcoes gerenciador;

    public MotorDoJogo() {
        this.terminal = new TerminalUI();
        this.gerenciador = new GerenciadorDeAcoes();
    }

    public void iniciar() {
        // --- ETAPA 1: ONBOARDING DO JOGADOR ---
        terminal.exibirTelaBoasVindas();
        String nomeJogador = terminal.pedirNomeJogador();
        AreaAtuacao areaAtuacao = terminal.pedirAreaAtuacao();

        this.jogador = new Jogador(nomeJogador, areaAtuacao, NivelCargo.ESTAGIARIO_INICIO);

        // --- ETAPA 2: O JOGO COMEÇA ---
        String log = "Bem-vindo ao DevLife, " + nomeJogador + "! Sua jornada como dev começa agora.";
        boolean jogoRodando = true;

        while (jogoRodando) {
            // MUDANÇA: Lógica de falta de energia foi melhorada
            if (jogador.getEnergia() <= 0) {
                log = "Você ficou sem energia e desmaiou! Passando para o próximo dia para descansar...";
                terminal.exibirDashboard(jogador, log, diaAtual);
                terminal.esperarEnterParaContinuar();
                avancarDias(1); // Força o avanço de 1 dia para recuperar energia
                continue; // Pula para a próxima iteração do loop
            }

            terminal.exibirDashboard(jogador, log, diaAtual);
            int opcao = terminal.lerOpcao();

            // MUDANÇA: O switch foi completamente reescrito para o novo menu
            switch (opcao) {
                case 1: // Minhas Habilidades
                    terminal.exibirMenuHabilidades(jogador);
                    terminal.esperarEnterParaContinuar();
                    log = "Você analisou seu progresso e suas habilidades.";
                    break;

                case 2: // Estudos (WIP)
                    terminal.exibirMensagemComDelay("Verificando catálogo de cursos...", 1500);
                    List<Curso> cursosDisponiveis = gerenciador.getCursosDisponiveis(jogador);
                    int escolhaCurso = terminal.exibirSubMenuCursos(cursosDisponiveis);

                    if (escolhaCurso > 0 && escolhaCurso <= cursosDisponiveis.size()) {
                        Curso cursoEscolhido = cursosDisponiveis.get(escolhaCurso - 1);
                        
                        jogador.gastarDinheiro(cursoEscolhido.getCustoDinheiro());
                        // Supondo que o jogador sempre tem energia para estudar
                        jogador.setHabilidade(cursoEscolhido.getHabilidadeEnsinada(), cursoEscolhido.getNivelResultante());
                        jogador.adicionarExperiencia(cursoEscolhido.getXpGanho());
                        
                        log = "Curso '" + cursoEscolhido.getNome() + "' concluído com sucesso!";
                        avancarDias(cursoEscolhido.getDuracaoEmDias());
                        
                    } else {
                        log = "Nenhum curso selecionado.";
                    }
                    break;


                case 3: // Vagas de Trabalho (WIP)
                    terminal.exibirMensagemComDelay("Buscando oportunidades no mercado...", 1500);
                    List<Vaga> vagasDisponiveis = gerenciador.getVagasDisponiveis(jogador);
                    int escolhaVaga = terminal.exibirSubMenuVagas(vagasDisponiveis);

                    if (escolhaVaga > 0 && escolhaVaga <= vagasDisponiveis.size()) {
                        Vaga vagaEscolhida = vagasDisponiveis.get(escolhaVaga - 1);
                        
                        if (gerenciador.jogadorTemRequisitosParaVaga(jogador, vagaEscolhida)) {
                            log = "Parabéns! Você foi contratado como " + vagaEscolhida.getTituloVaga() + " na " + vagaEscolhida.getNomeEmpresa() + "!";
                            terminal.exibirMensagemComDelay(log, 2500);
                            
                            jogador.setCargo(vagaEscolhida.getNivelPromocao());
                            jogador.adicionarExperiencia(vagaEscolhida.getXpBonus());
                            jogador.setNetworking(jogador.getNetworking() + vagaEscolhida.getNetBonus());
                            // jogador.setSalario(vagaEscolhida.getSalario()); // Futura implementação
                            
                            if (jogador.getCargo() == NivelCargo.CEO) {
                                terminal.exibirMensagemComDelay("VOCÊ ATINGIU O TOPO! ZEROU O JOGO!", 5000);
                                jogoRodando = false;
                            }
                            
                        } else {
                            log = "Você não tem os requisitos para a vaga de " + vagaEscolhida.getTituloVaga() + ".";
                        }
                    } else {
                        log = "Nenhuma vaga selecionada.";
                    }
                    break;

                case 4: // Eventos (WIP)
                    terminal.exibirMensagemComDelay("Funcionalidade de Eventos em desenvolvimento...", 1500);
                    log = "Nenhum evento interessante no momento.";
                    break;

                case 5: // Projetos
                    terminal.exibirMensagemComDelay("Verificando projetos compatíveis com seu nível e habilidades...", 1500);
                    List<Projeto> projetosDisponiveis = gerenciador.getProjetosDisponiveis(jogador);
                    int escolhaProjeto = terminal.exibirSubMenuProjetos(projetosDisponiveis);

                    if (escolhaProjeto > 0 && escolhaProjeto <= projetosDisponiveis.size()) {
                        Projeto projetoEscolhido = projetosDisponiveis.get(escolhaProjeto - 1);

                        // Aplica todos os efeitos do projeto
                        jogador.adicionarExperiencia(projetoEscolhido.getXpGanho());
                        jogador.adicionarDinheiro(projetoEscolhido.getDinheiroGanho());
                        jogador.alterarRecursoVital("energia", -projetoEscolhido.getEnergiaCusto());
                        jogador.alterarRecursoVital("sanidade", -projetoEscolhido.getSanidadeCusto());
                        jogador.setNetworking(jogador.getNetworking() + projetoEscolhido.getNetworkingGanho());
                        jogador.completarProjeto(projetoEscolhido); // Marca o projeto como concluído

                        log = "Projeto '" + projetoEscolhido.getNome() + "' concluído com sucesso!";
                        avancarDias(projetoEscolhido.getDuracaoEmDias()); // Avança o tempo

                    } else if (escolhaProjeto == 0) {
                        log = "Você decidiu não pegar nenhum projeto por enquanto.";
                    } else {
                        log = "Opção de projeto inválida.";
                    }
                    break;

                case 6: // Lista de Atividades
                    TipoAtividade tipoEscolhido = terminal.exibirSubMenuEscolhaAtividade();
                    if (tipoEscolhido != null) { // Se for null, o usuário escolheu voltar
                        terminal.exibirMensagemComDelay("Verificando atividades disponíveis...", 1000);
                        List<AcaoLazer> acoesDisponiveis = gerenciador.getAcoesLazerDisponiveis(jogador, tipoEscolhido);
                        int escolhaAcao = terminal.exibirSubMenuAtividades(acoesDisponiveis);

                        if (escolhaAcao > 0 && escolhaAcao <= acoesDisponiveis.size()) {
                            AcaoLazer acaoEscolhida = acoesDisponiveis.get(escolhaAcao - 1);

                            jogador.gastarDinheiro(acaoEscolhida.getCustoDinheiro());
                            jogador.alterarRecursoVital("energia", acaoEscolhida.getBonusEnergia());
                            jogador.alterarRecursoVital("saude", acaoEscolhida.getBonusSaude());
                            jogador.alterarRecursoVital("sanidade", acaoEscolhida.getBonusSanidade());

                            log = "Você decidiu: " + acaoEscolhida.getNome() + ".";
                            if (acaoEscolhida.getDuracaoEmDias() > 0) {
                                avancarDias(acaoEscolhida.getDuracaoEmDias());
                            }
                        } else {
                            log = "Nenhuma atividade selecionada.";
                        }
                    } else {
                        log = "Você voltou ao menu principal.";
                    }
                    break;

                case 7: // Finalizar o dia
                    log = "Você finalizou o dia e foi descansar.";
                    avancarDias(1);
                    break;

                default:
                    log = "Opção inválida, tente novamente.";
                    break;
            }
        }
        terminal.exibirMensagemComDelay("Fim de jogo. Obrigado por jogar!", 3000);
    }

    /**
     * Avança o tempo no jogo, restaurando a energia do jogador para 100 a cada dia.
     * @param dias O número de dias a avançar.
     */
    private void avancarDias(int dias) {
        for (int i = 0; i < dias; i++) {
            this.diaAtual++;
            // MUDANÇA: A energia é restaurada para 100 no início de cada novo dia.
            jogador.setEnergia(100);

            // TODO: Adicionar lógica de desgaste diário de saúde/sanidade com base no cargo.
        }
    }
}