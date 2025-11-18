package br.com.devlife.domain;

import java.util.Objects;

public final class AcaoLazer {
    private final String nome;
    private final String descricao;
    private final double custoDinheiro;
    private final int bonusEnergia;
    private final int bonusSaude;
    private final int bonusSanidade;
    private final int duracaoEmDias;

    public AcaoLazer(String nome, String descricao, double custoDinheiro, int bonusEnergia, int bonusSaude, int bonusSanidade, int duracaoEmDias) {
        this.nome = Objects.requireNonNull(nome);
        this.descricao = Objects.requireNonNull(descricao);
        this.custoDinheiro = custoDinheiro;
        this.bonusEnergia = bonusEnergia;
        this.bonusSaude = bonusSaude;
        this.bonusSanidade = bonusSanidade;
        this.duracaoEmDias = duracaoEmDias;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getCustoDinheiro() { return custoDinheiro; }
    public int getBonusEnergia() { return bonusEnergia; }
    public int getBonusSaude() { return bonusSaude; }
    public int getBonusSanidade() { return bonusSanidade; }
    public int getDuracaoEmDias() { return duracaoEmDias; }
}