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
        return 0d;
    }

}
