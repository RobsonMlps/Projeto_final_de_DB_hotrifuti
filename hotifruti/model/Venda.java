package hotifruti.model;
/*CREATE TABLE VENDA (
    ID_Venda INT PRIMARY KEY,
    ID_Cliente_CPF VARCHAR(14),
    Data_Hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ID_Cliente_CPF) REFERENCES CLIENTE(CPF)
); */
public class Venda {
    private int idVenda;
    private String idClienteCpf;
    private String dataHora;

    public Venda(int idVenda, String idClienteCpf, String dataHora) {
        this.idVenda = idVenda;
        this.idClienteCpf = idClienteCpf;
        this.dataHora = dataHora;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getIdClienteCpf() {
        return idClienteCpf;
    }

    public void setIdClienteCpf(String idClienteCpf) {
        this.idClienteCpf = idClienteCpf;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}