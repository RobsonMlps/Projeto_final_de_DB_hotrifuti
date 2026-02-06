package hotifruti.model;
/*CREATE TABLE LOTE (
    ID_Lote INT PRIMARY KEY,
    ID_Produto INT,
    ID_Fornecedor INT,
    Data_Entrada DATE,
    Data_Validade DATE,
    Custo DECIMAL(10,2),
    FOREIGN KEY (ID_Produto) REFERENCES PRODUTO(ID_Produto),
    FOREIGN KEY (ID_Fornecedor) REFERENCES FORNECEDOR(ID_Fornecedor)
);
 */
public class Lote {
    private int idLote;
    private int idProduto;
    private int idFornecedor;
    private Date dataEntrada;
    private Date dataValidade;
    private BigDecimal custo;

    public Lote(int idLote, int idProduto, int idFornecedor, Date dataEntrada, Date dataValidade, BigDecimal custo) {
        this.idLote = idLote;
        this.idProduto = idProduto;
        this.idFornecedor = idFornecedor;
        this.dataEntrada = dataEntrada;
        this.dataValidade = dataValidade;
        this.custo = custo;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
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

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }
}