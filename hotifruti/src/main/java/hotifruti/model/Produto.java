package hotifruti.model;
/*CREATE TABLE PRODUTO (
    ID_Produto INT PRIMARY KEY,
    ID_Categoria INT,
    Nome VARCHAR(100) NOT NULL,
    Descricao TEXT,
    Peso_KG DECIMAL(10,2),
    FOREIGN KEY (ID_Categoria) REFERENCES CATEGORIA(ID_Categoria)
); */
import java.math.BigDecimal;

public class Produto {
    private int idProduto;
    private int idCategoria;
    private String nome;
    private String descricao;
    private BigDecimal pesoKg;

    public Produto(int idProduto, int idCategoria, String nome, String descricao, BigDecimal pesoKg) {
        this.idProduto = idProduto;
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.descricao = descricao;
        this.pesoKg = pesoKg;
    }

    public Produto() {
}

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }
}
