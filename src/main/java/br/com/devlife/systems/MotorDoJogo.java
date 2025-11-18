package br.com.devlife.systems;

import br.com.devlife.core.Jogador;
import br.com.devlife.domain.enums.AreaAtuacao;
import br.com.devlife.domain.enums.NivelCargo;
import br.com.devlife.ui.TerminalUI;

public class MotorDoJogo {
    public void iniciar() {
        TerminalUI terminal = new TerminalUI();
        Jogador jogador = new Jogador("Dev Comum", AreaAtuacao.BACKEND, NivelCargo.ESTAGIARIO_PRECARIO);
        String log = "Bem-vindo ao DevLife! Sua jornada como dev começa agora.";
        boolean jogoRodando = true;

        while (jogoRodando) {
            terminal.exibirDashboard(jogador, log);
            int opcao = terminal.lerOpcao();

            switch (opcao) {
                case 1:
                    // 1. Exibe a mensagem de transição e espera
                    terminal.exibirMensagemComDelay("Acessando projetos disponíveis...", 1500);
                    // 2. Mostra o submenu de projetos e pega a escolha do usuário
                    int escolhaProjeto = terminal.exibirSubMenuProjetos();

                    // 3. Processa a escolha do submenu
                    if (escolhaProjeto == 1) { // Projeto Fácil
                        log = "Você fez uma landing page. +10 XP, -15 Energia.";
                        jogador.adicionarExperiencia(10);
                        jogador.alterarRecursoVital("energia", -15);
                    } else if (escolhaProjeto == 2) { // Projeto Médio
                        log = "Você desenvolveu uma API. +25 XP, -25 Energia.";
                        jogador.adicionarExperiencia(25);
                        jogador.alterarRecursoVital("energia", -25);
                    } else if (escolhaProjeto == 3) { // Projeto Difícil
                        log = "Você corrigiu um bug crítico. +50 XP, -40 Energia, -10 Sanidade.";
                        jogador.adicionarExperiencia(50);
                        jogador.alterarRecursoVital("energia", -40);
                        jogador.alterarRecursoVital("sanidade", -10);
                    } else {
                        // Se for 0 ou qualquer outra opção, apenas volta
                        log = "Você decidiu não pegar nenhum projeto por enquanto.";
                    }
                    break;
                case 2:
                    // Lógica para "Estudar (Cursos)" - Siga o mesmo padrão do case 1
                    terminal.exibirMensagemComDelay("Verificando cursos na plataforma...", 1500);
                    // terminal.exibirSubMenuCursos(); // Você criaria este método na TerminalUI
                    log = "Você fez um curso. +5 Sanidade, -10 Energia, -R$50.00.";
                    jogador.alterarRecursoVital("sanidade", 5);
                    jogador.alterarRecursoVital("energia", -10);
                    jogador.gastarDinheiro(50.00);
                    break;
                case 3:
                    // Lógica para "Cuidar de Si"
                    log = "Você tirou um tempo para relaxar. +15 Saúde, +10 Sanidade.";
                    jogador.alterarRecursoVital("saude", 15);
                    jogador.alterarRecursoVital("sanidade", 10);
                    break;
                // ... outros casos

                default:
                    log = "Opção inválida, tente novamente.";
                    break;
            }
        }
    }
}