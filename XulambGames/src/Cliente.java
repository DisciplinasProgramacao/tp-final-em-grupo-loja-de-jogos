import java.util.Date;
import java.util.LinkedList;

public class Cliente {
    private String nome;
    private String usuario;
    private String senha;
    private CategoriaCliente categoria;
    private LinkedList<Pedido> pedidos;

    public Cliente(String nome, String usuario, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.categoria = null;
        this.pedidos = new LinkedList<Pedido>();
    }

    public boolean mudarCategoria(CategoriaCliente categoria) {
        this.categoria = categoria;
        return true;
    }

    public String historicoDeCompras(CategoriaCliente categoria) {
        StringBuilder impressao = new StringBuilder();
        impressao.append("******HISTÓRICO DE COMPRAS******\nCATEGORIA: " + categoria + "\n");
        impressao.append("-------------------------------\n");
        impressao.append(this.pedidos.stream()
                                .map()
                                .filter(jogo -> jogo.getCategoria() == categoria)
                                .toString());
        impressao.append("********************************");
        return impressao.toString();
    }

    public String historicoDeCompras(Date data) {
        StringBuilder impressao = new StringBuilder();
        impressao.append("******HISTÓRICO DE COMPRAS******\nDATA: " + data + "\n");
        impressao.append("-------------------------------\n");
        impressao.append(this.pedidos.stream()
                                .filter(pedido -> pedido.getData().equals(data))
                                .getJogos()
                                .stream()
                                .toString());
        impressao.append("********************************");
        return impressao.toString();
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

    public CategoriaCliente getCategoria() {
        return categoria;
    }

    public LinkedList<Pedido> getPedidos() {
        return pedidos;
    }
}