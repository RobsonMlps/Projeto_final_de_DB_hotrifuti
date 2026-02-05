package hotifruti.model;
/*CREATE TABLE FORNECEDOR (
    ID_Fornecedor INT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    CNPJ VARCHAR(18) UNIQUE NOT NULL,
    Contato VARCHAR(100)
); */
public class Fornecedor {
    private int idFornecedor;
    private String nome, cnpj, contato;

    public Fornecedor(int idFornecedor, String nome, String cnpj, String contato){
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.cnpj = cnpj;
        this.contato = contato;
    }

    public int getIdFornecedor(){
        return idFornecedor;
    }
    public void setIdFornecedor(int idFornecedor){
        this.idFornecedor = idFornecedor;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCnpj(){
        return cnpj;
    }
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public String getContato(){
        return contato;
    }
    public void setContato(String contato){
        this.contato = contato;
    }
}
