import Exception.ForaDaFaixaException;

public class Jogo {
    private double precoOriginal;
    private double precoDesconto;
    private String nome;
    private EnumJogo categoria;
    private int numeroDeVendas;

    /**
     * 
     * @param precoOriginal double preco padrão do jogo
     * @param nome          String nome do jogo
     * @param categoria     ICategoriaJogo categoria do jogo
     * @throws ForaDaFaixaException
     */
    public Jogo(double precoOriginal, double precoDesconto, String nome, EnumJogo categoria) {
        this.precoOriginal = precoOriginal;
        this.nome = nome;
        this.categoria = categoria;

        verificarFaixa(precoDesconto);
        this.precoDesconto = precoDesconto;
        
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %.2fR$ - %s", this.nome, this.categoria, this.precoOriginal,
                this.numeroDeVendas);
    }

    /**
     * Muda a categoria do jogo e retorna verdadeiro
     * 
     * @param categoria ICategoriaJogo categoria que substituirá a anterior
     * @return boolean true sempré retornará verdadeiro
     */
    public boolean mudarCategoria(EnumJogo categoria) {
        this.categoria = categoria;
        return true;
    }

    public void verificarFaixa(double valor){
        if(!categoria.verificarFaixa(valor)){
            throw new ForaDaFaixaException("Valor incompatível com a categoria");
        }
    }

    /**
     * Método para calcular o preco de venda da instância do jogo
     * 
     * @return double preco de venda do jogo
     */
    public double precoVenda() {
        return this.precoDesconto;
    }

    /**
     * Retorna o preco original do jogo
     * 
     * @return double preco original do jogo
     */
    public double getPrecoOriginal() {
        return this.precoOriginal;
    }

    /**
     * Retorna o nome do jogo
     * 
     * @return String nome do jogo
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna a categoria do jogo
     * 
     * @return ICategoriaJogo categoria do jogo
     */
    public String getCategoria() {
        return this.categoria.name();
    }

    /**
     * Retorna o número de vendas do jogo
     * 
     * @return int número de vendas do jogo
     */
    public int getNumeroVendas() {
        return this.numeroDeVendas;
    }

    /**
     * Define o número de venda do jogo
     * 
     * @param numeroDeVendas int número de vendas a ser definido
     */
    public void setNumeroVendas(int numeroDeVendas) {
        this.numeroDeVendas = numeroDeVendas;
    }

}
