package br.com.unialfa.ciep.DAO;

import br.com.unialfa.ciep.domain.Usuario;

public class EmpresaDAO {

    private String razaoSocial;
    private String nomeFant;
    private String cnpj;
    private long usuario_id;
    private long vagas_id;
    private Usuario usuario;

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

    public long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public long getVagas_id() {
        return vagas_id;
    }

    public void setVagas_id(long vagas_id) {
        this.vagas_id = vagas_id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

