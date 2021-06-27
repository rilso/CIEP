package br.com.unialfa.ciep.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class CandidatarVaga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private boolean candidatar;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="vagas_id")
    private Vagas vagas;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="pessoa_fisica_id")
    private PessoaFisica pessoaFisica;

    public CandidatarVaga() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCandidatar() {
        return candidatar;
    }

    public void setCandidatar(boolean candidatar) {
        this.candidatar = candidatar;
    }

    public Vagas getVagas() {
        return vagas;
    }

    public void setVagas(Vagas vagas) {
        this.vagas = vagas;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
}
