
import java.io.Serializable;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Cliente implements Serializable {
    private String nome;
    private String usuario;
    private String senha;
    private CategoriaCliente categoria;
    private LinkedList<Pedido> pedidos;

    /**
     * 
     * @param nome nome do cliente
     * @param usuario nome usuario escolhido pelo cliente
     * @param senha senha escolhida pelo cliente
     */
    public Cliente(String nome, String usuario, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.categoria = CategoriaCliente.CADASTRADO;
        this.pedidos = new LinkedList<Pedido>();
    }

    /**
     * 
     * @param categoria categoria de cliente desejada para mudança
     * @return retorna verdadeiro se a operação for concluída
     */
    public boolean mudarCategoria(CategoriaCliente categoria) {
        this.categoria = categoria;
        return true;
    }

    /**
     * 
     * @param categoria categoria desejada para filtrar todos os jogos da lista de pedidos do cliente
     * @return retorna uma String contendo a impressao de jogos com a categoria desejada
     */
    public String historicoDeCompras(CategoriaJogo categoria) {
        StringBuilder impressao = new StringBuilder();
        impressao.append("******HISTÓRICO DE COMPRAS******\nCATEGORIA: " + categoria + "\n");
        impressao.append("-------------------------------\n");

        List<Jogo> jogos = new ArrayList<>();
        this.pedidos.forEach(pedido -> jogos.addAll(pedido.getJogos()));
        
        jogos.stream().filter(j -> j.getCategoria() == categoria).forEach(categoriaJogo -> {
            impressao.append(categoriaJogo.toString() + "\n");
        });

        impressao.append("********************************");
        return impressao.toString();
    }

    /**
     * @param data data desejada para filtrar todos os jogos da lista de pedidos do cliente
     * @return retorna uma String contendo a impressao de jogos com a data desejada
     */
    public String historicoDeCompras(Date data) {
        StringBuilder impressao = new StringBuilder();
        impressao.append("******HISTÓRICO DE COMPRAS******\nDATA: " + data + "\n");
        impressao.append("-------------------------------\n");

        List<Jogo> jogos = new ArrayList<>();
        this.pedidos.stream().filter(pedido -> pedido.getData().equals(data)).forEach(pedido -> jogos.addAll(pedido.getJogos()));
        jogos.stream().forEach(jogo -> {
            impressao.append(jogo.toString() + "\n");
        });
        impressao.append("********************************");
        return impressao.toString();
    }

    /**
     * 
     * @param Pedido pedido, adiciona pedido na lista de pedidos.
     */

     public void adicionarPedido(Pedido p) {
        pedidos.add(p);
     }

    /**
     * @return retorno de impressao com nome e usuario do cliente
     */
    @Override
    public String toString() {
        return ("*****USUARIO*****\nNome: " + this.nome + "\nUsuário: " + this.usuario);
    }

    /**
     * @return retorna nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome modifica o nome com base no parametro passado
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return retorna nome do usuario
    */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario modifica o nome do usuario com base no parametro passado
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return retorna senha do cliente
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha modifica a senha do cliente
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return retorna a categoria do cliente
     */
    public CategoriaCliente getCategoria() {
        return categoria;
    }

    /**
     * @return retorna a lista de pedidos do cliente
     */
    public LinkedList<Pedido> getPedidos() {
        return pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(usuario, cliente.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario);
    }
}