package hotifruti.model;
/*CREATE TABLE ITEM_VENDA (
    ID_Venda INT,
    ID_Produto INT,
    Quantidade_Vendida INT NOT NULL,
    Preco_Unidade DECIMAL(10,2) NOT NULL
    PRIMARY KEY (ID_Venda, ID_Produto),
    FOREIGN KEY (ID_Venda) REFERENCES VENDA(ID_Venda),
    FOREIGN KEY (ID_Produto) REFERENCES PRODUTO(ID_Produto)
); */

import java.math.BigDecimal;

public class ItemVenda {
    private int idVenda;
    private int idProduto;
    private int quantidadeVendida;
    private BigDecimal precoUnidade;

    public ItemVenda() {
    }

    public ItemVenda(int idVenda, int idProduto, int quantidadeVendida, BigDecimal precoUnidade) {
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.precoUnidade = precoUnidade;
    }



    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public BigDecimal getPrecoUnidade() {
        return precoUnidade;
    }

    public void setPrecoUnidade(BigDecimal precoUnidade) {
        this.precoUnidade = precoUnidade;
    }
}
