package br.com.unialfa.ciep.DAO;

import br.com.unialfa.ciep.domain.CandidatarVaga;

public class CandidatarVagaDAO {

    private boolean candidatar;
    private long pessoaFisica_id;
    private long vagasEmprego_id;

    public CandidatarVagaDAO(){

    }

    public boolean isCandidatar() {
        return candidatar;
    }

    public void setCandidatar(boolean candidatar) {
        this.candidatar = candidatar;
    }

    public long getPessoaFisica_id() {
        return pessoaFisica_id;
    }

    public void setPessoaFisica_id(long pessoaFisica_id) {
        this.pessoaFisica_id = pessoaFisica_id;
    }

    public long getVagasEmprego_id() {
        return vagasEmprego_id;
    }

    public void setVagasEmprego_id(long vagasEmprego_id) {
        this.vagasEmprego_id = vagasEmprego_id;
    }
}

