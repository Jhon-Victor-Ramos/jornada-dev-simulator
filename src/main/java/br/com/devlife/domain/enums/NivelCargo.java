package br.com.devlife.domain.enums;

public enum NivelCargo {
    ESTAGIARIO_INICIO(1),
    ESTAGIARIO(2),
    JUNIOR(3),
    PLENO(4),
    SENIOR(5),
    CEO(6);

    private final int valor;

    NivelCargo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}