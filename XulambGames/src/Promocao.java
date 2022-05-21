public class Promocao implements ICategoriaJogo {
    public static int[] FAIXA_PORCENTAGEM = { 30, 50 };
    private int porcentagem;

    public double precoVenda(double preco) {
        return preco * porcentagem;
    }

    private boolean verificarFaixa(int porcentagem) {
        return porcentagem >= FAIXA_PORCENTAGEM[0] && porcentagem <= FAIXA_PORCENTAGEM[1];
    }

    public int getPorcentagem() {
        return this.porcentagem;
    }

    public void setPorcentagem(int porcentagem) {
        if (verificarFaixa(porcentagem))
            this.porcentagem = porcentagem;
    }
}
