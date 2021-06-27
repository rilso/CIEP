package br.com.unialfa.ciep.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;


@Entity
public class Vagas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomeEmpresa;
    private String escolaridade;
    private String descricao;
    private double salario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="empresa_id")
    private Empresa empresa ;

    @OneToMany(mappedBy = "vagas")
    private List<CandidatarVaga> candidatarVagas;

    public Vagas() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<CandidatarVaga> getCandidatarVagas() {
        return candidatarVagas;
    }

    public void setCandidatarVagas(List<CandidatarVaga> candidatarVagas) {
        this.candidatarVagas = candidatarVagas;
    }
}
