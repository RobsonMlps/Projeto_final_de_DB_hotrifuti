package hotifruti.model;

import java.time.LocalDateTime; // <--- Import obrigatório

public class Venda {
    private int idVenda;
    private String idClienteCpf; // Mudado de int para String (VARCHAR no banco)
    private LocalDateTime dataHora; // Mudado de DATETIME para LocalDateTime

    // Construtor Vazio (Boa prática)
    public Venda() {
        this.dataHora = LocalDateTime.now();
    }

    // Construtor Cheio
    public Venda(int idVenda, String idClienteCpf, LocalDateTime dataHora) {
        this.idVenda = idVenda;
        this.idClienteCpf = idClienteCpf;
        this.dataHora = dataHora;
    }

    // --- Getters e Setters ---

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    // CPF deve ser String em tudo!
    public String getIdClienteCpf() {
        return idClienteCpf;
    }

    public void setIdClienteCpf(String idClienteCpf) {
        this.idClienteCpf = idClienteCpf;
    }

    // Data agora é LocalDateTime
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}