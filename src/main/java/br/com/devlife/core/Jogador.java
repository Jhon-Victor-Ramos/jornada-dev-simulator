package br.com.devlife.core;

import br.com.devlife.domain.enums.AreaAtuacao;
import br.com.devlife.domain.enums.NivelCargo;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Jogador {
    private final String nome;
    private final AreaAtuacao areaAtuacao;
    private int saude;
    private int sanidade;
    private int energia;
    private double dinheiro;
    private int experiencia;
    private int networking;
    private NivelCargo cargo;
    // Alterado para armazenar o nível da habilidade como String
    private final Map<String, String> habilidades;

    public Jogador(String nome, AreaAtuacao areaAtuacao, NivelCargo cargoInicial) {
        this.nome = nome;
        this.areaAtuacao = areaAtuacao;
        this.cargo = cargoInicial;
        this.saude = 100;
        this.sanidade = 100;
        this.energia = 100;
        this.dinheiro = 500.00;
        this.experiencia = 0;
        this.networking = 10;
        this.habilidades = new HashMap<>();
        inicializarHabilidadesPorArea();
    }

    // Adiciona habilidades de acordo com a area escolhida pelo jogador inicialmente
    private void inicializarHabilidadesPorArea() {
        switch (this.areaAtuacao) {
            case FRONTEND:
                this.habilidades.put("HTML", "Iniciante");
                this.habilidades.put("CSS", "Iniciante");
                this.habilidades.put("JavaScript", "Iniciante");
                break;
            case BACKEND:
                this.habilidades.put("Java", "Iniciante");
                this.habilidades.put("SQL", "Iniciante");
                this.habilidades.put("API REST", "Iniciante");
                break;
        }
    }

    public void aplicarPenalidadesTurno() {
        if (this.sanidade < 30) {
            System.out.println(this.nome + " está se sentindo esgotado mentalmente. A energia caiu mais rápido!");
            alterarRecursoVital("energia", -15);
        }
        if (this.saude < 30) {
            System.out.println(this.nome + " está se sentindo mal. A energia caiu mais rápido!");
            alterarRecursoVital("energia", -10);
        }
        // Penalidade padrão de energia por turno
        alterarRecursoVital("energia", -5);
    }

    public void alterarRecursoVital(String recurso, int valor) {
        int valorAtual = 0;
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

    /**
     * Atualiza o nível de uma habilidade seguindo a progressão:
     * (Nenhuma) -> Iniciante -> Intermediário -> Avançado
     * @param habilidade O nome da habilidade a ser aprendida ou melhorada.
     */
    public void aprenderOuMelhorarHabilidade(String habilidade) {
        String nivelAtual = this.habilidades.getOrDefault(habilidade, "Nenhum");
        String novoNivel;

        switch (nivelAtual) {
            case "Nenhum":
                novoNivel = "Iniciante";
                break;
            case "Iniciante":
                novoNivel = "Intermediário";
                break;
            case "Intermediário":
                novoNivel = "Avançado";
                break;
            case "Avançado":
                System.out.println("Habilidade " + habilidade + " já está no nível máximo!");
                return; // Encerra o método pois não há mais para onde progredir
            default:
                // Caso encontre um valor inesperado, define como Iniciante
                novoNivel = "Iniciante";
                break;
        }
        this.habilidades.put(habilidade, novoNivel);
        System.out.println("Habilidade " + habilidade + " melhorada para o nível " + novoNivel + "!");
    }

    // Retorna o nível da habilidade como String
    public String getNivelHabilidade(String habilidade) {
        return this.habilidades.getOrDefault(habilidade, "Nenhum");
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

    public String getNome() { return nome; }
    public AreaAtuacao getAreaAtuacao() { return areaAtuacao; }
    public int getSaude() { return saude; }
    public int getSanidade() { return sanidade; }
    public int getEnergia() { return energia; }
    public double getDinheiro() { return dinheiro; }
    public int getExperiencia() { return experiencia; }
    public int getNetworking() { return networking; }
    public NivelCargo getCargo() { return cargo; }
    public Map<String, String> getHabilidades() { return new HashMap<>(habilidades); }
    public void setCargo(NivelCargo novoCargo) { this.cargo = novoCargo; }
    public void setNetworking(int networking) { this.networking = networking; }

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
                .toString();
    }
}