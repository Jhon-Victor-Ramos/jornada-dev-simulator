package br.com.devlife.domain;

import br.com.devlife.domain.enums.NivelCargo;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public final class Projeto {
    private final String nome;
    private final String descricao;
    private final int xpGanho;
    private final double dinheiroGanho;
    private final int energiaCusto;
    private final int sanidadeCusto;
    private final int networkingGanho;
    private final Map<String, Integer> requisitos; // Habilidades e níveis mínimos necessários

    public Projeto(String nome, String descricao, int xpGanho, double dinheiroGanho, int energiaCusto, int sanidadeCusto, int networkingGanho, Map<String, Integer> requisitos) {
        this.nome = Objects.requireNonNull(nome, "O nome não pode ser nulo.");
        this.descricao = Objects.requireNonNull(descricao, "A descrição não pode ser nula.");
        this.xpGanho = xpGanho;
        this.dinheiroGanho = dinheiroGanho;
        this.energiaCusto = energiaCusto;
        this.sanidadeCusto = sanidadeCusto;
        this.networkingGanho = networkingGanho;
        this.requisitos = (requisitos != null) ? Collections.unmodifiableMap(requisitos) : Collections.emptyMap();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getXpGanho() {
        return xpGanho;
    }

    public double getDinheiroGanho() {
        return dinheiroGanho;
    }

    public int getEnergiaCusto() {
        return energiaCusto;
    }

    public int getSanidadeCusto() {
        return sanidadeCusto;
    }

    public int getNetworkingGanho() {
        return networkingGanho;
    }

    public Map<String, Integer> getRequisitos() {
        return requisitos;
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "nome='" + nome + '\'' +
                ", xpGanho=" + xpGanho +
                ", dinheiroGanho=" + dinheiroGanho +
                ", energiaCusto=" + energiaCusto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projeto projeto = (Projeto) o;
        return nome.equals(projeto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}