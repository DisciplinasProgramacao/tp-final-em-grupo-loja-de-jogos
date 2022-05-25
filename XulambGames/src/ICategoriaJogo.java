import java.io.Serializable;

public interface ICategoriaJogo extends Serializable {
    /**
     * Calcula o preço final do jogo baseado na regra da categoria
     * 
     * @param preco double preco original do jogo para ser calculado
     * @return double preco final do jogo, após ser aplicado a regra da categoria
     */
    public double precoVenda(double preco);

    public String getNomeCategoria();
}