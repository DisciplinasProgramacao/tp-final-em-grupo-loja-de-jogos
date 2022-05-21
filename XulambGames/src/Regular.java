public class Regular implements ICategoriaJogo {
    public static int[] FAIXA_PORCENTAGEM = { 70, 100 };
    private int porcentagem;

    public double precoVenda(double preco) {
        return preco * this.porcentagem;
    }

    /**
     * Verifica se a porcentagem a ser definida está na faixa permitida
     * 
     * @param porcentagem
     * @return boolean true se a porcentagem a ser definida estiver na faixa
     *         permitida, false se não
     */
    private boolean verificarFaixa(int porcentagem) {
        return porcentagem >= FAIXA_PORCENTAGEM[0] && porcentagem <= FAIXA_PORCENTAGEM[1];
    }

    /**
     * Retorna a porcentagem atual da categoria
     * 
     * @return int porcentagem atual da categoria
     */
    public int getPorcentagem() {
        return this.porcentagem;
    }

    /**
     * Define a porcentagem atual da categoria se a porcentagem a ser definida
     * estiver na faixa permitida
     * 
     * @param porcentagem int porcentagem a ser definida
     */
    public void setPorcentagem(int porcentagem) {
        if (verificarFaixa(porcentagem))
            this.porcentagem = porcentagem;
    }
}
