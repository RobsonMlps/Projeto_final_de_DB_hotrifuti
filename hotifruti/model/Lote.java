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
    private String dataEntrada;
    private String dataValidade;
    private double custo;

    public Lote(int idLote, int idProduto, int idFornecedor, String dataEntrada, String dataValidade, double custo) {
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

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
}