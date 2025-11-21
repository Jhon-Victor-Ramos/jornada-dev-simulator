package br.com.devlife.domain;

import java.util.Objects;

public final class Evento {

    private final String nome;
    private final String descricao;
    private final String data;
    private final String local;
    private final String publicoAlvo;
    private final int xpGanho;
    private final int networkingGanho;
    private final double custoFinanceiro;
    private final int energiaConsumida;
    private final int duracaoEmDias;
    private final boolean possuiBonusEspecial;

    public Evento(String nome, String descricao, String data, String local, String publicoAlvo, int xpGanho, int networkingGanho, double custoFinanceiro, int energiaConsumida, int duracaoEmDias, boolean possuiBonusEspecial) {
        // Usar Objects.requireNonNull para garantir que dados essenciais não sejam nulos
        this.nome = Objects.requireNonNull(nome, "O nome do evento não pode ser nulo.");
        this.descricao = Objects.requireNonNull(descricao, "A descrição do evento não pode ser nula.");
        this.data = Objects.requireNonNull(data, "A data do evento não pode ser nula.");
        this.local = Objects.requireNonNull(local, "O local do evento não pode ser nulo.");
        this.publicoAlvo = Objects.requireNonNull(publicoAlvo, "O público-alvo do evento não pode ser nulo.");

        // Validar valores numéricos para evitar inconsistências
        if (xpGanho < 0 || networkingGanho < 0 || custoFinanceiro < 0 || energiaConsumida < 0 || duracaoEmDias <= 0) {
            throw new IllegalArgumentException("Valores numéricos para o evento não podem ser negativos e a duração deve ser de pelo menos 1 dia.");
        }

        this.xpGanho = xpGanho;
        this.networkingGanho = networkingGanho;
        this.custoFinanceiro = custoFinanceiro;
        this.energiaConsumida = energiaConsumida;
        this.duracaoEmDias = duracaoEmDias;
        this.possuiBonusEspecial = possuiBonusEspecial;
    }

    // --- Getters ---

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getData() { return data; }
    public String getLocal() { return local; }
    public String getPublicoAlvo() { return publicoAlvo; }
    public int getXpGanho() { return xpGanho; }
    public int getNetworkingGanho() { return networkingGanho; }
    public double getCustoFinanceiro() { return custoFinanceiro; }
    public int getEnergiaConsumida() { return energiaConsumida; }
    public int getDuracaoEmDias() { return duracaoEmDias; }
    public boolean possuiBonusEspecial() { return possuiBonusEspecial; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return nome.equals(evento.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}