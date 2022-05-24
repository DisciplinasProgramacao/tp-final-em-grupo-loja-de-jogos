public class Empolgado implements ICategoriaCliente{
    private static final int MENSALIDADE = 10;
    private static final double DESCONTO = 0.1;
    private Pedido pedido;

    public Empolgado(){
        this.pedido = null;
    }

    public double desconto(){
        return (this.pedido.valorFinal() * DESCONTO);
    }

    public static int getMensalidade() {
        return MENSALIDADE;
    }

    public static double getDesconto() {
        return DESCONTO;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    
}
