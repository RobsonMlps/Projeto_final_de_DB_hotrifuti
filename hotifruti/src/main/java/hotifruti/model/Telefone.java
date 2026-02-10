package hotifruti.model;

/*CREATE TABLE TELEFONE_CLIENTE (
    ID_Telefone INT PRIMARY KEY AUTO_INCREMENT,
    ID_Cliente_CPF VARCHAR(14),
    Numero_Telefone VARCHAR(20),
    FOREIGN KEY (ID_Cliente_CPF) REFERENCES CLIENTE(CPF)
); */
public class Telefone {
    private int idTelefone;
    private int idCliente;
    private String numeroTelefone;

    public Telefone() {}

    public Telefone(int idTelefone, int idCliente, String numeroTelefone){
        this.idTelefone = idTelefone;
        this.idCliente = idCliente;

        this.numeroTelefone = numeroTelefone;
    }

    public int getIdTelefone(){
        return idTelefone;
    }
    public void setIdTelefone(int idTelefone){
        this.idTelefone = idTelefone;
    }

    public int getIdCliente(){
        return idCliente;
    }
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }

    public String getNumeroTelefone(){
        return numeroTelefone;
    }
    public void setNumeroTelefone(String numeroTelefone){
        this.numeroTelefone = numeroTelefone;
    }
}
