public class Jogo {
    private double precoOriginal;
    private String nome;
    private ICategoriaJogo categoria;
    private int numeroDeVendas;

    public Jogo(double precoOriginal, String nome, ICategoriaJogo categoria) {
        this.precoOriginal = precoOriginal;
        this.nome = nome;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %.2fR$ - %s", this.nome, this.categoria, this.precoOriginal,
                this.numeroDeVendas);
    }

    public boolean mudarCategoria(ICategoriaJogo categoria) {
        this.categoria = categoria;
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

    public void setNumeroVendas(int numeroDeVendas) {
        this.numeroDeVendas = numeroDeVendas;
    }

}
