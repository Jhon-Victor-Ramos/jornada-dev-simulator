package br.com.devlife.domain.enums;

public enum NivelHabilidade {
    NENHUM(0, "Nenhum"),
    INICIANTE(1, "Iniciante"),
    INTERMEDIARIO(2, "Intermediário"),
    AVANCADO(3, "Avançado");

    private final int valor;
    private final String nomeExibicao;

    NivelHabilidade(int valor, String nomeExibicao) {
        this.valor = valor;
        this.nomeExibicao = nomeExibicao;
    }

    public int getValor() { return valor; }
    public String getNomeExibicao() { return nomeExibicao; }
}