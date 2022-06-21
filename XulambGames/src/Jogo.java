import java.io.Serializable;
import java.util.Objects;
import Exception.*;

public class Jogo implements Serializable {
    private double precoOriginal;
    private double precoDesconto;
    private String nome;
    private CategoriaJogo categoria;
    private int numeroDeVendas;

    /**
     * 
     * @param precoOriginal double preco padrão do jogo
     * @param nome          String nome do jogo
     * @param categoria     ICategoriaJogo categoria do jogo
     * @throws ForaDaFaixaException preço fora da faixa estabelecida
     */
    public Jogo(double precoOriginal, double precoVenda, String nome, CategoriaJogo categoria) {
        this.precoOriginal = precoOriginal;
        this.nome = nome;
        this.categoria = categoria;

        verificarFaixa(precoOriginal, precoVenda);
        this.precoDesconto = precoVenda;

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
    public boolean mudarCategoria(CategoriaJogo categoria) {
        this.categoria = categoria;
        return true;
    }

    /**
     * responsável por verificar a faixa de preço pré-estabelecida na categoria de jogos
     * @param valor valor a ser verificado
     * @throws ForaDaFaixaException preço fora da faixa estabelecida
     */
    public void verificarFaixa(double valor, double valorVenda) {
        if (!categoria.verificarFaixa(valor, valorVenda)) {
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
    public CategoriaJogo getCategoria() {
        return this.categoria;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Jogo jogo = (Jogo) o;
        return Objects.equals(nome, jogo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
