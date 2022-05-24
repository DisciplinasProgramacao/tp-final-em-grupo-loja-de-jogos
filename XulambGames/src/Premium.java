public class Premium implements ICategoriaJogo {
    public double precoVenda(double preco) {
        return preco;
    }

    public String getNomeCategoria() {
        return this.getClass().getName();
    }
}
