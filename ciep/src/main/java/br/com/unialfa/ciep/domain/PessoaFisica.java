package br.com.unialfa.ciep.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
public class PessoaFisica  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String sexo;
    private long idade;
    private String descricao;

    @Column(unique = true, nullable = false)
    private String cpf;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoaFisica", cascade = CascadeType.REMOVE)
    private List<CandidatarVaga> candidatarVagas;

    public PessoaFisica() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public long getIdade() {
        return idade;
    }

    public void setIdade(long idade) {
        this.idade = idade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<CandidatarVaga> getCandidatarVagas() {
        return candidatarVagas;
    }

    public void setCandidatarVagas(List<CandidatarVaga> candidatarVagas) {
        this.candidatarVagas = candidatarVagas;
    }
}
