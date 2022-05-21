public class Jogo {
    private double precoOriginal;
    private String nome;
    private ICategoriaJogo categoria;
    private int numeroDeVendas;

    @Override
    public String toString() {
        return "";
    }

    public boolean mudarCategoria(ICategoriaJogo categoria) {
        return true;
    }

    public double precoVenda() {
        return this.categoria.precoVenda(this.precoOriginal);
    }

    public double getPrecoOriginal() {
        return this.precoOriginal;
    }

    public String getNome() {
        return this.nome;
    }

    public ICategoriaJogo getCategoria() {
        return this.categoria;
    }

    public int getNumeroVendas() {
        return this.numeroDeVendas;
    }

}
