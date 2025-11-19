package br.com.devlife.domain;

import br.com.devlife.domain.enums.NivelHabilidade;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public final class Curso {
    private final String nome;
    private final double custoDinheiro;
    private final int duracaoEmDias;
    private final String habilidadeEnsinada;
    private final NivelHabilidade nivelResultante;
    private final int xpGanho;
    private final Map<String, NivelHabilidade> requisitos; // NOVO ATRIBUTO

    public Curso(String nome, double custoDinheiro, int duracaoEmDias, String habilidadeEnsinada, NivelHabilidade nivelResultante, int xpGanho, Map<String, NivelHabilidade> requisitos) {
        this.nome = nome;
        this.custoDinheiro = custoDinheiro;
        this.duracaoEmDias = duracaoEmDias;
        this.habilidadeEnsinada = habilidadeEnsinada;
        this.nivelResultante = nivelResultante;
        this.xpGanho = xpGanho;
        this.requisitos = (requisitos != null) ? Collections.unmodifiableMap(requisitos) : Collections.emptyMap();
    }

    // Getters
    public String getNome() { return nome; }
    public double getCustoDinheiro() { return custoDinheiro; }
    public int getDuracaoEmDias() { return duracaoEmDias; }
    public String getHabilidadeEnsinada() { return habilidadeEnsinada; }
    public NivelHabilidade getNivelResultante() { return nivelResultante; }
    public int getXpGanho() { return xpGanho; }
    public Map<String, NivelHabilidade> getRequisitos() { return requisitos; } // NOVO GETTER

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return nome.equals(curso.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}