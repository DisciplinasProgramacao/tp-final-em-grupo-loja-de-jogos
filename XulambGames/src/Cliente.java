import java.util.Date;
import java.util.LinkedList;

public class Cliente {
    private String nome;
    private String usuario;
    private String senha;
    private ICategoriaCliente categoria;
    private LinkedList<Pedido> pedidos;

    public Cliente(String nome, String usuario, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.categoria = null;
        this.pedidos = new LinkedList<Pedido>();
    }

    public String historicoDeCompras(ICategoriaJogo categoria) {
        StringBuilder impressao = new StringBuilder();
        return impressao.toString();
    }

    public String historicoDeCompras() {
        StringBuilder impressao = new StringBuilder();
        return impressao.toString();
    }

    public boolean mudarCategoria(ICategoriaCliente categoria) {
        this.categoria = categoria;
        return true;
    }

    @Override
    public String toString() {
        return ("*****USUARIO*****\nNome: " + this.nome + "\nUsuário: " + this.usuario);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ICategoriaCliente getCategoria() {
        return categoria;
    }

    public LinkedList<Pedido> getPedidos() {
        return pedidos;
    }

    public String historicoDeCompras(String categoria) {

        StringBuilder impressao = new StringBuilder();

        impressao.append("******HISTÓRICO DE COMPRAS******\nCATEGORIA: " + categoria + "\n");

        impressao.append("-------------------------------\n");

        for (Pedido pedido : pedidos) {

            for (Jogo jogo : pedido.getJogos()) {

                Class<?> classeJogo = jogo.getCategoria().getClass();

                String categoriaJogo = classeJogo.getName();

                if (categoria.equals(categoriaJogo)) {

                    impressao.append(jogo + "\n");

                }

            }

        }

        impressao.append("********************************");

        return impressao.toString();

    }

    public String historicoDeCompras(Date data) {

        StringBuilder impressao = new StringBuilder();

        impressao.append("******HISTÓRICO DE COMPRAS******\nDATA: " + data + "\n");

        impressao.append("-------------------------------\n");

        for (Pedido pedido : pedidos) {

            if (pedido.getData().equals(data)) {

                for (Jogo jogo : pedido.getJogos()) {

                    impressao.append(jogo + "\n");

                }

            }

        }

        impressao.append("********************************");

        return impressao.toString();

    }
}