public enum CategoriaJogo {

    LANCAMENTO(1.1, 1.1),
    PREMIUM(1d,1d),
    REGULAR(0.7,1d),
    PROMOCAO(0.3,0.5);

    private double minPreco;
    private double maxPreco; 

    CategoriaJogo(double minPreco, double maxPreco){
        this.minPreco = minPreco;
        this.maxPreco = maxPreco;
    }

    public boolean verificarFaixa(double valorVenda, double valorOriginal){
        double valorDescontoMin = valorOriginal * minPreco;
        double valorDescontoMax = valorOriginal * maxPreco;
            if(valorVenda <= valorDescontoMax && valorVenda >= valorDescontoMin){
                return true;    
            }
        return false;
    }


}
