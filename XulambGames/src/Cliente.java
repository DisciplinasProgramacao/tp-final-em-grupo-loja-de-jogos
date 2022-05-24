import java.util.LinkedList;

public class Cliente{
    private String nome;
    private String usuario;
    private String senha;
    private ICategoriaCliente categoria;
    private LinkedList<Pedido> pedidos;

    public Cliente(String nome, String usuario, String senha){
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.categoria = null;
        this.pedidos = new LinkedList<Pedido>();
    }

    public String historicoDeCompras(ICategoriaJogo categoria){
        StringBuilder impressao = new StringBuilder();
        return impressao.toString();
    }

    public String historicoDeCompras(){
        StringBuilder impressao = new StringBuilder();
        return impressao.toString();
    }

    public boolean mudarCategoria(ICategoriaCliente categoria) {
        this.categoria = categoria;
        return true;
    }

    @Override
    public String toString(){
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
}