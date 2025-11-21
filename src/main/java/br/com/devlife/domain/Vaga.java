package br.com.devlife.domain;

import br.com.devlife.domain.enums.NivelCargo;
import br.com.devlife.domain.enums.NivelHabilidade;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public final class Vaga {
    private final String nomeEmpresa;
    private final String tituloVaga;
    private final double salario;
    private final int xpBonus;
    private final int netBonus;
    private final int penalidadeDiariaSanidade;
    private final int penalidadeDiariaEnergia;
    private final Map<String, NivelHabilidade> requisitos;
    private final int diasValidade;
    private final NivelCargo nivelRequerido;
    private final NivelCargo nivelPromocao;

    public Vaga(String nomeEmpresa, String tituloVaga, double salario, int xpBonus, int netBonus, int penalidadeDiariaSanidade, int penalidadeDiariaEnergia, Map<String, NivelHabilidade> requisitos, int diasValidade, NivelCargo nivelRequerido, NivelCargo nivelPromocao) {
        this.nomeEmpresa = nomeEmpresa;
        this.tituloVaga = tituloVaga;
        this.salario = salario;
        this.xpBonus = xpBonus;
        this.netBonus = netBonus;
        this.penalidadeDiariaSanidade = penalidadeDiariaSanidade;
        this.penalidadeDiariaEnergia = penalidadeDiariaEnergia;
        this.requisitos = Collections.unmodifiableMap(requisitos);
        this.diasValidade = diasValidade;
        this.nivelRequerido = nivelRequerido;
        this.nivelPromocao = nivelPromocao;
    }

    // Getters para todos os atributos
    public String getNomeEmpresa() { return nomeEmpresa; }
    public String getTituloVaga() { return tituloVaga; }
    public double getSalario() { return salario; }
    public int getXpBonus() { return xpBonus; }
    public int getNetBonus() { return netBonus; }
    public int getPenalidadeDiariaSanidade() { return penalidadeDiariaSanidade; }
    public int getPenalidadeDiariaEnergia() { return penalidadeDiariaEnergia; }
    public Map<String, NivelHabilidade> getRequisitos() { return requisitos; }
    public int getDiasValidade() { return diasValidade; }
    public NivelCargo getNivelRequerido() { return nivelRequerido; }
    public NivelCargo getNivelPromocao() { return nivelPromocao; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return nomeEmpresa.equals(vaga.nomeEmpresa) && tituloVaga.equals(vaga.tituloVaga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeEmpresa, tituloVaga);
    }
}