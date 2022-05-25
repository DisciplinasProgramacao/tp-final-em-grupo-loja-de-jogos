import java.io.Serializable;
import java.util.Objects;

public class Jogo implements Serializable {
    private double precoOriginal;
    private String nome;
    private ICategoriaJogo categoria;
    private int numeroDeVendas;

    /**
     * 
     * @param precoOriginal double preco padrão do jogo
     * @param nome          String nome do jogo
     * @param categoria     ICategoriaJogo categoria do jogo
     */
    public Jogo(double precoOriginal, String nome, ICategoriaJogo categoria) {
        this.precoOriginal = precoOriginal;
        this.nome = nome;
        this.categoria = categoria;
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
    public boolean mudarCategoria(ICategoriaJogo categoria) {
        this.categoria = categoria;
        return true;
    }

    /**
     * Método para calcular o preco de venda da instância do jogo
     * 
     * @return double preco de venda do jogo
     */
    public double precoVenda() {
        return this.categoria.precoVenda(this.precoOriginal);
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
    public ICategoriaJogo getCategoria() {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return Objects.equals(nome, jogo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
