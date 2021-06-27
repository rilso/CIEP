package br.com.unialfa.ciep.DAO;

public class VagasDAO {

    private String nomeEmpresa;
    private String escolaridade;
    private String descricao;
    private double salario;
    private long empresa_id;

    public VagasDAO() {

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

    public long getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(long empresa_id) {
        this.empresa_id = empresa_id;
    }
}
