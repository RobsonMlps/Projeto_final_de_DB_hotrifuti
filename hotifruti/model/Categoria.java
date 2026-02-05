package hotifruti.model;

public class Categoria {
    private int idCategoria;
    private String nome;
    private String descricao;

    public Categoria(int id_categoria, String nome, String descricao){
        this.idCategoria = id_categoria;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getIdCategoria(){
        return idCategoria;
    }
    public void setIdCategoria(int id_categoria){
        this.idCategoria = id_categoria;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
