package br.com.unialfa.ciep.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String razaoSocial;
    private String nomeFant;

    @Column (unique = true, nullable = false)
    private String cnpj;

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    private Usuario usuario;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.REMOVE)
    private List<Vagas> vagas;

    public Empresa() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFant() {
        return nomeFant;
    }

    public void setNomeFant(String nomeFant) {
        this.nomeFant = nomeFant;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Vagas> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vagas> vagas) {
        this.vagas = vagas;
    }
}
