public enum CategoriaJogo {

    LANCAMENTO(1.1, 1.1, 3d),
    PREMIUM(1d, 1d, 2d),
    REGULAR(0.7, 1d, 1d),
    PROMOCAO(0.3, 0.5, 0.5);

    private double minPreco;
    private double maxPreco;

    private double nivel;

    CategoriaJogo(Double minPreco, Double maxPreco) {
        this.minPreco = minPreco;
        this.maxPreco = maxPreco;
    }

    public boolean verificarFaixa(Double valorVenda) {
        double valorDescontoMin = valorVenda * minPreco;
        double valorDescontoMax = valorVenda * maxPreco;
        if (valorVenda > valorDescontoMin) {
            if (valorVenda < valorDescontoMax) {
                return true;
            }
        }
        return false;
    }

    public double getNivel() {
        return nivel;
    }

}
