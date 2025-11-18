package br.com.devlife.systems;

import br.com.devlife.core.Jogador;
import br.com.devlife.domain.AcaoLazer;
import br.com.devlife.domain.Curso;
import br.com.devlife.domain.Projeto;
import br.com.devlife.domain.enums.NivelHabilidade;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GerenciadorDeAcoes {
    private final List<Projeto> todosOsProjetos;
    private final List<Curso> todosOsCursos;
    private final List<AcaoLazer> todasAsAcoesLazer;

    public GerenciadorDeAcoes() {
        this.todosOsProjetos = List.of(
                new Projeto("Landing Page Simples", "Criar uma página estática. (Fácil)", 10, 150.00, 15, 0, 1, Map.of("HTML", NivelHabilidade.INICIANTE, "CSS", NivelHabilidade.INICIANTE)),
                new Projeto("API para Blog", "Desenvolver uma API REST simples. (Fácil)", 15, 200.00, 20, 2, 2, Map.of("Java", NivelHabilidade.INICIANTE, "API REST", NivelHabilidade.INICIANTE)),
                new Projeto("Website Interativo", "Desenvolver um website com JavaScript. (Médio)", 25, 450.00, 25, 5, 3, Map.of("JavaScript", NivelHabilidade.INTERMEDIARIO)),
                new Projeto("API para E-commerce", "Desenvolver os endpoints de uma loja virtual. (Médio)", 30, 600.00, 30, 8, 4, Map.of("Java", NivelHabilidade.INTERMEDIARIO, "SQL", NivelHabilidade.INICIANTE)),
                new Projeto("Bug em Sistema Legado", "Um bug crítico em um sistema antigo. (Difícil)", 70, 1200.00, 50, 15, 3, Map.of("Java", NivelHabilidade.AVANCADO, "SQL", NivelHabilidade.INTERMEDIARIO)),
                new Projeto("App de Banco Digital", "Desenvolver features essenciais para um app bancário. (Expert)", 150, 2500.00, 60, 25, 15, Map.of("Java", NivelHabilidade.AVANCADO, "API REST", NivelHabilidade.AVANCADO))
        );

        this.todosOsCursos = List.of();

        // Popula a lista de ações de lazer
        this.todasAsAcoesLazer = List.of(
                new AcaoLazer("Tomar um café expresso", "Um boost rápido de energia.", 5.00, 15, 0, -2, 0),
                new AcaoLazer("Tirar uma soneca (30 min)", "Recupera um pouco de energia e alivia a mente.", 0.00, 25, 0, 5, 0),
                new AcaoLazer("Ir ao Cinema", "Um bom filme para relaxar.", 30.00, 5, 0, 15, 0),
                new AcaoLazer("Sair com amigos", "Socializar para melhorar a sanidade.", 50.00, 10, 5, 20, 0)
        );
    }

    public List<Projeto> getProjetosDisponiveis(Jogador jogador) {
        return todosOsProjetos.stream()
                .filter(projeto -> jogadorTemRequisitosParaProjeto(jogador, projeto))
                .collect(Collectors.toList());
    }

    public List<Curso> getCursosDisponiveis(Jogador jogador) {
        return List.copyOf(todosOsCursos);
    }

    public List<AcaoLazer> getAcoesLazerDisponiveis(Jogador jogador) {
        // Filtra ações que o jogador pode pagar
        return todasAsAcoesLazer.stream()
                .filter(acao -> jogador.getDinheiro() >= acao.getCustoDinheiro())
                .collect(Collectors.toList());
    }

    private boolean jogadorTemRequisitosParaProjeto(Jogador jogador, Projeto projeto) {
        if (jogador.getEnergia() < projeto.getEnergiaCusto()) {
            return false;
        }

        for (Map.Entry<String, NivelHabilidade> requisito : projeto.getRequisitos().entrySet()) {
            String habilidadeRequerida = requisito.getKey();
            NivelHabilidade nivelMinimoRequerido = requisito.getValue();
            NivelHabilidade nivelJogador = jogador.getNivelHabilidade(habilidadeRequerida);

            if (nivelJogador.getValor() < nivelMinimoRequerido.getValor()) {
                return false;
            }
        }
        return true;
    }
}