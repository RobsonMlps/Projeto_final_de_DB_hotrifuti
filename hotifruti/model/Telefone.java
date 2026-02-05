package hotifruti.model;

/*CREATE TABLE TELEFONE_CLIENTE (
    ID_Telefone INT PRIMARY KEY AUTO_INCREMENT,
    ID_Cliente_CPF VARCHAR(14),
    Numero_Telefone VARCHAR(20),
    FOREIGN KEY (ID_Cliente_CPF) REFERENCES CLIENTE(CPF)
); */
public class Telefone {
    private int idTelefone;
    private String idClienteCpf;
    private String numeroTelefone;

    public Telefone(int idTelefone, String idClienteCpf, String numeroTelefone){
        this.idTelefone = idTelefone;
        this.idClienteCpf = idClienteCpf;
        this.numeroTelefone = numeroTelefone;
    }

    public int getIdTelefone(){
        return idTelefone;
    }
    public void setIdTelefone(int idTelefone){
        this.idTelefone = idTelefone;
    }

    public String getIdClienteCpf(){
        return idClienteCpf;
    }
    public void setIdClienteCpf(String idClienteCpf){
        this.idClienteCpf = idClienteCpf;
    }

    public String getNumeroTelefone(){
        return numeroTelefone;
    }
    public void setNumeroTelefone(String numeroTelefone){
        this.numeroTelefone = numeroTelefone;
    }
}
