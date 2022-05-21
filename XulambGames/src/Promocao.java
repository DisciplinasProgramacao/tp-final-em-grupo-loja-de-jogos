public class Promocao implements ICategoriaJogo {
    public static int[] FAIXA_PORCENTAGEM = { 30, 50 };
    private int porcentagem;

    public double precoVenda(double preco) {
        return preco * porcentagem;
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
