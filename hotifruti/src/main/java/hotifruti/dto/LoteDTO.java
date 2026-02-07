package hotifruti.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class LoteDTO {

    private int idLote;
    private String nomeProduto;
    private String nomeFornecedor;
    private BigDecimal custo;
    private Date dataEntrada;
    private Date dataValidade;

    public LoteDTO(int idLote, String nomeProduto, String nomeFornecedor, BigDecimal custo, Date dataEntrada, Date dataValidade) {
        this.idLote = idLote;
        this.nomeProduto = nomeProduto;
        this.nomeFornecedor = nomeFornecedor;
        this.custo = custo;
        this.dataEntrada = dataEntrada;
        this.dataValidade = dataValidade;
    }

    

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

}
