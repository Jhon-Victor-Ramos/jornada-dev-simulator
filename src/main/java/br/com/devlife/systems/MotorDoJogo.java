package br.com.devlife.systems;

import br.com.devlife.core.Jogador;
import br.com.devlife.domain.*;
import br.com.devlife.domain.enums.AreaAtuacao;
import br.com.devlife.domain.enums.NivelCargo;
import br.com.devlife.domain.enums.TipoAtividade;
import br.com.devlife.ui.TerminalUI;
import java.util.List;

public class MotorDoJogo {
    private int diaAtual = 0;
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
            if (jogador.getEnergia() <= 0) {
                log = "Você ficou sem energia e desmaiou! Passando para o próximo dia para descansar...";
                terminal.exibirDashboard(jogador, log, diaAtual);
                terminal.esperarEnterParaContinuar();
                avancarDias(1);
                continue;
            }

            terminal.exibirDashboard(jogador, log, diaAtual);
            int opcao = terminal.lerOpcao();

            switch (opcao) {
                case 1: // Minhas Habilidades
                    terminal.exibirMenuHabilidades(jogador);
                    terminal.esperarEnterParaContinuar();
                    log = "Você analisou seu progresso e suas habilidades.";
                    break;

                case 2: // Estudos ( << ALTERADO PARA A LÓGICA DO BÔNUS >> )
                    terminal.exibirMensagemComDelay("Verificando catálogo de cursos...", 1500);
                    List<Curso> cursosDisponiveis = gerenciador.getCursosDisponiveis(jogador);
                    int escolhaCurso = terminal.exibirSubMenuCursos(cursosDisponiveis);

                    if (escolhaCurso > 0 && escolhaCurso <= cursosDisponiveis.size()) {
                        Curso cursoEscolhido = cursosDisponiveis.get(escolhaCurso - 1);

                        // --- LÓGICA DE PAGAMENTO COM BÔNUS ---
                        double custoFinal = cursoEscolhido.getCustoDinheiro();
                        String logDesconto = "";

                        // Verifica se o jogador tem o bônus ATIVO
                        if (jogador.temDescontoCursos()) {
                            custoFinal *= 0.5; // Aplica o desconto de 50%
                            logDesconto = " (Desconto de 50% aplicado!)";
                            jogador.setTemDescontoCursos(false); // Desativa o bônus após o uso
                        }

                        // Verifica se o jogador pode pagar o custo final
                        if (jogador.temDinheiroSuficiente(custoFinal)) {
                            jogador.gastarDinheiro(custoFinal);

                            int custoTotalEnergia = cursoEscolhido.getEnergiaCustoPorDia() * cursoEscolhido.getDuracaoEmDias();
                            int custoTotalSanidade = cursoEscolhido.getSanidadeCustoPorDia() * cursoEscolhido.getDuracaoEmDias();
                            jogador.alterarRecursoVital("energia", -custoTotalEnergia);
                            jogador.alterarRecursoVital("sanidade", -custoTotalSanidade);

                            jogador.setHabilidade(cursoEscolhido.getHabilidadeEnsinada(), cursoEscolhido.getNivelResultante());
                            jogador.adicionarExperiencia(cursoEscolhido.getXpGanho());

                            log = "Curso '" + cursoEscolhido.getNome() + "' concluído com sucesso!" + logDesconto;
                            avancarDias(cursoEscolhido.getDuracaoEmDias());
                        } else {
                            log = "Você não tem dinheiro suficiente para o curso '" + cursoEscolhido.getNome() + "'.";
                        }

                    } else {
                        log = "Nenhum curso selecionado.";
                    }
                    break;

                case 3: // Vagas de Trabalho
                    terminal.exibirMensagemComDelay("Buscando oportunidades no mercado...", 1500);
                    List<Vaga> vagasDisponiveis = gerenciador.getVagasDisponiveis(jogador, this.diaAtual);
                    int escolhaVaga = terminal.exibirSubMenuVagas(vagasDisponiveis);

                    if (escolhaVaga > 0 && escolhaVaga <= vagasDisponiveis.size()) {
                        Vaga vagaEscolhida = vagasDisponiveis.get(escolhaVaga - 1);

                        if (gerenciador.jogadorTemRequisitosParaVaga(jogador, vagaEscolhida)) {
                            log = "Parabéns! Você foi contratado como " + vagaEscolhida.getTituloVaga() + " na " + vagaEscolhida.getNomeEmpresa() + "!";
                            terminal.exibirMensagemComDelay(log, 2500);

                            jogador.setCargo(vagaEscolhida.getNivelPromocao());
                            jogador.setVagaAtual(vagaEscolhida);
                            jogador.adicionarExperiencia(vagaEscolhida.getXpBonus());
                            jogador.setNetworking(jogador.getNetworking() + vagaEscolhida.getNetBonus());
                            jogador.setSalario(vagaEscolhida.getSalario());

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

                case 4: // Eventos
                    int escolhaMenuEventos = terminal.exibirMenuEventos();

                    switch (escolhaMenuEventos) {
                        case 1: // "Exibir informações dos eventos"
                            List<Evento> eventosParaInfo = gerenciador.getEventosDisponiveis(jogador);
                            terminal.exibirInformacoesEventos(eventosParaInfo);
                            log = "Você consultou os eventos disponíveis.";
                            break;

                        case 2: // "Realizar inscrição em um evento"
                            terminal.exibirMensagemComDelay("Verificando eventos compatíveis com seus recursos...", 1000);
                            List<Evento> eventosParaInscricao = gerenciador.getEventosDisponiveis(jogador);
                            int escolhaEvento = terminal.exibirMenuInscricao(eventosParaInscricao);

                            if (escolhaEvento > 0 && escolhaEvento <= eventosParaInscricao.size()) {
                                Evento eventoEscolhido = eventosParaInscricao.get(escolhaEvento - 1);

                                jogador.gastarDinheiro(eventoEscolhido.getCustoFinanceiro());
                                jogador.alterarRecursoVital("energia", -eventoEscolhido.getEnergiaConsumida());
                                jogador.adicionarExperiencia(eventoEscolhido.getXpGanho());
                                jogador.setNetworking(jogador.getNetworking() + eventoEscolhido.getNetworkingGanho());
                                jogador.concluirEvento(eventoEscolhido);

                                avancarDias(eventoEscolhido.getDuracaoEmDias());

                                log = "Inscrição no evento '" + eventoEscolhido.getNome() + "' realizada com sucesso!";

                                // Lógica do Easter Egg: Ativa o bônus no jogador.
                                if (eventoEscolhido.possuiBonusEspecial()) {
                                    jogador.setTemDescontoCursos(true); // ATIVA O BÔNUS!
                                    log += " Parabéns! Você ganhou um benefício: 50% de desconto na sua próxima compra de curso!";
                                }

                            } else if (escolhaEvento == 0) {
                                log = "Você decidiu não se inscrever em nenhum evento agora.";
                            } else {
                                log = "Opção de evento inválida.";
                            }
                            break;

                        case 0: // Voltar
                            log = "Você voltou ao menu principal.";
                            break;

                        default:
                            log = "Opção inválida no menu de eventos.";
                            break;
                    }
                    break;

                case 5: // Projetos
                    terminal.exibirMensagemComDelay("Verificando projetos compatíveis com seu nível e habilidades...", 1500);
                    List<Projeto> projetosDisponiveis = gerenciador.getProjetosDisponiveis(jogador);
                    int escolhaProjeto = terminal.exibirSubMenuProjetos(projetosDisponiveis);

                    if (escolhaProjeto > 0 && escolhaProjeto <= projetosDisponiveis.size()) {
                        Projeto projetoEscolhido = projetosDisponiveis.get(escolhaProjeto - 1);

                        jogador.adicionarExperiencia(projetoEscolhido.getXpGanho());
                        jogador.adicionarDinheiro(projetoEscolhido.getDinheiroGanho());
                        jogador.alterarRecursoVital("energia", -projetoEscolhido.getEnergiaCusto());
                        jogador.alterarRecursoVital("sanidade", -projetoEscolhido.getSanidadeCusto());
                        jogador.setNetworking(jogador.getNetworking() + projetoEscolhido.getNetworkingGanho());
                        jogador.completarProjeto(projetoEscolhido);

                        log = "Projeto '" + projetoEscolhido.getNome() + "' concluído com sucesso!";
                        avancarDias(projetoEscolhido.getDuracaoEmDias());

                    } else if (escolhaProjeto == 0) {
                        log = "Você decidiu não pegar nenhum projeto por enquanto.";
                    } else {
                        log = "Opção de projeto inválida.";
                    }
                    break;

                case 6: // Lista de Atividades
                    TipoAtividade tipoEscolhido = terminal.exibirSubMenuEscolhaAtividade();
                    if (tipoEscolhido != null) {
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

    private void avancarDias(int dias) {
        String logPagamento = "";
        for (int i = 0; i < dias; i++) {
            this.diaAtual++;
            jogador.alterarRecursoVital("energia", 20);

            if (this.diaAtual % 30 == 0 && jogador.getSalario() > 0) {
                jogador.adicionarDinheiro(jogador.getSalario());
                logPagamento = String.format(" | VOCÊ RECEBEU SEU SALÁRIO DE R$ %.2f!", jogador.getSalario());
            }

            Vaga vagaAtual = jogador.getVagaAtual();
            if (vagaAtual != null) {
                int penalidadeSanidade = vagaAtual.getPenalidadeDiariaSanidade();
                int penalidadeEnergia = vagaAtual.getPenalidadeDiariaEnergia();

                if (penalidadeSanidade > 0) {
                    jogador.alterarRecursoVital("sanidade", -penalidadeSanidade);
                }
                if (penalidadeEnergia > 0) {
                    jogador.alterarRecursoVital("energia", -penalidadeEnergia);
                }
            }
            if (!logPagamento.isEmpty()) {
                terminal.exibirDashboard(jogador, logPagamento.substring(3), diaAtual);
                terminal.esperarEnterParaContinuar();
            }
        }
    }
}