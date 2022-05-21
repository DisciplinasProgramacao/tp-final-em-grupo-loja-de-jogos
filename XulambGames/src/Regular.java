public class Regular implements ICategoriaJogo {
    public static int[] FAIXA_PORCENTAGEM = { 70, 100 };
    private int porcentagem;

    public double precoVenda(double preco) {
        return 0d;
    }

    public boolean verificarFaixa() {
        return false;
    }

    public int getPorcentagem() {
        return this.porcentagem;
    }

    public void setPorcentagem() {
    }
}
