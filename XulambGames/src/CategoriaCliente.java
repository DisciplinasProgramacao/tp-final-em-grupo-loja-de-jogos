public enum CategoriaCliente {
    CADASTRADO(0,0d),
    EMPOLGADO(10, 0.1),
    FANATICO(25, 0.3);

    private int mensalidade;
    private double desconto;

    private CategoriaCliente(int mensalidade, double desconto){
        this.mensalidade = mensalidade;
        this.desconto = desconto;
    }

    public int getMensalidade(){
        return this.mensalidade;
    }

    public double getDesconto(){
        return this.desconto;
    }
}
