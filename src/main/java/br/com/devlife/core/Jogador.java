package br.com.devlife.core;

import br.com.devlife.domain.Evento;
import br.com.devlife.domain.Projeto;
import br.com.devlife.domain.enums.AreaAtuacao;
import br.com.devlife.domain.enums.NivelCargo;
import br.com.devlife.domain.enums.NivelHabilidade;
import br.com.devlife.domain.Vaga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Jogador {
    private final String nome;
    private final AreaAtuacao areaAtuacao;
    private int saude;
    private int sanidade;
    private int energia;
    private double salario;
    private double dinheiro;
    private int experiencia;
    private int networking;
    private NivelCargo cargo;
    private final Map<String, NivelHabilidade> habilidades;
    private final List<Projeto> projetosConcluidos;
    private final List<Evento> eventosConcluidos;
    private Vaga vagaAtual;
    private boolean temDescontoCursos = false; // << NOVA ALTERAÇÃO 1

    public Jogador(String nome, AreaAtuacao areaAtuacao, NivelCargo cargoInicial) {
        this.nome = nome;
        this.areaAtuacao = areaAtuacao;
        this.cargo = cargoInicial;
        this.saude = 100;
        this.sanidade = 100;
        this.energia = 100;
        this.dinheiro = 500.00;
        this.salario = 0.00;
        this.experiencia = 0;
        this.networking = 10;
        this.habilidades = new HashMap<>();
        this.projetosConcluidos = new ArrayList<>();
        this.eventosConcluidos = new ArrayList<>();
        this.vagaAtual = null;
        inicializarHabilidadesPorArea();
    }

    private void inicializarHabilidadesPorArea() {
        switch (this.areaAtuacao) {
            case FRONTEND:
                this.habilidades.put("HTML", NivelHabilidade.INICIANTE);
                this.habilidades.put("CSS", NivelHabilidade.INICIANTE);
                this.habilidades.put("JavaScript", NivelHabilidade.INICIANTE);
                break;
            case BACKEND:
                this.habilidades.put("Java", NivelHabilidade.INICIANTE);
                this.habilidades.put("SQL", NivelHabilidade.INICIANTE);
                this.habilidades.put("API REST", NivelHabilidade.INICIANTE);
                break;
        }
    }

    public void aplicarPenalidadesTurno() {
        // Esta lógica pode ser movida para o MotorDoJogo no futuro
        if (this.sanidade < 30) {
            System.out.println(this.nome + " está se sentindo esgotado mentalmente. A energia caiu mais rápido!");
            alterarRecursoVital("energia", -15);
        }
        if (this.saude < 30) {
            System.out.println(this.nome + " está se sentindo mal. A energia caiu mais rápido!");
            alterarRecursoVital("energia", -10);
        }
        alterarRecursoVital("energia", -5);
    }

    public void alterarRecursoVital(String recurso, int valor) {
        int valorAtual;
        switch (recurso.toLowerCase()) {
            case "saude":
                valorAtual = this.saude;
                this.saude = Math.max(0, Math.min(100, valorAtual + valor));
                break;
            case "sanidade":
                valorAtual = this.sanidade;
                this.sanidade = Math.max(0, Math.min(100, valorAtual + valor));
                break;
            case "energia":
                valorAtual = this.energia;
                this.energia = Math.max(0, Math.min(100, valorAtual + valor));
                break;
            default:
                System.out.println("Aviso: Recurso vital '" + recurso + "' desconhecido.");
        }
    }

    public void aprenderOuMelhorarHabilidade(String habilidade) {
        NivelHabilidade nivelAtual = this.habilidades.getOrDefault(habilidade, NivelHabilidade.NENHUM);
        NivelHabilidade novoNivel;

        switch (nivelAtual) {
            case NENHUM:
                novoNivel = NivelHabilidade.INICIANTE;
                break;
            case INICIANTE:
                novoNivel = NivelHabilidade.INTERMEDIARIO;
                break;
            case INTERMEDIARIO:
                novoNivel = NivelHabilidade.AVANCADO;
                break;
            case AVANCADO:
                System.out.println("Habilidade " + habilidade + " já está no nível máximo!");
                return;
            default:
                novoNivel = NivelHabilidade.INICIANTE;
                break;
        }
        this.habilidades.put(habilidade, novoNivel);
        System.out.println("Habilidade " + habilidade + " melhorada para o nível " + novoNivel.getNomeExibicao() + "!");
    }

    public NivelHabilidade getNivelHabilidade(String habilidade) {
        return this.habilidades.getOrDefault(habilidade, NivelHabilidade.NENHUM);
    }

    public boolean temDinheiroSuficiente(double valor) {
        return this.dinheiro >= valor;
    }

    public void adicionarDinheiro(double quantia) {
        if (quantia > 0) {
            this.dinheiro += quantia;
        }
    }

    public void gastarDinheiro(double quantia) {
        if (quantia > 0 && temDinheiroSuficiente(quantia)) {
            this.dinheiro -= quantia;
        }
    }

    public void adicionarExperiencia(int pontosXP) {
        if (pontosXP > 0) {
            this.experiencia += pontosXP;
        }
    }

    // --- MÉTODOS PARA RASTREAR PROJETOS ---
    public void completarProjeto(Projeto projeto) {
        if (!this.projetosConcluidos.contains(projeto)) {
            this.projetosConcluidos.add(projeto);
        }
    }
    public boolean jaConcluiuProjeto(Projeto projeto) {
        return this.projetosConcluidos.contains(projeto);
    }

    // --- MÉTODOS PARA RASTREAR EVENTOS ---
    public void concluirEvento(Evento evento) {
        if (!this.eventosConcluidos.contains(evento)) {
            this.eventosConcluidos.add(evento);
        }
    }
    public boolean jaConcluiuEvento(Evento evento) {
        return this.eventosConcluidos.contains(evento);
    }

    public void setHabilidade(String habilidade, NivelHabilidade novoNivel) {
        this.habilidades.put(habilidade, novoNivel);
        System.out.println("Habilidade " + habilidade + " agora está no nível " + novoNivel.getNomeExibicao() + "!");
    }

    // --- Getters e Setters ---
    public String getNome() { return nome; }
    public AreaAtuacao getAreaAtuacao() { return areaAtuacao; }
    public int getSaude() { return saude; }
    public int getSanidade() { return sanidade; }
    public int getEnergia() { return energia; }
    public double getDinheiro() { return dinheiro; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public int getExperiencia() { return experiencia; }
    public int getNetworking() { return networking; }
    public NivelCargo getCargo() { return cargo; }
    public Map<String, NivelHabilidade> getHabilidades() { return new HashMap<>(habilidades); }
    public void setCargo(NivelCargo novoCargo) { this.cargo = novoCargo; }
    public void setNetworking(int networking) { this.networking = networking; }
    public void setEnergia(int energia) { this.energia = Math.max(0, Math.min(100, energia)); }
    public Vaga getVagaAtual() { return vagaAtual; }
    public void setVagaAtual(Vaga vagaAtual) { this.vagaAtual = vagaAtual; }

    public boolean temDescontoCursos() {
        return temDescontoCursos;
    }

    public void setTemDescontoCursos(boolean temDescontoCursos) {
        this.temDescontoCursos = temDescontoCursos;
    }

    @Override
    public String toString() {
        return new StringJoiner(",\n  ", Jogador.class.getSimpleName() + "[\n  ", "\n]")
                .add("nome='" + nome + "'")
                .add("areaAtuacao=" + areaAtuacao)
                .add("cargo=" + cargo)
                .add("saude=" + saude)
                .add("sanidade=" + sanidade)
                .add("energia=" + energia)
                .add("dinheiro=" + String.format("%.2f", dinheiro))
                .add("experiencia=" + experiencia)
                .add("networking=" + networking)
                .add("habilidades=" + habilidades)
                .add("projetosConcluidos=" + projetosConcluidos.size()) // Apenas para debug
                .toString();
    }
}