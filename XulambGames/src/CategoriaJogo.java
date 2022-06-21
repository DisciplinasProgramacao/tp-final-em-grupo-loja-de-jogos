public enum CategoriaJogo {

    LANCAMENTO(1.1, 1.1, 3d),
    PREMIUM(1d, 1d, 2d),
    REGULAR(0.7, 1d, 1d),
    PROMOCAO(0.3, 0.5, 0.5);

    private double minPreco;
    private double maxPreco;
    private double nivel;

    CategoriaJogo(double minPreco, double maxPreco, double nivel) {
        this.minPreco = minPreco;
        this.maxPreco = maxPreco;
        this.nivel = nivel;
    }


    public boolean verificarFaixa(double valorJogo,double valorVenda) {
        Double valorDescontoMin = valorJogo * minPreco;
        Double valorDescontoMax = valorJogo * maxPreco;
        return valorVenda >= Math.round(valorDescontoMin) && valorVenda <= Math.round(valorDescontoMax);
    }

    public double getNivel() {
        return nivel;
    }

}
