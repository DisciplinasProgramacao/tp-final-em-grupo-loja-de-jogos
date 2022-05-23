public class Lancamento implements ICategoriaJogo {
    public static double ADICIONAL = 0.1;

    public double precoVenda(double preco) {
        return preco + (preco * ADICIONAL);
    }

    public String getNomeCategoria() {
        return this.getClass().getName();
    }
}
